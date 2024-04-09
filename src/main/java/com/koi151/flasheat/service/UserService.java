package com.koi151.flasheat.service;

import com.koi151.flasheat.dto.UserDTO;
import com.koi151.flasheat.entity.Users;
import com.koi151.flasheat.repository.UserRepository;
import com.koi151.flasheat.service.imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserServiceImp {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<UserDTO> getAllUser() {
        List<Users> userList = userRepository.findAll();
        List<UserDTO> userDTOList = new ArrayList<>();

        for (Users user: userList) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setFullName(user.getFullName());
            userDTO.setPassword(user.getPassword());
            userDTO.setUserName(user.getUserName());

            userDTOList.add((userDTO));
        }

        return userDTOList;
    }
}
