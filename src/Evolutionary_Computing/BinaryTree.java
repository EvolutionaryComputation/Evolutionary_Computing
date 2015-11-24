package Evolutionary_Computing;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

public class BinaryTree {
	public Node root;

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

	public boolean lookup(char data) {
		return lookup(root, data);
	}

	private boolean lookup(Node node, char data) {
		if (node == null) {
			return false;
		}

		double sData = Double.parseDouble(Character.toString(data));

		if (sData == node.number || data == node.op) {
			return true;
		} else {
			if (node.left != null) {
				lookup(node.left, data);
			}
			if (node.right != null) {
				lookup(node.right, data);
			}
		}
		return false;
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
		/*if (root == null)
		{
			return new BinaryTree();
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
				if (!Double.isInfinite(s2 / s1))
				{
					st.push(s2 / s1);
				}
				else
				{
					st.push(1.0);
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
