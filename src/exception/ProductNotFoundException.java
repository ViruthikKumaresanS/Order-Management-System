package exception;
public class ProductNotFoundException extends Exception {

    public ProductNotFoundException(String message) {
        super(message);
    }
  
    public class OrderNotFoundException extends Exception
    	{
    		public OrderNotFoundException(String message) {
    	        super(message);
    	    }
}}

