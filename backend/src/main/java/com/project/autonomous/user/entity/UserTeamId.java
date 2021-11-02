package com.project.autonomous.user.entity;

import java.io.Serializable;

public class UserTeamId implements Serializable {

    private long user;
    private long team;

    public UserTeamId(){

    }
    public UserTeamId(long user, long team){
        this.user = user;
        this.team = team;
    }
}
