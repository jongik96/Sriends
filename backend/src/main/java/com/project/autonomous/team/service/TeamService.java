package com.project.autonomous.team.service;

import com.project.autonomous.team.dto.request.ApplyPostReq;
import com.project.autonomous.team.dto.request.TeamCreatePostReq;
import com.project.autonomous.team.dto.request.TeamModifyPostReq;
import com.project.autonomous.team.dto.response.*;
import com.project.autonomous.team.entity.Team;
import java.util.ArrayList;

public interface TeamService {
    Team create(TeamCreatePostReq teamInfo);

    ArrayList<TeamListRes> getList();

    ArrayList<TeamListRes> getChooseList(String cityName, String sportCategory);

    boolean modify(TeamModifyPostReq teamInfo, long teamId);

    boolean delete(long teamId);

    TeamInfoRes getTeamInfo(long teamId);

    void apply(long teamId, long userId, ApplyPostReq applyPostReq);

    ArrayList<ApplyListRes> applyList(long teamId);

    ArrayList<MemberListRes> memberList(long teamId);

    boolean permit(long teamId, long userId);

    void quit(long teamId);

    void kickout(long teamId, long userId);

    AuthorityRes checkAuthority(long teamId);

    boolean giveAuthority(long teamId, long userId);

}
