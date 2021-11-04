package com.project.autonomous.user.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.util.ArrayList;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MyProfileRes {

    @Schema(description = "유저 id", example = "3")
    private Long id;

    @Schema(description = "유저 email", example = "qjawlsqjacks@naver.com")
    private String email;

    @Schema(description = "유저명", example = "3")
    private String name;

    @Schema(description = "생년월일", example = "1995-09-06")
    private LocalDate birth;

    @Schema(description = "성별", example = "남")
    private String gender;

    @Schema(description = "전화번호", example = "01028732329")
    private String phone;

    @Schema(description = "지역", example = "서울")
    private String city;

    @Schema(description = "유저 사진", example = "경로 아직")
    private String pictureUrl;

    @Schema(description = "유저가 속한 팀 리스트", example = "[{\"id\":\"4\", \"pictureUrl\":\"경로 아직\", \"name\":\"BStory\"}, {\"id\":\"6\", \"pictureUrl\":\"경로 아직\", \"name\":\"조코피\"}]")
    private ArrayList<UserTeamListRes> teams;


}
