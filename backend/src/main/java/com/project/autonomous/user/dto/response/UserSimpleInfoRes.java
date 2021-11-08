package com.project.autonomous.user.dto.response;

import com.project.autonomous.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Schema(title = "다른 유저 정보 간단 조회", description = "다른 유저의 간단한 정보만 조회하기 위한 Response Dto Class")
@Getter
@Builder
@AllArgsConstructor
public class UserSimpleInfoRes {

    @Schema(description = "유저 id", example = "3")
    private Long id;

    @Schema(description = "유저명", example = "3")
    private String name;

    @Schema(description = "지역", example = "서울")
    private String city;

    @Schema(description = "유저 사진", example = "https://matchhere.s3.ap-northeast-2.amazonaws.com/%EA%%B8_EC%A7%84.JPG")
    private String pictureUrl;

    public static UserSimpleInfoRes from(User user) {
        return UserSimpleInfoRes.builder()
            .id(user.getId())
            .name(user.getName())
            .city(user.getCity().toString())
            .pictureUrl(user.getPicture() == null ? null : user.getPicture().getImageUrl())
            .build();
    }

}
