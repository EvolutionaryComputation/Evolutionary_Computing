package Evolutionary_Computing;

public class GlobalVars {
	// Place for Global Variables/Settings to be stored
	
	// Variables
	
	// Max depth of trees to be spawned
	private static int maxHeight = 4;
	
	public static double[] trainingData = {2.0, 3.0, 4.0, -5.0, 10, -10, 2.5};
	
	// Array of Binary Trees (might change to list later)
	public static BinaryTree[] trees = new BinaryTree[100];;
	
	
	// Fields
	public static int MaxHeight()
	{
		return maxHeight;
	}
	
	
	
}
