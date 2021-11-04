package com.project.autonomous.team.dto.response;

import lombok.Setter;

import java.time.LocalDateTime;

@Setter
public class CommentRes {
    long id;
    long writerId;
    String name;
    String content;
    LocalDateTime createDate;
    LocalDateTime modifyDate;
    boolean isModified;
    long parentId;
    int depth;
    int replyCount;

}
