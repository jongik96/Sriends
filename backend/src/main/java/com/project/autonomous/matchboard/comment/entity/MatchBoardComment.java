package com.project.autonomous.matchboard.comment.entity;

import com.project.autonomous.common.entity.BaseEntity;
import com.project.autonomous.matchboard.posts.entity.MatchBoardPost;
import com.project.autonomous.user.entity.User;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MatchBoardComment extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "match_board_post_id")
    private MatchBoardPost matchBoardPost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id")
    private User user;

    private String content;

    private LocalDateTime createAt;

    private int depth;

    private long parentId;

//    private long groupId;

    private int replyCount;

    private boolean modified;


}
