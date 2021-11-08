package com.project.autonomous.team.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class CommentListRes {
    int replyCount;
    ArrayList<CommentRes> commentsList;
}
