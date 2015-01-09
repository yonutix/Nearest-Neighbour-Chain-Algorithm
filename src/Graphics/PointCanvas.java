package Graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

import main.Point2D;
import API.BinaryTree;

public class PointCanvas<T> extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public final static double scaleX = 6.0;
	public final static double scaleY = 6.0;

	BinaryTree<?> tree;
	ArrayList<?> customLevelNodes;

	ArrayList<ArrayList<?>> groups;

	ArrayList<T> points;
	ArrayList<Color> colors;

	public PointCanvas(ArrayList<T> points, BinaryTree<?> tree, int level) {
		this.points = points;
		this.tree = tree;
		groups = new ArrayList<ArrayList<?>>();
		customLevelNodes = BinaryTree
				.getNodesOnLevel(tree, 0, level);
		
		

		for (Object currentTree : customLevelNodes) {
			
			groups.add(BinaryTree.getAllLeaves((BinaryTree<?>)currentTree));
		}
		
		colors = getRandomColors(customLevelNodes.size());
	}
	
	ArrayList<Color> getRandomColors(int count){
		ArrayList<Color> result = new ArrayList<Color>();
		
		for(int i = 0; i < count; ++i){
			
			
			int R = (int)(Math.random()*256);
			int G = (int)(Math.random()*256);
			int B= (int)(Math.random()*256);
			Color color = new Color(R, G, B); //random color, but can be bright or dull

			//to get rainbow, pastel colors
			Random random = new Random();
			final float hue = random.nextFloat();
			final float saturation = 0.9f;//1.0 for brilliant, 0.0 for dull
			final float luminance = 1.0f; //1.0 for brighter, 0.0 for black
			color = Color.getHSBColor(hue, saturation, luminance);
			/*
			int r = (int)(Math.random() * 256);
			int g = (int)(Math.random() * 256);
			int b = (int)(Math.random() * 256);
			*/
			result.add(color);
			
		}
		
		return result;
	}

	@Override
	public void paintComponent(Graphics g) {
		
		int i = 0;
		for (ArrayList<?> group : groups) {
			
			g.setColor(colors.get(i));
			
			for (Object index : group) {
				Point2D p = (Point2D) points.get((Integer) index);
				g.fillOval((int) (p.x * scaleX), (int) (p.y * scaleY), 5, 5);
			}
			i++;
		}
		
		g.setColor(Color.BLACK);
	}

}
