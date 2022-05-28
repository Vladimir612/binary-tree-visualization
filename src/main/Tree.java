package main;

public class Tree  {
	TreeNode root;
	TreeNode[][] matrixTree;
	int treeDepth;
	
	public Tree() {}
	
	public Tree(TreeNode root) {
		this.root = root;
	}
	
	public int getTreeDepth(TreeNode node) {
		if(node == null) return 0;
		
		int lDepth = getTreeDepth(node.leftChild);
		int dDepth = getTreeDepth(node.rightChild);
		
		if(lDepth > dDepth) return lDepth + 1;
		else return dDepth + 1;
	}
	
	private void formEmptyMatrix() {
		matrixTree = new TreeNode[treeDepth][(int) Math.pow(2, treeDepth) - 1];
		TreeNode c1 = new TreeNode(Integer.MIN_VALUE);
		
		for(int i = 0; i < treeDepth; i++) {
			for(int j = 0; j < (int) Math.pow(2, treeDepth) - 1; j++) {
				matrixTree[i][j] = c1;
			}
		}
	}
	
	private void formMatrix(TreeNode node, int level) {
		if(node == null) return;
		int i = treeDepth - level;
		if(i == 0) {
			matrixTree[i][(int) ((Math.pow(2, treeDepth) - 1) / 2)] = node;
		} else {
			int matrixWidth = (int) Math.pow(2, treeDepth) - 1;
			for(int j = 0; j < matrixWidth; j++) {
				if(matrixTree[i - 1][j].leftChild == node) {
					matrixTree[i][j - (int) Math.pow(2, level) / 2] = node; 
					break;
				}else if(matrixTree[i - 1][j].rightChild == node) {
					matrixTree[i][j + (int) Math.pow(2, level) / 2] = node;
					break;
				} else continue;
			}
		}
		
		formMatrix(node.leftChild, level - 1);
		formMatrix(node.rightChild, level - 1);
	}
	
	private void printMatrix() {
		int depth = getTreeDepth(this.root);
		
		for(int i = 0; i < depth; i++) {
			for(int j = 0; j < (int) Math.pow(2, depth) - 1; j++) {
				if(matrixTree[i][j].data == Integer.MIN_VALUE) System.out.print("  ");
				else {
					if(matrixTree[i][j].data < 0) {
						System.out.print(matrixTree[i][j].data);
					} else {
						System.out.print(" " + matrixTree[i][j].data);
					}
				} 
			}
			System.out.println();
			System.out.println();
		}
	}
	
	public void print() {
		if(root == null) System.out.println("Tree is empty");
		treeDepth = getTreeDepth(root);
		formEmptyMatrix();
		formMatrix(root, treeDepth);
		printMatrix();
	}
	
	//helper functions to add elements to tree
	public void addBstArr(int... numbers) {
		if(numbers.length == 0) {
			System.out.println("You didn't pass any number!");
			return;
		} 
		
		root = new TreeNode(numbers[0]);
		
		for(int i = 1; i < numbers.length; i++) addBst(root, numbers[i]);
	}
	
	private void addBst(TreeNode node, int number) {
		if(node == null) return;
		
		if(number > node.data) {
			if(node.rightChild == null)
				node.rightChild = new TreeNode(number);
			else addBst(node.rightChild, number);
		} else {
			if(node.leftChild == null)
				node.leftChild = new TreeNode(number);
			else addBst(node.leftChild, number);
		}
	}
}
