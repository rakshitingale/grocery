package com.example.demo.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;

@Entity
@Table(name = "user_order") 
public class Order {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   @Transient
    private List<OrderItems> orderItems;

    private String username;
    
   private long quantity;

   
	@Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	 public long getQuantity() {
			return quantity;
		    }

			public void setQuantity(long quantity) {
				this.quantity = quantity;
			}



	public List<OrderItems> getOrderItems() {
				return orderItems;
			}

			public void setOrderItems(List<OrderItems> orderItems) {
				this.orderItems = orderItems;
			}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
    
 
}