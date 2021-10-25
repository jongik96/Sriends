package com.project.autonomous.user.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class UserModifyPutReq {
    String name;
    Date birth;
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
