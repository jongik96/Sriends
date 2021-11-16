package com.project.autonomous.team.dto.response;

import com.project.autonomous.team.entity.Team;
import com.project.autonomous.user.dto.response.UserSimpleInfoRes;
import com.project.autonomous.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class TeamInfoRes {
    long id;
    String name;
    LocalDateTime createDate;
    UserSimpleInfoRes leader;
//    long leaderId;
//    String leaderName;
    String pictureDownloadUrl;
    int memberCount;
    int maxCount;
    String description;
    Boolean recruitmentState;
    Boolean membershipFee;
    String city;
    String sportCategory;

    public static TeamInfoRes from(Team team, User leader, String sportCategory){
        return TeamInfoRes.builder()
                .id(team.getId())
                .name(team.getName())
                .leader(UserSimpleInfoRes.from(leader))
                .city(team.getCity())
                .createDate(team.getCreateDate())
                .pictureDownloadUrl(team.getPicture() == null ? null : team.getPicture().getImageUrl())
                .memberCount(team.getMemberCount())
                .maxCount(team.getMaxCount())
                .description(team.getDescription())
                .recruitmentState(team.isRecruitmentState())
                .membershipFee(team.isMembershipFee())
                .sportCategory(sportCategory)
                .build();

    }

}
