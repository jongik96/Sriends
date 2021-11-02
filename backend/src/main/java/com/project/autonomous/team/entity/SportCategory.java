package com.project.autonomous.team.entity;

import com.project.autonomous.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class SportCategory extends BaseEntity {
    String name;
}
