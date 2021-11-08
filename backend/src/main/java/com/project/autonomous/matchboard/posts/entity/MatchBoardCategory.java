package com.project.autonomous.matchboard.posts.entity;

import com.project.autonomous.common.exception.CustomException;
import com.project.autonomous.common.exception.ErrorCode;

public enum MatchBoardCategory {
    매칭, 용병;

//    public static MatchBoardCategory from(String s) {
//        return MatchBoardCategory.valueOf(s);
//    }

    public static MatchBoardCategory from(String s) {
        MatchBoardCategory matchBoardCategory;
        if(s.equals("매칭")) {
            matchBoardCategory = MatchBoardCategory.매칭;
        } else if(s.equals("용병")) {
            matchBoardCategory = MatchBoardCategory.용병;
        } else {
            throw new CustomException(ErrorCode.BOARD_CATEGORY_NOT_FOUND);
        }
        return matchBoardCategory;
    }

}
