package com.koi151.flasheat.controller;

import com.koi151.flasheat.entity.payload.ResponseData;
import com.koi151.flasheat.entity.payload.request.OrderRequest;
import com.koi151.flasheat.service.imp.OrderServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    OrderServiceImp orderServiceImp;

    @GetMapping("/getHomeOrders")
    private ResponseEntity<?> getAllOrder () {
        ResponseData responseData = new ResponseData();
        responseData.setData(orderServiceImp.getAllOrder());

        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @PostMapping("/insertOrder")
    private ResponseEntity<?> insertOrder (@RequestBody OrderRequest orderRequest) {
        ResponseData responseData = new ResponseData();

        try {
            responseData.setData(orderServiceImp.insertOrder(orderRequest));

            responseData.setStatus(200);
            responseData.setDesc("Success");

            return new ResponseEntity<>(responseData, HttpStatus.OK);
        } catch (Exception e) {
            responseData.setStatus(400);
            responseData.setDesc("Failed");
            return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);

        }
    }
}
