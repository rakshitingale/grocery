package com.example.demo.services;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.GroceryItem;
import com.example.demo.model.Order;
import com.example.demo.model.OrderItems;
import com.example.demo.repositories.OrderItemsRepository;
import com.example.demo.repositories.OrderRepository;
@Service
public class UserService {
	
		 @Autowired
		 private OrderRepository orderRepository;
	    @Autowired
	    private GroceryItemService groceryItemService;
	    @Autowired
	    private OrderItemsRepository orderItemsRepository;
	 public String orderGroceryItems(List<Long> itemIds, List<Integer> quantities) {
	        if (itemIds.size() != quantities.size()) {
	            return "Invalid order request";
	        }

	        // Check if all items are available and have sufficient quantity in stock
	        for (int i = 0; i < itemIds.size(); i++) {
	            Long itemId = itemIds.get(i);
	            int quantity = quantities.get(i);

	            GroceryItem groceryItem = groceryItemService.getGroceryItemById(itemId);

	            if (groceryItem == null || groceryItem.getQuantityInStock() < quantity) {
	                return "Invalid order request";
	            }
	        }

	        // Deduct quantities from stock and create the order
	        Order order = new Order();

	        // Get the current authenticated user
	        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        if (authentication == null || !authentication.isAuthenticated()) {
	            return "User not authenticated";
	        }

	        String username = authentication.getName();

	        for (int i = 0; i < itemIds.size(); i++) {
	            Long itemId = itemIds.get(i);
	            int quantity = quantities.get(i);

	            GroceryItem groceryItem = groceryItemService.getGroceryItemById(itemId);
	            groceryItem.setQuantityInStock(groceryItem.getQuantityInStock() - quantity);
	            groceryItemService.updateGroceryItem(itemId, groceryItem);

	      
	            order.setQuantity(order.getQuantity() + quantity);

	        }
	        order.setUsername(username);
	        order.setOrderDate(new Date(0));
	        Order savedOrder=orderRepository.save(order);
	        Long order_id=savedOrder.getId();
	        OrderItems orderItems=null;
	        for (int i = 0; i < itemIds.size(); i++) {
	        	orderItems= new OrderItems();
	            Long itemId = itemIds.get(i);
	            int quantity = quantities.get(i);
	            
	            orderItems.setOrder_id(order_id);
	            orderItems.setItem_id(itemId);
	            orderItems.setQuantity(quantity);
	            orderItemsRepository.save(orderItems);
	            
	        }
	        return "Order placed successfully";
	    }
}
