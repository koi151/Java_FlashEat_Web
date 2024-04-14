package com.koi151.flasheat.service;

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
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService implements CategoryServiceImp {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<CategoryDTO> getCategoryHomePage() {
        PageRequest pageRequest = PageRequest.of(0, 2, Sort.by("id"));
        Page<FoodCategories> categoryList = categoryRepository.findAll(pageRequest);
        List<CategoryDTO> categoryDTOList = new ArrayList<>();

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

        return categoryDTOList;
    }
}
