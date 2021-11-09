package com.project.autonomous.matchboard.posts.service;

import com.project.autonomous.common.exception.CustomException;
import com.project.autonomous.common.exception.ErrorCode;
import com.project.autonomous.jwt.util.SecurityUtil;
import com.project.autonomous.matchboard.posts.dto.request.MatchBoardCreateReq;
import com.project.autonomous.matchboard.posts.dto.request.MatchBoardUpdateReq;
import com.project.autonomous.matchboard.posts.dto.response.MatchBoardPostInfoRes;
import com.project.autonomous.matchboard.posts.entity.MatchBoardPost;
import com.project.autonomous.matchboard.posts.repository.MatchBoardPostRepository;
import com.project.autonomous.team.entity.SportCategory;
import com.project.autonomous.team.entity.Team;
import com.project.autonomous.team.repository.SportCategoryRepository;
import com.project.autonomous.team.repository.TeamRepository;
import com.project.autonomous.user.dto.response.UserTeamListRes;
import com.project.autonomous.user.entity.User;
import com.project.autonomous.user.repository.UserRepository;
import com.project.autonomous.user.repository.UserTeamRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MatchBoardPostServiceImpl {

    private final UserRepository userRepository;
    private final SportCategoryRepository sportCategoryRepository;
    private final TeamRepository teamRepository;
    private final MatchBoardPostRepository matchBoardPostRepository;
    private final UserTeamRepository userTeamRepository;

    // 게시글 생성을 위한 팀 리스트 조회
    public List<UserTeamListRes> getMyTeams() {
        return userTeamRepository.findTeamByUserList(findMember(SecurityUtil.getCurrentMemberId()))
            .stream().map(UserTeamListRes::from).collect(Collectors.toList());
    }

    // 게시글 생성
    @Transactional
    public MatchBoardPostInfoRes createPost(MatchBoardCreateReq matchBoardCreateReq) {
        User user = findMember(SecurityUtil.getCurrentMemberId());
        SportCategory sportCategory = findSportCategory(matchBoardCreateReq.getSportCategory());
        Team team = findTeam(matchBoardCreateReq.getTeamId());

        MatchBoardPost matchBoardPost = matchBoardCreateReq.toMatchBoardPost(user, sportCategory, team);
        user.getMatchBoardPosts().add(matchBoardPost);

        return MatchBoardPostInfoRes.from(matchBoardPostRepository.save(matchBoardPost));
    }

    // 매치 게시글 상세 조회
    public MatchBoardPostInfoRes getPostInfo(Long postId) {
        return MatchBoardPostInfoRes.from(findMatchBoardPost(postId));
    }

    // 게시글 수정
    @Transactional
    public MatchBoardPostInfoRes updatePost(Long postId, MatchBoardUpdateReq matchBoardUpdateReq) {
        SportCategory sportCategory = findSportCategory(matchBoardUpdateReq.getSportCategory());
        Team team = findTeam(matchBoardUpdateReq.getTeamId());

        MatchBoardPost matchBoardPost = findMatchBoardPost(postId);
        checkAuthority(SecurityUtil.getCurrentMemberId(), matchBoardPost.getUser().getId());
        matchBoardPost.update(matchBoardUpdateReq, sportCategory, team);
        return MatchBoardPostInfoRes.from(matchBoardPost);
    }

    // 게시글 삭제
    @Transactional
    public void deletePost(Long postId) {
        MatchBoardPost matchBoardPost = findMatchBoardPost(postId);
        checkAuthority(SecurityUtil.getCurrentMemberId(), matchBoardPost.getUser().getId());
        matchBoardPostRepository.delete(matchBoardPost);
    }

    public User findMember(long userId) {
        return userRepository.findById(userId)
            .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
    }

    public Team findTeam(Long teamId) {
        if (teamId == null) {
            return null;
        }
        return teamRepository.findById(teamId)
            .orElseThrow(() -> new CustomException(ErrorCode.TEAM_NOT_FOUND));
    }

    public SportCategory findSportCategory(String name) {
        return sportCategoryRepository.findByName(name)
            .orElseThrow(() -> new CustomException(ErrorCode.SPORT_CATEGORY_NOT_FOUND));
    }

    public MatchBoardPost findMatchBoardPost(Long postId) {
        return matchBoardPostRepository.findById(postId)
            .orElseThrow(() -> new CustomException(ErrorCode.BOARD_NOT_FOUND));
    }

    public void checkAuthority(long userId, long writerId) {
        if(userId != writerId) {
            throw new CustomException(ErrorCode.UNAUTHORIZED_CHANGE);
        }
    }

}
