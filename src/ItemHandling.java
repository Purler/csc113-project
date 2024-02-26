
public class ItemHandling {
	protected Product[] items;
	protected int itemCount;
	
	public boolean addItem(Product product) {
		if(spaceAvailable()) {
			items[itemCount] = product;
			itemCount++;
			return true;
		}
		else
			return false;
	}
	
	public int findItem(String productName) {
		for (int i = 0; i < itemCount; i++) {
			if (items[i].getName().equals(productName))
				return i;
		}
		return -1;
	}
	
	public boolean removeItem(String productName) {
		int index = findItem(productName);
		if (index == -1)
			return false;
		else {
			for(int i = index; i < itemCount - 1; i++) 
				items[i] = items[i + 1];
			itemCount--;
			return true;
		}
	}
	
	public void viewItems() {
		for(int i = 0; i < itemCount; i++)
			System.out.println("Item #" + (i+1) + ": \n"+ items[i]);
	}
	
	public boolean spaceAvailable() {
		return itemCount < items.length;
	}
	
	public boolean isEmpty() {
		return itemCount == 0;
	}
}
