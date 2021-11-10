package com.project.autonomous.team.dto.response;

import com.project.autonomous.user.dto.response.UserSimpleInfoRes;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostViewRes {
    long id;
//    long writerId;
    UserSimpleInfoRes writer;
    String title;
    String content;
    LocalDateTime createDate;
}
