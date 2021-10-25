package com.project.autonomous.user.dto.respose;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class UserProfileRes {
    String email;
    String name;
    Date birth;
    String phone;
    String city;
}
