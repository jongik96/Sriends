package com.project.autonomous.team.controller;

import com.project.autonomous.team.dto.request.TeamCreatePostReq;
import com.project.autonomous.team.dto.request.TeamModifyPostReq;
import com.project.autonomous.team.dto.response.TeamInfoRes;
import com.project.autonomous.team.dto.response.TeamListRes;
import com.project.autonomous.team.entity.Team;
import com.project.autonomous.team.service.TeamService;
import com.project.autonomous.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/teams")
public class TeamController {

    @Autowired
    TeamService teamService;

    @PostMapping("/")
    public void createTeam(@RequestBody TeamCreatePostReq teamInfo){
        System.out.println("팀 생성");

        Team team = teamService.create(teamInfo);

    }

    @PutMapping("/{teamId}")
    public void createTeam(@PathVariable("teamId") long teamId, @RequestBody TeamModifyPostReq teamInfo){
        System.out.println("팀 수정");

        Team team = teamService.modify(teamInfo, teamId);

    }

    @DeleteMapping("/{teamId}")
    public void deleteTeam(@PathVariable("teamId") long teamId){
        System.out.println("팀 삭제");

        Team team = teamService.delete(teamId);

    }

    @GetMapping("/{teamId}")
    public ResponseEntity<TeamInfoRes> getTeamInfo(@PathVariable("teamId") long teamId){
        System.out.println("팀 정보 조회");

        TeamInfoRes teamInfoRes = teamService.getTeamInfo(teamId);

        return ResponseEntity.status(200).body(teamInfoRes);
    }

    @PostMapping("/{teamId}/{userId}")
    public void apply(@PathVariable("teamId") long teamId, @PathVariable("userId") long userId){
        System.out.println("팀 가입 신청");

        teamService.apply(teamId, userId);
        return;
    }

    @GetMapping("/apply-list/{teamId}")
    public ResponseEntity<ArrayList<User>> applyList(@PathVariable("teamId") long teamId){
        System.out.println("팀 가입 신청 리스트 조회");

        ArrayList<User> users = teamService.applyList(teamId);

        return ResponseEntity.ok(users);
    }

    @PostMapping("/permit/{teamId}/{userId}")
    public void permit(@PathVariable("teamId") long teamId, @PathVariable("userId") long userId){
        System.out.println("회원가입 승인");

        teamService.permit(teamId, userId);

        return;
    }

    @DeleteMapping("/{teamId}/{userId}")
    public void quit(@PathVariable("teamId") long teamId, @PathVariable("userId") long userId){
        System.out.println("회원 탈퇴");

        teamService.quit(teamId, userId);

        return;
    }

    @DeleteMapping("/kick-out/{teamId}/{userId}")
    public void kickOut(@PathVariable("teamId") long teamId, @PathVariable("userId") long userId){
        System.out.println("회원 강퇴");

        teamService.kickout(teamId,userId);

        return;
    }



    @GetMapping("/list")
    public ResponseEntity<ArrayList<TeamListRes>> getTeamList(){
        System.out.println("회원가입 정보로 팀 리스트 조회");

        ArrayList<TeamListRes> list = teamService.getList();

        return null;
    }

}
