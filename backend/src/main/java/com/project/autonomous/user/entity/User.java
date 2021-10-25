package com.project.autonomous.user.entity;

import com.project.autonomous.common.entity.BaseEntity;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User extends BaseEntity {

    String email;
    String name;
    private LocalDate birth;
    String gender;
    String phone;
    String city;
    String picture_id;
    String password;

    @NotNull
    @Enumerated(EnumType.STRING)
    private UserAuthority userAuthority;

    @NotNull
    private boolean deleted;

    @OneToMany(mappedBy = "user")
    private List<UserTeam> userTeamList = new ArrayList<>();

    // 유저 삭제
    public void delete() {
        this.deleted = true;
    }

    // 유저 복구
    public void restore() {
        this.deleted = false;
    }

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
