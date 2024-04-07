package com.koi151.flasheat.service.imp;

import com.koi151.flasheat.dto.UserDTO;
import com.koi151.flasheat.entity.payload.request.SignUpRequest;

import java.util.List;

public interface LoginServiceImp {
    List<UserDTO> getAllUser();
    boolean checkLogin(String username, String password);
    boolean checkSignup(SignUpRequest signUpRequest);
}
