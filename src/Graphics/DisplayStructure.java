package Graphics;

import javax.swing.JFrame;

import API.BinaryTree;

public class DisplayStructure {
	
	
	
	
	public static<T> void display(BinaryTree<T> tree){
		JFrame frame = new JFrame("Oval Sample");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    //frame.add(new OvalPanelCanvas());
	    frame.setSize(300, 200);
	    frame.setVisible(true);
	}
}
