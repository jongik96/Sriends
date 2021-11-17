package com.project.autonomous.team.dto.response;

import com.project.autonomous.team.entity.TeamBoard;
import com.project.autonomous.user.dto.response.UserSimpleInfoRes;
import com.project.autonomous.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class PostViewRes {
    long id;
//    long writerId;
    UserSimpleInfoRes writer;
    String title;
    String content;
    LocalDateTime createDate;

    public static PostViewRes from(TeamBoard teamBoard, User writer){
        return PostViewRes.builder()
                .id(teamBoard.getId())
                .writer(UserSimpleInfoRes.from(writer))
                .title(teamBoard.getTitle())
                .content(teamBoard.getContent())
                .createDate(teamBoard.getCreateDate())
                .build();
    }
}
