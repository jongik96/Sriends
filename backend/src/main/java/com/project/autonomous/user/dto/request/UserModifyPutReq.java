package com.project.autonomous.user.dto.request;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserModifyPutReq {
    String name;
    LocalDate birth;
    String gender;
    String phone;
    String city;
    String uuid;//이미지 아이디

    @Override
    public String toString() {
        return "UserModifyPutReq{" +
                "name='" + name + '\'' +
                ", birth=" + birth +
                ", gender='" + gender + '\'' +
                ", phone='" + phone + '\'' +
                ", city='" + city + '\'' +
                ", uuid='" + uuid + '\'' +
                '}';
    }
}
