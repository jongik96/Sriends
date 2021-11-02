package com.project.autonomous.user.service;

import com.project.autonomous.jwt.dto.TokenDto;
import com.project.autonomous.jwt.dto.TokenRequestDto;
import com.project.autonomous.user.dto.request.LoginReq;
import com.project.autonomous.user.dto.request.UserRegisterReq;

public interface AuthService {

    void signup(UserRegisterReq userRegisterReq);

    boolean checkEmail(String email);

    TokenDto login(LoginReq loginReq);

    TokenDto reissue(TokenRequestDto tokenRequestDto);

}
