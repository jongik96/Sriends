package com.project.autonomous.team.service;

import com.project.autonomous.team.dto.request.TeamCreatePostReq;
import com.project.autonomous.team.dto.request.TeamModifyPostReq;
import com.project.autonomous.team.dto.response.TeamInfoRes;
import com.project.autonomous.team.dto.response.TeamListRes;
import com.project.autonomous.team.entity.Team;
import com.project.autonomous.user.entity.User;

import java.util.ArrayList;

public interface TeamService {
    Team create(TeamCreatePostReq teamInfo);

    ArrayList<TeamListRes> getList();

    Team modify(TeamModifyPostReq teamInfo, long teamId);

    Team delete(long teamId);

    TeamInfoRes getTeamInfo(long teamId);

    void apply(long teamId, long userId);

    ArrayList<User> applyList(long teamId);

    void permit(long teamId, long userId);

    void quit(long teamId, long userId);

    void kickout(long teamId, long userId);
}
