package com.koi151.flasheat.repository;

import com.koi151.flasheat.entity.FoodCategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<FoodCategories, Integer> {
}
