package com.koi151.flasheat.controller;

import com.koi151.flasheat.service.imp.UserServiceImp;
import com.koi151.flasheat.utils.JwtUtilsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServiceImp userServiceImp;


    @GetMapping("")
    public ResponseEntity<?> getAllUser() {

        return new ResponseEntity<>(userServiceImp.getAllUser(), HttpStatus.OK);

    }

}
