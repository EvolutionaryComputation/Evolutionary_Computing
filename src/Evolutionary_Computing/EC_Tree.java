package Evolutionary_Computing;

import java.util.Arrays;

public class EC_Tree {

	private static double deleteIndex;
	private static double[] normalizedFitness = new double[GlobalVars.trees.length];
	
	public static BinaryTree targetTree = new BinaryTree();
	public static double lowFitness = 100000;
	public static double avgFitness = 0;
	
	private static double gensRun = 0;
	
	public static BinaryTree[] crossedTrees = new BinaryTree[100];
	
	private static int Random_ctrld(int min, int max) {
		int range = max - min + 1;
		return (int) (Math.random() * range) + min;
	}
	
	public static void SpawnTrees()
	{
		//GlobalVars.trees = new BinaryTree[100];
		//System.out.println(GlobalVars.trees.length);
		for (int i = 0; i < GlobalVars.trees.length; i++)
		{
			GlobalVars.trees[i] = new BinaryTree();
			GlobalVars.trees[i].Spawn();
			//System.out.print(GlobalVars.trees[i].Depth());
		}
	}
	
	private static int Ctrld_RandomIndex(int index, double[] a)
	{
		double tmpVal = 0;
		double chooseVal = Math.random();
		for (int j = 0; j < a.length; j++)
		{
			if (tmpVal >= chooseVal)
			{
				if (a[j] != 0)
				{
					index = j;
				}
				else
				{
					index = j - 1;
				}
				break;
			}
			else 
			{
				tmpVal += a[j];
			}
		}
		return index;
	}
	
