package exception;

import java.util.*;
import entity.Orders1;
import entity.Product;
import entity.User;

public class OrderProcessor implements IOrderManagementRepository{
	
	
	private HashMap<Integer,User>usertable=new HashMap<>();
	private HashMap<Integer,Product>producttable=new HashMap<>();
    private List<Orders1> ordertable = new ArrayList<>();

	  @Override
	    public void createOrder(User user, List<Product> products) throws Exception {
	        // Check if the user is already present in the database
	        if (!usertable.containsKey(user.getUserId())) {
	            throw new Exception("User not found. Cannot create order.");
	        }

	        // Assume generating orderId
	        int orderId = ordertable.size() + 1;

	        // Create an order and store it in the database
	        Orders1 order = new Orders1(orderId, user, products);
	        ordertable.add(order);
	    }

	    @Override
	    public void cancelOrder(int userId, int orderId) throws Exception, Exception {
	        // Check if the user is present in the database
	        if (!usertable.containsKey(userId)) {
	            throw new Exception("User not found. Cannot cancel order.");
	        }

	        // Check if the order is present in the database
	        boolean orderFound = false;
	        for (Orders1 order : ordertable) {
	            if (order.getOrderId() == orderId && order.getUser().getUserId() == userId) {
	                orderFound = true;
	                ordertable.remove(order);
	                break;
	            }
	        }

	        if (!orderFound) {
	            throw new Exception("Order not found. Cannot cancel order.");
	        }
	    }

	    @Override
	    public void createProduct(User adminUser, Product product) throws Exception {
	        // Check if the user is an admin and is present in the database
	        if (!usertable.containsKey(adminUser.getUserId()) || !"Admin".equals(adminUser.getRole())) {
	            throw new Exception("Admin user not found. Cannot create product.");
	        }

	        // Assume generating productId
	        int productId = producttable.size() + 1;

	        // Create a product and store it in the database
	        product.setProductId(productId);
	        producttable.put(productId, product);
	    }

	    @Override
	    public void createUser(User user) {
	        // Create a user and store it in the database
	        usertable.put(user.getUserId(), user);
	    }

	    @Override
	    public List<Product> getAllProducts() {
	        // Return all products from the database
	        return new ArrayList<>(producttable.values());
	    }

	    @Override
	    public List<Product> getOrderByUser(User user) throws Exception {
	        // Check if the user is present in the database
	        if (!usertable.containsKey(user.getUserId())) {
	            throw new Exception("User not found. Cannot retrieve orders.");
	        }

	        // Retrieve all products ordered by the specific user
	        List<Product> orderedProducts = new ArrayList<>();
	        for (Orders1 order : ordertable) {
	            if (order.getUser().getUserId() == user.getUserId()) {
	                orderedProducts.addAll(order.getProducts());
	            }
	        }

	        return orderedProducts;
	    }
	    public static void main (String[] args) {
	    	System.out.println("success");
	    }
	}


