import static org.junit.Assert.assertEquals;

import java.util.Vector;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dataStructures.Map;
import dataStructures.Node;
import pathFinding.PathFinding;
import virtualRobot.Robot;

public class JUnitTests {
	private Map map = new Map(10, 10);
	private int time = 0;
	private Vector<Integer> pathSequence;
	
	private Robot robot0;
	private Robot robot1;
	
	private PathFinding pathFinding;
	
	@Before
	public void setUp() throws Exception {
		//Create a dead end wall formation in the middle
		map.getNode(6, 4).status = Node.WALL;
		map.getNode(5, 4).status = Node.WALL;
		map.getNode(4, 4).status = Node.WALL;
		map.getNode(3, 4).status = Node.WALL;

		map.getNode(7, 4).status = Node.WALL;
		map.getNode(7, 5).status = Node.WALL;
		map.getNode(7, 6).status = Node.WALL;
		map.getNode(7, 7).status = Node.WALL;
		
		map.getNode(6, 7).status = Node.WALL;
		map.getNode(5, 7).status = Node.WALL;
		map.getNode(4, 7).status = Node.WALL;
		map.getNode(3, 7).status = Node.WALL;
		
		//Create 2 robots
		robot0 = new Robot(0, map);
		robot1 = new Robot(1, map);
		
		//Setup Path finding
		pathFinding = new PathFinding(map);
		pathFinding.addRobot(robot0);
		pathFinding.addRobot(robot1);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void test() {
		//////////////////////////////////////////////////Optimal Path Length Tests//////////////////////////////////////////////////
		pathSequence = pathFinding.GetPath(map.getNode(7, 1), map.getNode(6, 6), time, robot0);
		assertEquals(14, pathSequence.size());
		
		pathSequence = pathFinding.GetPath(map.getNode(1, 4), map.getNode(8, 7), time, robot1);
		assertEquals(12, pathSequence.size());
		
		//////////////////////////////////////////////////Multi-Robot Path Collision Tests//////////////////////////////////////////////////
		pathSequence = pathFinding.GetPath(map.getNode(1, 0), map.getNode(1, 2), time, robot0);
		assertEquals(2, pathSequence.size());
		
		pathSequence = pathFinding.GetPath(map.getNode(0, 1), map.getNode(2, 1), time, robot1);
		assertEquals(4, pathSequence.size());//If the path-finding algorithm would not consider a collision with robot0 then then path length would be 2
		//Also in this case the robot initially has a choice of going up (first step: 0,0) or down (first step: 0,2) to prevent a collision.
		//The down path is 6 units in length therefore should not be chosen
	}
}