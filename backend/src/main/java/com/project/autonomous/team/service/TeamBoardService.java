package com.project.autonomous.team.service;

import com.project.autonomous.jwt.util.SecurityUtil;
import com.project.autonomous.team.dto.request.PostingReq;
import com.project.autonomous.team.dto.response.PostViewListRes;
import com.project.autonomous.team.dto.response.PostViewRes;
import com.project.autonomous.team.entity.TeamBoard;
import com.project.autonomous.team.repository.TeamBoardRepository;
import com.project.autonomous.user.repository.UserRepository;
import com.project.autonomous.user.repository.UserTeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class TeamBoardService {

    @Autowired
    TeamBoardRepository teamBoardRepository;

    @Autowired
    UserTeamRepository userTeamRepository;

    @Autowired
    UserRepository userRepository;

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
}
