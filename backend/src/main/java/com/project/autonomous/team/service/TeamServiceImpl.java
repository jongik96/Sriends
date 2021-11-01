package com.project.autonomous.team.service;

import com.project.autonomous.common.entity.City;
import com.project.autonomous.jwt.util.SecurityUtil;
import com.project.autonomous.picture.repository.PictureRepository;
import com.project.autonomous.team.dto.request.TeamCreatePostReq;
import com.project.autonomous.team.dto.request.TeamModifyPostReq;
import com.project.autonomous.team.dto.response.TeamInfoRes;
import com.project.autonomous.team.dto.response.TeamListRes;
import com.project.autonomous.team.entity.Team;
import com.project.autonomous.team.repository.SportCategoryRepository;
import com.project.autonomous.team.repository.TeamRepository;
import com.project.autonomous.user.entity.Interest;
import com.project.autonomous.user.entity.User;
import com.project.autonomous.user.entity.UserTeam;
import com.project.autonomous.user.repository.InterestRepository;
import com.project.autonomous.user.repository.UserRepository;
import com.project.autonomous.user.repository.UserTeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TeamServiceImpl implements TeamService{

    @Autowired
    TeamRepository teamRepository;
    @Autowired
    UserTeamRepository userTeamRepository;

    @Autowired
    SportCategoryRepository sportCategoryRepository;

    @Autowired
    InterestRepository interestRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PictureRepository pictureRepository;


    @Override
    public Team create(TeamCreatePostReq teamInfo) {

        long userId = SecurityUtil.getCurrentMemberId();


        Team team = new Team();

        team.setName(teamInfo.getName());
        team.setCreateDate(teamInfo.getCreateDate());//현재시각 받는거면 수정
        team.setLeaderId(userId);//만약에 입력에 userId받으면 그냥 Req에 leaderId주석풀면 됨
        team.setMemberCount(teamInfo.getMemberCount());
        team.setMaxCount(teamInfo.getMaxCount());
        team.setDescription(teamInfo.getDescription());
        team.setSportCategory(sportCategoryRepository.findByName(teamInfo.getSportCategory()).get().getId());
        team.setCity(teamInfo.getCity());
        team.set_active(true);
        team.setRecruitmentState(teamInfo.getRecruitmentState());
        team.setMembershipFee(teamInfo.getMembershipFee());
//        team.setPictureId(teamInfo.getPictureId());

        UserTeam userTeam = new UserTeam();





        teamRepository.save(team);

        return null;
    }


    @Override
    public Team modify(TeamModifyPostReq teamInfo, long teamId) {

        //수정할 항목 보고 결정하기 (지금은 생성이랑 같음)
        Team team = teamRepository.findById(teamId).get();

        team.setName(teamInfo.getName());
        team.setCreateDate(teamInfo.getCreateDate());//현재시각 받는거면 수정
        team.setLeaderId(teamInfo.getLeaderId());//만약에 입력에 userId받으면 그냥 Req에 leaderId주석풀면 됨
        team.setMemberCount(teamInfo.getMemberCount());
        team.setMaxCount(teamInfo.getMaxCount());
        team.setDescription(teamInfo.getDescription());
        team.setSportCategory(sportCategoryRepository.findByName(teamInfo.getSportCategory()).get().getId());
        team.setCity(teamInfo.getCity());
        team.set_active(true);
        team.setRecruitmentState(teamInfo.getRecruitmentState());
        team.setMembershipFee(teamInfo.getMembershipFee());
//        team.setPictureId(teamInfo.getPictureId());


        teamRepository.save(team);
        return null;
    }

    @Override
    public Team delete(long teamId) {

        Team team = teamRepository.findById(teamId).get();

        team.set_active(false);

        //게시물을 다 지우는지??


        return null;
    }

    @Override
    public TeamInfoRes getTeamInfo(long teamId) {

        Team team = teamRepository.findById(teamId).get();
        TeamInfoRes teamInfoRes = new TeamInfoRes();

        teamInfoRes.setCity(team.getCity());
        teamInfoRes.setDescription(team.getDescription());
        teamInfoRes.setCreateDate(team.getCreateDate());
        teamInfoRes.setLeaderId(team.getLeaderId());
        teamInfoRes.setMaxCount(team.getMaxCount());
        teamInfoRes.setMemberCount(team.getMemberCount());
        teamInfoRes.setName(team.getName());
        teamInfoRes.setMembershipFee(team.isMembershipFee());
        teamInfoRes.setPictureId(team.getPictureId());
        teamInfoRes.setRecruitmentState(team.isRecruitmentState());
        teamInfoRes.setSportCategory(sportCategoryRepository.findById(team.getSportCategory()).get().getName());

        return teamInfoRes;
    }

    @Override
    public void apply(long teamId, long userId) {
        UserTeam userTeam = new UserTeam();

        userTeam.setUser(userRepository.findById(userId).get());
        userTeam.setTeam(teamRepository.findById(teamId).get());
        userTeam.set_active(false);//아직 회원 아님
//        userTeam.setAuthority("승인대기"); //권한 종류 정하고 회원 대기 상태 이름도 정해야함
//        userTeam.setRegister_date(); //가입한 날이 승인기준인지 신청인지 모르겠음...
        userTeamRepository.save(userTeam);
    }

    @Override
    public ArrayList<User> applyList(long teamId) {
        ArrayList<User> ret = new ArrayList<>();

        ArrayList<UserTeam> userTeams = userTeamRepository.findAllByTeamId(teamId);

        for(UserTeam userTeam : userTeams){
            if(userTeam.getAuthority().equals("승인대기")){
                ret.add(userTeam.getUser());
            }
        }

        return ret;
    }

    @Override
    public void permit(long teamId, long userId) {
        long leaderId = SecurityUtil.getCurrentMemberId();
        Team team = teamRepository.findById(teamId).get();

        UserTeam userTeam = userTeamRepository.findByUserId(leaderId).get();
        if(userTeam.getAuthority().equals("관리자")){//조회하는 사람이 관리자 이상이면 가능
            UserTeam applyUser = userTeamRepository.findByUserId(userId).get();
            applyUser.set_active(true);
            applyUser.setAuthority("회원");
            applyUser.setRegister_date(LocalDateTime.now());
            userTeamRepository.save(applyUser);
        }

    }

    @Override
    public void quit(long teamId, long userId) {
        UserTeam userTeam = userTeamRepository.findByUserId(userId).get();

        userTeam.set_active(false);
        userTeam.setAuthority("비회원");

        userTeamRepository.save(userTeam);
        return;
    }

    @Override
    public void kickout(long teamId, long userId) {
        long leaderId = SecurityUtil.getCurrentMemberId();
        Team team = teamRepository.findById(teamId).get();

        UserTeam userTeam = userTeamRepository.findByUserId(leaderId).get();
        if(userTeam.getAuthority().equals("관리자")){//조회하는 사람이 관리자 이상이면 가능
            UserTeam kickOutUser = userTeamRepository.findByUserId(userId).get();
            kickOutUser.set_active(false);
            kickOutUser.setAuthority("비회원");
            userTeamRepository.save(kickOutUser);
        }

        return;
    }

    @Override
    public ArrayList<TeamListRes> getList() {
        long userId = SecurityUtil.getCurrentMemberId();

        List<Interest> interests = interestRepository.findAllByUserId(userId).get();
        City city = userRepository.findById(userId).get().getCity();
        List<Team> teams = teamRepository.findAll();

        ArrayList<TeamListRes> teamListRes = new ArrayList<>();

        for(Team team : teams){
            if(team.getCity().equals(city)){//위치 일치
                //종목일치하는거 찾아야함
                for(Interest interest : interests){
                    if(interest.getSportCategoryId() == team.getSportCategory()){
                        TeamListRes teamListRes1 = new TeamListRes();
                        teamListRes1.setId(team.getId());
                        teamListRes1.setDescription(team.getDescription());
                        teamListRes1.setName(team.getName());
                        teamListRes1.setMembershipFee(team.isMembershipFee());
                        teamListRes1.setSportsCategory(city.toString());
                        teamListRes1.setMemberCount(team.getMemberCount());

                        String downloadUri = pictureRepository.findById(team.getPictureId()).get().getImageUrl();
                        teamListRes1.setPictureDownloadUri(downloadUri);

                        teamListRes.add(teamListRes1);

                    }
                }

            }
        }



        return teamListRes;
    }
}
