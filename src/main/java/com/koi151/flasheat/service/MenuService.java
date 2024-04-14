package com.koi151.flasheat.service;

import com.koi151.flasheat.dto.RestaurantHomeDTO;
import com.koi151.flasheat.entity.Food;
import com.koi151.flasheat.entity.FoodCategories;
import com.koi151.flasheat.repository.FoodRepository;
import com.koi151.flasheat.service.imp.FileServiceImp;
import com.koi151.flasheat.service.imp.MenuRestaurantImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.Set;

@Service
public class MenuService implements MenuRestaurantImp {

    @Autowired
    FileServiceImp fileServiceImp;

    @Autowired
    FoodRepository foodRepository;

    @Override
    public boolean insertMenu(String title, MultipartFile file, double price, String timeShip, int cateId, boolean isFreeShip) {
        boolean isInsertSuccess = false;

        try {
            boolean isSaveFileSucceed = fileServiceImp.saveFile(file);

            if (isSaveFileSucceed) {
                Food food = new Food();
                food.setTitle(title);
                food.setPrice(price);
                food.setTimeShip(timeShip);
                food.setFreeship(isFreeShip);

                food.setImage(file.getOriginalFilename());

                FoodCategories foodCategories = new FoodCategories();
                foodCategories.setId(cateId);
                food.setFoodCategory(foodCategories);

                foodRepository.save(food);
                isInsertSuccess = true;

            } else {
                System.out.println("Creating menu process stopped due to save file failed");
            }

        } catch (Exception e) {
            System.out.println("Error occurred while creating menu: " + e.getMessage());
        }

        return isInsertSuccess;
    }
}
