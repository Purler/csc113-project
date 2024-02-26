import java.util.Scanner;

public class Main {
	
	private static Scanner read = new Scanner(System.in);
	private static Customer[] customerList = new Customer[50];
	private static int customerCount = 0;
	private static Inventory inventory = new Inventory();
	
	
	public static void main(String[] args) {
		
		
		boolean wantsExit = false;
		
		while(!wantsExit) {
			displayMainMenu();
			
			System.out.print("your choice is: ");
			int choice = read.nextInt();
			read.nextLine();
			
			switch(choice) {
			case 1: customerHandling(); break;
			case 2: cartOperations(); break;
			case 3: inventoryHandling(); break;
			}
		}
	}
	
	public static void customerHandling() {
		boolean wantsBack = false;
		
		while(!wantsBack) {
			
			displayCustomerMenu();
			
			System.out.print("your choice is: ");
			int choice = read.nextInt();
			read.nextLine();
			
			if(choice == 1) {
				if(customerCount < customerList.length) {
					System.out.print("Enter Name: ");
					String customerName = read.nextLine();
					
					System.out.print("Enter Address: ");
					String customerAddress = read.nextLine();
					
					customerList[customerCount] = new Customer(customerName, customerAddress);
					customerCount++;
					
					System.out.println("Customer Added!");
				}
				else {
					System.out.println("Can't add customer (Full)");
				}
			} else if(choice == 2) {
				System.out.print("Enter the name of the customer you want to remove: ");
				String customerName = read.nextLine();
				
				int index = findCustomer(customerName);
				
				if(index == -1) {
					System.out.println("Customer not found");
				}
				else {
					for(int i = index; i < customerCount - 1; i++) {
						customerList[i] = customerList[i + 1];
					}
					customerCount--;
					System.out.println("Customer removed!");
				}
				
			} else if(choice == 3) {
				if(customerCount == 0)
					System.out.println("There are no customers");
				for(int i = 0; i < customerCount; i++) {
					System.out.println(customerList[i]);
				}
			} else if(choice == 4) {
				wantsBack = true;
			}
			else {
				System.out.println("Invalid Choice!");
			}
		}
	}
	
	public static void cartOperations() {
		boolean wantsBack = false;
		boolean customerSelected = false;
		String customerName = "";
		
		while(!wantsBack) {
			if(!customerSelected) {
				System.out.println("Enter the customer name (or 'B' to go back): ");
				customerName = read.nextLine();
				customerSelected = true;
			}
			
			if(customerName.equalsIgnoreCase("B")) {
				wantsBack = true;
				continue;
			}
			
			int index = findCustomer(customerName);
			
			if(index == -1)
				System.out.println("Customer Not Found!");
			else {
				displayCartMenu();
				
				Customer customer = customerList[index];
				ShoppingCart shoppingCart = customer.getShoppingCart();
				
				System.out.print("your choice is: ");
				int choice = read.nextInt();
				read.nextLine();
				
				if(choice == 1) {
					System.out.print("Enter the name of the product you want to add to cart: ");
					String productName = read.nextLine();
					
					Product addedItem = inventory.items[inventory.findItem(productName)];
					
					shoppingCart.addItem(addedItem);
				}else if(choice == 2) {
					
					System.out.print("Enter the name of the product you want to remove from cart: ");
					String productName = read.nextLine();
					
					shoppingCart.removeItem(productName);
					
				}else if(choice == 3) {
					
					shoppingCart.viewItems();
					
				}else if(choice == 4) {
					
					System.out.println("Shopping cart total is " + shoppingCart.calculateTotal() + " SR");
					
				}else if(choice == 5) {
					wantsBack = true;
					continue;
				}
			}
		}
	}
	
	public static void inventoryHandling() {
		boolean wantsBack = false;
		
		while(!wantsBack) {
			displayInventoryMenu();
			
			System.out.print("your choice is: ");
			int choice = read.nextInt();
			read.nextLine();
			
			if(choice == 1) {
				if(inventory.spaceAvailable()) {
					System.out.println("Is it food or nonfood: ");
					String type = read.nextLine();
					boolean isFood = false;
					
					if(type.equalsIgnoreCase("food")) {
						isFood = true;
					}
					else if(type.equalsIgnoreCase("nonfood")) {
						isFood = false;
					}
					else {
						System.out.println("Invalid type!");
						continue;
					}
					
					
					System.out.print("Enter Name: ");
					String itemName = read.nextLine();
					
					System.out.print("Enter Price: ");
					double itemPrice = read.nextDouble();
					read.nextLine();
					
					System.out.print("Enter Category: ");
					String itemCategory = read.nextLine();
					
					Product item;
					
					if(isFood) {
						System.out.print("Enter Calories: ");
						int foodCalories = read.nextInt();
						item = new Food(itemName, itemPrice, itemCategory, foodCalories);
					}
					else {
						System.out.print("Enter Warranty Period (in months): ");
						int nonFoodWarranty = read.nextInt();
						item = new NonFood(itemName, itemPrice, itemCategory, nonFoodWarranty);
					}
					
					inventory.addItem(item);
					
				}
				else
					System.out.println("Inventory Full!");
				
				
			}else if(choice == 2) {
				
				System.out.print("Enter the name of the product you want to remove: ");
				String productName = read.nextLine();
				
				inventory.removeItem(productName);
			}else if(choice == 3) {
				
				if(inventory.isEmpty())
					System.out.println("Inventory is empty!");
				else
					inventory.viewItems();
				
			}else if(choice == 4) {
				
				wantsBack = true;
				
			}else {
				System.out.println("Invalid input!");
			}
			
			
		}
	}
	
	public static int findCustomer(String name) {
		for(int i = 0; i < customerCount; i++) {
			if(customerList[i].getName().equalsIgnoreCase(name))
				return i;
		}
		return -1;
	}
	
	public static void displayMainMenu() {
		System.out.println("**************************************");
		System.out.println("*   Welcome to your Store Manager    *");
		System.out.println("**************************************");
		System.out.println("*  choose one of the options below:  *");
		System.out.println("*  1- Customer Information           *");
		System.out.println("*  2- Customer Shopping Carts        *");
		System.out.println("*  3- Manage Inventory               *");
		System.out.println("*  4- Exit                           *");
		System.out.println("**************************************");
	}
	
	public static void displayCustomerMenu() {
		System.out.println("**************************************");
		System.out.println("*  choose one of the options below:  *");
		System.out.println("*  1- Add a customer                 *");
		System.out.println("*  2- Remove a customer              *");
		System.out.println("*  3- View customers                 *");
		System.out.println("*  4- Back to main menu              *");
		System.out.println("**************************************");
	}
	
	public static void displayCartMenu() {
		System.out.println("**************************************");
		System.out.println("*  choose one of the options below:  *");
		System.out.println("*  1- Add an item                    *");
		System.out.println("*  2- Remove an item                 *");
		System.out.println("*  3- View items in cart             *");
		System.out.println("*  4- Calculate total                *");
		System.out.println("*  5- Back to main menu              *");
		System.out.println("**************************************");
	}
	
	public static void displayInventoryMenu() {
		System.out.println("**************************************");
		System.out.println("*  choose one of the options below:  *");
		System.out.println("*  1- Add an item                    *");
		System.out.println("*  2- Remove an item                 *");
		System.out.println("*  3- View items in inventory        *");
		System.out.println("*  4- Back to main menu              *");
		System.out.println("**************************************");
	}
}