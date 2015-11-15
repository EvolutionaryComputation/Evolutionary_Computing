package Evolutionary_Computing;

import java.util.Arrays;

public class EC_Tree {

	private static double deleteIndex;
	
	public static void SpawnTrees()
	{
		GlobalVars.trees = new BinaryTree[14];
		//System.out.println(GlobalVars.trees.length);
		for (int i = 0; i < GlobalVars.trees.length; i++)
		{
			GlobalVars.trees[i] = new BinaryTree();
			GlobalVars.trees[i].Spawn();
			//System.out.print(GlobalVars.trees[i].Depth());
		}
	}
	
	public static void FillEmptyTrees()
	{
		for (int i = 0; i < GlobalVars.trees.length; i++)
		{
			if (GlobalVars.trees[i] == null)
			{
				GlobalVars.trees[i] = new BinaryTree();
				GlobalVars.trees[i].Spawn();
			}
			GlobalVars.trees[i].printTree();
		}
	}
	
	// Spawn a Binary Tree with the parameters we're looking for
	// OUT OF DATE: USE SPAWNTREES() INSTEAD
	public static void SpawnTree()
	{
		// JUST TEMPORARY STUFF BELOW
		BinaryTree tree = new BinaryTree();
		/*Node m = new Node('-');
		Node n = new Node('*');
		Node o = new Node(8);
		tree.insert(m);
		tree.insert(new Node('-'));
		tree.insert(o);
		tree.insert(n);
		tree.insert(new Node(2));
		tree.insert(new Node(2));
		tree.insert(new Node());
		System.out.println(tree.nodeDepth(tree.root, m, 0));
		System.out.println(tree.nodeDepth(tree.root, n, 0));
		System.out.println(tree.nodeDepth(tree.root, o, 0));*/
		tree.Spawn();
		//tree.SimpleSpawn();
		tree.printTree();
		System.out.println("Depth: " + tree.Depth());
		System.out.println("Size: " + tree.Size());
		tree.doMath(GlobalVars.trainingData[1]);
		
	}
	
	public static BinaryTree TargetTree()
	{
		BinaryTree targetTree = new BinaryTree();
		Node m = new Node('*');
		targetTree.insert(new Node('/'));
		targetTree.insert(new Node('-'));
		targetTree.insert(new Node(2));
		targetTree.insert(m);
		m.left = new Node();
		m.right = new Node();
		targetTree.insert(new Node(1));
		//targetTree.printTree();

		//System.out.println("Depth: " + targetTree.Depth());
		//System.out.println("Size: " + targetTree.Size());
		//System.out.println(targetTree.doMath(GlobalVars.var[0]));
		return targetTree;
	}
	
	// Handle converting a string/formula into a tree
	
	// Mutate the tree
	public static void MutateTree(BinaryTree tree)
	{
		
	}
	
	// Have two trees reproduce together
	public static void Reproduce(BinaryTree tree_1, BinaryTree tree_2)
	{
		
	}
	
	public static void RemoveTrees()
	{
		double[] fitnessVals = new double[GlobalVars.trees.length];
		double[] sortedVals = new double[GlobalVars.trees.length];
		for (int i = 0; i < GlobalVars.trees.length; i++)
		{
			fitnessVals[i] = FitnessValue(GlobalVars.trees[i]);
		}
		System.arraycopy(fitnessVals, 0, sortedVals, 0, fitnessVals.length);
		//sortedVals = fitnessVals;
		Arrays.sort(sortedVals);
		double indexLoc = sortedVals.length * 3 / 5;
		deleteIndex = sortedVals[(int)indexLoc];
		System.out.println(deleteIndex);
		
		for (int i = 0; i < GlobalVars.trees.length; i++)
		{
			if (fitnessVals[i] >= deleteIndex)
			{
				GlobalVars.trees[i] = null;
			}
		}
	}
	
	// Return the Fitness Value of a tree
	public static double FitnessValue(BinaryTree tree)
	{
		double fitnessTotal = 0.0;
		
		for (int i = 0; i < GlobalVars.trainingData.length; i++)
		{

			fitnessTotal += Math.abs(tree.doMath(GlobalVars.trainingData[i]) - TargetTree().doMath(GlobalVars.trainingData[i]));
			//fitnessTotal -= Math.abs(TargetTree().doMath(GlobalVars.var[i]));
			
		}		
		
		//System.out.println(fitnessTotal);
		return fitnessTotal;
	}
	
}
