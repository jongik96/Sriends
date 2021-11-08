package com.project.autonomous.team.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MemberListRes {
    long userId;
    String name;
    String email;
    LocalDateTime registerDate;
}
