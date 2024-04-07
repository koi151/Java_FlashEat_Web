package com.koi151.flasheat.service;

import com.koi151.flasheat.dto.UserDTO;
import com.koi151.flasheat.entity.Roles;
import com.koi151.flasheat.entity.Users;
import com.koi151.flasheat.entity.payload.request.SignUpRequest;
import com.koi151.flasheat.repository.UserRepository;
import com.koi151.flasheat.service.imp.LoginServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoginService implements LoginServiceImp {

    @Autowired
    UserRepository userRepository;
    @Override
    public List<UserDTO> getAllUser() {

        List<Users> userList = userRepository.findAll();
        List<UserDTO> userDTOList = new ArrayList<>();

        for(Users user: userList) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setUserName(user.getUserName());
            userDTO.setFullName(user.getFullName());
            userDTO.setPassword(user.getPassword());

            userDTOList.add((userDTO));
        }

        return userDTOList;
    }

    @Override
    public boolean checkLogin(String username, String password) {
        List<Users> userList = userRepository.findByUserNameAndPassword(username, password);
        return !userList.isEmpty();
    }

    @Override
    public boolean checkSignup(SignUpRequest signUpRequest) {
        Roles roles = new Roles();
        roles.setId(signUpRequest.getRoleId());

        System.out.println("signUpRequest:" + signUpRequest.getRoleId());
        System.out.println("signUpRequest fn:" + signUpRequest.getFullName());

        Users user = new Users();
        user.setFullName(signUpRequest.getFullName());
        user.setPassword(signUpRequest.getPassword());
        user.setEmail(signUpRequest.getEmail());
        user.setRoles(roles);

        try {
            userRepository.save(user);
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return false;
        }

        return true;
    }
}

