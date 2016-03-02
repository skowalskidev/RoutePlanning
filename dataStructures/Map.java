package dataStructures;

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
			return GeneralProtocol.EAST;
		}
		else if(node2.x < node1.x){
			return GeneralProtocol.WEST;
		}
		else if(node2.y > node1.y){
			return GeneralProtocol.SOUTH;
		}
		else if(node2.y < node1.y){
			return GeneralProtocol.NORTH;
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