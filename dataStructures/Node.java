package dataStructures;

public class Node {
	public int x;
	public int y;
	
	/**
	 * "x,y" free node
	 */
	public String status;
	public static final String GOALPATH = " - ";
	public static final String WALL = "   ";
	public static final String GOAL = " | ";
	public static final String START = " / ";
	
	public Node(int x, int y, String status) {
		this.x = x;
		this.y = y;
		this.status = status;
	}
}
