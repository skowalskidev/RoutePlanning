import java.util.Vector;

import dataStructures.Map;
import dataStructures.Node;
import pathFinding.PathFinding;
import virtualRobot.Robot;

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
		
		int time = 0;
		
		Node startNode = test.map.getNode(1, 4);
		Node endNode = test.map.getNode(8, 7);
		
		Robot robot = new Robot(0, test.map);
		
		PathFinding pathFinding = new PathFinding(test.map);
		pathFinding.addRobot(robot);
		Vector<Integer> pathSequence = pathFinding.GetPath(startNode, endNode, time, robot);
		
		robot.setUpPath(startNode, pathSequence);

		while(true){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			robot.goToNextNode();
			test.DrawMap();
			
			time++;
			System.out.println("////////////////////////////////////////////////////////////////////////////////////////");
		}
	}

}
