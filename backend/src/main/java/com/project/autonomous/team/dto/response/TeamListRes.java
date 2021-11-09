package com.project.autonomous.team.dto.response;

import com.project.autonomous.user.dto.response.UserSimpleInfoRes;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeamListRes {
    long id;
    String pictureDownloadUri;
    String name;
    UserSimpleInfoRes leader;
//    long leaderId;
//    String leaderName;
    String description;
    boolean membershipFee;
    int memberCount;
    String sportsCategory;
    String city;
}
