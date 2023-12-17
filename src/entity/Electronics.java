package entity;

public class Electronics extends Product{
		
	private String brand;
	private int warrantyPeriod;
	
	public Electronics() {
		super();
	}
	public Electronics (int productId,String productName,String description,double price,
						int quantityInStock,String type,String brand,int warrantyPeriod) 
	{
		this.brand=brand;
		this.warrantyPeriod=warrantyPeriod;
	
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public int getWarrantyPeriod() {
		return warrantyPeriod;
	}
	public void setWarrantyPeriod(int warrantyPeriod) {
		this.warrantyPeriod = warrantyPeriod;
	}
	
	public String toString() {
		return"Electronics{" +
            "productId=" + getProductId() +
            ", productName='" + getProductName() + '\'' +
            ", description='" + getDescription() + '\'' +
            ", price=" + getPrice() +
			", quantityInStock=" + getQuantityInStock() +
			", type='" + getType() + '\'' +
		    ", brand='" + brand + '\'' +
			", warrantyPeriod=" + warrantyPeriod +
		'}';
	
}}
