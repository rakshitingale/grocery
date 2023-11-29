package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.GroceryItem;

public interface GroceryItemRepository extends JpaRepository<GroceryItem, Long> {
    // Additional custom queries if needed
}