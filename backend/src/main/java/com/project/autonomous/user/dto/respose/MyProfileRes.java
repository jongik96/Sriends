package com.project.autonomous.user.dto.respose;

import com.project.autonomous.team.entity.Team;
import com.project.autonomous.user.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.ArrayList;

@Getter
@Setter
public class MyProfileRes {

    String email;
    String name;
    Date birth;
    String gender;
    String phone;
    String city;
    String pictureDownloadUri;
    ArrayList<UserTeamListRes> teams;

}
