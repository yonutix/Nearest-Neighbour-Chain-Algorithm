package API;

import java.util.ArrayList;

public class BinaryTree<T> {
	
	boolean empty = true;

	BinaryTree<T> left = null;
	
	BinaryTree<T> right = null;

	T info = null;
	
	public BinaryTree(){
	}

	public BinaryTree(T val) {
		info = val;
		empty = false;
	}
	
	

	public void insertLeft(T val) {
		left = new BinaryTree<T>(val);
	}

	public void insertRight(T val) {
		right = new BinaryTree<T>(val);
	}

	public void removeLeft() {
		left = null;
	}

	public void removeRight() {
		right = null;
	}
	
	public BinaryTree<T> getLeft() {
		return left;
	}

	public void setLeft(BinaryTree<T> left) {
		this.left = left;
	}

	public BinaryTree<T> getRight() {
		return right;
	}

	public void setRight(BinaryTree<T> right) {
		this.right = right;
	}

	public T getInfo() {
		return info;
	}

	public void setInfo(T info) {
		this.info = info;
	}
	
	public static<T> BinaryTree<T> merge(BinaryTree<T> a, BinaryTree<T> b){
		BinaryTree<T> result = new BinaryTree<T>();
		result.setLeft(a);
		result.setRight(b);
		return result;
	}
	
	public String toString(){
		String dump;
		if(empty){
			dump = "*\n";
		}
		else{
			dump = info.toString() + "\n";
		}
		
		if(left != null){
			String[] lines = left.toString().split("\n");
			for(String line: lines){
				dump = dump + "| " + line + "\n";
			}
		}
		if(right != null){
			String[] lines = right.toString().split("\n");
			for(String line: lines){
				dump = dump + "| " + line + "\n";
			}
		}
		return dump;
	}
	
	public static<T> int getLevelCount(BinaryTree<T> tree){
		ArrayList<BinaryTree<T>> level1 = new ArrayList<BinaryTree<T>>();
		ArrayList<BinaryTree<T>> level2 = new ArrayList<BinaryTree<T>>();
		
		level1.add(tree);
		int count = 0;
		while(!level1.isEmpty()){
			count++;
			for(BinaryTree<T> bt: level1){
				if(bt.left != null)
					level2.add(bt.left);
				if(bt.right != null)
					level2.add(bt.right);
			}
			
			level1.clear();
			
			ArrayList<BinaryTree<T>> tmpLevel = level1;
			level1 = level2;
			level2 = tmpLevel;
			
		}
		return count;
	}
	
	public boolean isLeaf(){
		return ((left == null) && (right == null));
	}
	
	public static<T> int getLeafNr(BinaryTree<T> tree){
		ArrayList<BinaryTree<T>> level1 = new ArrayList<BinaryTree<T>>();
		ArrayList<BinaryTree<T>> level2 = new ArrayList<BinaryTree<T>>();
		
		level1.add(tree);
		int count = 0;
		while(!level1.isEmpty()){
			
			for(BinaryTree<T> bt: level1){
				if(bt.getInfo() != null){
					count++;
				}
				
				if(bt.left != null)
					level2.add(bt.left);
				if(bt.right != null)
					level2.add(bt.right);
			}
			
			level1.clear();
			
			ArrayList<BinaryTree<T>> tmpLevel = level1;
			level1 = level2;
			level2 = tmpLevel;
			
		}
		return count;
	}
	
	public static<T> ArrayList<BinaryTree<T>> getNodesOnLevel(BinaryTree<T> tree, int currentLevel, int level){
		ArrayList<BinaryTree<T>> result = new ArrayList<BinaryTree<T>>();
		if(currentLevel == level){
			result.add(tree);
			return result;
		}
		ArrayList<BinaryTree<T>> resultLeft = new ArrayList<BinaryTree<T>>();
		ArrayList<BinaryTree<T>> resultRight = new ArrayList<BinaryTree<T>>();
		
		if(tree.getLeft() != null){
			resultLeft = getNodesOnLevel(tree.getLeft(), currentLevel+1, level);
		}
		
		if(tree.getRight() != null){
			resultRight = getNodesOnLevel(tree.getRight(), currentLevel+1, level);
		}
		
		result.addAll(resultLeft);
		result.addAll(resultRight);
		
		return result;
	}
	
	public static<T> ArrayList<T> getAllLeaves(BinaryTree<T> tree){
		ArrayList<BinaryTree<T>> level1 = new ArrayList<BinaryTree<T>>();
		ArrayList<BinaryTree<T>> level2 = new ArrayList<BinaryTree<T>>();
		
		ArrayList<T> result = new ArrayList<T>();
		
		level1.add(tree);
		while(!level1.isEmpty()){
			
			for(BinaryTree<T> bt: level1){
				if(bt.getInfo() != null){
					result.add(bt.getInfo());
				}
				
				if(bt.left != null)
					level2.add(bt.left);
				if(bt.right != null)
					level2.add(bt.right);
			}
			
			level1.clear();
			
			ArrayList<BinaryTree<T>> tmpLevel = level1;
			level1 = level2;
			level2 = tmpLevel;
			
		}
		
		return result;
	}

}
