//Transaction.java
import java.util.StringTokenizer;

/**
 * Reads in the transaction.txt file; creates a database for 
 * all the transaction files
 * 
 * @author Muhammad Islam
 */
public class Transaction {
	
	// all transaction items are stored here
	public ItemList<UserItem> data = new ItemList();
	private ProduceItem item;
	// number of items on the transaction.txt
	private int length;
	
	/**
	 * constructor of the class, initializes length to 0
	 * and calls initialize() method.
	 */
	public Transaction(){
		length = 0;
		
	} // end of constructor
	
	
	public ListNode Iterator(){
		return data.getIterator();
	}
	
	/**
	 * to get the code at a particular index
	 * @param i index of a transaction item
	 * @return String the code of that Item
	 */
	public String getCode(ListNode i){
		return ((ProduceItem) i.data).getName();
	}
	
	/**
	 * to get the weight of a item at a particular index
	 * @param i index of an item
	 * @return float the weight of that item
	 */
	public float getWeight(ListNode i){
		return ((ProduceItem) i.data).getWeight();
		
	}
	/**
	 * @return int length of the bought[] array.
	 */
	public int getLength(){
		return length;
	}
	
	/**
	 * Dummy Method to check all elements on bought. Used
	 * during project development.
	 */
	public String toString(){
		
		return data.toString();
		
	}

}
