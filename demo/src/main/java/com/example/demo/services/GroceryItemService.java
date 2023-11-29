package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.model.GroceryItem;
import com.example.demo.model.Order;
import com.example.demo.model.OrderItems;
import com.example.demo.repositories.GroceryItemRepository;
import com.example.demo.repositories.OrderItemsRepository;
import com.example.demo.repositories.OrderRepository;

@Service
public class GroceryItemService {

    @Autowired
    private GroceryItemRepository groceryItemRepository;

    @Autowired
    private OrderItemsRepository orderItemsRepository;
    
    @Autowired
    OrderRepository orderRepository;


    public List<GroceryItem> getAllGroceryItems() {
        return groceryItemRepository.findAll();
    }

    public GroceryItem getGroceryItemById(Long id) {
        return groceryItemRepository.findById(id).orElse(null);
    }

    public GroceryItem addGroceryItem(GroceryItem groceryItem) {
        return groceryItemRepository.save(groceryItem);
    }
    public ResponseEntity<List<OrderItems>> getOrderItemsById(Long id) {
    	List<OrderItems> orderItems=orderItemsRepository.findByOrderid(id);
    	List<OrderItems> groceryItems=new ArrayList<>();
    	for(OrderItems order:orderItems) {
    		Long itemId=order.getItem_id();
    		GroceryItem groceryItem = getGroceryItemById(itemId);
    		order.setItem(groceryItem.getName());
    		groceryItems.add(order);

    	}
      

        if (groceryItems != null) {
            return new ResponseEntity<>(groceryItems, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    public ResponseEntity<List<Order>> getOrdersForUser(String name) {
    	List<Order> orders=orderRepository.findByUsername(name);
    	List<Order> orderResponse=new ArrayList<>();
    	for(Order order:orders) {
    		Long orderid=order.getId();
    		List<OrderItems> orderItems=orderItemsRepository.findByOrderid(orderid);
    		List<OrderItems> groceryItems=new ArrayList<>();
    		for(OrderItems oi:orderItems) {
        		Long itemId=oi.getItem_id();
        		GroceryItem groceryItem = getGroceryItemById(itemId);
        		oi.setItem(groceryItem.getName());
        		groceryItems.add(oi);

        	}
    		order.setOrderItems(groceryItems);
    		orderResponse.add(order);
    	}
    	
      

        if (orderResponse != null) {
            return new ResponseEntity<>(orderResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public void updateGroceryItem(Long id, GroceryItem updatedItem) {
        GroceryItem existingItem = groceryItemRepository.findById(id).orElse(null);

        if (existingItem != null) {
            existingItem.setName(updatedItem.getName());
            existingItem.setPrice(updatedItem.getPrice());
            existingItem.setQuantityInStock(updatedItem.getQuantityInStock());
            groceryItemRepository.save(existingItem);
        }
    }

    public void removeGroceryItem(Long id) {
        groceryItemRepository.deleteById(id);
    }
}
