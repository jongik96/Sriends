package com.project.autonomous.user.controller;


import com.project.autonomous.user.dto.request.LoginReq;
import com.project.autonomous.user.dto.request.PasswordReq;
import com.project.autonomous.user.dto.request.UserModifyPutReq;
import com.project.autonomous.user.dto.request.UserRegisterPostReq;
import com.project.autonomous.user.dto.response.MyProfileRes;
import com.project.autonomous.user.dto.response.UserProfileRes;
import com.project.autonomous.user.entity.User;
import com.project.autonomous.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    UserService userService;

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
        Long userId = 1L;//임시로
        MyProfileRes userRes = userService.getMyProfile(userId);

        return ResponseEntity.status(200).body(userRes);

    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserProfileRes> getUserProfile(@PathVariable("userId") Long userId){
        //토큰으로 userid 찾는거 추가
        System.out.println("다른 회원정보 조회");

        UserProfileRes userRes = userService.getUserProfile(userId);

        return ResponseEntity.status(200).body(userRes);

    }

}
