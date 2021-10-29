package com.project.autonomous.user.dto.response;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProfileRes {
    String email;
    String name;
    LocalDate birth;
    String phone;
    String city;
}
