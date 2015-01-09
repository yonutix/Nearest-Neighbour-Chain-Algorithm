package API;

import java.awt.geom.Point2D;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Stack;

public class NearestNeighbourChainAlgorithm<T extends AbstractNode> {
	ArrayList<T> points;
	Class<T> reference;

	ArrayList<Cluster<Integer, T>> clusterSet;
	
	BinaryTree<Integer> output;
	ArrayList<BinaryTree<Integer>> intermediar;

	public NearestNeighbourChainAlgorithm(String filename, Class<T> classRef) {
		reference = classRef;
		points = new ArrayList<T>();
		clusterSet = new ArrayList<Cluster<Integer, T>>();
		intermediar = new ArrayList<BinaryTree<Integer>>();
		try {
			RandomAccessFile raf = new RandomAccessFile(filename, "r");
			String line = "";
			int i = 0;
			while ((line = raf.readLine()) != null) {
				T newNode = reference.newInstance();
				newNode.newInstance(line);
				points.add(newNode);
				clusterSet.add(new Cluster<Integer, T>((T) newNode.copy()));
				clusterSet.get(clusterSet.size() - 1).add(i);
				intermediar.add(new BinaryTree<Integer>(i));
				i++;
			}
			raf.close();
		} catch (Exception e) {
			System.out.println(e.getMessage() + " Could not read from file");
		}

	}

	int it = 0;

	public void execute() {
		Stack<Cluster<Integer, T>> st = new Stack<Cluster<Integer, T>>();
		while (clusterSet.size() > 1) {
			it++;
			/*
			System.out.println(it + "=============================");
			for(Cluster<Integer, T> e: st){
				System.out.println(e);
			}
			System.out.println(it + "=============================");
			*/
			
			if (st.empty()) {

				int randomCluster = (int) (Math.random() * clusterSet.size());
				st.push(clusterSet.get(randomCluster));
				//System.out.println("Stack empty, " + randomCluster	+ " selected");
				continue;
			}

			Cluster<Integer, T> currentCluster = st.peek();
			//System.out.println("Current cluster: ");
			//System.out.println(currentCluster);

			// Compute the distance from current clusters to all other clusters
			// and get the minimum distance
			double minDist = Double.MAX_VALUE;
			int minIndex = -1;
			int currentIndex = -1;

			for (int i = 0; i < clusterSet.size(); ++i) {
				if (!(currentCluster.compareTo(clusterSet.get(i)))) {
					Cluster<Integer, T> iteratedCluster = clusterSet.get(i);
					double dist = currentCluster.getDistance(iteratedCluster);

					if (dist < minDist) {
						minDist = dist;
						minIndex = i;
					}

				}
				else{
					currentIndex = i;
				}
			}
			//System.out.println("**** " + minIndex + " " + currentIndex);
			
			//System.out.println("Min cluster: ");
			//System.out.println(clusterSet.get(minIndex));
			//System.out.println("#############" + minDist + " " + minIndex);

			if (st.contains(clusterSet.get(minIndex))) {
				//System.out.println("st contain " + minIndex);
				Cluster<Integer, T> a = clusterSet.get(minIndex);
				Cluster<Integer, T> b = st.peek();

				clusterSet.add(Cluster.merge(a, b));
				
				//System.out.println("Merged cluster");
				
				//System.out.println(clusterSet.get(clusterSet.size()-1));

				st.remove(st.indexOf(clusterSet.get(minIndex)));
				st.pop();
				
				//System.out.println("deleted:");
				//System.out.println(clusterSet.get(minIndex));
				//System.out.println(clusterSet.get(currentIndex));
				
				int minI = (minIndex<currentIndex)?minIndex:currentIndex;
				int maxI = (minIndex>currentIndex)?minIndex:currentIndex;
				
				clusterSet.remove(maxI);
				clusterSet.remove(minI);
				
				BinaryTree<Integer> mergeResult = BinaryTree.merge(intermediar.get(minIndex), intermediar.get(currentIndex));
				intermediar.add(mergeResult);
				
				intermediar.remove(maxI);
				intermediar.remove(minI);
				
				
			} else {
				//System.out.println("Add new cluster to stack");
				//System.out.println(clusterSet.get(minIndex));
				st.push(clusterSet.get(minIndex));
			}
		}
		if(intermediar.size() > 0)
			output = intermediar.get(0);

	}
	
	public BinaryTree<Integer> getOutput(){
		return output;
	}
	
	public ArrayList<T> getPoints(){
		return points;
	}
}
