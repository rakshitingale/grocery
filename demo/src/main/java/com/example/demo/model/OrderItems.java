package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "order_items")
public class OrderItems {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(name = "order_id", nullable = false)
	Long orderid;
	@Column(name = "item_id", nullable = false)
	Long itemid;
	int quantity;
	@Transient
	String item;
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public Long getOrder_id() {
		return orderid;
	}
	public void setOrder_id(Long order_id) {
		this.orderid = order_id;
	}
	public Long getItem_id() {
		return itemid;
	}
	public void setItem_id(Long item_id) {
		this.itemid = item_id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
