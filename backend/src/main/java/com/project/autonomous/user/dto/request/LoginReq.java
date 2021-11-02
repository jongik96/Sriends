package com.project.autonomous.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Getter
@Setter
public class LoginReq {

    @Email
    @Schema(description = "로그인 이메일", example = "qjawlsqjacks@naver.com")
    String email;

    @Schema(description = "비밀번호", example = "abcdef12345^")
    String password;

    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }
}
