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
	
	public static<T> ArrayList<T> getNodesOnLevel(BinaryTree<T> tree, int currentLevel, int level){
		ArrayList<T> result = new ArrayList<T>();
		if(currentLevel == level){
			result.add(tree.getInfo());
			return result;
		}
		ArrayList<T> resultLeft = new ArrayList<T>();
		ArrayList<T> resultRight = new ArrayList<T>();
		
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

}
