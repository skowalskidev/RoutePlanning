import dataStructures.Map;
import dataStructures.Node;
import pathFinding.PathFinding;

public class Test {

	private Map map = new Map(10, 10);
	
	public void DrawMap() {
		for (int j = 0; j < map.getHeight(); j++){
			for (int i = 0; i < map.getWidth(); i++){
				System.out.print(map.getNode(i, j).status + "  ");
			}
			System.out.println();
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		Test test = new Test();
		
		test.map.getNode(6, 4).status = Node.WALL;
		test.map.getNode(5, 4).status = Node.WALL;
		test.map.getNode(4, 4).status = Node.WALL;
		test.map.getNode(3, 4).status = Node.WALL;

		test.map.getNode(7, 4).status = Node.WALL;
		test.map.getNode(7, 5).status = Node.WALL;
		test.map.getNode(7, 6).status = Node.WALL;
		test.map.getNode(7, 7).status = Node.WALL;
		
		test.map.getNode(6, 7).status = Node.WALL;
		test.map.getNode(5, 7).status = Node.WALL;
		test.map.getNode(4, 7).status = Node.WALL;
		test.map.getNode(3, 7).status = Node.WALL;
		
		PathFinding pathFinding = new PathFinding(test.map);
		pathFinding.GetPath(test.map.getNode(7, 1), test.map.getNode(6, 6));
		test.DrawMap();
	}

}
