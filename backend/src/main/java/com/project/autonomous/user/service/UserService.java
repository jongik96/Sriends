package com.project.autonomous.user.service;


import com.project.autonomous.user.dto.request.CheckPasswordReq;
import com.project.autonomous.user.dto.request.InterestReq;
import com.project.autonomous.user.dto.request.UserModifyReq;
import com.project.autonomous.user.dto.response.MyInfoRes;
import com.project.autonomous.user.dto.response.MyProfileRes;
import com.project.autonomous.user.dto.response.UserProfileRes;
import com.project.autonomous.user.dto.response.UserTeamListRes;
import com.project.autonomous.user.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface UserService {

    boolean checkPassword(CheckPasswordReq checkPasswordReq);

    void changePassword(CheckPasswordReq checkPasswordReq);

    MyInfoRes modifyUser(UserModifyReq modifyInfo);

    Slice<UserTeamListRes> getMyTeams(Pageable pageable);

    User deleteUser(Long userId);

    MyProfileRes getMyProfile();

    UserProfileRes getUserProfile(Long userId);

    User getUser(String userEmail);

    void updateInterest(InterestReq interestReq);
}
