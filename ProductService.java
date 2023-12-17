package dao;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import entity.*;
public interface ProductService {
	
	

	    // Product related methods
	    boolean addProduct(Product product);
	    Product getProductById(int productId);
	    List<Product> getAllProducts();
	    boolean updateProduct(Product product);
	    boolean deleteProduct(int productId);

	    // Electronics related methods
	    void addElectronics(Electronics electronics);
	    Electronics getElectronicsById(int productId);
	    List<Electronics> getAllElectronics();
	    void updateElectronics(Electronics electronics);
	    void deleteElectronics(int productId);

	    // Clothing related methods
	    void addClothing(Clothing clothing);
	    Clothing getClothingById(int productId);
	    List<Clothing> getAllClothing();
	    void updateClothing(Clothing clothing);
	    void deleteClothing(int productId);

	    // User related methods
	    void addUser(User user);
	    User getUserById(int userId);
	    List<User> getAllUsers();
	    boolean updateUser(User user);
	    boolean deleteUser(int userId) throws SQLException;

	    // Order related methods
	    void addOrder(Orders1 order);
	    Orders1 getOrderById(User user);
	    List<Orders1> getAllOrders();
	    void updateOrder(Orders1 order);
	    void deleteOrder(int orderId);
		Iterable<Product> getOrderByUser(User user);
		boolean productExists(int productId);
		boolean userExists(int userId);
		boolean createUser(User User);
		User getUser(int UserId);
		Collection<User> getAllUser();
		boolean createProduct(Product product);
		Product getProduct(int productId);
		boolean addProduct1(Product product);
	    boolean cancelOrder(int userId,int orderId);
	}

