import java.awt.*;
import java.text.DecimalFormat;

import javax.swing.*;

public class GUI extends JFrame{
	
	private static Container myContentPane;
	public static TextArea allText;
	public static JLabel label;
	public static DatabaseMenuHandler dmh;
	public static FileMenuHandler al;
	JMenu fileMenu, DataMenu;
	public GUI(String title){
		
		setTitle(title);
		setSize(700, 700);
		setLocation(0, 0);
		
		createMenu();
		createTextArea("1");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		allText.setEditable(false);
		
		setVisible(true);
	}
	
	public void createMenu(){
		JMenuBar menuBar = new JMenuBar();
		fileMenu = new JMenu("File");
		DataMenu = new JMenu("Database");
		JMenuItem fOpen, fQuit, dOpen, dFruits, dVeg;
		al = new FileMenuHandler(this);
		dmh = new DatabaseMenuHandler(this);
		fOpen = new JMenuItem("Open"); 
		fOpen.addActionListener( al );
		fileMenu.add( fOpen );
		fileMenu.addSeparator(); 
		fQuit = new JMenuItem("Quit"); 
		fQuit.addActionListener( al );
		fileMenu.add( fQuit );
		
		dOpen = new JMenuItem("Open");
		dOpen.addActionListener( dmh );
		DataMenu.add(dOpen);
		DataMenu.addSeparator();
		
		dFruits = new JMenuItem("Display Fruits");
		dFruits.addActionListener( dmh );
		DataMenu.add(dFruits);
		DataMenu.addSeparator();
		
		dVeg = new JMenuItem("Display Vegetables");
		dVeg.addActionListener( dmh );
		DataMenu.add(dVeg);
				
		menuBar.add(fileMenu);
		menuBar.add(DataMenu);
		dmh.jframe.setJMenuBar(menuBar);
		
	}
	
	public void createTextArea(String t1){
		// Initialize contentPane
		myContentPane = dmh.jframe.getContentPane();
		// Initialize TextArea for data
		allText = new TextArea(500, 500);
		
		// Initialize label to display the sum
		label = new JLabel();
		//label.setText("Total: $" + total);
				
		/** 
		* Set location of textAreas
		* Name(WEST) 	Price(Center)	Cost(EAST) 
		* label(South)								
		*/
		myContentPane.add(allText, BorderLayout.WEST);
		myContentPane.add(label, BorderLayout.SOUTH);
		
		//myContentPane.add(label, BorderLayout.SOUTH);
		allText.append("CLICK DATABASE -> Open and browse to database2.txt to create database");
		
	}
	
	public void appendText(String st){
		allText.setText("");
		allText.append(st);
	}
	
	public void clear(){
		allText.setText("");
	}
	
	public void  getTotal(){
		String str = "" + al.getPrice();
		
		label.setText("Total: $"+str);
	}
	/*
	public String display(){
		int j = 0;
		String n = "";
		for (ListNode i=al.tr.Iterator(); i!=null; i = i.next)
		{
			// Initialize DecimalFormat object which controls number of decimal places
			DecimalFormat df = new DecimalFormat();
			// Set decimal places to 2
			df.setMaximumFractionDigits(2);
			
			float temp = (float)((UserItem) i.data).getWeight() + Float.parseFloat(((UserItem) i.data).getPrice());
			n += ( "Item #  " + Integer.toString(++j) + "  " + ((UserItem) i.data).getName() + " ");
			n += (((UserItem) i.data).getPrice() + "\n");
			String n2 = "" + df.format(temp);
			
		}
		return n;
	}*/
}
