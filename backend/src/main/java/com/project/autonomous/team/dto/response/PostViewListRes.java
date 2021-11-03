package com.project.autonomous.team.dto.response;

import lombok.Setter;

import java.time.LocalDateTime;

@Setter
public class PostViewListRes {
    long id;
    String name;
    String title;
    String content;
    LocalDateTime createDate;
}
