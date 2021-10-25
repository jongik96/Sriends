package com.project.autonomous.user.controller;

<<<<<<< HEAD
import com.project.autonomous.user.dto.request.LoginReq;
import com.project.autonomous.user.dto.request.PasswordReq;
import com.project.autonomous.user.dto.request.UserModifyPutReq;
import com.project.autonomous.user.dto.request.UserRegisterPostReq;
import com.project.autonomous.user.dto.respose.MyProfileRes;
import com.project.autonomous.user.dto.respose.UserProfileRes;
import com.project.autonomous.user.dto.respose.UserRegisterRes;
import com.project.autonomous.user.entity.User;
import com.project.autonomous.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class UserController {


    @Autowired
    UserService userService;


    @GetMapping("/sign-up/{email}")
    public ResponseEntity<Boolean> emailCheck(@PathVariable("email") String email){
        System.out.println("이메일 중복 검사");

        boolean check = userService.emailCheck(email);
        if(check){
            return ResponseEntity.status(200).body(true);//사용가능한 이메일
        }

        return ResponseEntity.status(400).body(false);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<Boolean> signUp(@RequestBody UserRegisterPostReq registerInfo){
        System.out.println("회원가입");

        if(!userService.emailCheck(registerInfo.getEmail())){
            System.out.println("중복된 이메일");
            return ResponseEntity.status(400).body(false);
        }
        User user = userService.createUser(registerInfo);

        return ResponseEntity.status(200).body(true);
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

    @PostMapping("/check-password")
    public ResponseEntity<Boolean> checkPassword(@RequestBody PasswordReq checkInfo){
        System.out.println("비밀번호 확인");
        String password = checkInfo.getPassword();



        return ResponseEntity.status(400).body(Boolean.FALSE);
    }

    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody LoginReq loginInfo){
        System.out.println("로그인");
        String userEmail = loginInfo.getEmail();
        String password = loginInfo.getPassword();

        User user = userService.getUser(userEmail);
        if(user == null){
            //회원 정보 없음
            return ResponseEntity.status(400).body(Boolean.FALSE);
        }
        if(user.getPassword().equals(password)){
            return ResponseEntity.status(200).body(Boolean.TRUE);
        }
        return ResponseEntity.status(400).body(Boolean.FALSE);
=======
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    @PostMapping("/sign-up")
    @ApiOperation(value = "프로젝트 생성", notes = "<strong>받은 정보로프로젝트를 생성</strong>한다.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "정보 조회"),
        @ApiResponse(code = 404, message = "MEMBER_NOT_FOUND"),
    })
    public String hello(){
        return "hello!";
>>>>>>> 50a2b1ba14286ef47887be3dca4daffc9b9e54b7
    }
}
