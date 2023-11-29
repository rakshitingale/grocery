package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.GroceryItem;
import com.example.demo.model.OrderItems;
import com.example.demo.repositories.OrderItemsRepository;
import com.example.demo.repositories.OrderRepository;
import com.example.demo.services.GroceryItemService;
import com.example.demo.model.Order;

@RestController
@RequestMapping("/api/admin")
public class GroceryController {

    @Autowired
    private GroceryItemService groceryItemService;
    
    @Autowired
    private OrderItemsRepository orderItemsRepository;
    
    @Autowired
    OrderRepository orderRepository;

    @GetMapping("/items")
    public List<GroceryItem> getAllGroceryItems() {
        return groceryItemService.getAllGroceryItems();
    }

    @GetMapping("/items/{id}")
    public ResponseEntity<GroceryItem> getGroceryItemById(@PathVariable Long id) {
    	
        GroceryItem groceryItem = groceryItemService.getGroceryItemById(id);

        if (groceryItem != null) {
            return new ResponseEntity<>(groceryItem, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/order/{id}")
    public ResponseEntity<List<OrderItems>> getOrderItemsById(@PathVariable Long id) {
    	return groceryItemService.getOrderItemsById(id);
    }
    @GetMapping("/order/user/{name}")
    public ResponseEntity<List<Order>> getOrdersForUser(@PathVariable String name) {
    	return groceryItemService.getOrdersForUser(name);
    }

    @PostMapping("/items")
    public ResponseEntity<GroceryItem> addGroceryItem(@RequestBody GroceryItem groceryItem) {
        GroceryItem addedItem = groceryItemService.addGroceryItem(groceryItem);
        return new ResponseEntity<>(addedItem, HttpStatus.CREATED);
    }

    @PutMapping("/items/{id}")
    public ResponseEntity<Void> updateGroceryItem(@PathVariable Long id, @RequestBody GroceryItem updatedItem) {
        groceryItemService.updateGroceryItem(id, updatedItem);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/items/{id}")
    public ResponseEntity<Void> removeGroceryItem(@PathVariable Long id) {
        groceryItemService.removeGroceryItem(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
