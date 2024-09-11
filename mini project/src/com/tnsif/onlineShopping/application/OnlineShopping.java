package com.tnsif.onlineShopping.application;

import java.util.List;
import java.util.Scanner;

import com.tnsif.onlineShopping.entities.Admin;
import com.tnsif.onlineShopping.entities.Customer;
import com.tnsif.onlineShopping.entities.Order;
import com.tnsif.onlineShopping.entities.Product;
import com.tnsif.onlineShopping.entities.ProductQuantityPair;
import com.tnsif.onlineShopping.services.AdminService;
import com.tnsif.onlineShopping.services.CustomerService;
import com.tnsif.onlineShopping.services.OrderService;
import com.tnsif.onlineShopping.services.ProductService;

public class OnlineShopping {
	private static ProductService productService=new ProductService();
	private static CustomerService customerService=new CustomerService();
	private static OrderService orderService=new OrderService();
	private static AdminService adminService=new AdminService();

	public static void main(String[] args) {
		
		Scanner scanner= new Scanner(System.in);
		while(true) {
			System.out.println("1. Admin Menu");
			System.out.println("2. Customer Menu");
			System.out.println("3. Exit");
			System.out.println("Choose an option:");
			int choice= scanner.nextInt();
			
			
			switch (choice) {
			case 1: //Admin module
				int adminChoice;
				do {
					System.out.println("\n Admin Menu:");
					System.out.println("1. Add Product");
					System.out.println("2. Remove Product");
					System.out.println("3. View Products");
					System.out.println("4. Create Admin");
					System.out.println("5. View Admins");
					System.out.println("6. Update order Status");
					System.out.println("7. View orders");
					System.out.println("8. Return");
					System.out.println("CHOOSE AN OPTION:-");
					adminChoice=scanner.nextInt();
					switch(adminChoice) {
					case 1:
						addProduct(scanner);
						break;
					case 2:
						removeProduct(scanner);
						break;
					case 3:
						viewProducts();
						break;
					case 4:
						createAdmin(scanner);
						break;
					case 5:
						viewAdmins();
						break;
					case 6:
						updateOrderStatus(scanner);
						break;
					case 7:
						viewOrders();
						break;
					case 8:
						System.out.println("Exiting Admin.....");
						break;
					default:
						System.out.println("Invalid choice!! Please try again");
						
					}
					
				}
				while(adminChoice!=8);
			case 2:
				int customerChoice;//customer module
				do {
					System.out.println("\n Customer Menu:");
					System.out.println("1. Create Customer");
					System.out.println("2. View Customer");
					System.out.println("3. Place order");
					System.out.println("4. View order");
					System.out.println("5. View products");
					System.out.println("6. Return");
					System.out.println("Choose an option:-");
					customerChoice=scanner.nextInt();
					switch (customerChoice) {
					case 1:
						createCustomer(scanner);
						break;
					case 2:
						viewCustomers();
						break;
					case 3:
						viewOrders();
						break;
					case 4:
						viewProducts();
						break;
					case 5:
						System.out.println("Exiting customer menu.....");
						break;
					default :
						System.out.println("Invalid choice!! Please try again....");
					}
					
				}
				while(customerChoice !=6);
				break;
			case 3:
				System.out.println("Exiting......");
				scanner.close();
				return;
			default:
				System.out.println("Invalid choice!! please try again");
					
			}
		}
		
		

	}


	private static void addProduct(Scanner scanner) {
		System.out.println("Enter Product ID:");
		int productId=scanner.nextInt();
		System.out.println("Enter Product Name");
		String name=scanner.next();
		System.out.println("Enter product price");
		double price=scanner.nextDouble();
		System.out.println("Enter Stock Quantity:");
		int stockQuantity=scanner.nextInt();
		
		Product product = new Product(productId, name, price, stockQuantity);
		productService.addProduct(product);
		System.out.println("Product added successfully!!!!!");
	}
	
	private static void removeProduct(Scanner scanner) {
		System.out.println("Enter product ID:");
		int productId= scanner.nextInt();
		
		productService.removeProduct(productId);
		System.out.println("Product removed successfullyyyyyyy.......!!!!");
	}
	
	private static void viewProducts() {
		List<Product>products=productService.getProducts();
		if(products.isEmpty()) {
			System.out.println("no products available!!");
		}
		else {
		System.out.println("Available Products:");
		for(Product product :products) {
			System.out.println("Product ID: " +product.getProductId());
			System.out.println("Product name:" +product.getName());
			System.out.println("Product price:"+product.getPrice());
			System.out.println("Stock quantity:"+product.getStockQuantity());
			System.out.println();
		}
		
		}
	}
	private static void createAdmin(Scanner scanner) {
		System.out.println("Enter User ID: ");
		int userId=scanner.nextInt();
		System.out.println("Enter user UserName");
		String userName=scanner.next();
		System.out.println("Enter email: ");
		String email=scanner.next();
		
		Admin admin=new Admin(userId,userName,email);
		adminService.addAdmin(admin);
	}
	
