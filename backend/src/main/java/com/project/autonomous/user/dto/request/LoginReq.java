package com.project.autonomous.user.dto.request;

import static com.project.autonomous.common.exception.ValidatorMessage.EMPTY_MESSAGE;
import static com.project.autonomous.common.exception.ValidatorMessage.USER_PW_FORMAT;
import static com.project.autonomous.common.exception.ValidatorMessage.USER_PW_MESSAGE;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Getter
@AllArgsConstructor
public class LoginReq {

    @Schema(description = "로그인 이메일", example = "qjawlsqjacks@naver.com")
    @NotBlank(message = EMPTY_MESSAGE)
    @Email
    private String email;

    @Schema(description = "비밀번호", example = "abcdef12345^")
    @NotNull(message = EMPTY_MESSAGE)
    @Pattern(regexp = USER_PW_FORMAT, message = USER_PW_MESSAGE)
    private String password;

    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }
}
