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
        if(teamBoard.getWriterId() != userId){//공지사항은 회원만 가능
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
        if(teamBoard.getWriterId() != userId){//공지사항은 회원만 가능
            return false;
        }

        teamBoardRepository.delete(teamBoard);
        return true;
    }

    public PostViewRes postingView(long teamId, long boardId) {
        long userId = SecurityUtil.getCurrentMemberId();

        TeamBoard teamBoard = teamBoardRepository.findById(boardId).get();

        PostViewRes postViewRes = new PostViewRes();
        postViewRes.setWriterId(teamBoard.getWriterId());
        postViewRes.setName(userRepository.findById(teamBoard.getWriterId()).get().getName());
        postViewRes.setContent(teamBoard.getContent());
        postViewRes.setTitle(teamBoard.getTitle());
        postViewRes.setCreateDate(teamBoard.getCreateDate());

        return postViewRes;
    }

    public ArrayList<PostViewListRes> postingViewList(long teamId) {
        long userId = SecurityUtil.getCurrentMemberId();
        ArrayList<PostViewListRes> ret = new ArrayList<>();
        System.out.println("서비스");


        for(TeamBoard teamBoard : teamBoardRepository.findAllByTeamId(teamId)){
            System.out.println("조회중");
            PostViewListRes postViewListRes = new PostViewListRes();
            postViewListRes.setId(teamBoard.getId());
            postViewListRes.setWriterId(teamBoard.getWriterId());
            postViewListRes.setName(userRepository.findById(teamBoard.getWriterId()).get().getName());
            postViewListRes.setContent(teamBoard.getContent());
            postViewListRes.setTitle(teamBoard.getTitle());
            postViewListRes.setCreateDate(teamBoard.getCreateDate());

            ret.add(postViewListRes);
        }
        System.out.println(ret.size());
        return ret;
    }

    public TeamBoardComment comment(CommentPostReq commentPostReq, long boardId) {
        long userId = SecurityUtil.getCurrentMemberId();


        if (commentPostReq.getParentId() != 0) {
            TeamBoardComment teamBoardComment = new TeamBoardComment();
            TeamBoardComment parentComment = teamBoardCommentRepository.findById(commentPostReq.getParentId()).get();
            parentComment.setReplyCount(parentComment.getReplyCount()+1);
            teamBoardCommentRepository.save(parentComment);

            teamBoardComment.setTeamBoardId(boardId);
            teamBoardComment.setWriterId(userId);
            teamBoardComment.setParentId(parentComment.getId());
            teamBoardComment.setDepth(2);
            teamBoardComment.setCreateDate(LocalDateTime.now());
            teamBoardComment.setModified(false);
            teamBoardComment.setModifyDate(LocalDateTime.now());
            teamBoardComment.setContent(commentPostReq.getContent());
            teamBoardComment.setReplyCount(0);

            return teamBoardCommentRepository.save(teamBoardComment);

        } else {
            TeamBoardComment teamBoardComment = new TeamBoardComment();
            teamBoardComment.setTeamBoardId(boardId);
            teamBoardComment.setWriterId(userId);
            teamBoardComment.setParentId(0);
            teamBoardComment.setDepth(1);
            teamBoardComment.setCreateDate(LocalDateTime.now());
            teamBoardComment.setModified(false);
            teamBoardComment.setModifyDate(LocalDateTime.now());//modified가 false면 이거 무시
            teamBoardComment.setContent(commentPostReq.getContent());
            teamBoardComment.setReplyCount(0);

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
        teamBoardComment.setModifyDate(LocalDateTime.now());
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
        commentRes.setModifyDate(teamBoardComment.getModifyDate());
        commentRes.setModified(teamBoardComment.isModified());
        commentRes.setParentId(teamBoardComment.getParentId());
        commentRes.setDepth(teamBoardComment.getDepth());
        commentRes.setReplyCount(teamBoardComment.getReplyCount());

        return commentRes;
    }

    public CommentListRes getCommentList(long boardId, long parentId) {
        System.out.println("댓글리스트 받기");
        if(!teamBoardRepository.findById(boardId).isPresent()){
            throw new CustomException(ErrorCode.BOARD_NOT_FOUND);
        }
        if(teamBoardCommentRepository.findAllByParentIdAndTeamBoardId(parentId, boardId).isEmpty()){
            throw new CustomException(ErrorCode.LIST_NOT_FOUND);
        }
        System.out.println("댓글리스트 받기2");
        CommentListRes commentListRes = new CommentListRes();

        List<TeamBoardComment> teamBoardCommentList = teamBoardCommentRepository.findAllByParentIdAndTeamBoardId(parentId,boardId);
        commentListRes.setReplyCount(teamBoardCommentList.size());

        ArrayList<CommentRes> commentResArrayList = new ArrayList<>();
        for (TeamBoardComment teamBoardComment : teamBoardCommentList){
            CommentRes commentRes = new CommentRes();
            commentRes.setId(teamBoardComment.getId());
            commentRes.setWriterId(teamBoardComment.getWriterId());
            commentRes.setName(userRepository.findById(teamBoardComment.getWriterId()).get().getName());
            commentRes.setContent(teamBoardComment.getContent());
            commentRes.setCreateDate(teamBoardComment.getCreateDate());
            commentRes.setModifyDate(teamBoardComment.getModifyDate());
            commentRes.setModified(teamBoardComment.isModified());
            commentRes.setParentId(teamBoardComment.getParentId());
            commentRes.setDepth(teamBoardComment.getDepth());
            commentRes.setReplyCount(teamBoardComment.getReplyCount());

            commentResArrayList.add(commentRes);
        }
        commentListRes.setCommentsList(commentResArrayList);

        return commentListRes;
    }
}
