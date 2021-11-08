package com.project.autonomous.matchboard.posts.dto.response;

import static com.project.autonomous.common.exception.ValidatorMessage.DATE_FORMAT;
import static com.project.autonomous.common.exception.ValidatorMessage.EMPTY_MESSAGE;

import com.project.autonomous.matchboard.posts.entity.MatchBoardPost;
import com.project.autonomous.user.dto.response.UserTeamListRes;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

@Schema(title = "매치 게시글 상세 정보")
@Getter
@Builder
@AllArgsConstructor
public class MatchBoardPostInfo {

    @Schema(description = "작성자 id", example = "3")
    private long writerId;

    @Schema(description = "작성자명", example = "박범진")
    private String writerName;

    @Schema(description = "작성일자", example = "2021-09-06 06:57:37.667537")
    private LocalDateTime createAt;

    @Schema(description = "모집 상태", example = "false(모집 중)")
    private boolean recruited;

    @Schema(description = "종목명", example = "농구")
    private String sportCategory;

    @Schema(description = "게시판 유형", example = "매칭 or 용병")
    private String matchBoardCategory;

    @Schema(description = "경기 날짜", example = "1995-09-06")
    @DateTimeFormat(pattern = DATE_FORMAT)
    private LocalDate playingTime;

    @Schema(description = "지역", example = "서울특별시")
    @NotBlank(message = EMPTY_MESSAGE)
    private String city;

    @Schema(description = "경기 장소", example = "인동초등학교")
    private String place;

    @Schema(description = "모집 인원", example = "4 ~ 10명")
    private String recruitmentCount;

    @Schema(description = "팀 정보", example = "{\"id\": 4, \"name\":\"대명농구회\", \"pictureUrl\":\"경로 미정\"}")
    private UserTeamListRes team;

    @Schema(description = "내용", example = "5 : 5 농구 인원구합니다.")
    private String content;

    public static MatchBoardPostInfo from(MatchBoardPost matchBoardPost) {
        return MatchBoardPostInfo.builder()
            .writerId(matchBoardPost.getUser().getId())
            .writerName(matchBoardPost.getUser().getName())
            .createAt(matchBoardPost.getCreatedAt())
            .recruited(matchBoardPost.isRecruited())
            .sportCategory(matchBoardPost.getSportCategory().getName())
            .matchBoardCategory(matchBoardPost.getMatchBoardCategory().toString())
            .playingTime(matchBoardPost.getPlayingTime())
            .city(matchBoardPost.getCity().toString())
            .place(matchBoardPost.getPlace())
            .recruitmentCount(matchBoardPost.getRecruitmentCount())
            .team(matchBoardPost.getTeam() == null ? null : UserTeamListRes.from(
                matchBoardPost.getTeam()))
            .content(matchBoardPost.getContent())
            .build();
    }
}
