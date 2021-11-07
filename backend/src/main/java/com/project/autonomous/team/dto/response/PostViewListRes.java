package com.project.autonomous.team.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostViewListRes {
    long id;
    String name;
    long writerId;
    String title;
    String content;
    LocalDateTime createDate;
}
