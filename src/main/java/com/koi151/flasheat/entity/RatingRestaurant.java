package com.koi151.flasheat.entity;

import jakarta.persistence.*;

@Entity(name = "rating_restaurant")
public class RatingRestaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "content")
    private String content;

    @Column(name = "rating_point")
    private int ratingPoint;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;

    @ManyToOne
    @JoinColumn(name = "rest_id")
    private Restaurant restaurant;
}
