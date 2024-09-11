package com.tnsif.onlineShopping.entities;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User{
	private String address;
	private ShoopingCart shoopingCart;
	private List<Order> orders;
	
	
	public Customer(int userId, String userName, String email, String address, ShoopingCart shoopingCart) {
		super(userId, userName, email);
		this.address = address;
		this.shoopingCart = shoopingCart;
		this.orders =new ArrayList<>();
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public ShoopingCart getShoopingCart() {
		return shoopingCart;
	}


	public void setShoopingCart(ShoopingCart shoopingCart) {
		this.shoopingCart = shoopingCart;
	}


	public List<Order> getOrders() {
		return orders;
	}


	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}


	public Customer(int userId, String userName, String email) {
		super(userId, userName, email);
	}


	
	
	
}
