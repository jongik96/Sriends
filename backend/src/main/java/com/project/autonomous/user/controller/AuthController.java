package com.project.autonomous.user.controller;

import com.project.autonomous.common.exception.ErrorResponse;
import com.project.autonomous.jwt.dto.TokenDto;
import com.project.autonomous.user.dto.request.LoginReq;
import com.project.autonomous.user.dto.request.PasswordReq;
import com.project.autonomous.user.dto.request.UserRegisterPostReq;
import com.project.autonomous.user.service.AuthServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Auth", description = "토큰 없는 유저 API")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthServiceImpl authService;

    @PostMapping("/sign-up")
    @Operation(summary = "회원 가입", description = "<strong>입력 받은 정보</strong>를 사용해 회원 가입한다.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "정상 가입", content = @Content),
        @ApiResponse(responseCode = "400", description = "ALREADY_JOIN",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    public ResponseEntity<String> signUp(
        @Valid @RequestBody UserRegisterPostReq userRegisterPostReq) {
        authService.signup(userRegisterPostReq);
        return ResponseEntity.ok("정상 가입");
    }

    @GetMapping("/sign-up/{email}")
    @Operation(summary = "중복 이메일 확인", description = "<strong>받은 이메일 주소</strong>를 사용해 중복을 확인한다.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "true = 이미 존재하는 이메일\n\nfalse = 사용 가능"),
    })
    public ResponseEntity<Boolean> emailCheck(@PathVariable("email") String email) {
        return ResponseEntity.ok(authService.checkEmail(email));
    }

    @PostMapping("/check-password")
    public ResponseEntity<Boolean> checkPassword(@RequestBody PasswordReq checkInfo) {
        System.out.println("비밀번호 확인");
        String password = checkInfo.getPassword();
        return ResponseEntity.status(400).body(Boolean.FALSE);
    }

    @GetMapping("/pageable-test")
    public ResponseEntity<String> pageable(@ParameterObject Pageable pageable) {
        return ResponseEntity.ok("fsda");
    }

    @PostMapping("/login")
    @Operation(summary = "로그인", description = "<strong>받은 이메일, 패스워드</strong>를 사용해 로그인한다.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "토큰 정보",
            content = @Content(schema = @Schema(implementation = TokenDto.class))),
    })
    public ResponseEntity<TokenDto> login(@Valid @RequestBody LoginReq loginReq) {
        return ResponseEntity.ok(authService.login(loginReq));
    }

}
