package com.koi151.flasheat.entity;

import com.koi151.flasheat.entity.keys.KeyMenuRestaurant;
import jakarta.persistence.*;

import java.util.Date;

@Entity(name = "menu_restaurant")
public class MenuRestaurant {

    @EmbeddedId
    KeyMenuRestaurant keys;

    @ManyToOne
    @JoinColumn(name = "cate_id", insertable = false, updatable = false)
    private FoodCategories foodCategories;

    @ManyToOne
    @JoinColumn(name = "rest_id", insertable = false, updatable = false)
    private Restaurant restaurant;

    @Column(name = "created_date")
    private Date createdDate;

    public FoodCategories getFoodCategory() {
        return foodCategories;
    }

    public void setFoodCategory(FoodCategories foodCategories) {
        this.foodCategories = foodCategories;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public KeyMenuRestaurant getKeys() {
        return keys;
    }

    public void setKeys(KeyMenuRestaurant keys) {
        this.keys = keys;
    }

    public FoodCategories getCategory() {
        return foodCategories;
    }

    public void setCategory(FoodCategories foodCategories) {
        this.foodCategories = foodCategories;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
