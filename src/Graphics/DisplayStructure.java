package Graphics;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import API.BinaryTree;

public class DisplayStructure {

	public static JFrame frame;

	public static <T> void display(BinaryTree<T> tree, ArrayList<?> points) {

		frame = new JFrame("Sample");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		CanvasBoard cb = new CanvasBoard(tree);
		
		JScrollPane scrollPane = new JScrollPane(cb);

		scrollPane.setPreferredSize(new Dimension(2000, 600));

		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollPane,
				new PointCanvas(points, tree, 2));
		
		splitPane.setOneTouchExpandable(true);
		splitPane.setDividerLocation(150);

		Dimension minimumSize = new Dimension(600, 600);
		cb.setMinimumSize(minimumSize);

		frame.add(splitPane);

		frame.setSize(1200, 600);
		frame.setVisible(true);
	}
}
