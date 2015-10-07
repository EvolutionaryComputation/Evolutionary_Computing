package Evolutionary_Computing;


public class EC_Main {

	public static void main(String[] args) {
		BinaryTree tree = new BinaryTree();
		tree.insert(new Node(10));
		tree.insert(new Node('+'));
		tree.insert(new Node(12));
		tree.insert(new Node(13));
		tree.insert(new Node(14));
		tree.insert(new Node(15));
		tree.insert(new Node(16));
		tree.printTree();
		System.out.println("Depth: " + tree.Depth());
		System.out.println("Size: " + tree.Size());
	}

}
