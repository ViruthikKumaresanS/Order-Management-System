package entity;

public class User {

	int userId;
	String username,password,role;
	
	public User() {
		super();
	}
	public User(int userId,String username,String password,String role) 
	{
		this.userId=userId;
		this.username=username;
		this.password=password;
		this.role=role;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	} 
	public String toString() {
		return"User{" +
            "userId=" + getUserId() +
            ", username='" + getUsername() + '\'' +
            ", password='" + getPassword() + '\'' +
            ", role=" + getRole() +
		'}';
	
}}