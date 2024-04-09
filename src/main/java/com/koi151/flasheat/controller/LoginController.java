package com.koi151.flasheat.controller;

import com.koi151.flasheat.entity.payload.ResponseData;
import com.koi151.flasheat.entity.payload.request.SignUpRequest;
import com.koi151.flasheat.service.imp.LoginServiceImp;
import com.koi151.flasheat.utils.JwtUtilsHelper;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    LoginServiceImp loginServiceImp;

    @Autowired
    JwtUtilsHelper jwtUtilsHelper;

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestParam String username, @RequestParam String password){
        ResponseData responseData = new ResponseData();

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
