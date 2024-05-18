package com.koi151.flasheat.service;

import com.koi151.flasheat.dto.CategoryDTO;
import com.koi151.flasheat.dto.MenuDTO;
import com.koi151.flasheat.dto.RestaurantHomeDTO;
import com.koi151.flasheat.entity.Food;
import com.koi151.flasheat.entity.MenuRestaurant;
import com.koi151.flasheat.entity.RatingRestaurant;
import com.koi151.flasheat.entity.Restaurant;
import com.koi151.flasheat.repository.RestaurantRepository;
import com.koi151.flasheat.service.imp.FileServiceImp;
import com.koi151.flasheat.service.imp.RestaurantServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.*;

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

    @Override
    public List<RestaurantHomeDTO> getAllHomeRestaurant() {
        List<RestaurantHomeDTO> restaurantHomeDTOList = new ArrayList<>();
        PageRequest pageRequest = PageRequest.of(0, 6);
        Page<Restaurant> listData = restaurantRepository.findAll(pageRequest);

        for (Restaurant data: listData) {
            RestaurantHomeDTO restaurantHomeDTO = new RestaurantHomeDTO();

            restaurantHomeDTO.setTitle(data.getTitle());
            restaurantHomeDTO.setSubTitle(data.getSubtitle());
            restaurantHomeDTO.setDescription(data.getDescription());
            restaurantHomeDTO.setAddress(data.getAddress());
            restaurantHomeDTO.setFreeShip(data.getIsFreeship());
            restaurantHomeDTO.setImage(data.getImage());
            restaurantHomeDTO.setRatingPoint(calculateAverageRatingPoint(data.getRatingRestaurantSet()));

            restaurantHomeDTOList.add(restaurantHomeDTO);
        }

        return restaurantHomeDTOList;
    }

    @Override
    public RestaurantHomeDTO getDetailRestaurant(int id) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        RestaurantHomeDTO restaurantHomeDTO = new RestaurantHomeDTO();

        if (restaurant.isPresent()) {
            Restaurant restData = restaurant.get();
            List<CategoryDTO> categoryDTOList = new ArrayList<>();

            restaurantHomeDTO.setImage(restData.getImage());
            restaurantHomeDTO.setTitle(restData.getTitle());
            restaurantHomeDTO.setOpenDate(restData.getOpenDate());
            restaurantHomeDTO.setDescription(restData.getDescription());
            restaurantHomeDTO.setAddress(restData.getAddress());
            restaurantHomeDTO.setRatingPoint(calculateAverageRatingPoint(restData.getRatingRestaurantSet()));

            // category
            for (MenuRestaurant menuRestaurant: restData.getMenuRestaurantList()) {
                List<MenuDTO> menuDTOList = new ArrayList<>();
                CategoryDTO categoryDTO = new CategoryDTO();
                categoryDTO.setName(menuRestaurant.getFoodCategory().getName());

                // menu
                for (Food food: menuRestaurant.getFoodCategory().getListFood()) {
                    MenuDTO menuDTO = new MenuDTO();

                    menuDTO.setImage(food.getImage());
                    menuDTO.setTitle(food.getTitle());
                    menuDTO.setFreeShip(food.getIsFreeship());

                    menuDTOList.add(menuDTO);
                }
                categoryDTO.setMenus(menuDTOList);
                categoryDTOList.add(categoryDTO);
            }
            restaurantHomeDTO.setCategoryList(categoryDTOList);
        }
        return restaurantHomeDTO;
    }

    public double calculateAverageRatingPoint(Set<RatingRestaurant> ratingRestaurantSet) {
        double totalPoint = 0.0f;

        for (RatingRestaurant restaurant: ratingRestaurantSet) {
            totalPoint += restaurant.getRatingPoint();
        }

        return totalPoint / ratingRestaurantSet.size();
    }

}
