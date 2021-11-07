package com.project.autonomous.matchboard.posts.entity;

public enum MatchBoardCategory {
    매칭, 용병;

    public static MatchBoardCategory from(String s) {
        return MatchBoardCategory.valueOf(s);
    }

}
