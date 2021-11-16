package com.project.autonomous.team.dto.response;

import com.project.autonomous.user.dto.response.UserSimpleInfoRes;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentRes {
    long id;
//    long writerId;
    UserSimpleInfoRes writer;
//    String name;
    String content;
    LocalDateTime createDate;
    LocalDateTime modifyDate;
    boolean isModified;
    long parentId;
    int depth;
    int replyCount;

}
