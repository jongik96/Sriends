package com.project.autonomous.team.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TeamInfoRes {
    String name;
    LocalDateTime createDate;
    long leaderId;
    String pictureId;
    int memberCount;
    int maxCount;
    String description;
    Boolean recruitmentState;
    Boolean membershipFee;
    String city;
    String sportCategory;
}
