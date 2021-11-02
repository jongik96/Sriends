package com.project.autonomous.user.service;


import com.project.autonomous.user.dto.request.UserModifyPutReq;
import com.project.autonomous.user.dto.request.UserRegisterPostReq;
import com.project.autonomous.user.dto.response.MyProfileRes;
import com.project.autonomous.user.dto.response.UserProfileRes;
import com.project.autonomous.user.entity.User;

public interface UserService {
    User createUser(UserRegisterPostReq registerInfo);

    Boolean emailCheck(String email);

    User modifyUser(Long userId, UserModifyPutReq modifyInfo);

    User deleteUser(Long userId);

    MyProfileRes getMyProfile();

    UserProfileRes getUserProfile(Long userId);

    User getUser(String userEmail);

//    void sendEmail(String email);
}