	public static void FillEmptyTrees()
	{
		
		int timesRun = 0;
		int loopsRun = 0;
		
		int strtIndex1 = 0;
		int crIndex1 = 0;
		
		int strtIndex2 = 0;
		int crIndex2 = 0;
		
		int mutIndex = 0;
		
		crossedTrees = null;
		crossedTrees = new BinaryTree[100];
		
		// ERROR IS BECAUSE TREE DOESN'T EXIST, WILL BE FIXED WHEN EVERYTHING IS SORTED
		for (int i = 0; i < GlobalVars.trees.length; i++)
		{
			if (GlobalVars.trees[i] == null)
			{
				GlobalVars.trees[i] = new BinaryTree();
				
				// Other algorithm, assuming only small number are kept
				
				if (timesRun == (loopsRun * 4) || timesRun == ((loopsRun * 4) + 1))
				{	
					
					strtIndex1 = Ctrld_RandomIndex(strtIndex1, normalizedFitness);
					crIndex1 = Ctrld_RandomIndex(crIndex1, normalizedFitness);
					
					/*while (GlobalVars.trees[strtIndex1].Equals(GlobalVars.trees[crIndex1]))
					{
						strtIndex1 = Ctrld_RandomIndex(strtIndex1, normalizedFitness);
					}*/
					
					/*System.out.println("st " + strtIndex1 + " || ");
					GlobalVars.trees[strtIndex1].printTree();
					System.out.println("cr " + crIndex1 + " || ");
					GlobalVars.trees[crIndex1].printTree();*/
					GlobalVars.trees[i] = Crossover(GlobalVars.trees[strtIndex1], GlobalVars.trees[crIndex1]);
				}
				//else if (timesRun == ((loopsRun * 4) + 1))
				/*else if (timesRun == ((loopsRun * 4) + 1))
				{
					double tmpVal = 0;
					double chooseVal = Math.random();
					for (int j = 0; j < normalizedFitness.length; j++)
					{
						if (tmpVal >= chooseVal)
						{
							strtIndex2 = j;
							//System.out.println(j);
							break;
						}
						else 
						{
							tmpVal += normalizedFitness[j];
							//System.out.println(tmpVal);
						}
					}
					chooseVal = Math.random();
					for (int j = 0; j < normalizedFitness.length; j++)
					{
						if (tmpVal >= chooseVal)
						{
							crIndex2 = j;
							//System.out.println(j);
							break;
						}
						else 
						{
							tmpVal += normalizedFitness[j];
							//System.out.println(tmpVal);
						}
					}
					GlobalVars.trees[i] = Crossover(GlobalVars.trees[strtIndex2], GlobalVars.trees[crIndex2]);
				}*/
				else if (timesRun == ((loopsRun * 4) + 2))
				{
					mutIndex = Ctrld_RandomIndex(mutIndex, normalizedFitness);
					GlobalVars.trees[i] = MutateTree(GlobalVars.trees[mutIndex]);
					//GlobalVars.trees[i] = Crossover(GlobalVars.trees[1], GlobalVars.trees[0]);
				}
				else
				{
					GlobalVars.trees[i].Spawn();
					loopsRun++;
				}
				
				
				
				
				
				
				
				
				
				/*
				//if (timesRun == (loopsRun * 4))
				if (timesRun == 0)
				{
					while (GlobalVars.trees[strtIndex1].Equals(GlobalVars.trees[crIndex1]))
					{

						if (GlobalVars.trees[crIndex1+1] == null)
						{
							break;
						}
						else
						{
							crIndex1++;
						}
					}
					//GlobalVars.trees[curIndex].printTree();
					//System.out.println("lENGTH: " + GlobalVars.trees.length + " || CUR: " + curIndex);
					//GlobalVars.trees[curIndex+1].printTree();
					
					GlobalVars.trees[i] = Crossover(GlobalVars.trees[strtIndex1], GlobalVars.trees[crIndex1]);
					/*for (int j = 0; j < crossedTrees.length; j++)
					{
						if (crossedTrees[j] != null)
						{
							if (GlobalVars.trees[i].Equals(crossedTrees[j]))
							{
								GlobalVars.trees[i].Spawn();
							}
						}
					}
					//crossedTrees[i] = GlobalVars.trees[i]; 
					strtIndex1++;
					//GlobalVars.trees[i].printTree();
				}
				//else if (timesRun == ((loopsRun * 4) + 1))
				else if (timesRun == 1)
				{
					while (GlobalVars.trees[strtIndex2].Equals(GlobalVars.trees[crIndex2]))
					{
						if (GlobalVars.trees[crIndex2+1] == null)
						{
							break;
						}
						else
						{
							crIndex2++;
						}
						
					}
					GlobalVars.trees[i] = Crossover(GlobalVars.trees[crIndex2], GlobalVars.trees[strtIndex2]);
					//crossedTrees[i] = GlobalVars.trees[i]; 
					strtIndex2++;
					//GlobalVars.trees[i].printTree();
				}
				else if (timesRun == ((loopsRun * 4) + 2))
				{
					GlobalVars.trees[i] = MutateTree(GlobalVars.trees[mutIndex]);
					//GlobalVars.trees[i] = Crossover(GlobalVars.trees[1], GlobalVars.trees[0]);
					mutIndex++;
				}
				else
				{
					GlobalVars.trees[i].Spawn();
					loopsRun++;
				}
			
				*/
				timesRun++;
			}
			//GlobalVars.trees[i].printTree();
		}
		
		// ugh
		
		boolean isEq = false;
		
		for (int i = 0; i < GlobalVars.trees.length; i++)
		{
			isEq = false;
			for (int j = 0; j < GlobalVars.trees.length; j++)
			{
				//if (GlobalVars.trees[i].Equals(GlobalVars.trees[j] ))
				if (FitnessValue(GlobalVars.trees[i]) == FitnessValue(GlobalVars.trees[j]) || 
						GlobalVars.trees[i].gensLived >= 100)
				{
					if (isEq)
					{
						GlobalVars.trees[i] = null;
						GlobalVars.trees[i] = new BinaryTree();
						GlobalVars.trees[i].Spawn();
						//GlobalVars.trees[i].printTree();
						break;
					}
					isEq = true;
				}
			}
		}
		
		gensRun++;
		
		if (gensRun >= 250)
		{
			gensRun = 0;
			SpawnTrees();
		}

		//Crossover(GlobalVars.trees[0], GlobalVars.trees[1]);
	}
	
