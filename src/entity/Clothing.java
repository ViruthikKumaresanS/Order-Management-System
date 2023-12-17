package entity;

public class Clothing extends Product {
	
	private String color;
	private String size;

	
	public Clothing() {
		super();
	}
	public Clothing(int productId,String productName,String description,double price,
						int quantityInStock,String type,String size,String color) 
	{
		this.size=size;
		this.color=color;
}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}	
		public String toString() {
			return"Electronics{" +
	            "productId=" + getProductId() +
	            ", productName='" + getProductName() + '\'' +
	            ", description='" + getDescription() + '\'' +
	            ", price=" + getPrice() +
				", quantityInStock=" + getQuantityInStock() +
				", type='" + getType() + '\'' +
			    ", color='" + color + '\'' +
				", size=" + size +
			'}';
		
}}