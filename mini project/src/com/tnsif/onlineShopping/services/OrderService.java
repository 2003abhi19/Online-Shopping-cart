package com.tnsif.onlineShopping.services;

import java.util.ArrayList;
import java.util.List;

import com.tnsif.onlineShopping.entities.Order;
import com.tnsif.onlineShopping.entities.Product;
import com.tnsif.onlineShopping.entities.ProductQuantityPair;

public class OrderService {
	private List<Order> orderList=new ArrayList<>();
	
	public OrderService() {
		
	}
	
	public void placeOrder(Order order) {
		orderList.add(order);
		
	}
	private Order getOrderId(int orderId) {
		// TODO Auto-generated method stub
		return null;
	}
	public void updateOrderStatus(int orderId, String status) {
		Order order = getOrderId(orderId);
		
		if(order!= null) {
			if("Completed".equalsIgnoreCase(status)&&"Pending".equalsIgnoreCase(order.getStatus())) {
				for (ProductQuantityPair pair:order.getProducts()) {
					Product product=pair.getProduct();
					int quantity=pair.getQuantity();
					
					if (product.getStockQuantity()>=quantity) {
						product.setStockQuantity(product.getStockQuantity()-quantity);
					}
					else {
						System.out.println("Insufficient stock for product:"+product.getName());
						return;
					}
				}
			}
			else if("Cancelled".equalsIgnoreCase(status)) {
				if("Completed".equalsIgnoreCase(order.getStatus())|| "Pending".equalsIgnoreCase(order.getStatus())) {
					for (ProductQuantityPair pair:order.getProducts()) {
						Product product=pair.getProduct();
						int quantity=pair.getQuantity();
						product.setStockQuantity(product.getStockQuantity()+quantity);
						
						}
					}
				}
			else if("Delivered".equalsIgnoreCase(status)&& "Completed".equalsIgnoreCase(order.getStatus())) {
				//only update the status to delivered, no stock adjustement needed
			}
			order.setStatus(status);
		}
		else
			System.out.println("Invalid order");
	}
	
	public Order getOrder(int orderId) {
		return orderList.stream().filter(order->order.getOrderId()==orderId).findFirst().orElse(null);
		
		
	}
	
	public List<Order>getOrders(){
		return orderList;
		
	}


}
