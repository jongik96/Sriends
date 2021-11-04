package com.project.autonomous.team.controller;

import com.project.autonomous.common.exception.ErrorResponse;
import com.project.autonomous.team.dto.request.ApplyPostReq;
import com.project.autonomous.team.dto.request.TeamCreatePostReq;
import com.project.autonomous.team.dto.request.TeamModifyPostReq;
import com.project.autonomous.team.dto.response.ApplyListRes;
import com.project.autonomous.team.dto.response.AuthorityRes;
import com.project.autonomous.team.dto.response.TeamInfoRes;
import com.project.autonomous.team.dto.response.TeamListRes;
import com.project.autonomous.team.entity.Team;
import com.project.autonomous.team.service.TeamService;
import com.project.autonomous.user.entity.Interest;
import com.project.autonomous.user.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    public ResponseEntity<Boolean> createTeam(@RequestBody TeamCreatePostReq teamInfo){
        System.out.println("팀 생성");

        Team team = teamService.create(teamInfo);

        return ResponseEntity.ok(true);

    }
    @GetMapping("/list")
    public ResponseEntity<ArrayList<TeamListRes>> getTeamList(){
        System.out.println("회원가입 정보로 팀 리스트 조회");

        ArrayList<TeamListRes> list = teamService.getList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/list/{city}/{sportCategory}")
    public ResponseEntity<ArrayList<TeamListRes>> getTeamListChoose(@PathVariable("city") String city, @PathVariable("sportCategory") String sportCategory){
        System.out.println("선택한 정보로 팀 리스트 조회");

        ArrayList<TeamListRes> list = teamService.getChooseList(city, sportCategory);
        if(list.isEmpty()){
            return ResponseEntity.status(400).body(list);
        }

        return ResponseEntity.ok(list);
    }

    @PutMapping("/{teamId}")
    public ResponseEntity<Boolean> createTeam(@PathVariable("teamId") long teamId, @RequestBody TeamModifyPostReq teamInfo){
        System.out.println("팀 수정");

        if(teamService.modify(teamInfo, teamId)){
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.status(400).body(false);
    }

    @DeleteMapping("/{teamId}")
    public ResponseEntity<Boolean> deleteTeam(@PathVariable("teamId") long teamId){
        System.out.println("팀 삭제");

        if(teamService.delete(teamId)){
            return ResponseEntity.ok(true);
        }

        return ResponseEntity.status(400).body(false);

    }

    @GetMapping("/{teamId}")
    public ResponseEntity<TeamInfoRes> getTeamInfo(@PathVariable("teamId") long teamId){
        System.out.println("팀 정보 조회");

        TeamInfoRes teamInfoRes = teamService.getTeamInfo(teamId);

        return ResponseEntity.status(200).body(teamInfoRes);
    }

    @GetMapping("/{teamId}/{userId}")
    public ResponseEntity<AuthorityRes> getAuthortiy(@PathVariable("teamId") long teamId, @PathVariable("userId") long userId){

        return null;
    }

    @PostMapping("/{teamId}/{userId}")
    public void apply(@PathVariable("teamId") long teamId, @PathVariable("userId") long userId, @RequestBody ApplyPostReq applyPostReq){
        System.out.println("팀 가입 신청");

        teamService.apply(teamId, userId, applyPostReq);
        return;
    }

    @GetMapping("/apply-list/{teamId}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "리스트 조회", content = @Content),
            @ApiResponse(responseCode = "400", description = "NO_INTERESTING_ITEMS\n\nBAD_REQUEST",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    public ResponseEntity<ArrayList<ApplyListRes>> applyList(@PathVariable("teamId") long teamId){
        System.out.println("팀 가입 신청 리스트 조회");

        ArrayList<ApplyListRes> users = teamService.applyList(teamId);

        return ResponseEntity.ok(users);
    }

    @GetMapping("/permit/{teamId}/{userId}")
    public ResponseEntity<Boolean> permit(@PathVariable("teamId") long teamId, @PathVariable("userId") long userId){
        System.out.println("팀 가입 승인");

        if(teamService.permit(teamId, userId)){
            return ResponseEntity.ok(true);
        }

        return ResponseEntity.status(400).body(false);
    }

    @GetMapping("/{teamId}/me")
    public ResponseEntity<AuthorityRes> checkAuthortiy(@PathVariable("teamId") long teamId){
        System.out.println("본인 권한 조회");

        return ResponseEntity.ok(teamService.checkAuthority(teamId));
    }

    @PostMapping("/manager/{teamId}/{userId}")
    public ResponseEntity<Boolean> giveAuthority(@PathVariable("teamId") long teamId, @PathVariable("userId") long userId){
        System.out.println("팀 관리자 권한 부여");

        if(teamService.giveAuthority(teamId,userId)){
           return ResponseEntity.ok(true);
        }
        return ResponseEntity.status(400).body(false);
    }

    @DeleteMapping("/{teamId}/me")
    public void quit(@PathVariable("teamId") long teamId){
        System.out.println("회원 탈퇴");

        teamService.quit(teamId);

        return;
    }

    @DeleteMapping("/kick-out/{teamId}/{userId}")
    public void kickOut(@PathVariable("teamId") long teamId, @PathVariable("userId") long userId){
        System.out.println("회원 강퇴");

        teamService.kickout(teamId,userId);

        return;
    }



}
