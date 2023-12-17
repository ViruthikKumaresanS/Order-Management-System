package exception;

import java.util.*;

import entity.Product;
import entity.User;

public interface IOrderManagementRepository {

    // Create order for a user with a list of products
    void createOrder(User user, List<Product> products) throws Exception;

    // Cancel an order by userId and orderId
    void cancelOrder(int userId, int orderId) throws Exception;

    // Create a product if the user is an admin
    void createProduct(User adminUser, Product product) throws Exception;

    // Create a user and store in the database
    void createUser(User user);

    // Get all products from the database
    List<Product> getAllProducts();

    // Get all orders placed by a specific user
    List<Product> getOrderByUser(User user) throws Exception;
    
    
}