package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.GroceryItem;
import com.example.demo.services.GroceryItemService;
import com.example.demo.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private GroceryItemService groceryItemService;
    
    @Autowired
    private UserService userService;

    
    @GetMapping("/groceries")
    public List<GroceryItem> getAllGroceries() {
        return groceryItemService.getAllGroceryItems();
    }

    @PostMapping("/order")
    public String orderGroceryItems(@RequestParam List<Long> itemIds, @RequestParam List<Integer> quantities) {
     return userService.orderGroceryItems(itemIds, quantities);
    }
}