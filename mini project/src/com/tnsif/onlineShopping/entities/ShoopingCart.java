package com.tnsif.onlineShopping.entities;

import java.util.HashMap;
import java.util.Map;

public class ShoopingCart {
	private Map<Product, Integer> items;
	
	public ShoopingCart() {
		
		this.items = new HashMap<>();
	}
	//getters nd setters
	public Map<Product, Integer> getItems() {
		return items;
	}
	
	public void addItem(Product product,int quantity) {
		items.put(product , quantity);
	}
	public void removeItem(Product product) {
		items.remove(product);
	}
	@Override
	public String toString() {
		return "ShoopingCart [items=" + items + "]";
	}
}
