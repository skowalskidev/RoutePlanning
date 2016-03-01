package pathFinding;

import java.util.Vector;

import dataStructures.CameFrom;
import dataStructures.Map;
import dataStructures.Node;

public class PathFinding {
	/**
	 * Node map
	 */
	Map map;
	
	private Node current;//Current node
	
	/**
	 * Explored node set
	 */
	private Vector<Node> explored = new Vector<Node>(0);
	/**
	 * Frontier node set
	 */
	private Vector<Node> frontier = new Vector<Node>(0);
	/**
	 * PathTrace node set
	 */
	private Vector<CameFrom> pathTrace = new Vector<CameFrom>(0);
	
	
	/**
	 * gCosts(cost of journey from start to current node) for explored & frontier nodes
	 */
	private int gCost = Integer.MAX_VALUE;
	private Vector<Integer> gCosts = new Vector<Integer>(0);
	
	/**
	 * fCosts(gCost + hCost()) for explored & frontier nodes
	 */
	private int fCost = Integer.MAX_VALUE;
	private Vector<Integer> fCosts = new Vector<Integer>(0);
	
	/**
	 * Only set map once and keep for the duration of the program
	 * @param map
	 */
	public PathFinding(Map map) {
		this.map = map;
	}
	
	/*private enum motionInstruction {
		forward, back, left, right;
	}*/
	
	public Vector<Integer> GetPath(Node startNode, Node goalNode) {
		SetUp(startNode, goalNode);//Set up & clean up after potential previous search
		
		while(!frontier.isEmpty()) {
			current = GetBestNode();
			
			if(current.equals(goalNode))
				return ReconstructPath(goalNode);
			
			int currentGCost = GetGCost(current);
			RemoveFromFrontier(current);
			explored.addElement(current);
			
			Node neighbourNode = null;
			for(int i = 0; i < 4; i++){//For each neighbour node (at most 4) 
				
				switch (i) {
				case 0:
					neighbourNode = map.getAboveNode(current);
					break;
				case 1:
					neighbourNode = map.getBelowNode(current);	
					break;
				case 2:
					neighbourNode = map.getLeftNode(current);
					break;
				case 3:
					neighbourNode = map.getRightNode(current);
					break;
				}
				if(neighbourNode == null || explored.contains(neighbourNode) || neighbourNode.status == Node.WALL){//Tried to find neighbour out of bounds
					continue;
				}
				
				fCost = GetFCost(currentGCost, current, goalNode);
				
				if(!frontier.contains(neighbourNode)){
					frontier.add(neighbourNode);
					gCosts.add(currentGCost + 1);
					fCosts.add(GetFCost(neighbourNode, goalNode));
				}
				else if (fCost >= GetFCost(neighbourNode, goalNode)){
					continue;
				}
				
				AddToPathTrace(current, neighbourNode);
			}
			
		}
		
		return null;//Return Failure
	}
	
	private void SetUp(Node startNode, Node goalNode){
		explored.clear();
		
		frontier.clear();
		frontier.add(startNode);
		
		pathTrace.clear();
		
		gCost = Integer.MAX_VALUE;
		gCosts.clear();
		gCosts.add(0);
		
		fCost = Integer.MAX_VALUE;
		fCosts.clear();
		fCosts.add(GetFCost(0, startNode, goalNode));
	}

	private int GetGCost(Node node){
		return gCosts.get(frontier.indexOf(node));
	}
	
	/**
	 * Heuristic returning the path cost from given to goal node
	 * @param node
	 * @return
	 */
	private int GetHCost(Node fromNode, Node goalNode){
		return Math.abs(goalNode.x - fromNode.x) + Math.abs(goalNode.y - fromNode.y);
	}
	
	private int GetFCost(Node node, Node goalNode){
		return GetGCost(node) + GetHCost(node, goalNode);
	}
	
	private int GetFCost(int gCost, Node node, Node goalNode){
		return gCost + GetHCost(node, goalNode);
	}
	
	/**
	 * Return the best node in the frontier according to fCost value
	 * @return
	 */
	private Node GetBestNode(){
		int bestNodeCost = Integer.MAX_VALUE;
		int bestNodeID = 0;
		
		for (int i = 0; i < frontier.size(); i++){
			if(fCosts.get(i) < bestNodeCost){
				bestNodeCost = fCosts.get(i);
				bestNodeID = i;
			}
		}
		
		return frontier.get(bestNodeID);
	}
	
	private void RemoveFromFrontier(Node node){
		int nodeIndex = frontier.indexOf(node);
		
		gCosts.remove(nodeIndex);
		fCosts.remove(nodeIndex);
		frontier.remove(nodeIndex);
	}
	
	private void AddToPathTrace(Node fromNode, Node toNode) {
		for (int i = 0; i < pathTrace.size(); i++){
			if (toNode.equals(pathTrace.get(i).toNode)){//A worse path from node is linked, replace it with the given(better) fromNode
				pathTrace.get(i).fromNode = fromNode;
				return;
			}
		}
		pathTrace.add(new CameFrom(fromNode, toNode));
	}
	
	/**
	 * 
	 * @param goalNode
	 * @return set of directions to the goal
	 */
	private Vector<Integer> ReconstructPath(Node goalNode) {
		Vector<Integer> directions = new Vector<Integer>(0);
		int noOfSteps = 0;//Debug
		
		//Look for goal node i.e. a starting point to trace the solution path
		for (int i = 0; i < pathTrace.size(); i++){
			Node toNode = pathTrace.get(i).toNode;
			if (goalNode.equals(toNode)){//Found path trace end
				goalNode.status = Node.GOAL;
				
				Node fromNode = pathTrace.get(i).fromNode;
				directions.insertElementAt(map.getRelativePosition(fromNode, toNode), 0);//insert last direction i.e. stack vector
				System.out.println("Last directi0n: " + map.getRelativePosition(fromNode, toNode));
				boolean foundPrevNode;
				
				do{
					foundPrevNode = false;
					
					for (int j = 0; j < pathTrace.size(); j++){
						toNode = pathTrace.get(j).toNode;
						if (fromNode.equals(toNode)){//Found prev node container
							fromNode.status = Node.GOALPATH; noOfSteps++;
							fromNode = pathTrace.get(j).fromNode;
							directions.insertElementAt(map.getRelativePosition(fromNode, toNode), 0);
							foundPrevNode = true;
						}
					}
					
				}
				while(foundPrevNode);
				
				fromNode.status = Node.START;
				
				//End of path trace
				System.out.println("No of steps: " + noOfSteps);
				for (int d : directions){
					System.out.print(d + ", ");
				}
				System.out.println();
				return directions;
			}
			
			//Path obtained, cease the search for goal node
			if(directions.size() > 0)
				break;
		}
		return null;
	}
}
