package com.project.autonomous.team.controller;

import com.project.autonomous.team.dto.request.PostingReq;
import com.project.autonomous.team.dto.request.TeamCreatePostReq;
import com.project.autonomous.team.dto.response.PostViewListRes;
import com.project.autonomous.team.dto.response.PostViewRes;
import com.project.autonomous.team.entity.Team;
import com.project.autonomous.team.service.TeamBoardService;
import com.project.autonomous.team.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/teams/board")
public class TeamBoardController {

    @Autowired
    TeamBoardService teamBoardService;

    @PostMapping("/{teamId}")
    public ResponseEntity<Boolean> createPost(@PathVariable("teamId") long teamId, @RequestBody PostingReq postingReq){
        System.out.println("공지사항 등록");

        return ResponseEntity.ok(teamBoardService.posting(postingReq, teamId));

    }

    @PutMapping("/{teamId}/{boardId}")
    public ResponseEntity<Boolean> modifyPost(@PathVariable("teamId") long teamId, @PathVariable("boardId") long boardId, @RequestBody PostingReq postingReq){
        System.out.println("공지사항 수정");

        return ResponseEntity.ok(teamBoardService.postingModify(postingReq, teamId, boardId));

    }

    @DeleteMapping("/{teamId}/{boardId}")
    public ResponseEntity<Boolean> deletePost(@PathVariable("teamId") long teamId, @PathVariable("boardId") long boardId){
        System.out.println("공지사항 삭제");

        return ResponseEntity.ok(teamBoardService.postingDelete(teamId, boardId));

    }

    @GetMapping("/{teamId}/{boardId}")
    public ResponseEntity<PostViewRes> getPost(@PathVariable("teamId") long teamId, @PathVariable("boardId") long boardId){
        System.out.println("공지사항 조회");

        return ResponseEntity.ok(teamBoardService.postingView(teamId, boardId));

    }

    @GetMapping("/{teamId}")
    public ResponseEntity<ArrayList<PostViewListRes>> getPostList(@PathVariable("teamId") long teamId){
        System.out.println("공지사항 리스트 조회");

        return ResponseEntity.ok(teamBoardService.postingViewList(teamId));

    }

}
