package com.koi151.flasheat.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity(name = "food")
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @Column(name = "title")
    public String title;

    @Column(name = "image")
    public String image;

    @Column(name = "time_ship")
    public String timeShip;

    @Column(name = "price")
    public double price;

    @Column(name = "is_freeship")
    public boolean isFreeship;

    @ManyToOne
    @JoinColumn(name = "cate_id")
    private FoodCategories foodCategories;

    @OneToMany(mappedBy = "food")
    private Set<RatingFood> ratingFoodList;

    @OneToMany(mappedBy = "food")
    private Set<OrderItems> orderItemsList;

    public boolean isFreeship() {
        return isFreeship;
    }

    public void setFreeship(boolean freeship) {
        isFreeship = freeship;
    }

    public FoodCategories getFoodCategories() {
        return foodCategories;
    }

    public void setFoodCategories(FoodCategories foodCategories) {
        this.foodCategories = foodCategories;
    }

    public Set<OrderItems> getOrderItemsList() {
        return orderItemsList;
    }

    public void setOrderItemsList(Set<OrderItems> orderItemsList) {
        this.orderItemsList = orderItemsList;
    }

    public Set<OrderItems> getOrderItemList() {
        return orderItemsList;
    }

    public void setOrderItemList(Set<OrderItems> orderItemsList) {
        this.orderItemsList = orderItemsList;
    }

    public Set<RatingFood> getRatingFoodList() {
        return ratingFoodList;
    }

    public void setRatingFoodList(Set<RatingFood> ratingFoodList) {
        this.ratingFoodList = ratingFoodList;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public FoodCategories getFoodCategory() {
        return foodCategories;
    }

    public void setFoodCategory(FoodCategories foodCategories) {
        this.foodCategories = foodCategories;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTimeShip() {
        return timeShip;
    }

    public void setTimeShip(String timeShip) {
        this.timeShip = timeShip;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
