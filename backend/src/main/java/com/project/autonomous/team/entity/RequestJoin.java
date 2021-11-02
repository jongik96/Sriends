package com.project.autonomous.team.entity;

import com.project.autonomous.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class RequestJoin extends BaseEntity {
    long teamId;
    long userId;
    String description;
    LocalDateTime create_date;
}
