package dao;

import entity.Clothing;

import entity.Electronics;
import entity.Orders1;
import entity.Product;
import entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public  class ProductServiceImp1 implements ProductService {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/order_management_system";
    private static final String USER = "root";
    private static final String PASSWORD = "virtik1234";
    private final Scanner scanner;

    public ProductServiceImp1(Scanner scanner) {
        this.scanner = scanner;
    }
    @Override
    public boolean createProduct(Product product) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String sql = "INSERT INTO Product (productId, productName, description, price, quantityInStock, type) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, product.getProductId());
                statement.setString(2, product.getProductName());
                statement.setString(3, product.getDescription());
                statement.setDouble(4, product.getPrice());
                statement.setInt(5, product.getQuantityInStock());
                statement.setString(6, product.getType());

                int rowsInserted = statement.executeUpdate();
                return rowsInserted > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Product getProduct(int productId) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String sql = "SELECT * FROM Product WHERE productId = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, productId);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return new Product(
                                resultSet.getInt("productId"),
                                resultSet.getString("productName"),
                                resultSet.getString("description"),
                                resultSet.getDouble("price"),
                                resultSet.getInt("quantityInStock"),
                                resultSet.getString("type")
                        );
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        Collection<Product> products = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String sql = "SELECT * FROM Product";
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(sql)) {
                    while (resultSet.next()) {
                        Product product = new Product(
                                resultSet.getInt("productId"),
                                resultSet.getString("productName"),
                                resultSet.getString("description"),
                                resultSet.getDouble("price"),
                                resultSet.getInt("quantityInStock"),
                                resultSet.getString("type")
                        );
                        products.add(product);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (List<Product>) products;
    }

    @Override
    public boolean updateProduct(Product product) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String sql = "UPDATE Product SET productName = ?, description = ?, price = ?, quantityInStock = ?, type = ? WHERE productId = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, product.getProductName());
                statement.setString(2, product.getDescription());
                statement.setDouble(3, product.getPrice());
                statement.setInt(4, product.getQuantityInStock());
                statement.setString(5, product.getType());
                statement.setInt(6, product.getProductId());

                int rowsUpdated = statement.executeUpdate();
                return rowsUpdated > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteProduct(int productId) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String sql = "DELETE FROM Product WHERE productId = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, productId);

                int rowsDeleted = statement.executeUpdate();
                if (rowsDeleted > 0) {
                    System.out.println("Product deleted successfully.");
                    return true;
                } else {
                    System.out.println("Product not found or deletion failed.");
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean addProduct1(Product product) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String sql = "INSERT INTO Product (productId, productName, description, price, quantityInStock, type) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, product.getProductId());
                statement.setString(2, product.getProductName());
                statement.setString(3, product.getDescription());
                statement.setDouble(4, product.getPrice());
                statement.setInt(5, product.getQuantityInStock());
                statement.setString(6, product.getType());

                int rowsInserted = statement.executeUpdate();
                return rowsInserted > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
	@Override
	public boolean addProduct(Product product) {
		return false;
		// TODO Auto-generated method stub
		
	}
	@Override
	public Product getProductById(int productId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void addElectronics(Electronics electronics) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Electronics getElectronicsById(int productId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Electronics> getAllElectronics() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void updateElectronics(Electronics electronics) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deleteElectronics(int productId) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void addClothing(Clothing clothing) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Clothing getClothingById(int productId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Clothing> getAllClothing() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void updateClothing(Clothing clothing) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deleteClothing(int productId) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public User getUserById(int userId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean updateUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean deleteUser(int userId) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void addOrder(Orders1 order) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Orders1 getOrderById(User user) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Orders1> getAllOrders() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void updateOrder(Orders1 order) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deleteOrder(int orderId) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Iterable<Product> getOrderByUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean productExists(int productId) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean userExists(int userId) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean createUser(User User) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public User getUser(int UserId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Collection<User> getAllUser() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean cancelOrder(int userId, int orderId) {
		// TODO Auto-generated method stub
		return false;
	}
	
}

