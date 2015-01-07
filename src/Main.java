import API.NearestNeighbourChainAlgorithm;


public class Main {
	public static void main(String[] args) {
		NearestNeighbourChainAlgorithm<Point2D> engine = 
				new NearestNeighbourChainAlgorithm<Point2D>("point2d_dataset.txt", Point2D.class);
		engine.loop();
		
	}
}
