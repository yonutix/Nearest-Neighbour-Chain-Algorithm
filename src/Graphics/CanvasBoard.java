package Graphics;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.util.ArrayList;

import javax.swing.JPanel;

import API.BinaryTree;

public class CanvasBoard<T> extends JPanel {
	private static final long serialVersionUID = 1L;

	public static final double STD_WIDTH =32.0;
	public static final double STD_HEIGHT = 32.0;

	BinaryTree<T> tree;

	public CanvasBoard() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CanvasBoard(BinaryTree<T> tree) {
		super();
		this.tree = tree;
	}

	public CanvasBoard(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	public CanvasBoard(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	public CanvasBoard(LayoutManager layout) {
		super(layout);
		// TODO Auto-generated constructor stub
	}

	public void drawRectangle(Graphics g, double offsetX, double offsetY,
			double width, double height, BinaryTree<T> node) {

		if (node == null) {

			// System.out.println("*");
		} else {
			if (node.getInfo() == null) {
				
				g.drawLine((int) (offsetX + width / 2), (int) offsetY,
						(int) (offsetX + width / 2),
						(int) (offsetY + height / 2));

				if (node.getLeft() != null) {
					
					double w = BinaryTree.getLeafNr(node.getLeft()) * STD_WIDTH;

					g.drawLine((int) (offsetX + w/2),
							(int) (offsetY + height / 2),
							(int) (offsetX + width / 2),
							(int) (offsetY + height / 2));

					g.drawLine((int) (offsetX + w/2),
							(int) (offsetY + height / 2),
							(int) (offsetX + w/2),
							(int) (offsetY + height));
				}

				if (node.getRight() != null) {
					double wl = BinaryTree.getLeafNr(node.getLeft()) * STD_WIDTH;
					
					double w = BinaryTree.getLeafNr(node.getRight()) * STD_WIDTH;
					
					g.drawLine((int) (offsetX + width / 2),
							(int) (offsetY + height / 2),
							(int) (offsetX + wl + w/2),
							(int) (offsetY + height / 2));

					g.drawLine((int) (offsetX + wl + w/2),
							(int) (offsetY + height / 2),
							(int) (offsetX + wl + w/2),
							(int) (offsetY + height));
				}
			} else {
				
				FontMetrics fontMetrics = g.getFontMetrics();

				int fWidth = fontMetrics.stringWidth(node.getInfo().toString());
				int fHeight = fontMetrics.getHeight();
				g.drawOval((int) (offsetX + width
						/ 2 - fWidth / 2), (int) (offsetY ), fWidth+ 2, fHeight + 5);
				
				g.drawString(node.getInfo().toString(), (int) (offsetX + width
						/ 2 - fWidth / 2), (int) (offsetY + fHeight));
						 
						
			}
		}

		//g.drawRect((int) offsetX, (int) offsetY, (int) width, (int) height);
	}

	@Override
	public void paintComponent(Graphics g) {

		int maxC = BinaryTree.getLevelCount(tree);
		ArrayList<BinaryTree<T>> tmpTree = new ArrayList<BinaryTree<T>>();
		ArrayList<BinaryTree<T>> listB = new ArrayList<BinaryTree<T>>();
		listB.add(tree);

		for (int i = 0; i < maxC; ++i) {

			double itWidth = 0;
			for (int j = 0; j < listB.size(); ++j) {
				double currentWidth = 0;
				if (listB.get(j) != null) {
					currentWidth = BinaryTree.getLeafNr(listB.get(j))
							* STD_WIDTH;

					drawRectangle(g, 2 + itWidth, 2 + i * STD_HEIGHT,
							currentWidth, STD_HEIGHT, listB.get(j));

					if (listB.get(j).getLeft() != null)
						tmpTree.add(listB.get(j).getLeft());

					if (listB.get(j).getRight() != null)
						tmpTree.add(listB.get(j).getRight());
					
					if(listB.get(j).isLeaf()){
						tmpTree.add(null);
					}
					
					
					itWidth += currentWidth;
				}
				else{
					itWidth += STD_WIDTH;
					tmpTree.add(null);
				}

				
			}
			listB.clear();
			ArrayList<BinaryTree<T>> swapTree = tmpTree;
			tmpTree = listB;
			listB = swapTree;
		}

	}

}
