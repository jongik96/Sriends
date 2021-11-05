package com.project.autonomous.user.dto.response;

import com.project.autonomous.user.entity.UserInterest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Schema(title = "유저의 관심 종목 조회", description = "유저가 관심 정보를 조회하기 위한 Response Dto Class")
@Getter
@Builder
@AllArgsConstructor
public class UserInterestRes {

    @Schema(description = "종목명", example = "농구")
    private String interest;

    public static UserInterestRes from(UserInterest userInterest) {
        return UserInterestRes.builder()
            .interest(userInterest.getUserInterestId().getSportCategory().getName())
            .build();
    }
}
