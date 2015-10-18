package Evolutionary_Computing;

public class EC_Tree {

	// Spawn a Binary Tree with the parameters we're looking for
	public static void SpawnTree()
	{
		// JUST TEMPORARY STUFF BELOW
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
	
	// Mutate the tree
	public static void MutateTree(BinaryTree tree)
	{
		
	}
	
	// Have two trees reproduce together
	public static void Reproduce(BinaryTree tree_1, BinaryTree tree_2)
	{
		
	}
	
	// Return the Fitness Value of a tree
	public static float FitnessValue(BinaryTree tree)
	{
		return 0;
	}
	
}
