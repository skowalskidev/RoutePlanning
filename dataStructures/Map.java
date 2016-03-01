package dataStructures;

import routeExecution.RouteExecution;

public class Map {
	/**
	 * Width * Height in node units
	 */
	private int width;
	private int height;
	private Node[][] map;

	public Map(int width, int height) {
		this.width = width;
		this.height = height;
		
		//Set up & populate map
		map = new Node[width][height];	
		
		for (int i = 0; i < width; i++){
			for (int j = 0; j < height; j++){
				map[i][j] = new Node(i, j, ((Integer) i).toString() + "," + ((Integer) j).toString());
			}
		}
	}
	
	public Node getNode(int x, int y){
		return map[x][y];
	}
	
	public Node getAboveNode(Node node){
		int x = node.x;
		int y = node.y;
		
		if (y == 0)
			return null;
		return map[x][y - 1];
	}
	
	public Node getBelowNode(Node node){
		int x = node.x;
		int y = node.y;
		
		if (y == height - 1)
			return null;
		return map[x][y + 1];
	}
	
	public Node getLeftNode(Node node){
		int x = node.x;
		int y = node.y;
		
		if (x == 0)
			return null;
		return map[x - 1][y];
	}
	
	public Node getRightNode(Node node){
		int x = node.x;
		int y = node.y;
		
		if (x == width - 1)
			return null;
		return map[x + 1][y];
	}
	
	/**
	 * 
	 * @param node1
	 * @param node2
	 * @return relative position defined by constants, only returns relative position in one direction i.e. never diagonal relative position
	 */
	public int getRelativePosition(Node node1, Node node2){
		if(node2.x > node1.x){
			return RouteExecution.EAST;
		}
		else if(node2.x < node1.x){
			return RouteExecution.WEST;
		}
		else if(node2.y > node1.y){
			return RouteExecution.SOUTH;
		}
		else if(node2.y < node1.y){
			return RouteExecution.NORTH;
		}
		else {//Should never happen
			return (Integer) null;
		}
		
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
}

/*
function A*(start,goal)
ClosedSet := {}    	  // The set of nodes already evaluated.
OpenSet := {start}    // The set of tentative nodes to be evaluated, initially containing the start node
Came_From := the empty map    // The map of navigated nodes.

g_score := map with default value of Infinity
g_score[start] := 0    // Cost from start along best known path.
// Estimated total cost from start to goal through y.
f_score := map with default value of Infinity
f_score[start] := heuristic_cost_estimate(start, goal)

while OpenSet is not empty
    current := the node in OpenSet having the lowest f_score[] value
    if current = goal
        return reconstruct_path(Came_From, goal)

    OpenSet.Remove(current)
    ClosedSet.Add(current)
    for each neighbor of current
        if neighbor in ClosedSet
            continue		// Ignore the neighbor which is already evaluated.
        tentative_g_score := g_score[current] + dist_between(current,neighbor) // length of this path.
        if neighbor not in OpenSet	// Discover a new node
            OpenSet.Add(neighbor)
        else if tentative_g_score >= g_score[neighbor]
            continue		// This is not a better path.

        // This path is the best until now. Record it!
        Came_From[neighbor] := current
        g_score[neighbor] := tentative_g_score
        f_score[neighbor] := g_score[neighbor] + heuristic_cost_estimate(neighbor, goal)

return failure

function reconstruct_path(Came_From,current)
total_path := [current]
while current in Came_From.Keys:
    current := Came_From[current]
    total_path.append(current)
return total_path*/