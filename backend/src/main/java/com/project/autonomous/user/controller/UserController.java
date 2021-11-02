package com.project.autonomous.user.controller;


import com.project.autonomous.common.exception.ErrorResponse;
import com.project.autonomous.user.dto.request.CheckPasswordReq;
import com.project.autonomous.user.dto.request.InterestReq;
import com.project.autonomous.user.dto.request.UserModifyPutReq;
import com.project.autonomous.user.dto.response.MyProfileRes;
import com.project.autonomous.user.dto.response.UserProfileRes;
import com.project.autonomous.user.entity.User;
import com.project.autonomous.user.service.EmailService;
import com.project.autonomous.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User", description = "토큰이 필요한 유저 API")
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    EmailService emailService;

    @PostMapping("/check-password")
    @Operation(summary = "비밀번호 확인 (비밀번호 수정 페이지에 사용)", description = "<strong>입력 받은 비밀번호</strong>를 사용해 현 사용자의 비밀번호와 비교한다.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "true, false", content = @Content),
        @ApiResponse(responseCode = "404", description = "USER_NOT_FOUND",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    public ResponseEntity<Boolean> checkPassword(@Valid @RequestBody CheckPasswordReq checkPasswordReq) {
        return ResponseEntity.ok(userService.checkPassword(checkPasswordReq));
    }

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


    @PostMapping("/interest")
    public void interest(@RequestBody InterestReq interestReq){
        System.out.println("흥미있는 종목 선택");

        userService.interest(interestReq);
    }



}
