package com.project.autonomous.team.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeamListRes {
    long id;
    String pictureDownloadUri;
    String name;
    String description;
    boolean membershipFee;
    int memberCount;
    String sportsCategory;
    String city;
}
