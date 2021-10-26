package com.project.autonomous.user.dto.request;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterPostReq {

    String email;
    String name;
    LocalDate birth;
    String gender;
    String phone;
    String city;

    String password;
}
