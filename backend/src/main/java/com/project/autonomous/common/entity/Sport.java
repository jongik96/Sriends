package com.project.autonomous.common.entity;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Sport {
    축구("축구"),
    풋살("풋살"),
    농구("농구"),
    야구("야구"),
    배구("배구"),
    핸드볼("핸드볼"),
    당구("당구"),
    골프("골프"),
    티볼("티볼"),
    크리켓("크리켓"),
    하키("하키"),
    스쿼시("스쿼시"),
    테니스("테니스"),
    탁구("탁구"),
    배드민턴("배드민턴"),
    ;

    String value;
    Sport(String value) { this.value = value; }
    public String value() { return value; }

    @JsonCreator
    public static Sport from(String s) {
        return Sport.valueOf(s.toUpperCase());
    }
}
