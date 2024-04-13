package com.koi151.flasheat.controller;

import com.koi151.flasheat.entity.FoodCategories;
import com.koi151.flasheat.entity.payload.ResponseData;
import com.koi151.flasheat.service.imp.MenuRestaurantImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    MenuRestaurantImp menuRestaurantImp;

    @PostMapping("/create")
    public ResponseEntity<?> createMenu (
            @RequestParam String title,
            @RequestParam MultipartFile file,
            @RequestParam double price,
            @RequestParam String timeShip,
            @RequestParam int cateId,
            @RequestParam boolean isFreeShip
    ) {
        ResponseData responseData = new ResponseData();

        boolean isCreatedSuccessful = menuRestaurantImp.insertMenu
                (title, file, price, timeShip, cateId, isFreeShip);

        responseData.setData((isCreatedSuccessful));

        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

}
