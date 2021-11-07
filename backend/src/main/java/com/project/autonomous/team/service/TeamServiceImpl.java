package com.project.autonomous.team.service;

import com.project.autonomous.common.exception.CustomException;
import com.project.autonomous.common.exception.ErrorCode;
import com.project.autonomous.jwt.util.SecurityUtil;
import com.project.autonomous.picture.repository.PictureRepository;
import com.project.autonomous.team.dto.request.ApplyPostReq;
import com.project.autonomous.team.dto.request.TeamCreatePostReq;
import com.project.autonomous.team.dto.request.TeamModifyPostReq;
import com.project.autonomous.team.dto.response.*;
import com.project.autonomous.team.entity.RequestJoin;
import com.project.autonomous.team.entity.Team;
import com.project.autonomous.team.repository.RequestJoinRepository;
import com.project.autonomous.team.repository.SportCategoryRepository;
import com.project.autonomous.team.repository.TeamRepository;
import com.project.autonomous.user.entity.UserInterest;
import com.project.autonomous.user.entity.UserTeam;
import com.project.autonomous.user.repository.UserInterestRepository;
import com.project.autonomous.user.repository.UserRepository;
import com.project.autonomous.user.repository.UserTeamRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamServiceImpl implements TeamService{

    @Autowired
    TeamRepository teamRepository;
    @Autowired
    UserTeamRepository userTeamRepository;

    @Autowired
    SportCategoryRepository sportCategoryRepository;

    @Autowired
    UserInterestRepository userInterestRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PictureRepository pictureRepository;

    @Autowired
    RequestJoinRepository requestJoinRepository;


    @Override
    public Team create(TeamCreatePostReq teamInfo) {

        long userId = SecurityUtil.getCurrentMemberId();


        Team team = new Team();

        team.setName(teamInfo.getName());
        team.setCreateDate(LocalDateTime.now());//현재시각 받는거면 수정
        team.setLeaderId(userId);//만약에 입력에 userId받으면 그냥 Req에 leaderId주석풀면 됨
        team.setMemberCount(1);
        team.setMaxCount(teamInfo.getMaxCount());
        team.setDescription(teamInfo.getDescription());
        team.setSportCategoryId(sportCategoryRepository.findByName(teamInfo.getSportCategory()).get().getId());
        team.setCity(teamInfo.getCity());
        team.setRecruitmentState(teamInfo.getRecruitmentState());
        team.setMembershipFee(teamInfo.getMembershipFee());
//        team.setPictureId(teamInfo.getPictureId());

        team = teamRepository.save(team);

        UserTeam userTeam = new UserTeam();
        userTeam.setUser(userRepository.getById(userId));
        userTeam.setTeam(team);
        userTeam.setRegisterDate(LocalDateTime.now());
        userTeam.setAuthority("대표");
        userTeamRepository.save(userTeam);

        return null;
    }


    @Override
    public ArrayList<TeamListRes> getList() {
        long userId = SecurityUtil.getCurrentMemberId();

        ArrayList<UserInterest> interests = userInterestRepository.findAllByUserInterestIdUserId(userId).get();
        if(interests.isEmpty()){
            throw new CustomException(ErrorCode.NO_INTERESTING_ITEMS);
        }
        String city = userRepository.findById(userId).get().getCity().value();
        List<Team> teams = teamRepository.findAll();

        ArrayList<TeamListRes> teamListRes = new ArrayList<>();

        for(Team team : teams){
            if(team.getCity().equals(city)){//위치 일치
                //종목일치하는거 찾아야함
                for(UserInterest interest : interests){
                    if(interest.getUserInterestId().getSportCategory().getId() == team.getSportCategoryId()){
                        TeamListRes teamListRes1 = new TeamListRes();
                        teamListRes1.setId(team.getId());
                        teamListRes1.setLeaderId(team.getLeaderId());
                        teamListRes1.setLeaderName(userRepository.findById(team.getLeaderId()).get().getName());
                        teamListRes1.setDescription(team.getDescription());
                        teamListRes1.setName(team.getName());
                        teamListRes1.setMembershipFee(team.isMembershipFee());
                        teamListRes1.setSportsCategory(sportCategoryRepository.findById(interest.getUserInterestId().getSportCategory().getId()).get().getName());
                        teamListRes1.setCity(city.toString());

                        teamListRes1.setMemberCount(team.getMemberCount());

//                        String downloadUri = pictureRepository.findById(team.getPictureId()).get().getImageUrl();
//                        teamListRes1.setPictureDownloadUri(downloadUri);

                        teamListRes.add(teamListRes1);

                    }
                }

            }

        }
        if(teamListRes.isEmpty()){
            throw new CustomException(ErrorCode.LIST_NOT_FOUND);
        }


        return teamListRes;
    }

    @Override
    public ArrayList<TeamListRes> getChooseList(String cityName, String sportCategoryName) {

        String city = cityName;
        List<Team> teams = teamRepository.findAll();
//        System.out.println(teams.size());

        ArrayList<TeamListRes> teamListRes = new ArrayList<>();

        for(Team team : teams) {
//            System.out.println(team.getCity());
            if (team.getCity().equals(city)) {//위치 일치
                if (sportCategoryRepository.findByName(sportCategoryName).get().getId() == team.getSportCategoryId()) {
                    TeamListRes teamListRes1 = new TeamListRes();
                    teamListRes1.setId(team.getId());
                    teamListRes1.setLeaderId(team.getLeaderId());
                    teamListRes1.setLeaderName(userRepository.findById(team.getLeaderId()).get().getName());
                    teamListRes1.setDescription(team.getDescription());
                    teamListRes1.setName(team.getName());
                    teamListRes1.setMembershipFee(team.isMembershipFee());
                    teamListRes1.setSportsCategory(sportCategoryName);
                    teamListRes1.setMemberCount(team.getMemberCount());
                    teamListRes1.setCity(cityName);

//                    String downloadUri = pictureRepository.findById(team.getPictureId()).get().getImageUrl();
//                    teamListRes1.setPictureDownloadUri(downloadUri);

                    teamListRes.add(teamListRes1);
                }

            }
        }
        if(teamListRes.isEmpty()){
            throw new CustomException(ErrorCode.LIST_NOT_FOUND);
        }

        return teamListRes;
    }

    @Override
    public boolean modify(TeamModifyPostReq teamInfo, long teamId) {

        long userId = SecurityUtil.getCurrentMemberId();

        if(userTeamRepository.findByUserIdAndTeamId(userId, teamId).get().getAuthority().equals("매니저")||userTeamRepository.findByUserIdAndTeamId(userId, teamId).get().getAuthority().equals("대표")){
            //수정할 항목 보고 결정하기 (지금은 생성이랑 같음)
            Team team = teamRepository.findById(teamId).get();

            team.setName(teamInfo.getName());
            team.setMaxCount(teamInfo.getMaxCount());
            team.setDescription(teamInfo.getDescription());
            team.setSportCategoryId(sportCategoryRepository.findByName(teamInfo.getSportCategory()).get().getId());
            team.setCity(teamInfo.getCity());
            team.setRecruitmentState(teamInfo.getRecruitmentState());
            team.setMembershipFee(teamInfo.getMembershipFee());
    //        team.setPictureId(teamInfo.getPictureId());


            teamRepository.save(team);

            return true;
        }

        return false;
    }

    @Override
    public boolean delete(long teamId) {

        long userId = SecurityUtil.getCurrentMemberId();
        Team team = teamRepository.findById(teamId).get();

        if(team.getLeaderId().equals(userId)){

            ArrayList<UserTeam> list = userTeamRepository.findAllByTeamId(teamId);

            for(UserTeam userTeam : list){
                userTeamRepository.delete(userTeam);
            }

            teamRepository.delete(team);

            //게시물을 다 지우는지??
            return true;
        }

        throw new CustomException(ErrorCode.COMMON_MEMBER_CANNOT_REMOVE);

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
        teamInfoRes.setLeaderId(team.getLeaderId());
        teamInfoRes.setLeaderName(userRepository.findById(team.getLeaderId()).get().getName());
//        teamInfoRes.setPictureId(team.getPicture().getId());
        teamInfoRes.setRecruitmentState(team.isRecruitmentState());
        teamInfoRes.setSportCategory(sportCategoryRepository.findById(team.getSportCategoryId()).get().getName());

        return teamInfoRes;
    }

    @Override
    public void apply(long teamId, long userId, ApplyPostReq applyPostReq) {

        if(userTeamRepository.findByUserIdAndTeamId(userId,teamId).isPresent()){
            throw new CustomException(ErrorCode.ALREADY_APPLY);
        }
        RequestJoin requestJoin = new RequestJoin();
        requestJoin.setUserId(userId);
        requestJoin.setTeamId(teamId);
        requestJoin.setDescription(applyPostReq.getDescription());
        requestJoin.setCreate_date(LocalDateTime.now());

        requestJoinRepository.save(requestJoin);
    }

    @Override
    public ArrayList<ApplyListRes> applyList(long teamId) {
        long userId = SecurityUtil.getCurrentMemberId();

        ArrayList<ApplyListRes> ret = new ArrayList<>();

        ArrayList<RequestJoin> list = requestJoinRepository.findAllByTeamId(teamId);

        for(RequestJoin rj : list){
            ApplyListRes applyListRes = new ApplyListRes();
            applyListRes.setUserId(rj.getUserId());
            applyListRes.setName(userRepository.findById(rj.getUserId()).get().getName());
            applyListRes.setEmail(userRepository.findById(rj.getUserId()).get().getEmail());
            applyListRes.setDescription(rj.getDescription());
            applyListRes.setCreateDate(rj.getCreate_date());

            ret.add(applyListRes);
        }

        return ret;
    }

    @Override
    public ArrayList<MemberListRes> memberList(long teamId) {
        ArrayList<MemberListRes> ret = new ArrayList<>();

        ArrayList<UserTeam> list = userTeamRepository.findAllByTeamId(teamId);

        for(UserTeam userTeam : list){
            MemberListRes memberListRes = new MemberListRes();
            memberListRes.setUserId(userTeam.getUser().getId());
            memberListRes.setName(userTeam.getUser().getName());
            memberListRes.setEmail(userTeam.getUser().getEmail());
            memberListRes.setRegisterDate(userTeam.getRegisterDate());

            ret.add(memberListRes);
        }

        return ret;
    }

    @Override
    public boolean permit(long teamId, long userId) {
        long managerId = SecurityUtil.getCurrentMemberId();
        Team team = teamRepository.findById(teamId).get();

        UserTeam userTeam = userTeamRepository.findByUserIdAndTeamId(managerId, teamId).get();
        if(userTeam.getAuthority().equals("매니저")||userTeam.getAuthority().equals("대표")){//조회하는 사람이 관리자 이상이면 가능

            if(!requestJoinRepository.findByUserIdAndTeamId(userId,teamId).isPresent()){
                throw new CustomException(ErrorCode.APPLIY_FORM_NOT_FOUND);
            }

            if(userTeamRepository.findByUserIdAndTeamId(userId,teamId).isPresent()){//예전에 탈퇴했던 사람이 다시 신청할 경우
                RequestJoin requestJoin = requestJoinRepository.findByUserIdAndTeamId(userId,teamId).get();
                System.out.println("회원 있는데");
//                UserTeam applyUser = userTeamRepository.findByUserId(userId).get();
//                applyUser.setAuthority("회원");
//                applyUser.setRegisterDate(LocalDateTime.now());
//                userTeamRepository.save(applyUser);
                requestJoinRepository.delete(requestJoin);
                throw new CustomException(ErrorCode.ALREADY_APPLY);

            }else{
                RequestJoin requestJoin = requestJoinRepository.findByUserIdAndTeamId(userId,teamId).get();
                UserTeam applyUser = new UserTeam();
                applyUser.setUser(userRepository.findById(userId).get());
                applyUser.setTeam(teamRepository.findById(teamId).get());
                applyUser.setAuthority("회원");
                applyUser.setRegisterDate(LocalDateTime.now());
                userTeamRepository.save(applyUser);

                requestJoinRepository.delete(requestJoin);
            }
            return true;
        }else {
            throw new CustomException(ErrorCode.AUTHORITY_NOT_FOUND);
        }

    }

    @Override
    public AuthorityRes checkAuthority(long teamId) {
        long userId = SecurityUtil.getCurrentMemberId();
        if(!userTeamRepository.findByUserIdAndTeamId(userId,teamId).isPresent())
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
        UserTeam userTeam = userTeamRepository.findByUserIdAndTeamId(userId,teamId).get();

        AuthorityRes authorityRes = new AuthorityRes();
        authorityRes.setAuthority(userTeam.getAuthority());

        return authorityRes;
    }

    @Override
    public boolean giveAuthority(long teamId, long userId) {
        long managerId = SecurityUtil.getCurrentMemberId();
        if(userTeamRepository.findByUserIdAndTeamId(managerId,teamId).get().getAuthority().equals("대표")){
            UserTeam userTeam = userTeamRepository.findByUserIdAndTeamId(userId,teamId).get();

            userTeam.setAuthority("매니저");
            userTeamRepository.save(userTeam);

            return true;
        }
        throw new CustomException(ErrorCode.AUTHORITY_NOT_FOUND);
    }


    @Override
    public void quit(long teamId) {
        long userId = SecurityUtil.getCurrentMemberId();
        if(!userTeamRepository.findByUserIdAndTeamId(userId,teamId).isPresent())
            throw new CustomException(ErrorCode.USER_NOT_FOUND);

        UserTeam userTeam = userTeamRepository.findByUserId(userId).get();

        userTeamRepository.delete(userTeam);
        return;
    }

    @Override
    public void kickout(long teamId, long userId) {
        long leaderId = SecurityUtil.getCurrentMemberId();
        if(!userTeamRepository.findByUserIdAndTeamId(userId,teamId).isPresent())
            throw new CustomException(ErrorCode.USER_NOT_FOUND);

        Team team = teamRepository.findById(teamId).get();

        UserTeam userTeam = userTeamRepository.findByUserIdAndTeamId(leaderId,teamId).get();
        if(userTeam.getAuthority().equals("매니저")||userTeam.getAuthority().equals("대표")){//조회하는 사람이 관리자 이상이면 가능
            UserTeam kickOutUser = userTeamRepository.findByUserId(userId).get();
            userTeamRepository.delete(kickOutUser);
            return;
        }
        throw new CustomException(ErrorCode.AUTHORITY_NOT_FOUND);

    }


}
