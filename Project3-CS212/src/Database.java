//Database.java

import java.util.StringTokenizer;

import javax.swing.JOptionPane;

/**
 * Reads the database.txt; stores all the items and creates the database.
 * @author Muhammad Islam
 *
 */
public class Database {
	
	// All items are stored here.
	public static ItemList<ProduceItem> data = new ItemList();
	private ProduceItem item;
	// Tracks the length of the array
	private int length;
	
	/**
	 * Class constructor. No arguments.
	 * initializes length to zero and 
	 * calls initialize()
	 */
	public Database(){
		length = 0;		// so data[0] stores first value 
		//initialize();	// Calls to initialize method
	} // end of constructor
	
	
	/**
	 * Dummy Method to check all elements on data. Used
	 * during project development.
	 */
	public String toString(){
		
		return data.toString();
		
	}
	
	public String listFruits(){
		return data.listFruits();
	}
	
	public String listVegetables(){
		return data.listVegetables();
	}
	/**
	 * @return int length of the data[] array.
	 */
	public int getLength(){
		return length;
	}
	
	/**
	 * Takes the code of a produce item; uses search method
	 * to get the index of that item. Then returns the name
	 * stored in that produce item object
	 * 
	 * @param code the code of any produce item
	 * @return String the name of the produce item whose code was passed
	 */
	public String getName(String code){
		// calls search method and passes the code
		
		try{
			ListNode i = data.find(code);
			if(i== null) 
				throw new ItemNotFound();
			ProduceItem p= (ProduceItem) i.data;
			return p.getName();
		}
		
		catch(ItemNotFound e){
			return "Missing Item";
		}	
		
				
	}
	
	/**
	 * Finds the price of the produce item whose code
	 * was passed as argument. Uses search method to get the index
	 * of that produce item. 
	 * 
	 * @param code the code of any produce item
	 * @return float the price of the produce item whose code was passed
	 */
	public float getPrice(String code){
		// calls the search method with the code, uses the index to get the price as string
		try{
			ListNode i = (data.find(code));
			if(i == null )
				throw new ItemNotFound();
			String temp = ((ProduceItem) i.data).getPrice();
			// converts the String to a float
			return Float.parseFloat(temp);  
		}
		catch(ItemNotFound e){
			String p = JOptionPane.showInputDialog("Enter price of the missing item:");
			return Float.parseFloat(p);
		}
	}
	
} // end of class Database.java
