package com.project.autonomous.matchboard.comments.service;

import com.project.autonomous.common.exception.CustomException;
import com.project.autonomous.common.exception.ErrorCode;
import com.project.autonomous.jwt.util.SecurityUtil;
import com.project.autonomous.matchboard.comments.dto.request.MatchBoardCreateCommentReq;
import com.project.autonomous.matchboard.comments.dto.request.MatchBoardUpdateCommentReq;
import com.project.autonomous.matchboard.comments.dto.response.MatchBoardCommentRes;
import com.project.autonomous.matchboard.comments.entity.MatchBoardComment;
import com.project.autonomous.matchboard.comments.repository.MatchBoardCommentRepository;
import com.project.autonomous.matchboard.posts.entity.MatchBoardPost;
import com.project.autonomous.matchboard.posts.repository.MatchBoardPostRepository;
import com.project.autonomous.team.entity.SportCategory;
import com.project.autonomous.team.entity.Team;
import com.project.autonomous.team.repository.SportCategoryRepository;
import com.project.autonomous.team.repository.TeamRepository;
import com.project.autonomous.user.entity.User;
import com.project.autonomous.user.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MatchBoardCommentService {

    private final UserRepository userRepository;
    private final MatchBoardPostRepository matchBoardPostRepository;
    private final MatchBoardCommentRepository matchBoardCommentRepository;

    // 댓글 생성
    @Transactional
    public MatchBoardCommentRes createComment(Long postId, MatchBoardCreateCommentReq matchBoardCreateCommentReq) {
        if(matchBoardCreateCommentReq.getParnetId() > 0) { // 부모 댓글이 존재한다면
            MatchBoardComment parentComment = findMatchBoardComment(matchBoardCreateCommentReq.getParnetId());
            parentComment.addReplyCount();
        }
        MatchBoardPost matchBoardPost = findMatchBoardPost(postId);
        User user = findMember(SecurityUtil.getCurrentMemberId());

        MatchBoardComment matchBoardComment = matchBoardCreateCommentReq.toMatchBoardComment(matchBoardPost, user);
        matchBoardComment.setDepth(matchBoardCreateCommentReq.getParnetId());
        matchBoardPost.getComments().add(matchBoardComment);
        user.getComments().add(matchBoardComment);
        matchBoardCommentRepository.save(matchBoardComment);

        if(matchBoardCreateCommentReq.getParnetId() == 0) {
            matchBoardComment.setParentId(matchBoardComment.getId());
        }
        return MatchBoardCommentRes.from(matchBoardComment);
    }

    // 댓글 조회
    public List<MatchBoardCommentRes> getAllComment(Long postId) {
        return matchBoardCommentRepository.findAllComment(findMatchBoardPost(postId))
            .stream().map(MatchBoardCommentRes::from).collect(Collectors.toList());
    }

    // 댓글 수정
    @Transactional
    public MatchBoardCommentRes updateComment(Long commentId, MatchBoardUpdateCommentReq matchBoardUpdateCommentReq) {
        MatchBoardComment matchBoardComment = findMatchBoardComment(commentId);
        checkAuthority(SecurityUtil.getCurrentMemberId(), matchBoardComment.getUser().getId());

        matchBoardComment.changeContent(matchBoardUpdateCommentReq.getContent());
        return MatchBoardCommentRes.from(matchBoardComment);
    }

    // 댓글 삭제
    @Transactional
    public void deleteComment(Long commentId) {
        MatchBoardComment matchBoardComment = findMatchBoardComment(commentId);
        checkAuthority(SecurityUtil.getCurrentMemberId(), matchBoardComment.getUser().getId());

        // 대댓글을 제거할 시점에는 부모 댓글이 이미 삭제되어있을 수 있음
        if(matchBoardComment.getParentId() != commentId) {
            Optional<MatchBoardComment> parentComment = matchBoardCommentRepository.findById(
                matchBoardComment.getParentId());

            if(parentComment.isPresent()) {
                parentComment.get().removeReplyCount();
            }
        }
        // 자식 댓글을 가지고 있는 부모 댓글의 삭제는?
        // 유저의 탈퇴로 인한 댓글의 삭제시 부모 댓글의 replyCount는 어떻게 변경할 수 있을까?

        matchBoardCommentRepository.delete(matchBoardComment);
    }

    public User findMember(long userId) {
        return userRepository.findById(userId)
            .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
    }

    public MatchBoardPost findMatchBoardPost(Long postId) {
        return matchBoardPostRepository.findById(postId)
            .orElseThrow(() -> new CustomException(ErrorCode.BOARD_NOT_FOUND));
    }

    public MatchBoardComment findMatchBoardComment(Long commentId) {
        return matchBoardCommentRepository.findById(commentId)
            .orElseThrow(() -> new CustomException(ErrorCode.COMMENT_NOT_FOUND));
    }

    public void checkAuthority(long userId, long writerId) {
        if(userId != writerId) {
            throw new CustomException(ErrorCode.UNAUTHORIZED_CHANGE);
        }
    }

}
