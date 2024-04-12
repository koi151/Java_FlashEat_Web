package com.koi151.flasheat.controller;

import com.koi151.flasheat.entity.payload.ResponseData;
import com.koi151.flasheat.service.imp.FileServiceImp;
import com.koi151.flasheat.service.imp.RestaurantServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    @Autowired
    FileServiceImp fileServiceImp;

    @Autowired
    RestaurantServiceImp restaurantServiceImp;

    @PostMapping("/create")
    private ResponseEntity<?> createRestaurant (
            @RequestParam MultipartFile file,
            @RequestParam String resTitle,
            @RequestParam String resSubTitle,
            @RequestParam String resDesc,
            @RequestParam String resAddress,
            @RequestParam boolean resIsFreeShip,
            @RequestParam String resOpenDate
    ) {

        ResponseData responseData = new ResponseData();

        boolean isSuccess = restaurantServiceImp.insertRestaurant(
            file, resTitle, resSubTitle, resDesc,
            resAddress, resIsFreeShip, resOpenDate
        );

        responseData.setData(isSuccess);

        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<?> getRestaurantFiles(@PathVariable String filename) {
        Resource resource = fileServiceImp.loadFile(filename);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + resource.getFilename() + "\"").body(resource);
    }
}
