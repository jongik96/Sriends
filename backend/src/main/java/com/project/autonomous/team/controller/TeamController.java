package com.project.autonomous.team.controller;

import com.project.autonomous.team.dto.request.TeamCreatePostReq;
import com.project.autonomous.team.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teams")
public class TeamController {

    @Autowired
    TeamService teamService;

    @PostMapping("/")
    public void createTeam(@RequestBody TeamCreatePostReq teamInfo){
        System.out.println("팀 생성");

    }
}
