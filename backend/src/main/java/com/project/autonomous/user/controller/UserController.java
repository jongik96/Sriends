package com.project.autonomous.user.controller;


import com.project.autonomous.common.exception.ErrorResponse;
import com.project.autonomous.user.dto.request.CheckPasswordReq;
import com.project.autonomous.user.dto.request.InterestReq;
import com.project.autonomous.user.dto.request.UserModifyReq;
import com.project.autonomous.user.dto.response.MyInfoRes;
import com.project.autonomous.user.dto.response.UserProfileRes;
import com.project.autonomous.user.dto.response.UserTeamListRes;
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
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    private final UserService userService;

    @Autowired
    EmailService emailService;

    @InitBinder
    public void InitBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @PostMapping("/password")
    @Operation(summary = "비밀번호 확인 (비밀번호 수정 페이지에 사용)", description = "<strong>입력 받은 비밀번호</strong>를 사용해 현 사용자의 비밀번호와 비교한다.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "true, false", content = @Content),
        @ApiResponse(responseCode = "404", description = "USER_NOT_FOUND",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    public ResponseEntity<Boolean> checkPassword(
        @Valid @RequestBody CheckPasswordReq checkPasswordReq) {
        return ResponseEntity.ok(userService.checkPassword(checkPasswordReq));
    }

    @PutMapping("/password")
    @Operation(summary = "비밀번호 수정 (비밀번호 수정 페이지에 사용)", description = "<strong>입력 받은 비밀번호</strong>를 사용해 사용자의 비밀번호를 변경한다.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "변경되었습니다.", content = @Content),
        @ApiResponse(responseCode = "404", description = "USER_NOT_FOUND",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    public ResponseEntity<String> changePassword(
        @Valid @RequestBody CheckPasswordReq checkPasswordReq) {
        userService.changePassword(checkPasswordReq);
        return ResponseEntity.ok("변경되었습니다.");
    }

    @PutMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    @Operation(summary = "회원 정보 수정 (회원 정보 수정 페이지에 사용)", description = "<strong>입력 받은 정보</strong>를 사용해 유저 정보를 변경한다.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "변경된 회원 정보 조회",
            content = @Content(schema = @Schema(implementation = MyInfoRes.class))),
        @ApiResponse(responseCode = "400", description = "BAD_REQUEST"),
        @ApiResponse(responseCode = "404", description = "USER_NOT_FOUND\n\nDELETED_USER",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    public ResponseEntity<MyInfoRes> modifyUserInfo(
        @Valid @ModelAttribute UserModifyReq modifyInfo, BindingResult theBindingResult) {
        return ResponseEntity.ok(userService.modifyUser(modifyInfo));
    }

    @GetMapping("/team")
    @Operation(summary = "나의 팀 정보 조회", description = "<strong>페이징</strong>을 사용해 유저의 팀 정보를 조회한다.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "팀 정보 조회",
            content = @Content(schema = @Schema(implementation = UserTeamListRes.class))),
        @ApiResponse(responseCode = "400", description = "BAD_REQUEST"),
        @ApiResponse(responseCode = "404", description = "USER_NOT_FOUND\n\nDELETED_USER",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    public ResponseEntity<Slice<UserTeamListRes>> getMyTeams(@ParameterObject
        @PageableDefault(size = 5) Pageable pageable) {
        return ResponseEntity.ok(userService.getMyTeams(pageable));
    }

    @GetMapping("/me")
    @Operation(summary = "나의 개인 정보 조회", description = "유저의 개인 정보를 조회한다.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "팀 정보 조회",
            content = @Content(schema = @Schema(implementation = MyInfoRes.class))),
        @ApiResponse(responseCode = "400", description = "BAD_REQUEST"),
        @ApiResponse(responseCode = "404", description = "USER_NOT_FOUND\n\nDELETED_USER",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    public ResponseEntity<MyInfoRes> getMyInfo() {
        return ResponseEntity.ok(userService.getMyInfo());
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") Long userId) {
        System.out.println("회원 탈퇴");

        //userId가 토큰이랑 일치하면 회원탈퇴 진행
        if (true) {
            User user = userService.deleteUser(userId);
        }

        return;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserProfileRes> getUserProfile(@PathVariable("userId") Long userId) {
        //토큰으로 userid 찾는거 추가
        System.out.println("다른 회원정보 조회");

        UserProfileRes userRes = userService.getUserProfile(userId);

        return ResponseEntity.status(200).body(userRes);

    }


    @PostMapping("/interest")
    public void interest(@RequestBody InterestReq interestReq) {
        System.out.println("흥미있는 종목 선택");

//        userService.interest(interestReq);
    }


}
