package com.koi151.flasheat.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.koi151.flasheat.dto.CategoryDTO;
import com.koi151.flasheat.dto.MenuDTO;
import com.koi151.flasheat.entity.Food;
import com.koi151.flasheat.entity.FoodCategories;
import com.koi151.flasheat.repository.CategoryRepository;
import com.koi151.flasheat.service.imp.CategoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService implements CategoryServiceImp {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    RedisTemplate redisTemplate;

    private Gson gson = new Gson();

//    @Cacheable("categoryHome")
    @Override
    public List<CategoryDTO> getCategoryHomePage() {

        String dataRedis = (String) redisTemplate.opsForValue().get("category");
        List<CategoryDTO> categoryDTOList = new ArrayList<>();

        if (dataRedis == null) {
            System.out.println("Still dont have data");

            PageRequest pageRequest = PageRequest.of(0, 2, Sort.by("id"));
            Page<FoodCategories> categoryList = categoryRepository.findAll(pageRequest);

            for (FoodCategories category: categoryList) {
                CategoryDTO categoryDTO = new CategoryDTO();

                categoryDTO.setName(category.getName());

                List<MenuDTO> menuDTOList = new ArrayList<>();

                for (Food foodData: category.getListFood()) {
                    MenuDTO menuDTO = new MenuDTO();

                    menuDTO.setTitle(foodData.getTitle());
                    menuDTO.setFreeShip(foodData.getIsFreeship());
                    menuDTO.setImage(foodData.getImage());

                    menuDTOList.add((menuDTO));
                }

                categoryDTO.setMenus(menuDTOList);

                categoryDTOList.add((categoryDTO));
            }

            String jsonData = gson.toJson(categoryDTOList);
            redisTemplate.opsForValue().set("category", jsonData);

        } else {
            System.out.println("Data existed");
            Type listType = new TypeToken<List<CategoryDTO>>(){}.getType();
            categoryDTOList = gson.fromJson(dataRedis, listType);
        }


        return categoryDTOList;
    }
}
