package com.project.autonomous.user.dto.response;

import java.time.LocalDate;
import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyProfileRes {

    String email;
    String name;
    LocalDate birth;
    String gender;
    String phone;
    String city;
    String pictureDownloadUri;
    ArrayList<UserTeamListRes> teams;

}
