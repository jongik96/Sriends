package com.project.autonomous.team.dto.response;

import lombok.Setter;

import java.util.ArrayList;

@Setter
public class CommentListRes {
    int replyCount;
    ArrayList<CommentRes> commentsList;
}
