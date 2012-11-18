import java.awt.*;

import javax.swing.*;

import junit.framework.Test;

public class BirderGUI {
	
	public static void main(String[] args)
	{
		new BirderGUI();
	}

	//SUPER basic GUI layout to show how a Java GUI looks and interacts. I tried to leave super detailed comments so it could be potentially
	//easy to add to this GUI without much learning. It is a lot of stacking and adding one thing to something else. Pretty simple however.
	public BirderGUI()
	{
		
		//I forget how to Java
		//menu including file, options, and search
		JMenuBar menuBar = new JMenuBar(); //constructs a menu bar
		JMenu file = new JMenu("File"); //creates the file menu dropdown
		JMenu options = new JMenu("Options"); //creates the options menu dropdown
		TextField search = new TextField("Search for a specific bird by name", 30); //creates the search field allowing up to 30 chars before text scrolls
		
		menuBar.add(file); //adds file to menu bar
		menuBar.add(options); //adds options to menu bar
		menuBar.add(search); //adds search to menu bar
		
		JMenuItem quit = new JMenuItem("Quit"); //creates a quit menu item
		JMenuItem newSearch = new JMenuItem("New Search"); //creates a new search menu item
		
		file.add(newSearch); //adds new search to the file dropdown
		file.add(quit); //adds quit to the file dropdown
		
		//for looks only right now... just places text on gui
		Label centerText = new Label("                                                                                              Picture here");
		Label leftText = new Label("Bird details here");
		Label bottomText = new Label("Additional Info");
		Label rightText = new Label("Whatever else we want here");
		
		//creation of GUI look
		JFrame f = new JFrame("Bothell Birder"); //constructing actual outside window frame w/name Bothell Birder
		f.setSize(1000,700); //sets size of frame
		
		f.getContentPane().setLayout(new BorderLayout()); //will set up a borderlayout inside bothellbirder frame for positioning purposes
		f.add(menuBar, BorderLayout.NORTH); //adds the menu bar to the north section of the GUI
		f.add(centerText, BorderLayout.CENTER); //adds my label to show where pic might go
		f.add(leftText, BorderLayout.WEST); //adds my label to show where pics details would be
		f.add(bottomText, BorderLayout.SOUTH); //adds my label to show additional info spot
		f.add(rightText, BorderLayout.EAST); //adds my label to show whatever else we want
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//stops program if gui is closed
		f.setVisible(true); //makes it so that the gui is visible 
	}
}
