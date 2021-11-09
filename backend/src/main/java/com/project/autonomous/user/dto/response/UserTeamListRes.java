package com.project.autonomous.user.dto.response;

import com.project.autonomous.team.entity.Team;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Schema(title = "팀 간단 정보", description = "팀의 간단한 정보를 조회하는 Response Dto Class")
@Getter
@Builder
@AllArgsConstructor
public class UserTeamListRes {

    @Schema(description = "팀 id", example = "3")
    private long id;

    @Schema(description = "팀명", example = "부천 농구 스렌즈")
    private String name;

    @Schema(description = "유저 사진", example = "https://matchhere.s3.ap-northeast-2.amazonaws.com/%EA%%B8_EC%A7%84.JPG")
    private String pictureUrl;

    public static UserTeamListRes from(Team team) {
        return UserTeamListRes.builder()
            .id(team.getId())
            .pictureUrl(team.getPicture() == null ? null : team.getPicture().getImageUrl())
            .name(team.getName())
            .build();
    }

}
