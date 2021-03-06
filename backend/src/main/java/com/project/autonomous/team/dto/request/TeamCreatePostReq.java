package com.project.autonomous.team.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.time.LocalDateTime;

@Getter
@Setter
public class TeamCreatePostReq {
    String name;
//    LocalDateTime createDate;
//    long leaderId;
//    String pictureId;
//    int memberCount;
    int maxCount;
    String description;
    Boolean recruitmentState;
    Boolean membershipFee;
    String city;
    String sportCategory;
    MultipartFile file;
}
