package com.koi151.flasheat.service;

import com.koi151.flasheat.dto.RestaurantHomeDTO;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

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

    public double calculateAverageRatingPoint(Set<RatingRestaurant> ratingRestaurantSet) {
        double totalPoint = 0.0f;

        for (RatingRestaurant restaurant: ratingRestaurantSet) {
            totalPoint += restaurant.getRatingPoint();
        }

        return totalPoint / ratingRestaurantSet.size();
    }

}
