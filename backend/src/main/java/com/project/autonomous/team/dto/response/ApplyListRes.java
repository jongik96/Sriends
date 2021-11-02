package com.project.autonomous.team.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ApplyListRes {
    String email;
    String description;
    LocalDateTime createDate;
}
