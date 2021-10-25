package com.project.autonomous.user.entity;

import com.project.autonomous.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
public class User extends BaseEntity {


    String email;
    String name;
    Date birth;
    String gender;
    String phone;
    String city;
    String picture_id;

    String password;

    @OneToMany(mappedBy = "user")
    private List<UserTeam> userTeamList = new ArrayList<>();

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", birth=" + birth +
                ", gender='" + gender + '\'' +
                ", phone='" + phone + '\'' +
                ", city='" + city + '\'' +
                ", picture_id='" + picture_id + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
