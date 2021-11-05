package com.project.autonomous.team.entity;

import com.project.autonomous.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class TeamBoardComment extends BaseEntity {
    long teamBoardId;
    long writerId;
    String content;
    LocalDateTime createDate;
    LocalDateTime modifyDate;
    boolean isModified;
    long parentId;
    int depth;
    int replyCount;
}
