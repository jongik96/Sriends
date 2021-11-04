package com.project.autonomous.team.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentPostReq {
    String content;
    long parentId;
}