	// Spawn a Binary Tree with the parameters we're looking for
	// DEPRECATED: USE SPAWNTREES() INSTEAD
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
	
	public static void TargetTree()
	{
		targetTree = new BinaryTree();
		Node m = new Node('*');
		targetTree.insert(new Node('/'));
		targetTree.insert(new Node('-'));
		targetTree.insert(new Node(2));
		targetTree.insert(m);
		m.left = new Node();
		m.right = new Node();
		targetTree.insert(new Node(1));		
		
		/*BinaryTree copyTest = new BinaryTree();
		copyTest = targetTree.copyTree();
		//copyTest.root.op = '+';
		
		System.out.println(targetTree.Equals(copyTest));*/
		
		/*targetTree.printTree();
		
		int i = 1;
		
		Node tmpN = targetTree.getNode("*");
		if (tmpN != null)
		{
			System.out.println("Found: " + tmpN.type);
			tmpN.op = '/';
			targetTree.printTree();
		}*/

		//System.out.println("Depth: " + targetTree.Depth());
		//System.out.println("Size: " + targetTree.Size());
		//System.out.println(targetTree.doMath(GlobalVars.var[0]));
		//return targetTree;
	}
	
	// Mutate the tree
	public static BinaryTree MutateTree(BinaryTree t)
	{
		BinaryTree tmptree = new BinaryTree();
		
		tmptree = t.copyTree();
		//tmptree = TargetTree();
		
		Node tmpNode;
		
		int varstoChange = Random_ctrld(1, 4);
		int numToChange = Random_ctrld(0, 14);
		int changeTo = Random_ctrld(0, 14);
		
		String dataToFind = "";
		
		for (int i = 0; i < varstoChange; i++)
		{
			// See what data you should try to find first
			if (numToChange >= 0 && numToChange <= 9) {
				dataToFind = Integer.toString(numToChange);
			} else if (numToChange == 10) {
				dataToFind = "x";
			} else if (numToChange == 11) {
				dataToFind = "+";
			} else if (numToChange == 12) {
				dataToFind = "-";
			} else if (numToChange == 13) {
				dataToFind = "*";
			} else if (numToChange == 14) {
				dataToFind = "/";
			}
			
			// Loop inside the tree until you find a piece of data you can replace
			while (tmptree.getNode(dataToFind) == null)
			{
				numToChange = Random_ctrld(0, 14);
				
				if (numToChange >= 0 && numToChange <= 9) {
					dataToFind = Integer.toString(numToChange);
				} else if (numToChange == 10) {
					dataToFind = "x";
				} else if (numToChange == 11) {
					dataToFind = "+";
				} else if (numToChange == 12) {
					dataToFind = "-";
				} else if (numToChange == 13) {
					dataToFind = "*";
				} else if (numToChange == 14) {
					dataToFind = "/";
				}
			}
			

			// Store the node with the data we're looking to change here
			tmpNode = tmptree.getNode(dataToFind);

			// Actually find the piece of data and "mutate" it.
			// If random 0-10, mutate operands to operands
			// If random 11-14, mutate operators to operators
			if (numToChange >= 0 && numToChange <= 10) {
				changeTo = Random_ctrld(0, 10);
				if (changeTo >= 0 && changeTo <= 9)
				{
					tmpNode.type = NodeType.NUMBER;
					tmpNode.number = changeTo;
				}
				else
				{
					tmpNode.number = -1;
					tmpNode.type = NodeType.VAR;
				}
			} 
			else 
			{
				changeTo = Random_ctrld(11, 14);
				
				if (changeTo == 11) {
					tmpNode.op = '+';
				} else if (changeTo == 12) {
					tmpNode.op = '-';
				} else if (changeTo == 13) {
					tmpNode.op = '*';
				} else if (changeTo == 14) {
					tmpNode.op = '/';
				}
			}			
			
			//System.out.println("not found " + dataToFind);
		}
		
		
		
		
		//t.printTree();
		
		//System.out.println(t.Size());
		
		return tmptree;
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
		
		//t1.printTree();
		//t2.printTree();
		
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
	
	public static void RemoveAndSort()
	{
		QuickSort sorter = new QuickSort();
		
		lowFitness = 100000;
		avgFitness = 0;
		
		double[] fitnessVals = new double[GlobalVars.trees.length];
		double[] sortedVals = new double[GlobalVars.trees.length];
		double totalFitness = 0;
		
		for (int i = 0; i < GlobalVars.trees.length; i++)
		{
			fitnessVals[i] = FitnessValue(GlobalVars.trees[i]);
			if (fitnessVals[i] < lowFitness)
			{
				lowFitness = fitnessVals[i];
			}
			avgFitness += fitnessVals[i];
		}
		System.arraycopy(fitnessVals, 0, sortedVals, 0, fitnessVals.length);
		//sortedVals = fitnessVals;
		//Arrays.sort(sortedVals);
		sorter.sort(sortedVals);
		double indexLoc = sortedVals.length * 2 / 10;
		deleteIndex = sortedVals[(int)indexLoc];
		
		//System.out.println(deleteIndex);
		
		avgFitness /= fitnessVals.length;
		System.out.println(lowFitness);
		System.out.println(avgFitness);
		
		
		
		// TESTING STUFF
		
		for (int i = 0; i < GlobalVars.trees.length; i++)
		{
			if (GlobalVars.trees[i] != null)
			{
				//System.out.print("Tree #" + i + ": ");
				//System.out.println(FitnessValue(GlobalVars.trees[i]));
				//System.out.println(fitnessVals[i]);
				//GlobalVars.trees[i].printTree();
			}
		}
		
		// Sort the trees in the array by fitness
		sorter.sort(fitnessVals, GlobalVars.trees);
		System.out.println();
		
		// Remove trees that aren't fit enough
		for (int i = 0; i < GlobalVars.trees.length; i++)
		{
			/*if (fitnessVals[i] > deleteIndex)
			{
				System.out.println(i);
				GlobalVars.trees[i] = null;
			}*/
			if (i > indexLoc)
			{
				GlobalVars.trees[i] = null;
			}
			else
			{
				totalFitness +=  1 / fitnessVals[i];
				normalizedFitness[i] = 1 / fitnessVals[i];
				//System.out.println(normalizedFitness[i]);
			}
		}
		
		for (int i = 0; i < normalizedFitness.length; i++)
		{
			if (normalizedFitness[i] != 0)
			{
				normalizedFitness[i] = normalizedFitness[i] / totalFitness;
				//System.out.println(normalizedFitness[i] / totalFitness);
			}
		}
		
		
		for (int i = 0; i < GlobalVars.trees.length; i++)
		{
			if (GlobalVars.trees[i] != null)
			{
				//System.out.print("Tree #" + i + ": ");
				//System.out.println(FitnessValue(GlobalVars.trees[i]));
				
				//System.out.println(fitnessVals[i]);
				
				GlobalVars.trees[i].printTree();
			}
		}
	}
	
	// Return the Fitness Value of a tree
	// In this case, LOWER fitness is better
	public static double FitnessValue(BinaryTree tree)
	{
		double fitnessTotal = 0.0;
		
		for (int i = 0; i < GlobalVars.trainingData.length; i++)
		{

			fitnessTotal += Math.abs(tree.doMath(GlobalVars.trainingData[i]) - targetTree.doMath(GlobalVars.trainingData[i]));
			//fitnessTotal -= Math.abs(TargetTree().doMath(GlobalVars.var[i]));
		}		
		
		//System.out.println(fitnessTotal);
		return fitnessTotal;
	}
	
	
	
}

