package com.project.autonomous.team.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SchedulePostReq {
    long teamId;
    long writerId;
    String name;
    String content;
    LocalDateTime schedule;
}
