package Evolutionary_Computing;

enum NodeType { NUMBER, OPERATOR, VAR }

public class Node {
	NodeType type;
	double number;
	char var;
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
	}
}
