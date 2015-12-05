package Evolutionary_Computing;


public class EC_Main {

	public static void main(String[] args) {
		EC_Tree.SpawnTrees();
		
		EC_Tree.TargetTree();
		//EC_Tree.SpawnTree();
		//EC_Tree.TargetTree();
		//System.out.println(EC_Tree.FitnessValue(EC_Tree.TargetTree()));
		

		
		for (int i=0; i < 100000; i++)
		{
			//System.out.println("times run: " + i);
			EC_Tree.RemoveAndSort();
			/*if (EC_Tree.FitnessValue(GlobalVars.trees[0]) == 0.0)
			{
				
				System.out.println("derp herp");
				System.out.println(EC_Tree.FitnessValue(GlobalVars.trees[0]));
				GlobalVars.trees[0].printTree();
				EC_Tree.targetTree.printTree();
				break;
			}*/
			
			if (GlobalVars.trees[0].Equals(EC_Tree.targetTree))
			{
				
				System.out.println("derp herp");
				System.out.println(EC_Tree.FitnessValue(GlobalVars.trees[0]));
				GlobalVars.trees[0].printTree();
				EC_Tree.targetTree.printTree();
				break;
			}
			EC_Tree.FillEmptyTrees();
		}
		
		/*for (int i = 0; i < GlobalVars.trees.length; i++)
		{
			if (GlobalVars.trees[i] != null)
			{
				System.out.print("Tree #" + i + ": ");
				System.out.println(EC_Tree.FitnessValue(GlobalVars.trees[i]));
			}
		}*/
		
	}

}
