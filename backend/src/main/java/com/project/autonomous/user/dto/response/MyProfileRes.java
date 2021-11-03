package com.project.autonomous.user.dto.response;

import com.project.autonomous.picture.entity.Picture;
import com.project.autonomous.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MyProfileRes {

    @Schema(description = "유저 id", example = "3")
    Long id;

    @Schema(description = "유저 email", example = "qjawlsqjacks@naver.com")
    String email;

    @Schema(description = "유저명", example = "3")
    String name;

    @Schema(description = "생년월일", example = "1995-09-06")
    LocalDate birth;

    @Schema(description = "성별", example = "남")
    String gender;

    @Schema(description = "전화번호", example = "01028732329")
    String phone;

    @Schema(description = "지역", example = "서울")
    String city;

    @Schema(description = "유저 사진", example = "경로 아직")
    String pictureUrl;

    @Schema(description = "유저가 속한 팀 리스트", example = "[{\"id\":\"4\", \"pictureUrl\":\"경로 아직\", \"name\":\"BStory\"}, {\"id\":\"6\", \"pictureUrl\":\"경로 아직\", \"name\":\"조코피\"}]")
    ArrayList<UserTeamListRes> teams;

    public void setPictureUrl(Picture picture){
        if(picture == null) {
            this.pictureUrl = null;
            return;
        }
        this.pictureUrl = picture.getImageUrl();
    }

    public static MyProfileRes of(User user, ArrayList<UserTeamListRes> teams){
        return MyProfileRes.builder()
            .id(user.getId())
            .email(user.getEmail())
            .name(user.getName())
            .birth(user.getBirth())
            .gender(user.getGender())
            .phone(user.getPhone())
            .city(user.getCity().toString())
//            .pictureUrl(user.getPicture_id())
            .teams(teams)
            .build();
    }

    public static MyProfileRes from(User user){
        return MyProfileRes.builder()
            .id(user.getId())
            .email(user.getEmail())
            .name(user.getName())
            .birth(user.getBirth())
            .gender(user.getGender())
            .phone(user.getPhone())
            .city(user.getCity().toString())
//            .pictureUrl(user.getPicture_id())
            .build();
    }

}
