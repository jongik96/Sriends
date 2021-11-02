package com.project.autonomous.user.entity;

import com.project.autonomous.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class Interest extends BaseEntity {
    long userId;
    long sportCategoryId;
}
