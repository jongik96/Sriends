package com.project.autonomous.team.entity;

import com.project.autonomous.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Calendar extends BaseEntity {
    long teamId;
    long writerId;
    String name;
    String content;
    LocalDateTime schedule;
}
