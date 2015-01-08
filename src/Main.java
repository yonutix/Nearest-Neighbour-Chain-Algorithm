import API.BinaryTree;
import API.NearestNeighbourChainAlgorithm;
import Graphics.DisplayStructure;
import Graphics.Displayable;


public class Main {
	public static void main(String[] args) {
		NearestNeighbourChainAlgorithm<Point2D> engine = 
				new NearestNeighbourChainAlgorithm<Point2D>("point2d_dataset.txt", Point2D.class);
		engine.execute();
		
		
		BinaryTree<Integer> tree = engine.getOutput();
		System.out.println("#####################################");
		System.out.println(tree);
		for(int i = 0; i < 9; ++i){
			System.out.println(BinaryTree.getNodesOnLevel(tree, 0, i));
		}
		
		
		//DisplayStructure.display(tree);
		
		
		
	}
}
