package com.koi151.flasheat.entity;

import com.koi151.flasheat.entity.keys.KeyOrderItem;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "order_items")
public class OrderItems {

    @EmbeddedId
    KeyOrderItem keyOrderItem;

    @ManyToOne
    @JoinColumn(name = "food_id", updatable = false, insertable = false)
    private Food food;

    @ManyToOne
    @JoinColumn(name = "order_id", updatable = false, insertable = false)
    private Orders orders;

}
