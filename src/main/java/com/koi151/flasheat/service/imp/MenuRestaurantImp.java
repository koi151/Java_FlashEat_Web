package com.koi151.flasheat.service.imp;

import org.springframework.web.multipart.MultipartFile;

public interface MenuRestaurantImp {
    boolean insertMenu(
            String title,
            MultipartFile file,
            double price,
            String timeShip,
            int cateId,
            boolean isFreeShip
    );
}
