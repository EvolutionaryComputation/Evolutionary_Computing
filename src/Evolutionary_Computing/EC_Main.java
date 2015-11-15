package Evolutionary_Computing;


public class EC_Main {

	public static void main(String[] args) {
		EC_Tree.SpawnTrees();
		//EC_Tree.SpawnTree();
		//EC_Tree.TargetTree();
		//System.out.println(EC_Tree.FitnessValue(EC_Tree.TargetTree()));
		EC_Tree.RemoveTrees();
		EC_Tree.FillEmptyTrees();
	}

}
