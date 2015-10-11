package Evolutionary_Computing;

import java.util.Scanner;
import java.lang.*;
import java.util.*;

public class EC_Main {

	public static void main(String[] args) {
/*Add input data or random number generated into tree created by Brian.
 * Will take testers input data, generate a list of random data and  place into binary tree constructor.		
 */
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
		
		Scanner input = new Scanner(System.in);
		int data;
		int data2;
		
		System.out.print("Enter first training data: ");
		data = input.nextInt();
		
		System.out.print("Enter second training data range: ");
		data2 = input.nextInt();
		
		
		
		
	}

}
