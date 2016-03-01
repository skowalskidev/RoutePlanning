package routeExecution;

import java.util.Vector;

import dataStructures.Map;
import dataStructures.Node;
import pathFinding.PathFinding;
import virtualRobot.Robot;

public class RouteExecution {
	private Map map;
	private Robot robots[];
	private PathFinding pathFinding;
	
	public static final int NORTH = 0;
	public static final int SOUTH = 1;
	public static final int EAST = 2;
	public static final int WEST = 3;
	
	public RouteExecution(Map map, Robot robots[]){
		this.map = map;
		this.robots = robots;
		
		pathFinding = new PathFinding(map);
	}
	
	public void executePath(Node start, Node goal){
		/*//Get set of nodes on the path to the goal
		Vector<Node> path = pathFinding.GetPath(start, goal);
		
		//Translate set of nodes to movement directions
		for(int i = 0; i < path.size(); i++){
			if ()
		}*/
	}
	
	public void run(){
		
	}
}
