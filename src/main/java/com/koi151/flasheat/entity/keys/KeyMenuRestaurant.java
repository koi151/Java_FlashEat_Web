package com.koi151.flasheat.entity.keys;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class KeyMenuRestaurant implements Serializable {

    @Column(name = "cate_id")
    private int cateId;

    @Column(name = "rest_id")
    private int restId;

    public KeyMenuRestaurant() {}

    public KeyMenuRestaurant(int cateId, int restId) {
        this.cateId = cateId;
        this.restId = restId;
    }

    public int getCateId() {
        return cateId;
    }

    public void setCateId(int cateId) {
        this.cateId = cateId;
    }

    public int getRestId() {
        return restId;
    }

    public void setRestId(int restId) {
        this.restId = restId;
    }
}
