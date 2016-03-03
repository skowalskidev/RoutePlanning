package virtualRobot;

import java.util.Vector;

import dataStructures.GeneralProtocol;
import dataStructures.Map;
import dataStructures.Node;
import dataStructures.Reservation;

public class Robot {
	private int robotID;
	private Map map;
	private Node currentNode;
	private Vector<Integer> pathSequence;
	private int pathSequenceProgress = 0;//Index of the next move
	
	/**
	 * After robot stops at goal and remains stationary(no jobs) it becomes an obstacle and has the node it resides at reserved until the next move
	 */
	private Reservation lastReservation;
	
	
	public Robot(int robotID, Map map){
		this.robotID = robotID;
		this.map = map;
	}
	
	public void setUpPath(Node currentNode, Vector<Integer> pathSequence){
		this.currentNode = currentNode;
		this.pathSequence = pathSequence;
	}
	
	public void goToNextNode(){
		if (pathSequenceProgress >= pathSequence.size())
			return;//End of path
		
		switch (pathSequence.get(pathSequenceProgress++)) {
		case GeneralProtocol.NORTH:
			moveUp();
			break;

		case GeneralProtocol.SOUTH:
			moveDown();	
			break;
					
		case GeneralProtocol.EAST:
			moveRight();
			break;
			
		case GeneralProtocol.WEST:
			moveLeft();
			break;
		}
	}
	
	private void move(Node destinationNode){
		currentNode.status = Node.GOALPATH;
		currentNode = destinationNode;
		currentNode.status = " " + robotID + " ";
	}
	
	public void moveUp() {
		move(map.getAboveNode(currentNode));
	}
	
	public void moveDown() {
		move(map.getBelowNode(currentNode));
	}
	
	public void moveLeft() {
		move(map.getLeftNode(currentNode));
	}
	
	public void moveRight() {
		move(map.getRightNode(currentNode));
	}
	
	public void setLastReservation(Reservation lastReservation) {
		this.lastReservation = lastReservation;
	}
	
	public Reservation getLastReservation() {
		return lastReservation;
	}
	
	public int getID(){
		return robotID;
	}
}
