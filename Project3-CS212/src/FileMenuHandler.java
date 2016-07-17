import javax.swing.*;
import java.text.DecimalFormat;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.StringTokenizer;

// Action
public class FileMenuHandler extends DatabaseMenuHandler{
	
	public boolean flag = false;
	public static Transaction tr;
	public static float temp;
	private UserItem item;
	private int length;
	public FileMenuHandler(GUI jf) {
		super(jf);
		jframe = jf;
		length = 0;
	}

	public void actionPerformed(ActionEvent event) {
		String menuName;
		menuName = event.getActionCommand();
		if (menuName.equals("Open")){
			openFile();
			
			match();
			
			t_jf.appendText(display());
			
			jframe.getTotal();
		}
		else if (menuName.equals("Quit"))
			System.exit(0);
	} // actionPerformed

	protected void openFile() {
		JFileChooser chooser;
		int status;
		chooser = new JFileChooser();
		status = chooser.showOpenDialog(null);
		if (status == JFileChooser.APPROVE_OPTION)
			readSource(chooser.getSelectedFile());
		else
			JOptionPane.showMessageDialog(null, "Open File dialog canceled");
	} // openFile

	private void readSource(File chosenFile) {
       String chosenFileName = chosenFile.getPath();
       initialize(chosenFileName);
       
	}
	public void initialize(String chosenFileName){
		/*
		 * temp[0] -> code
		 * temp[1] -> weight
		 */
		tr = new Transaction();
		String temp[] = new String[2];
		// Initialize TextFileInpout object
		TextFileInput fileIn = new TextFileInput(chosenFileName);
		// Declare tokenizer object
		StringTokenizer myToken;
		// to hold string from the .txt file
		String line;
		
		
		do {
			//read a new line
			line = fileIn.readLine();
			//break at end of file
			if(line==null) break;
			
			//initialize StringTokenizer object with line as string and ',' as token
			myToken = new StringTokenizer(line, ",");
			
			
			int i=0; // loop iterator 
			while(myToken.hasMoreTokens()){
				//tokenize line and store to produce[]
				 temp[i]=myToken.nextToken();
		         i++; 
			}
			//creates a new data after each iteration and calls constructor
			
			item = new UserItem(temp[0], temp[1]);
			
			tr.data.append(item);
			//increment length
			length++;
		}while(line!=null);

	}	// end of initialize()
	
	public void match(){
		// Initialize DecimalFormat object which controls number of decimal places
		DecimalFormat df = new DecimalFormat();
		// Set decimal places to 2
		df.setMaximumFractionDigits(2);
		
		/** Iterator to the first node of the linked list */
		ListNode itr = tr.Iterator();
		// Stores the subtotal for each term and adds them
		float total = 0;
		String price, name;
		while(itr != null){
			
				name = DB.getName(((ProduceItem) itr.data).getCode());
				
				price = ""+DB.getPrice(((ProduceItem) itr.data).getCode());
			
			// Sets the name of the transection item
			((ProduceItem) itr.data).setName(name);
			// Sets rthe price of the item
			((ProduceItem) itr.data).setPrice(price);
			//float temp = (tr.getWeight(itr));
			//total += (DB.getPrice(((ProduceItem) itr.data).getCode())*temp);
			
			// Shifts to next node
			itr = itr.next;
		}
	}
	
	public String display(){
		int j = 0;
		String n = "";
		float t = 0;
		for (ListNode<ProduceItem> i=tr.Iterator(); i!=null; i = i.next)
		{
			
			// Initialize DecimalFormat object which controls number of decimal places
			DecimalFormat df = new DecimalFormat();
			// Set decimal places to 2
			df.setMaximumFractionDigits(2);
			
			t = (float)((UserItem) i.data).getWeight() + Float.parseFloat(((UserItem) i.data).getPrice());
			n += ( "Item #  " + Integer.toString(++j) + "  " + ((UserItem) i.data).getName() + " ");
			n += (((UserItem) i.data).getPrice() + "\n");
			temp += t;
		}
		return n;
	}
	
	public float getPrice(){
		return temp;
	}

}
       