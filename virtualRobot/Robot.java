package virtualRobot;

import dataStructures.Map;
import dataStructures.Node;
import routeExecution.RouteExecution;

public class Robot {
	private int robotID;
	private Map map;
	private Node currentNode;
	private int pathSequence[];
	private int pathSequenceProgress = 0;//Index of the next move
	
	public Robot(int robotID, Map map, Node currentNode, int pathSequence[]){
		this.robotID = robotID;
		this.map = map;
		this.currentNode = currentNode;
		this.pathSequence = pathSequence;
	}
	
	public void goToNextNode(){
		if (pathSequenceProgress >= pathSequence.length)
			return;//End of path
		
		switch (pathSequence[pathSequenceProgress]) {
		case RouteExecution.NORTH:
			moveUp();
			break;

		case RouteExecution.SOUTH:
			moveDown();	
			break;
					
		case RouteExecution.EAST:
			moveRight();
			break;
			
		case RouteExecution.WEST:
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
}
