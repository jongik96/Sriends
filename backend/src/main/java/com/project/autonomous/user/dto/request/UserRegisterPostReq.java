package com.project.autonomous.user.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class UserRegisterPostReq {

    String email;
    String name;
    Date birth;
    String gender;
    String phone;
    String city;

    String password;
}
