package com.koi151.flasheat.service.imp;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

public interface RestaurantServiceImp {
    boolean insertRestaurant(
            MultipartFile file,
            String resTitle,
            String resSubTitle,
            String resDesc,
            String resAddress,
            boolean resIsFreeShip,
            String resOpenDate
    );
}
