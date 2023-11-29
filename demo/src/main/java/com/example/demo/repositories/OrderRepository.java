package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Order;
import com.example.demo.model.OrderItems;

public interface OrderRepository extends JpaRepository<Order, Long> {
	List<Order> findByUsername(String username);
}
