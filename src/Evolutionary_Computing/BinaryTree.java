package Evolutionary_Computing;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

public class BinaryTree {
	public Node root;
	public int gensLived = 0;

	public BinaryTree() {
		root = null;
	}
	
	public BinaryTree(Node n)
	{
		root = n;
	}
	
	/*public BinaryTree getLeft()
	{
		BinaryTree tmp = root.left;
	}
	
	public BinaryTree getRight()
	{
		BinaryTree tmp = root.right;
	}*/

	private int Random_ctrld(int min, int max) {
		int range = max - min + 1;
		return (int) (Math.random() * range) + min;
		
	}
	
	private double[] Rand_Chance(double w, double x, double y, double z)
	{
		double total = w + x + y + z;
		// Normalize values
		
		w /= total;
		x /= total;
		y /= total;
		z /= total;
		
		System.out.println(y);
		return new double[]{w, x, y, z};
	}
	
	private double ChanceToSpawn()
	{
		return 0;
	}

	public int nodeDepth(Node nRoot, Node node, int depthLvl) {

		if (nRoot == null) {
			return -1;
		}

		if (nRoot != node) {
			if (nRoot.left != null) {
				// System.out.println("happened");
				nodeDepth(nRoot.left, node, depthLvl + 1);
			}
			if (nRoot.right != null) {
				nodeDepth(nRoot.right, node, depthLvl + 1);
			}
			return Math.max(nodeDepth(nRoot.left, node, depthLvl + 1), nodeDepth(nRoot.right, node, depthLvl + 1));
		} else {
			System.out.println("test: " + depthLvl);
			return depthLvl;
		}

		/*
		 * if (nRoot != null) { if (nRoot != node) { if (nRoot.left != null) {
		 * //System.out.println("happened"); nodeDepth(nRoot.left, node,
		 * depthLvl + 1); } if (nRoot.right != null) { nodeDepth(nRoot.right,
		 * node, depthLvl + 1); } return -1; } else { System.out.println(
		 * "test: " + depthLvl); return depthLvl; } } else { return -1; }
		 */
		// return -1;
	}

	public void Spawn() {
		root = Spawn(root, 0);
	}

	private Node Spawn(Node node, int curDepth) {
		//System.out.println(curDepth);
		// Node tmpNode;
		// while (this.Depth() <= GlobalVars.MaxHeight())
		// {
		int s = -1;
		if (node == null) {
			s = Random_ctrld(11, 14);
				
			if (s == 11) {
				node = new Node('+');
			} else if (s == 12) {
				node = new Node('-');
			} else if (s == 13) {
				node = new Node('*');
			} else if (s == 14) {
				node = new Node('/');
			}
			//s = 11;
			//node = new Node('-');

		} else if (curDepth < (GlobalVars.MaxHeight() - 2)) {
			// System.out.println(nodeDepth(root, node, 0));
			s = Random_ctrld(0, 14);
		} else {
			s = Random_ctrld(0, 10);
		}

		if (node.left == null) {
			if (s >= 0 && s <= 9) {
				// Insert number
				node.left = new Node(s);
				// Stop
			} else if (s == 10) {
				// Insert Variable
				node.left = new Node();
				// Stop
			} else if (s == 11) {
				// Insert + node
				// tmpNode = new Node('+');
				// this.insert(tmpNode);
				node.left = new Node('+');
				curDepth += 1;
				Spawn(node.left, curDepth);
				// node.left = Spawn(node);
				// node.right = Spawn(node);
			} else if (s == 12) {
				// Insert / node
				// this.insert(new Node('-'));
				node.left = new Node('-');
				curDepth += 1;
				Spawn(node.left, curDepth);
			} else if (s == 13) {
				// Insert * node
				// this.insert(new Node('*'));
				node.left = new Node('*');
				curDepth += 1;
				Spawn(node.left, curDepth);
			} else if (s == 14) {
				// Insert / node
				// this.insert(new Node('/'));
				node.left = new Node('/');
				curDepth += 1;
				Spawn(node.left, curDepth);
			}
		}

		if (curDepth < (GlobalVars.MaxHeight() - 2)) {
			s = Random_ctrld(0, 14);
		} else {
			s = Random_ctrld(0, 10);
		}

		if (node.right == null) {
			if (s >= 0 && s <= 9) {
				node.right = new Node(s);
			} else if (s == 10) {
				node.right = new Node();
			} else if (s == 11) {
				node.right = new Node('+');
				curDepth += 1;
				Spawn(node.right, curDepth);
			} else if (s == 12) {
				node.right = new Node('-');
				curDepth += 1;
				Spawn(node.right, curDepth);
			} else if (s == 13) {
				node.right = new Node('*');
				curDepth += 1;
				Spawn(node.right, curDepth);
			} else if (s == 14) {
				node.right = new Node('/');
				curDepth += 1;
				Spawn(node.right, curDepth);
			}
		}

		// What I want to do:
		// Recursive?
		// Start at top, spawn an operator node
		// Spawn a node to the left, if it's an operator, spawn left and right
		// If it's an operand, don't spawn left or right
		// }
		return node;
	}

