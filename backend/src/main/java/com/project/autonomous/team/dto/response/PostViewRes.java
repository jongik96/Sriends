package com.project.autonomous.team.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostViewRes {
    long id;
    long writerId;
    String name;
    String title;
    String content;
    LocalDateTime createDate;
}
