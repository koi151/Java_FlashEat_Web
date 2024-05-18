package com.koi151.flasheat.repository;

import com.koi151.flasheat.entity.OrderItems;
import com.koi151.flasheat.entity.keys.KeyOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItems, KeyOrderItem> {
}
