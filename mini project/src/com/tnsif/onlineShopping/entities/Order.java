package com.tnsif.onlineShopping.entities;

import java.util.List;

public class Order {
	private int orderId;
    private Customer customer;
    private List<ProductQuantityPair> products;
    private String status;
    
    
	public Order(int orderId, Customer customer, List<ProductQuantityPair> products, String status) {
		super();
		this.orderId = orderId;
		this.customer = customer;
		this.products = products;
		this.status = status;
	}
	private Order getOrderId(int orderId) {
		// TODO Auto-generated method stub
		return null;
	}


	public int getOrderId() {
		return orderId;
	}


	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public List<ProductQuantityPair> getProducts() {
		return products;
	}


	public void setProducts(List<ProductQuantityPair> products) {
		this.products = products;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}
    
	
	
    

}
