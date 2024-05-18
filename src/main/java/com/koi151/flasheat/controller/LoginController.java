package com.koi151.flasheat.controller;

import com.koi151.flasheat.entity.payload.ResponseData;
import com.koi151.flasheat.entity.payload.request.SignUpRequest;
import com.koi151.flasheat.service.imp.LoginServiceImp;
import com.koi151.flasheat.utils.JwtUtilsHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    LoginServiceImp loginServiceImp;

    @Autowired
    JwtUtilsHelper jwtUtilsHelper;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestParam String username, @RequestParam String password){
        ResponseData responseData = new ResponseData();

        logger.trace("Checking trace log");
        logger.debug("Checking debug log");
        logger.info("Checking info log");
        logger.warn("Checking warn log");
        logger.error("Checking error log");


        if(loginServiceImp.checkLogin(username, password)) {
            responseData.setDesc("Success");
            responseData.setData(true);
            String token = jwtUtilsHelper.generateToken(username);
            responseData.setData(token);
        } else {
            responseData.setDesc("Login failed");
            responseData.setStatus(400);
            responseData.setData("");
            responseData.setSuccess(false);
        }

        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignUpRequest signUpRequest) {
        ResponseData responseData = new ResponseData();

        if(loginServiceImp.checkSignup(signUpRequest)) {
            responseData.setDesc("Success");
            responseData.setData(true);
        } else {
            responseData.setDesc("Sign up failed");
            responseData.setStatus(400);
            responseData.setData(false);
        }

        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
}
