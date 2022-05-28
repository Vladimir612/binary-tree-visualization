package test;

import main.Tree;

public class Test {
	public static void main(String[] args) {
		Tree tree = new Tree();
		
		tree.addBstArr(3, 8, 4, 9, 6, 2, 1, 3);
		
		tree.print();
	}
}
