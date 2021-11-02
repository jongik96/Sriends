package com.project.autonomous.user.dto.request;

import static com.project.autonomous.common.exception.ValidatorMessage.EMAIL_MESSAGE;
import static com.project.autonomous.common.exception.ValidatorMessage.EMPTY_MESSAGE;
import static com.project.autonomous.common.exception.ValidatorMessage.USER_NAME_MESSAGE;
import static com.project.autonomous.common.exception.ValidatorMessage.USER_PHONE_FORMAT;
import static com.project.autonomous.common.exception.ValidatorMessage.USER_PHONE_MESSAGE;
import static com.project.autonomous.common.exception.ValidatorMessage.USER_PW_FORMAT;
import static com.project.autonomous.common.exception.ValidatorMessage.USER_PW_MESSAGE;

import com.project.autonomous.common.entity.City;
import com.project.autonomous.user.entity.User;
import com.project.autonomous.user.entity.UserAuthority;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
public class UserRegisterPostReq {

    @Schema(description = "Email", example = "k5d106@naver.com")
    @NotBlank(message = EMPTY_MESSAGE)
    @Email(message = EMAIL_MESSAGE)
    String email;

    @Schema(description = "이름", example = "박범진")
    @NotBlank(message = EMPTY_MESSAGE)
    @Length(min = 2, max = 45, message = USER_NAME_MESSAGE)
    String name;

    @Schema(description = "생년월일", example = "1995-09-06")
    @NotNull(message = EMPTY_MESSAGE)
    LocalDate birth;

    @Schema(description = "성별 남, 여", example = "남")
    @NotBlank(message = EMPTY_MESSAGE)
    String gender;

    @Schema(description = "휴대폰 번호", example = "01028732329")
    @Pattern(regexp = USER_PHONE_FORMAT, message = USER_PHONE_MESSAGE)
    String phone;
    @Schema(description = "서울, 부산 등", example = "서울")
    @NotBlank(message = EMPTY_MESSAGE)
    String city;

    @Schema(description = "패스워드 숫자, 문자, 특수 문자 포함 8 ~ 20자", example = "qwer1234^")
    @NotNull(message = EMPTY_MESSAGE)
    @Pattern(regexp = USER_PW_FORMAT, message = USER_PW_MESSAGE)
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
