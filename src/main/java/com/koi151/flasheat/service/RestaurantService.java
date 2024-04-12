package com.koi151.flasheat.service;

import com.koi151.flasheat.entity.Restaurant;
import com.koi151.flasheat.repository.RestaurantRepository;
import com.koi151.flasheat.service.imp.FileServiceImp;
import com.koi151.flasheat.service.imp.RestaurantServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;


@Service
public class RestaurantService implements RestaurantServiceImp {

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    FileServiceImp fileServiceImp;


    @Override
    public boolean insertRestaurant(MultipartFile file, String resTitle, String resSubTitle, String resDesc, String resAddress, boolean resIsFreeShip, String resOpenDate) {
        boolean isInsertSuccess = false;

        try {
            boolean isSaveFileSuccess = fileServiceImp.saveFile(file);

            if (isSaveFileSuccess) {
                Restaurant restaurant = new Restaurant();
                restaurant.setTitle(resTitle);
                restaurant.setSubtitle(resSubTitle);
                restaurant.setDescription(resDesc);
                restaurant.setAddress(resAddress);
                restaurant.setIsFreeship(resIsFreeShip);
                restaurant.setImage(file.getOriginalFilename());

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
                Date openDate = simpleDateFormat.parse(resOpenDate);
                restaurant.setOpenDate(openDate);

                restaurantRepository.save(restaurant);
                isInsertSuccess = true;
            }

        } catch (Exception e) {
            System.out.println("Error occurred while inserting restaurant: " + e.getMessage());
        }

        return isInsertSuccess;
    }
}
