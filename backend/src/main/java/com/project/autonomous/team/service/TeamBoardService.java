package com.project.autonomous.team.service;

import com.project.autonomous.common.exception.CustomException;
import com.project.autonomous.common.exception.ErrorCode;
import com.project.autonomous.jwt.util.SecurityUtil;
import com.project.autonomous.team.dto.request.CommentModifyPutReq;
import com.project.autonomous.team.dto.request.CommentPostReq;
import com.project.autonomous.team.dto.request.PostingReq;
import com.project.autonomous.team.dto.response.CommentListRes;
import com.project.autonomous.team.dto.response.CommentRes;
import com.project.autonomous.team.dto.response.PostViewListRes;
import com.project.autonomous.team.dto.response.PostViewRes;
import com.project.autonomous.team.entity.TeamBoard;
import com.project.autonomous.team.entity.TeamBoardComment;
import com.project.autonomous.team.repository.TeamBoardCommentRepository;
import com.project.autonomous.team.repository.TeamBoardRepository;
import com.project.autonomous.user.repository.UserRepository;
import com.project.autonomous.user.repository.UserTeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TeamBoardService {

    @Autowired
    TeamBoardRepository teamBoardRepository;

    @Autowired
    UserTeamRepository userTeamRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TeamBoardCommentRepository teamBoardCommentRepository;

    public boolean posting(PostingReq postingReq, long teamId) {
        long userId = SecurityUtil.getCurrentMemberId();
        if(!userTeamRepository.findByUserIdAndTeamId(userId, teamId).isPresent()){//공지사항은 회원만 가능
            return false;
        }

        TeamBoard teamBoard = new TeamBoard();
        teamBoard.setWriterId(userId);
        teamBoard.setTitle(postingReq.getTitle());
        teamBoard.setContent(postingReq.getContent());
        teamBoard.setCreateDate(LocalDateTime.now());
        teamBoard.setTeamId(teamId);

        teamBoardRepository.save(teamBoard);

        return true;
    }

    public boolean postingModify(PostingReq postingReq, long teamId, long boardId) {
        long userId = SecurityUtil.getCurrentMemberId();

        TeamBoard teamBoard = teamBoardRepository.findById(boardId).get();
        if(teamBoard.getWriterId() == userId){//공지사항은 회원만 가능
            return false;
        }

        teamBoard.setTitle(postingReq.getTitle());
        teamBoard.setContent(postingReq.getContent());
        teamBoardRepository.save(teamBoard);

        return true;
    }

    public boolean postingDelete(long teamId, long boardId) {
        long userId = SecurityUtil.getCurrentMemberId();

        TeamBoard teamBoard = teamBoardRepository.findById(boardId).get();
        if(teamBoard.getWriterId() == userId){//공지사항은 회원만 가능
            return false;
        }

        teamBoardRepository.delete(teamBoard);
        return true;
    }

    public PostViewRes postingView(long teamId, long boardId) {
        long userId = SecurityUtil.getCurrentMemberId();

        TeamBoard teamBoard = teamBoardRepository.findById(boardId).get();

        PostViewRes postViewRes = new PostViewRes();
        postViewRes.setName(userRepository.findById(userId).get().getName());
        postViewRes.setContent(teamBoard.getContent());
        postViewRes.setTitle(teamBoard.getTitle());
        postViewRes.setCreateDate(teamBoard.getCreateDate());

        return postViewRes;
    }

    public ArrayList<PostViewListRes> postingViewList(long teamId) {
        long userId = SecurityUtil.getCurrentMemberId();
        ArrayList<PostViewListRes> ret = new ArrayList<>();

        for(TeamBoard teamBoard : teamBoardRepository.findAllByTeamId(teamId)){
            PostViewListRes postViewListRes = new PostViewListRes();
            postViewListRes.setId(teamBoard.getId());
            postViewListRes.setName(userRepository.findById(userId).get().getName());
            postViewListRes.setContent(teamBoard.getContent());
            postViewListRes.setTitle(teamBoard.getTitle());
            postViewListRes.setCreateDate(teamBoard.getCreateDate());

            ret.add(postViewListRes);
        }
        return ret;
    }

    public TeamBoardComment comment(CommentPostReq commentPostReq, long boardId) {
        long userId = SecurityUtil.getCurrentMemberId();

        TeamBoardComment teamBoardComment = new TeamBoardComment();

        if(commentPostReq.getParentId() == 0){
            teamBoardComment.setTeamBoardId(boardId);
            teamBoardComment.setWriterId(userId);
            teamBoardComment.setParentId(0);
            teamBoardComment.setDepth(1);
            teamBoardComment.setCreateDate(LocalDateTime.now());
            teamBoardComment.setModified(false);
            teamBoardComment.setModifiyDate(LocalDateTime.now());//modified가 false면 이거 무시
            teamBoardComment.setContent(commentPostReq.getContent());
            teamBoardComment.setReplycount(0);

            return teamBoardCommentRepository.save(teamBoardComment);

        }else{
            TeamBoardComment parentComment = teamBoardCommentRepository.findById(commentPostReq.getParentId()).get();
            parentComment.setReplycount(parentComment.getReplycount()+1);
            teamBoardCommentRepository.save(parentComment);

            teamBoardComment.setTeamBoardId(boardId);
            teamBoardComment.setWriterId(userId);
            teamBoardComment.setParentId(parentComment.getId());
            teamBoardComment.setDepth(2);
            teamBoardComment.setCreateDate(LocalDateTime.now());
            teamBoardComment.setModified(false);
            teamBoardComment.setModifiyDate(LocalDateTime.now());
            teamBoardComment.setContent(commentPostReq.getContent());
            teamBoardComment.setReplycount(0);

            return teamBoardCommentRepository.save(teamBoardComment);
        }

    }

    public TeamBoardComment modifyComment(CommentModifyPutReq commentModifyPutReq, long commentId) {
        if(!teamBoardCommentRepository.findById(commentId).isPresent()){
            throw new CustomException(ErrorCode.COMMENT_NOT_FOUND);
        }
        long userId = SecurityUtil.getCurrentMemberId();
        TeamBoardComment teamBoardComment = teamBoardCommentRepository.findById(commentId).get();

        teamBoardComment.setModified(true);
        teamBoardComment.setModifiyDate(LocalDateTime.now());
        teamBoardComment.setContent(commentModifyPutReq.getContent());

        return teamBoardCommentRepository.save(teamBoardComment);
    }

    public boolean deleteComment(long commentId) {
        if(!teamBoardCommentRepository.findById(commentId).isPresent()){
            throw new CustomException(ErrorCode.COMMENT_NOT_FOUND);
        }

        teamBoardCommentRepository.deleteById(commentId);
        return true;
    }

    public CommentRes getComment(long commentId) {
        if(!teamBoardCommentRepository.findById(commentId).isPresent()){
            throw new CustomException(ErrorCode.COMMENT_NOT_FOUND);
        }

        TeamBoardComment teamBoardComment = teamBoardCommentRepository.findById(commentId).get();
        CommentRes commentRes = new CommentRes();

        commentRes.setId(teamBoardComment.getId());
        commentRes.setWriterId(teamBoardComment.getWriterId());
        commentRes.setName(userRepository.findById(teamBoardComment.getWriterId()).get().getName());
        commentRes.setContent(teamBoardComment.getContent());
        commentRes.setCreateDate(teamBoardComment.getCreateDate());
        commentRes.setModifyDate(teamBoardComment.getModifiyDate());
        commentRes.setModified(teamBoardComment.isModified());
        commentRes.setParentId(teamBoardComment.getParentId());
        commentRes.setDepth(teamBoardComment.getDepth());
        commentRes.setReplyCount(teamBoardComment.getReplycount());

        return commentRes;
    }

    public CommentListRes getCommentList(long boardId, long parentId) {
        if(!teamBoardRepository.findById(boardId).isPresent()){
            throw new CustomException(ErrorCode.BOARD_NOT_FOUND);
        }
        if(teamBoardCommentRepository.findAllByParentIdAAndTeamBoardId(parentId, boardId).isEmpty()){
            throw new CustomException(ErrorCode.LIST_NOT_FOUND);
        }
        CommentListRes commentListRes = new CommentListRes();

        List<TeamBoardComment> teamBoardCommentList = teamBoardCommentRepository.findAllByParentIdAAndTeamBoardId(parentId,boardId);
        commentListRes.setReplyCount(teamBoardCommentList.size());

        ArrayList<CommentRes> commentResArrayList = new ArrayList<>();
        for (TeamBoardComment teamBoardComment : teamBoardCommentList){
            CommentRes commentRes = new CommentRes();
            commentRes.setId(teamBoardComment.getId());
            commentRes.setWriterId(teamBoardComment.getWriterId());
            commentRes.setName(userRepository.findById(teamBoardComment.getWriterId()).get().getName());
            commentRes.setContent(teamBoardComment.getContent());
            commentRes.setCreateDate(teamBoardComment.getCreateDate());
            commentRes.setModifyDate(teamBoardComment.getModifiyDate());
            commentRes.setModified(teamBoardComment.isModified());
            commentRes.setParentId(teamBoardComment.getParentId());
            commentRes.setDepth(teamBoardComment.getDepth());
            commentRes.setReplyCount(teamBoardComment.getReplycount());

            commentResArrayList.add(commentRes);
        }
        commentListRes.setCommentsList(commentResArrayList);

        return commentListRes;
    }
}