	private static void viewAdmins() {
		List<Admin>admins=adminService.getAdmins();
		if(admins.isEmpty()) {
			System.out.println("no admins found");
		}
		else {
		System.out.println("ADMINS:");
		for (Admin admin:admins) {
			System.out.println("User ID:"+admin.getUserId());
			System.out.println("User name:"+admin.getUserName());
			System.out.println("email:"+admin.getEmail());
		}
		}
		
	}
	private static void updateOrderStatus(Scanner scanner) {
		System.out.println("Enter the order ID");
		int orderId=scanner.nextInt();
		scanner.nextLine();
		System.out.println("Enter new status/n Pending:/nCompleted:/nCancelled:/nDelvered:");
		
		String status=scanner.nextLine().trim();
		
		Order order=orderService.getOrder(orderId);
		
		if(order==null) {
			System.out.println("order not found");
			return;
		}
		
		if ("Completed".equalsIgnoreCase(status)) {
			if("Pending".equalsIgnoreCase(order.getStatus())) {
				updateStockForOrder(order);
			}
			else {
				System.out.println("Order status cannot be updated to 'Completed' from current status.");
				return;
			}
		}
		else if ("Cancelled".equalsIgnoreCase(status)) {
			if ("Completed".equalsIgnoreCase(order.getStatus()) || "Pending".equalsIgnoreCase(order.getStatus())) {
				restoreStockForOrder(order);
			}
			else {
				System.out.println("Order status cannot be updated to 'Cancelled' from current status.");
	            return;

			}
		}
		else if ("Delivered".equalsIgnoreCase(status)) {
	        if ("Completed".equalsIgnoreCase(order.getStatus())) {
		}
	        else {
	        	System.out.println("Order status cannot be updated to 'Delivered' from current status.");
	            return;

	        }
			
		
	}
		else {
			System.out.println("Invalid status provided");
			return;
		}
		
		order.setStatus(status);
		System.out.println("order status updated successfullyy.......");
	}
	
	
	private static void updateStockForOrder(Order order) {
		for (ProductQuantityPair pair : order.getProducts()) {
			Product product=pair.getProduct();
			int quantity=pair.getQuantity();
			if(product.getStockQuantity()>=quantity) {
				product.setStockQuantity(product.getStockQuantity()-quantity);
			}
			else {
				System.out.println("insufficient stock for product:"+product.getName());
				return;
			}
		}
	}
	
	private static void restoreStockForOrder(Order order) {
		for (ProductQuantityPair pair : order.getProducts()) {
			Product product = pair.getProduct();
	        int quantity = pair.getQuantity();
	        product.setStockQuantity(product.getStockQuantity() + quantity);

		}
	}
	private static void viewOrders() {
		List<Order>orders=orderService.getOrders();
		if(orders.isEmpty()) {
			System.out.println("no orders available!!");
		}
		else {
			System.out.println("ORDERS:");
			for(Order order:orders) {
				System.out.println("Order ID:"+order.getOrderId());
				System.out.println("Customer:"+order.getCustomer());
				System.out.println("Products:"+order.getProducts());
				System.out.println("Status"+order.getStatus());
				
			}
		}
	}
	
	
	private static void createCustomer(Scanner scanner) {
		System.out.println("Enter user ID:");
		int userId=scanner.nextInt();
	    System.out.println("Enter customer username: ");
	    String userName = scanner.nextLine();
	    System.out.println("Enter customer email: ");
	    String email = scanner.nextLine();
	    System.out.println("Enter customer address: ");
	    String address = scanner.nextLine();
	    
	    Customer customer = new Customer(userId, userName, email);
	    
	    
	    customerService.addCustomer(customer);
	    
	    System.out.println("customer created sucessfull.......");

	}
	
	private static void viewCustomers() {
		List<Customer> customers = customerService.getCustomers();
	    
	    	System.out.println("CUSTOMERS:");
	    	for(Customer customer :customers) {
	    		System.out.println("UserId:"+customer.getUserId());
	    		System.out.println("name:"+customer.getUserName());
	    		System.out.println("email:"+customer.getEmail());
	    		System.out.println("Address:"+customer.getAddress());
	    	}
	    }
	
	
	
	
	
}
