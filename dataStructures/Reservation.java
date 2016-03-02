package dataStructures;

public class Reservation {
	private int time;
	private Node node;
	
	public Reservation(int time, Node node) {
		this.time = time;
		this.node = node;
	}
	
	public int getTime() {
		return time;
	}
	
	public Node getNode() {
		return node;
	}
}
