package main;

import java.util.ArrayList;

import API.BinaryTree;
import API.NearestNeighbourChainAlgorithm;
import Graphics.DisplayStructure;


public class Main {
	public static void main(String[] args) {
		long timei = System.currentTimeMillis();
		
		
		NearestNeighbourChainAlgorithm<Point2D> engine = 
				new NearestNeighbourChainAlgorithm<Point2D>("point2d_dataset.txt", Point2D.class);
		engine.execute();
		long timef = System.currentTimeMillis();
		
		BinaryTree<Integer> tree = engine.getOutput();
		ArrayList<Point2D> points = engine.getPoints();
		
		DisplayStructure.display(tree, points);
		
		System.out.println("Time:" + ((double)(timef - timei))/1000);
	}
}
