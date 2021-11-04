package com.project.autonomous.user.dto.response;

import com.project.autonomous.team.entity.Team;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserTeamListRes {

    @Schema(description = "팀 id", example = "3")
    private long id;

    @Schema(description = "유저 사진", example = "경로 아직")
    private String pictureUrl;

    @Schema(description = "팀명", example = "내일은 풋살왕")
    private String name;

    public static UserTeamListRes from(Team team) {
        return UserTeamListRes.builder()
            .id(team.getId())
            .pictureUrl(team.getPicture() == null ? null : team.getPicture().getImageUrl())
            .name(team.getName())
            .build();
    }

}
