package com.project.autonomous.user.controller;


import com.project.autonomous.jwt.util.SecurityUtil;
import com.project.autonomous.user.dto.request.AuthCode;
import com.project.autonomous.user.dto.request.UserModifyPutReq;
import com.project.autonomous.user.dto.response.MyProfileRes;
import com.project.autonomous.user.dto.response.UserProfileRes;
import com.project.autonomous.user.entity.User;
import com.project.autonomous.user.service.EmailService;
import com.project.autonomous.user.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User", description = "토큰이 필요한 유저 API")
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    EmailService emailService;

    @PutMapping("/{userId}")
    public ResponseEntity<Boolean> modifyUserInfo(@PathVariable("userId") Long userId, @RequestBody UserModifyPutReq modifyInfo){
        System.out.println("회원정보 수정");

        User user = userService.modifyUser(userId, modifyInfo);

        return ResponseEntity.status(200).body(true);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") Long userId){
        System.out.println("회원 탈퇴");

        //userId가 토큰이랑 일치하면 회원탈퇴 진행
        if(true){
            User user = userService.deleteUser(userId);
        }

        return;
    }

    @GetMapping("/me")
    public ResponseEntity<MyProfileRes> getMyProfile(){
        //토큰으로 userid 찾는거 추가

        System.out.println("본인 회원정보 조회");

        MyProfileRes userRes = userService.getMyProfile();

        return ResponseEntity.status(200).body(userRes);

    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserProfileRes> getUserProfile(@PathVariable("userId") Long userId){
        //토큰으로 userid 찾는거 추가
        System.out.println("다른 회원정보 조회");

        UserProfileRes userRes = userService.getUserProfile(userId);

        return ResponseEntity.status(200).body(userRes);

    }

    @GetMapping("/auth/{email}")
    public ResponseEntity<Boolean> sendMail(@PathVariable("email") String email){
        System.out.println("이메일 발송");

        emailService.sendMail(email);

        return ResponseEntity.status(200).body(true);
    }

    @PostMapping("/auth/{email}")
    public ResponseEntity<Boolean> checkMail(@PathVariable("email") String email, @RequestBody AuthCode authCode){
        System.out.println("이메일 확인");

        if(emailService.checkCode(authCode, email)){

            return ResponseEntity.status(200).body(true);
        }
        return ResponseEntity.status(500).body(false);
    }

}
