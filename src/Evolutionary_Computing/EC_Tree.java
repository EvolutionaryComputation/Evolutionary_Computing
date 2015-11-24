package Evolutionary_Computing;

import java.util.Arrays;

public class EC_Tree {

	private static double deleteIndex;
	
	public static void SpawnTrees()
	{
		GlobalVars.trees = new BinaryTree[10];
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
		int timesRun = 0; 
		
		for (int i = 0; i < GlobalVars.trees.length; i++)
		{
			if (GlobalVars.trees[i] == null)
			{
				GlobalVars.trees[i] = new BinaryTree();
				if (timesRun == 0)
				{
					GlobalVars.trees[i] = Crossover(GlobalVars.trees[0], GlobalVars.trees[1]);
					GlobalVars.trees[i].printTree();
				}
				else if (timesRun == 1)
				{
					GlobalVars.trees[i] = Crossover(GlobalVars.trees[1], GlobalVars.trees[0]);
					GlobalVars.trees[i].printTree();
				}
				else if (timesRun == 2)
				{
					// WHERE MUTATE SHOULD BE
					//GlobalVars.trees[i] = Crossover(GlobalVars.trees[1], GlobalVars.trees[0]);
				}
				else
				{
					GlobalVars.trees[i].Spawn();
					timesRun = -1;
				}
				timesRun++;
			}
			//GlobalVars.trees[i].printTree();
		}

		//Crossover(GlobalVars.trees[0], GlobalVars.trees[1]);
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
		//tree.printTree();
		//System.out.println("Depth: " + tree.Depth());
		//System.out.println("Size: " + tree.Size());
		//tree.doMath(GlobalVars.trainingData[1]);
		
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
	
	// Mutate the tree
	public static void MutateTree(BinaryTree t)
	{
		
	}
	
	// Have two trees reproduce together
	public static BinaryTree Crossover(BinaryTree t1, BinaryTree t2)
	{
		BinaryTree tmptree = new BinaryTree();
		BinaryTree tmptree2 = new BinaryTree();
		// Cutoff at left on t1
		// Cutoff at right on t2
		// Combine to make new tree
		
		//System.out.println(t1.root.left.op);
		//System.out.println(t1.Depth());
		
		//t1.printTree();
		//t2.printTree();
		
		tmptree = t1.copyTree();
		tmptree2 = t2.copyTree();
		
		tmptree.root.left = null;
		//tmptree.printTree();
		
		tmptree.root.left = tmptree2.copyTreeFromNode(tmptree2.root.left).root;
		
		//tmptree.printTree();
		//tmptree2.printTree();
		
		//System.out.println();
		
		t1.printTree();
		t2.printTree();
		
		//tmptree = t1.copyTreeFromNode(t1.root.left);
		//tmptree.printTree();
		//tmptree = t1.copyTreeFromNode(t1.root.right);
		
		//tmptree.root.right = null;
		//t1.printTree();
		
		//tmptree.printTree();
		
		//System.out.print("Copied tree: " + t1.copyTree(t1.root).op);
		//System.out.print("Copied tree: " + t1.copyTree(t1.root).right);
		
		//System.out.println("tmp dpth " + tmptree.Depth());
		
		return tmptree;
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
		//System.out.println(deleteIndex);
		
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
