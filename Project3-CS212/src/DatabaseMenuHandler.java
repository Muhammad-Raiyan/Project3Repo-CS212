import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.StringTokenizer;
import javax.swing.*;



public class DatabaseMenuHandler implements ActionListener {
	protected GUI jframe;
	protected static Database DB;
	protected GUI t_jf;
	// All items are stored here.
	//private ItemList<ProduceItem> data = new ItemList();
	private ProduceItem item;
	// Tracks the length of the array
	private int length;
	public DatabaseMenuHandler(GUI jf) {
		jframe = jf;
		t_jf = jf;
	}
	
	public void actionPerformed(ActionEvent event) {
	      String menuName = event.getActionCommand();
	      Calculate c = new Calculate();
	      
	      if (menuName.equals("Open")){
	    	  openFile( );
	    	  t_jf.clear();
	    	  
	      }
	      else if (menuName.equals("Display Fruits")){
	    	  t_jf.clear();
	    	  t_jf.appendText(DB.listFruits());
	      }
	      else if (menuName.equals("Display Vegetables")){
	    	  //t_jf.clear();
	    	  t_jf.appendText(DB.listVegetables());
	    	
	      }
	      
	    	  
	} //actionPerformed
	
	protected void openFile( ) {
	       JFileChooser chooser;
	       int          status;
	       chooser = new JFileChooser( );
	       status = chooser.showOpenDialog(null);
	       if (status == JFileChooser.APPROVE_OPTION){ 
	          readSource(chooser.getSelectedFile());
	       }
	       else 
	          JOptionPane.showMessageDialog(null, "Open File dialog canceled");
	    } //openFile
	
	private void readSource(File chosenFile) {
	       String chosenFileName = chosenFile.getPath();
	       initializeList(chosenFileName);
	       
	}
	
	private void initializeList(String chosenFileName){
		
		/*
		 * produce[0] -> F/V
		 * produce[1] -> Code
		 * produce[2] -> Name
		 * produce[3] -> Price
		 */
		DB = new Database();
		String produce[] = new String[4];
		//Initialize TextFileInput object
		TextFileInput fileIn = new TextFileInput(chosenFileName);
		//Declare StringTokenizer Object
		StringTokenizer myToken;
		//to store each line from text file.
		String line;
		//Iterates through the file; each time read a new line and tokenize
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
				 produce[i]=myToken.nextToken();
				 
				 i++; 
			}
			//creates a new data after each iteration and calls constructor
			
			if(produce[0].equals("F"))	
				item = new Fruit(produce[1], produce[2], produce[3]);
			else if(produce[0].equals("V"))
				item = new Vegetable(produce[1], produce[2], produce[3]);
			DB.data.append(item);
			//increment length
			length++;
		}while(line!=null);
	} // end of initialize()

}
