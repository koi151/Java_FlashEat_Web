package com.koi151.flasheat.service.imp;

import com.koi151.flasheat.dto.OrderDTO;
import com.koi151.flasheat.entity.payload.request.OrderRequest;

import java.util.List;

public interface OrderServiceImp {

    List<OrderDTO> getAllOrder();

    boolean insertOrder(OrderRequest orderRequest);
}
