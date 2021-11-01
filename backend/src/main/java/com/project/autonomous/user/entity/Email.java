package com.project.autonomous.user.entity;

import com.project.autonomous.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.processing.Generated;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Email extends BaseEntity {
    LocalDateTime expirationDate;
    boolean expired;
    LocalDateTime createDate;
    LocalDateTime lastModifiedDate;
    String email;
    int code;
}
