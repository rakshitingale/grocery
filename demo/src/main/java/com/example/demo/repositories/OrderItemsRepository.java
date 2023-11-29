package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.OrderItems;

public interface OrderItemsRepository extends JpaRepository<OrderItems, Long> {
    // Additional custom queries if needed
//	@Query("SELECT oi FROM order_items oi WHERE oi.order_id = :orderId")
    List<OrderItems> findByOrderid(Long order_id);
	//List<OrderItems> findByOrder_id(Long orderId);
}
