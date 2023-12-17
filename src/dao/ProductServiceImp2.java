package dao;

import entity.Clothing;

import entity.Electronics;
import entity.Orders1;
import entity.Product;
import entity.User;
import exception.OrderNotFoundException;
import exception.UserNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public  class ProductServiceImp2 implements ProductService {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/order_management_system";
    private static final String USER = "root";
    private static final String PASSWORD = "virtik1234";
    private final Scanner scanner;

    public ProductServiceImp2(Scanner scanner) {
        this.scanner = scanner;
    }
    @Override
    public boolean createUser(User user) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String sql = "INSERT INTO User (userId, userName, password, role) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, user.getUserId());
                statement.setString(2, user.getUsername());
                statement.setString(3, user.getPassword());
                statement.setString(4, user.getRole());

                int rowsInserted = statement.executeUpdate();
                return rowsInserted > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public User getUser(int userId) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String sql = "SELECT * FROM User WHERE userId = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, userId);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return new User(
                                resultSet.getInt("userId"),
                                resultSet.getString("userName"),
                                resultSet.getString("password"),
                                resultSet.getString("role")
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
    public Collection<User> getAllUser() {
        Collection<User> users = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String sql = "SELECT * FROM User";
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(sql)) {
                    while (resultSet.next()) {
                        User user = new User(
                                resultSet.getInt("userId"),
                                resultSet.getString("userName"),
                                resultSet.getString("password"),
                                resultSet.getString("role")
                        );
                        users.add(user);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public boolean updateUser(User user) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String sql = "UPDATE User SET userName = ?, password = ?, role = ? WHERE userId = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, user.getUsername());
                statement.setString(2, user.getPassword());
                statement.setString(3, user.getRole());
                statement.setInt(4, user.getUserId());

                int rowsUpdated = statement.executeUpdate();
                return rowsUpdated > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteUser(int userId) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String sql = "DELETE FROM User WHERE userId = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, userId);

                int rowsDeleted = statement.executeUpdate();
                if (rowsDeleted > 0) {
                    System.out.println("User deleted successfully.");
                    return true;
                } else {
                    System.out.println("User not found or deletion failed.");
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

	
	
	    public Iterable<Product> getOrdersByUser(int userId) {
	        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
	            String sql = "SELECT p.* FROM Orders1 o " +
	                         "JOIN Product p ON o.productId = p.productId " +
	                         "WHERE o.userId = ?";
	            try (PreparedStatement statement = connection.prepareStatement(sql)) {
	                statement.setInt(1, userId);

	                try (ResultSet resultSet = statement.executeQuery()) {
	                    List<Product> orderedProducts = new ArrayList<>();
	                    while (resultSet.next()) {
	                        orderedProducts.add(new Product(
	                                resultSet.getInt("productId"),
	                                resultSet.getString("productName"),
	                                resultSet.getString("description"),
	                                (int) resultSet.getDouble("price"),
	                                resultSet.getInt("quantityInStock"),
	                                resultSet.getString("type")
	                        ));
	                    
	                  }   
	                    return orderedProducts;
	                }}}
	                    catch (SQLException e) {
	                        e.printStackTrace();
	                    }
	            return null;
			
	        } 
	    
	    
	
	    public boolean cancelOrder(int userId,int orderId) {
	        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
	            // Check if the user exists
	            if (!userExists(userId)) {
	                throw new UserNotFoundException("User with ID " + userId + " not found.");
	            }

	            // Check if the order exists
	            if (!orderExists(orderId, userId)) {
	                throw new OrderNotFoundException("Order with ID " + orderId + " not found.");
	            }
	            // Proceed with canceling the order
	            String cancelOrderSql = "DELETE FROM Orders1 WHERE userId = ? AND orderId = ?";
	            try (PreparedStatement cancelOrderStatement = connection.prepareStatement(cancelOrderSql)) {
	                cancelOrderStatement.setInt(1, userId);
	                cancelOrderStatement.setInt(2, orderId);

	                int rowsDeleted = cancelOrderStatement.executeUpdate();
	                return rowsDeleted > 0;
	            }}
	       	  catch (SQLException e) {
	             e.printStackTrace();
	            return false;
	        } catch (UserNotFoundException e) {
	            System.out.println("User not found: " + e.getMessage());
	            return false;
	        } catch (OrderNotFoundException e) {
	            e.printStackTrace();
	            return false;
	        }

	    }

	    private boolean orderExists(int orderId,int userId) {
	        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
	            String sql = "SELECT COUNT(*) FROM Order WHERE orderId = ?";
	            try (PreparedStatement statement = connection.prepareStatement(sql)) {
	                statement.setInt(1, orderId);
	                statement.setInt(2, userId);
	                

	                try (ResultSet resultSet = statement.executeQuery()) {
	                    if (resultSet.next()) {
	                        int count = resultSet.getInt(1);
	                        return count > 0;
	                    }
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return false;
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
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean updateProduct(Product product) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean deleteProduct(int productId) {
		// TODO Auto-generated method stub
		return false;
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
	public boolean createProduct(Product product) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Product getProduct(int productId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean addProduct1(Product product) {
		// TODO Auto-generated method stub
		return false;
	
	}

}

