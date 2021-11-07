package com.project.autonomous.matchboard.posts.service;

import com.fasterxml.jackson.databind.deser.DataFormatReaders.Match;
import com.project.autonomous.common.exception.CustomException;
import com.project.autonomous.common.exception.ErrorCode;
import com.project.autonomous.jwt.util.SecurityUtil;
import com.project.autonomous.matchboard.posts.dto.request.MatchBoardCreateReq;
import com.project.autonomous.matchboard.posts.dto.response.MatchBoardPostInfo;
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

    @Transactional
    public MatchBoardPostInfo createPost(MatchBoardCreateReq matchBoardCreateReq) {
        User user = findMember(SecurityUtil.getCurrentMemberId());
        SportCategory sportCategory = findSportCategory(matchBoardCreateReq.getSportCategory());
        Team team = findTeam(matchBoardCreateReq.getTeamId());

        MatchBoardPost matchBoardPost = matchBoardCreateReq.toMatchBoardPost(user, sportCategory, team);
        user.getMatchBoardPosts().add(matchBoardPost);

        return MatchBoardPostInfo.from(matchBoardPostRepository.save(matchBoardPost));
    }

    // 매치 게시글 상세 조회
    public MatchBoardPostInfo getPostInfo(Long postId) {
        return MatchBoardPostInfo.from(findMatchBoardPost(postId));
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
}
