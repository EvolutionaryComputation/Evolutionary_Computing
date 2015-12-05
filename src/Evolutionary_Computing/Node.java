package Evolutionary_Computing;

enum NodeType { NUMBER, OPERATOR, VAR, NONE }

public class Node {
	NodeType type;
	double number = -1;
	char op;
	boolean visited = false;
	Node left;
	Node right;
	
	public Node (double val)
	{
		type = NodeType.NUMBER;
		this.number = val;
	}
	
	public Node (char oprtr)
	{
		type = NodeType.OPERATOR;
		this.op = oprtr;
	}
	
	public Node ()
	{
		type = NodeType.VAR;
		this.number = -1;
	}
}
