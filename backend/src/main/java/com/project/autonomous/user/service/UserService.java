package com.project.autonomous.user.service;

<<<<<<< HEAD
import com.project.autonomous.user.dto.request.PasswordReq;
import com.project.autonomous.user.dto.request.UserModifyPutReq;
import com.project.autonomous.user.dto.request.UserRegisterPostReq;
import com.project.autonomous.user.dto.respose.MyProfileRes;
import com.project.autonomous.user.dto.respose.UserProfileRes;
import com.project.autonomous.user.entity.User;

public interface UserService {
    User createUser(UserRegisterPostReq registerInfo);

    Boolean emailCheck(String email);

    User modifyUser(Long userId, UserModifyPutReq modifyInfo);

    User deleteUser(Long userId);

    MyProfileRes getMyProfile(Long userId);

    UserProfileRes getUserProfile(Long userId);

    User getUser(String userEmail);
=======
public interface UserService {
>>>>>>> 50a2b1ba14286ef47887be3dca4daffc9b9e54b7

}
