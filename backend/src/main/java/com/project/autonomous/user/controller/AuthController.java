package com.project.autonomous.user.controller;

import com.project.autonomous.jwt.dto.TokenDto;
import com.project.autonomous.user.dto.request.LoginReq;
import com.project.autonomous.user.dto.request.UserRegisterPostReq;
import com.project.autonomous.user.service.AuthServiceImpl;
import com.project.autonomous.user.service.EmailService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    // 토큰을 사용하지 않는 API

    private final AuthServiceImpl authService;

    @Autowired
    EmailService emailSenderService;

    @PostMapping("/sign-up")
    @ApiOperation(value = "회원 가입", notes = "<strong>입력 받은 정보</strong>를 사용해 회원 가입한다.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "정상 가입"),
        @ApiResponse(code = 400, message = "ALREADY_JOIN"),
    })
    public ResponseEntity<String> signUp(@RequestBody UserRegisterPostReq userRegisterPostReq){
        authService.signup(userRegisterPostReq);
        return ResponseEntity.ok("정상 가입");
    }

    @GetMapping("/sign-up/{email}")
    @ApiOperation(value = "중복 이메일 확인", notes = "<strong>받은 이메일 주소</strong>를 사용해 중복을 확인한다.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "true = 이미 존재하는 이메일\nfalse = 사용 가능"),
    })
    public ResponseEntity<Boolean> emailCheck(@PathVariable("email") String email){
        return ResponseEntity.ok(authService.checkEmail(email));
    }

//    @PostMapping("/check-password")
//    public ResponseEntity<Boolean> checkPassword(@RequestBody PasswordReq checkInfo){
//        System.out.println("비밀번호 확인");
//        String password = checkInfo.getPassword();
//
//
//
//        return ResponseEntity.status(400).body(Boolean.FALSE);
//    }

    @PostMapping("/login")
    @ApiOperation(value = "로그인", notes = "<strong>받은 이메일, 패스워드</strong>를 사용해 로그인한다.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "토큰 정보"),
    })
    public ResponseEntity<TokenDto> login(@RequestBody LoginReq loginReq){
        return ResponseEntity.ok(authService.login(loginReq));
    }



}
