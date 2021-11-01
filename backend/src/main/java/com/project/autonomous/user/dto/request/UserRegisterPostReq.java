package com.project.autonomous.user.dto.request;

import com.project.autonomous.common.entity.City;
import com.project.autonomous.user.entity.User;
import com.project.autonomous.user.entity.UserAuthority;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

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

    public User toUser(PasswordEncoder passwordEncoder) {
        return User.builder()
            .email(email)
            .name(name)
            .birth(birth)
            .gender(gender)
            .phone(phone)
            .city(City.from(city))
            .password(passwordEncoder.encode(password))
            .userAuthority(UserAuthority.ROLE_USER)
            .deleted(false)
            .build();
    }
}