	// HOW TO: 
	// LOOKUP OPERATOR: getNode("*");
	// LOOKUP OPERAND: getNode("2");
	// LOOKUP VAR: getNode("x");
	public Node getNode(String data) {
		return getNode(root, data);
	}


	private Node getNode(Node node, String data) {
		if (node == null) {
			return null;
		}
		
		double sData = -1;
		
		
		Node nLeft = new Node();
		nLeft.type = NodeType.NONE;
		Node nRight = new Node();
		nRight.type = NodeType.NONE;
		
		try
		{
			sData = Double.parseDouble(data);
		}
		catch (NumberFormatException e)
		{
			sData = -1;
		}
		
		//System.out.println(sData);
		
		
		if (data.charAt(0) == node.op || 
			(data == "x" && node.type == NodeType.VAR) || 
			(sData == node.number && node.type == NodeType.NUMBER))
		{
			return node;
		}
		
		nLeft = getNode(node.left, data);
		nRight = getNode(node.right, data);
		
		/*if (data == "x" && node.type == NodeType.VAR)
		{
			System.out.println("hither");
			return node;
		}*/
		/*else 
		{
			if (node.left != null) 
			{
				getNode(node.left, data);
				
				//System.out.println(node.left.type);
			}
			if (node.right != null) 
			{
				getNode(node.right, data);
				//System.out.println(node.right.type);
			}
		}*/
		
		if (nLeft != null && 
			(data.charAt(0) == nLeft.op || 
			(data == "x" && nLeft.type == NodeType.VAR) || 
			(sData == nLeft.number && nLeft.type == NodeType.NUMBER)))
		{
			return nLeft;
		}
		if (nRight != null  && 
			(data.charAt(0) == nRight.op || 
			(data == "x" && nRight.type == NodeType.VAR) || 
			(sData == nRight.number && nRight.type == NodeType.NUMBER)))
		{
			return nRight;
		}
		
		return null;
	}
	
	public boolean Equals (BinaryTree t2)
	{
		return Equals (root, t2.root);
	}
	
