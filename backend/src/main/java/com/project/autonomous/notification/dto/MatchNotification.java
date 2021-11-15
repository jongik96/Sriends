package com.project.autonomous.notification.dto;

import com.project.autonomous.matchboard.posts.entity.MatchBoardPost;
import com.project.autonomous.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MatchNotification {

    private User sender;

    private User receiver;

    private MatchBoardPost matchBoardPost;

}
