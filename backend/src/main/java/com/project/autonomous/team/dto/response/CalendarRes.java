package com.project.autonomous.team.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CalendarRes {
    long id;
    long writerId;
    String writerName;
    String name;
    String content;
    LocalDateTime schedule;
}
