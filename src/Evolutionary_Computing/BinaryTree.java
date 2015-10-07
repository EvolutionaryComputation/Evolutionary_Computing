package Evolutionary_Computing;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree {
	private Node root;
	
	void BinaryTree()
	{
		root = null;
	}
	
	public boolean lookup(char data)
	{
		return lookup(root, data);
	}
	
	private boolean lookup(Node node, char data)
	{
		if (node == null)
		{
			return false;
		}

		double sData = Double.parseDouble(Character.toString(data));
		
		if (sData == node.number || data == node.op)
		{
			return true;
		}
		else
		{
			if (node.left != null)
			{
				lookup(node.left, data);
			}
			if (node.right != null)
			{
				lookup(node.right, data);
			}
		}
		return false;
	}
	
	/*public void insert (double data)
	{
		root = insert(root, data);
	}
	
	private Node insert(Node node, double data)
	{
		if (node == null)
		{
			node = new Node(data);
		}
		else
		{
			if (node.left == null)
			{
				node.left = insert(node.left, data);
			}
			else
			{
				node.right = insert(node.right, data);
			}
		}
		
		return node;
	}*/
	
	// Breadth First Search queue algorithm
	// Always inserts the item into the first available left-most spot 
	// Source: http://stackoverflow.com/questions/16630823/binary-tree-insert-algorithm
	public void insert(Node node) {
	    if(root == null) {
	        root = node;
	        return;
	    }

	    /* insert using Breadth-first-search (queue to the rescue!) */
	    Queue<Node> queue = new LinkedList<Node>();
	    queue.offer(root);

	    while(true) {
	        Node n = queue.remove();
	        if(!n.visited)
	        {
	        	if (n.type == NodeType.NUMBER)
	        	{
	        		System.out.println(n.number);
	        	}
	        	else if (n.type == NodeType.OPERATOR)
	        	{
	        		System.out.println(n.op);
	        	}
	        	else
	        	{
	        		// REPLACE WITH VAR
	        		System.out.println(n.var);
	        	}
	        }
	        n.visited = true;

	        if(n.left == null) {
	            n.left = node;
	            break;
	        } else {
	            queue.offer(n.left);
	        }           

	        if(n.right == null) {
	            n.right = node;
	            break;
	        } else {
	            queue.offer(n.right);
	        }
	    }
	}
	
	public void printTree()
	{
		printTree(root);
		System.out.println();
	}
	
	// Recursive print out of tree
	private void printTree(Node node)
	{
		if (node == null)
		{
			return;	
		}
		
		printTree(node.left);
		if (node.type == NodeType.NUMBER)
		{
			System.out.print(node.number + " ");
		}
		else if (node.type == NodeType.OPERATOR)
		{
			System.out.print(node.op + " ");
		}
		else
		{
			System.out.print(node.var + " ");
		}
		printTree(node.right);
	}
	
	public int Depth()
	{
		return Depth(root);
	}
	
	private int Depth(Node node)
	{
		if (node == null)
		{
			return 0;
		}
		else
		{
			int depth_L = Depth(node.left);
			int depth_R = Depth(node.right);
			
			if (depth_L > depth_R)
			{
				return depth_L + 1;
			}
			else
			{
				return depth_R + 1;
			}
		}
	}
	
	public int Size()
	{
		return Size(root);
	}
	
	private int Size(Node node)
	{
		if (node == null)
		{
			return 0;
		}
		else
		{
			return (Size(node.left) + 1 + Size(node.right));
		}
	}
}
