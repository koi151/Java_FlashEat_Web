package com.koi151.flasheat.service.imp;

import com.koi151.flasheat.dto.RestaurantHomeDTO;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

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

    List<RestaurantHomeDTO> getAllHomeRestaurant();

}
