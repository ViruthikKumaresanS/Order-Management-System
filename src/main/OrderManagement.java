package main;

import java.util.Scanner;


import entity.Product;
import entity.User;

import java.util.Scanner;

import dao.*;
import entity.*;

public class OrderManagement {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        ProductServiceImp1 productService = new ProductServiceImp1(scanner);
        ProductServiceImp2 userService = new ProductServiceImp2(scanner);

        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Create User");
            System.out.println("2. Create Product");
            System.out.println("3. Get All Products");
            System.out.println("4. Get Orders by User");
            System.out.println("5. Cancel Orders ");
            System.out.println("6. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    createUser(userService);
                    break;
                case 2:
                    createProduct(productService);
                    break;
                case 3:
                    getAllProducts(productService);
                    break;
                case 4:
                    getOrdersByUser(userService);
                    break;
                case 5:
                	 cancelOrder(userService);
                    break;
                case 6:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void createUser(ProductServiceImp2 userService) {
        System.out.println("Enter User ID:");
        int userId = scanner.nextInt();
        scanner.nextLine();  // Consume the newline character
        System.out.println("Enter User Name:");
        String userName = scanner.nextLine();
        System.out.println("Enter Password:");
        String password = scanner.nextLine();

        User newUser = new User(userId, userName, password, "User");
        if (userService.createUser(newUser)) {
            System.out.println("User created successfully.");
        } else {
            System.out.println("User ID already exists. Enter a new User ID.");
        }
    }

    private static void createProduct(ProductServiceImp1 productService) {
        System.out.println("Enter Product ID:");
        int productId = scanner.nextInt();
        scanner.nextLine();  // Consume the newline character
        System.out.println("Enter Product Name:");
        String productName = scanner.nextLine();
        System.out.println("Enter Description:");
        String description = scanner.nextLine();
        System.out.println("Enter Price:");
        double price = scanner.nextDouble();
        System.out.println("Enter Quantity in Stock:");
        int quantityInStock = scanner.nextInt();
        scanner.nextLine();  // Consume the newline character
        System.out.println("Enter Type:");
        String type = scanner.nextLine();

        Product newProduct = new Product(productId, productName, description, price, quantityInStock, type);
        if (productService.createProduct(newProduct)) {
            System.out.println("Product created successfully.");
        } else {
            System.out.println("Product ID already exists. Enter a new Product ID.");
        }
    }

    private static void getAllProducts(ProductServiceImp1 productService) {
        System.out.println("All Products:");
        productService.getAllProducts().forEach(System.out::println);
    }

    private static void cancelOrder(ProductServiceImp2 productService) {
        System.out.println("Enter User ID to cancel order:");
        int userId = scanner.nextInt();
        System.out.println("Enter Order ID to cancel:");
        int orderId = scanner.nextInt();
        try {
        boolean canceled = productService.cancelOrder(userId, orderId);

        if (canceled) {
            System.out.println("Order canceled successfully.");
        } else {
            System.out.println("Order not found or cancellation failed.");
        }
    } catch (Exception e) {
        e.printStackTrace();
        System.out.println("An unexpected error occurred.");
    }
}
    
    
    
    
    private static void getOrdersByUser(ProductServiceImp2 userService) {
    	
    	
            System.out.println("Enter User ID to get orders:");
            int userId = scanner.nextInt();

            Iterable<Product> orderedProducts = userService.getOrdersByUser(userId);
            if (orderedProducts != null) {
                System.out.println("Products ordered by user:");
                orderedProducts.forEach(System.out::println);
            } else {
                System.out.println("No orders found for the specified user.");
            }
        }
  }
