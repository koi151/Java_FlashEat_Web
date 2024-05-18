package com.koi151.flasheat.service;

import com.koi151.flasheat.dto.OrderDTO;
import com.koi151.flasheat.entity.*;
import com.koi151.flasheat.entity.keys.KeyOrderItem;
import com.koi151.flasheat.entity.payload.request.OrderRequest;
import com.koi151.flasheat.repository.OrderItemRepository;
import com.koi151.flasheat.repository.OrderRepository;
import com.koi151.flasheat.service.imp.OrderServiceImp;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional // neu luu orderItemsList that bai => huy order => ko gay loi
public class OrderService implements OrderServiceImp {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Override
    public List<OrderDTO> getAllOrder() {
        PageRequest pageRequest = PageRequest.of(0, 4, Sort.by("id"));
        Page<Orders> orderList = orderRepository.findAll(pageRequest);

        List<OrderDTO> orderDTOList = new ArrayList<>();

        for (Orders order: orderList) {
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setId(order.getId());
            orderDTO.setRestId(order.getRestaurant().getId());
            orderDTO.setUserId(order.getUsers().getId());
            orderDTO.setCreatedDate(order.getCreatedDate());

            orderDTOList.add(orderDTO);
        }

        return orderDTOList;
    }

    @Override
    public boolean insertOrder(OrderRequest orderRequest) {
        try {
            Users user = new Users();
            user.setId(orderRequest.getUserId());

            Restaurant restaurant = new Restaurant();
            restaurant.setId(orderRequest.getRestId());

            Orders orders = new Orders();
            orders.setRestaurant(restaurant);
            orders.setUsers(user);

            orderRepository.save(orders);

            List<OrderItems> orderItemsList = new ArrayList<>();
            for (int idFood: orderRequest.getFoodIds()) {
                Food food = new Food();
                food.setId(idFood);

                OrderItems orderItems = new OrderItems();
                KeyOrderItem keyOrderItem = new KeyOrderItem(orders.getId(), food.getId());
                orderItems.setKeyOrderItem(keyOrderItem);
                orderItems.setFood(food);
                orderItems.setOrders(orders);

                orderItemsList.add(orderItems);
            }

            orderItemRepository.saveAll(orderItemsList);

            return true;

        } catch (Exception e) {
            System.out.println("Error occurred while inserting order: " + e.getMessage());
            return false;
        }
    }
}
