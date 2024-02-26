
public class Customer {
	private String name;
	private String address;
	private ShoppingCart shoppingCart;
	
	public Customer(String name, String address) {
		this.name = name;
		this.address = address;
		this.shoppingCart = new ShoppingCart();
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String toString() {
		return "Name: " + name + ", Address: " + address;
	}
	
}