	private boolean Equals (Node t1, Node t2)
	{
		
		if (t1 == null || t2 == null)
		{
			return true;
		}
		if (t1.type == t2.type)
		{
			if (t1.type == NodeType.OPERATOR)
			{
				if (t1.op == t2.op && 
					Equals(t1.left, t2.left) &&
					Equals(t1.right, t2.right))
				{
					return true;
				}
				else
				{
					return false;
				}
			}
			else if (t1.type == NodeType.NUMBER)
			{
				if (t1.number == t2.number && 
					Equals(t1.left, t2.left) &&
					Equals(t1.right, t2.right))
				{
					return true;
				}
				else
				{
					return false;
				}
			}
			else if (t1.type == NodeType.VAR)
			{
				if (Equals(t1.left, t2.left) &&
					Equals(t1.right, t2.right))
				{
					return true;
				}
				else
				{
					return false;
				}
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
		/*if (t1 == null || t2 == null)
		{
			return false;
		}
		if (t1.type != t2.type)
		{
			return false;
		}
		
		if (t1.type == NodeType.OPERATOR)
		{
			if (t1.op != t2.op)
			{
				return false;
			}
		}
		else if (t1.type == NodeType.NUMBER)
		{
			if (t1.number != t2.number)
			{
				return false;
			}
		}
		else if (t1.type == NodeType.VAR)
		{
			
		}
		else
		{
			if (t1.left != null)
			{
				Equals(t1.left, t2.left);
			}
			if (t1.right != null)
			{
				Equals(t1.right, t2.right);
			}
		}
		
		return false;*/
	}

	// Breadth First Search queue algorithm
	// Always inserts the item into the first available left-most spot
	// Source:
	// http://stackoverflow.com/questions/16630823/binary-tree-insert-algorithm
	public void insert(Node node) {
		if (root == null) {
			root = node;
			return;
		}

		/* insert using Breadth-first-search (queue to the rescue!) */
		Queue<Node> queue = new LinkedList<Node>();
		queue.offer(root);

		while (true) {
			Node n = queue.remove();
			if (!n.visited) {
				if (n.type == NodeType.NUMBER) {
					//System.out.println(n.number);
				} else if (n.type == NodeType.OPERATOR) {
					//System.out.println(n.op);
				} else {
					// REPLACE WITH VAR
					//System.out.println(GlobalVars.Var());
				}
			}
			n.visited = true;

			if (n.left == null) {
				n.left = node;
				break;
			} else {
				queue.offer(n.left);
			}

			if (n.right == null) {
				n.right = node;
				break;
			} else {
				queue.offer(n.right);
			}
		}
	}
	
	public BinaryTree copyTree()
	{
		
		return new BinaryTree(copyTree(root));
	}
	
	// Copy a tree from a specific node down
	// Can copy part of a tree
	public BinaryTree copyTreeFromNode(Node n)
	{
		
		return new BinaryTree(copyTree(n));
	}
	
	private Node copyTree(Node n)
	{
		/*if (n == null)
		{
			return null;
		}*/
		
		Node tmpN = n;
		
		if (n.type == NodeType.OPERATOR)
		{
			tmpN = new Node(n.op);
		}
		else if (n.type == NodeType.NUMBER)
		{
			tmpN = new Node(n.number);
		}
		else if (n.type == NodeType.VAR)
		{
			tmpN = new Node();
		}
		
		if (n.left != null)
		{
			tmpN.left = copyTree(n.left);
		}
		if (n.right != null)
		{
			tmpN.right = copyTree(n.right);
		}
		
		return tmpN;
	}

	public void printTree() {
		printTree(root);
		System.out.println();
	}

	// Recursive print out of tree
	private void printTree(Node node) {
		if (node == null) {
			return;
		}

		printTree(node.left);
		if (node.type == NodeType.NUMBER) {
			System.out.print(node.number + " ");
		} else if (node.type == NodeType.VAR) {
			System.out.print("x" + " ");
		} else if (node.type == NodeType.OPERATOR) {
			System.out.print(node.op + " ");
		}

		printTree(node.right);

	}

	public double doMath(double var) {
		Stack<Double> lifo = new Stack<Double>();
		doMath(root, lifo, var);
		//System.out.println(lifo.pop());
		return lifo.pop();
	}

	private void doMath(Node node, Stack<Double> st, double var) {
		if (node == null) {
			return;
		}

		doMath(node.left, st, var);
		doMath(node.right, st, var);
		if (node.type == NodeType.NUMBER) {
			st.push(node.number);
		} else if (node.type == NodeType.VAR) {
			st.push(var);
		} else if (node.type == NodeType.OPERATOR) {

			double s1 = (double) st.pop();
			double s2 = (double) st.pop();
			switch (node.op) {
			case '+':
				st.push(s2 + s1);
				break;
			case '-':
				st.push(s2 - s1);
				break;
			case '*':
				st.push(s2 * s1);
				break;
			case '/':
				if (s1 != 0.0)
				{
					st.push(s2 / s1);
				}
				else
				{
					st.push(1000.0);
				}
				break;
			}

		}
	}

	public int Depth() {
		return Depth(root);
	}

	private int Depth(Node node) {
		if (node == null) {
			return 0;
		} else {
			int depth_L = Depth(node.left);
			int depth_R = Depth(node.right);

			if (depth_L > depth_R) {
				return depth_L + 1;
			} else {
				return depth_R + 1;
			}
		}
	}

	public int Size() {
		return Size(root);
	}

	private int Size(Node node) {
		if (node == null) {
			return 0;
		} else {
			return (Size(node.left) + 1 + Size(node.right));
		}
	}
	
	
}
