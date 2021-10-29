package com.project.autonomous.team.entity;

import com.project.autonomous.common.entity.BaseEntity;
import com.project.autonomous.user.entity.UserTeam;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.sql.Date;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

@Entity
@Getter
@Setter
public class Team extends BaseEntity {
    Long leader_id;
    String picture_id;
    String name;
    Date create_date;
    int member_count;
    int max_count;
    String description;
    BitSet recruitment_state;
    BitSet is_active;
    BitSet membership_fee;
    BitSet public_state;
    String city;
    Long sport_category;


    @OneToMany(mappedBy = "team")
    private List<UserTeam> userTeamList = new ArrayList<>();
}
