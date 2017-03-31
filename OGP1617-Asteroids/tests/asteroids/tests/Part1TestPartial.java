package asteroids.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import asteroids.model.Ship;
import asteroids.facade.Facade;
import asteroids.part1.facade.IFacade;
import asteroids.util.ModelException;

public class Part1TestPartial {
	
	private static final double EPSILON = 0.0001;

	IFacade facade;

	@Before
	public void setUp() {
		facade = new Facade();
	}

	@Test
	public void testCreateShip() throws ModelException {
		Ship ship = facade.createShip(100, 200, 10, -10, 20, Math.PI);
		Ship ship2 = facade.createShip(100, 200, 1000000, -10, 20, -Math.PI);
		assertNotNull(ship);
		double[] position = facade.getShipPosition(ship);
		assertNotNull(position);
		assertEquals(100, position[0], EPSILON);
		assertEquals(200, position[1], EPSILON);
		assertEquals(20, facade.getShipRadius(ship), EPSILON);
		double[] velocity = facade.getShipVelocity(ship);
		assertNotNull(velocity);
		assertEquals(10, velocity[0], EPSILON);
		assertEquals(-10, velocity[1], EPSILON);
		double orientation = facade.getShipOrientation(ship);
		assertNotNull(orientation);
		assertEquals(Math.PI, orientation, EPSILON);
		double[] velocity2 = facade.getShipVelocity(ship2);
		assertNotNull(velocity2);
//		System.out.print(velocity2[0]);
		assertEquals(300000, velocity2[0], EPSILON);
		assertEquals(-3, velocity2[1], EPSILON);
		double orientation2 = facade.getShipOrientation(ship2);
		assertNotNull(orientation2);
		assertEquals(-Math.PI, orientation2, EPSILON);
	}
	
	@Test 
	public void testsetPosition() throws ModelException {
		Ship ship = facade.createShip(0, 0, 0, 0, 15, 0);
		ship.setPosition(10,20);
		double[] position = facade.getShipPosition(ship);
		assertNotNull(position);
		assertEquals(10, position[0], EPSILON);
		assertEquals(20, position[1], EPSILON);
	}
	
	@Test 
	public void testsetVelocity() throws ModelException {
		Ship ship = facade.createShip(0, 0, 0, 0, 15, 0);
		ship.setVelocity(10,20);
		double[] velocity = facade.getShipVelocity(ship);
		assertNotNull(velocity);
		assertEquals(10, velocity[0], EPSILON);
		assertEquals(20, velocity[1], EPSILON);
	}

	@Test 
	public void testsetOrientation() throws ModelException {
		Ship ship = facade.createShip(0, 0, 0, 0, 15, 0);
		ship.setOrientation(2.46);
		double orientation = facade.getShipOrientation(ship);
		assertNotNull(orientation);
		assertEquals(2.46, orientation, EPSILON);
	}
	
	@Test(expected = ModelException.class)
	public void testCreateShipXIsNan() throws ModelException {
		facade.createShip(Double.NaN, 200, 10, -10, 20, -Math.PI);
	}

	@Test(expected = ModelException.class)
	public void testCreateShipRadiusNegative() throws ModelException {
		facade.createShip(100, 200, 10, -10, -20, -Math.PI);
	}
	
	
	
	@Test
	public void testDstBtw() throws ModelException{
		Ship ship = facade.createShip(0,0,30,-15,20,0);
		Ship other = facade.createShip(0,50,30,-15,20,0);
//		System.out.print(facade.getDistanceBetween(ship, other));
		assertEquals(10,facade.getDistanceBetween(ship, other), EPSILON);
	}
	
	@Test
	public void test2DstBtw() throws ModelException{
		Ship ship = facade.createShip(-10,0,30,-15,20,0);
		Ship other = facade.createShip(60,0,30,-15,20,0);
//		System.out.print(facade.getDistanceBetween(ship, other));
		assertEquals(30,facade.getDistanceBetween(ship, other), EPSILON);
	}
	
	@Test
	public void test3DstBtw() throws ModelException{
		Ship ship = facade.createShip(0,0,30,-15,20,0);
		Ship other = facade.createShip(20,0,30,-15,20,0);
//		System.out.print(facade.getDistanceBetween(ship, other));
		assertEquals(-20,facade.getDistanceBetween(ship, other), EPSILON);
	}
	
	@Test
	public void test4DstBtw() throws ModelException{
		Ship ship = facade.createShip(0,0,30,-15,20,0);
		Ship other = facade.createShip(0,0,30,-15,20,0);
//		System.out.print(facade.getDistanceBetween(ship, other));
		assertEquals(-40,facade.getDistanceBetween(ship, other), EPSILON);
	}
	
	@Test
	public void test5DstBtw() throws ModelException{
		Ship ship = facade.createShip(0,0,30,-15,20,0);
//		System.out.print(facade.getDistanceBetween(ship, ship));
		assertEquals(0,facade.getDistanceBetween(ship, ship), EPSILON);
	}
	
	@Test
	public void test6DstBtw() throws ModelException{
		Ship ship = facade.createShip(30,50,30,-15,20,0);
		Ship other = facade.createShip(-20,-60,30,-15,20,0);
//		System.out.print(facade.getDistanceBetween(ship, other));
		assertEquals(80.8304597,facade.getDistanceBetween(ship, other), EPSILON);
	}
	
	
	@Test
	public void testOverlap() throws ModelException{
		Ship ship = facade.createShip(0,0,30,-15,20,0);
		Ship other = facade.createShip(20,0,30,-15,20,0);
//		System.out.print(facade.getDistanceBetween(ship, other));
		assert(facade.overlap(ship, other));
	}
	
	@Test
	public void testOverlap2() throws ModelException{
		Ship ship = facade.createShip(0,0,30,-15,20,0);
		Ship other = facade.createShip(60,0,30,-15,20,0);
//		System.out.print(facade.getDistanceBetween(ship, other));
		assert(!facade.overlap(ship, other));
	}
	
	@Test
	public void testCollisiontime() throws ModelException{
		Ship ship = facade.createShip(0,0,0,0,20,0);
		Ship other = facade.createShip(0,-60,0,1,20,Math.PI/2.0);
//		System.out.print(facade.getTimeToCollision(ship, other));
		assertEquals(20, facade.getTimeToCollision(ship, other), EPSILON);
	}
	
	@Test
	public void testCollisiontime2() throws ModelException{
		Ship ship = facade.createShip(0,0,0,0,20,0);
		Ship other = facade.createShip(0,-60,1,0,20,Math.PI);
//		System.out.print(facade.getTimeToCollision(ship, other));
		assertEquals(Double.POSITIVE_INFINITY, facade.getTimeToCollision(ship, other), EPSILON);
	}
	
	@Test
	public void testCollisiontime3() throws ModelException{
		Ship ship = facade.createShip(35,-120,-20,30,25,0);
		Ship other = facade.createShip(-90,60,20,-30,25,Math.PI/2.0);
//		System.out.print(facade.getTimeToCollision(ship, other));
		assertEquals(2.347490607, facade.getTimeToCollision(ship, other), EPSILON);
	}
	
	@Test
	public void testCollisionPosition() throws ModelException{
		Ship ship = facade.createShip(0,0,0,1,25,0);
		Ship other = facade.createShip(0,60,0,0,25,0);
//		System.out.print(facade.getCollisionPosition(ship, other)[1]);
		assertEquals(35, facade.getCollisionPosition(ship, other)[1], EPSILON);
		assertEquals(0, facade.getCollisionPosition(ship, other)[0], EPSILON);
	}
	
	@Test
	public void testCollisionPosition2() throws ModelException{
		Ship ship = facade.createShip(0,0,0,-1,25,0);
		Ship other = facade.createShip(0,60,0,0,25,0);
//		System.out.print(facade.getCollisionPosition(ship, other));
		assert(facade.getCollisionPosition(ship, other) == null);
	}
	
	@Test
	public void testCollisionPosition3() throws ModelException{
		Ship ship = facade.createShip(35,-120,-20,30,25,0);
		Ship other = facade.createShip(-90,60,20,-30,25,Math.PI/2.0);
//		System.out.print(facade.getCollisionPosition(ship, other)[0]);
//		System.out.print(facade.getCollisionPosition(ship, other)[1]);
		assertEquals(-27.5, facade.getCollisionPosition(ship, other)[0], EPSILON);
		assertEquals(-30.0, facade.getCollisionPosition(ship, other)[1], EPSILON);
	}
	
	@Test
	public void testMove() throws ModelException {
		Ship ship = facade.createShip(100, 100, 30, -15, 20, 0);
		facade.move(ship, 1);
		double[] position = facade.getShipPosition(ship);
		assertNotNull(position);
//		System.out.print(position[0]);
		assertEquals(130, position[0], EPSILON);
		assertEquals(85, position[1], EPSILON);
		facade.move(ship, 3);
		double[] position2 = facade.getShipPosition(ship);
		assertNotNull(position2);
//		System.out.print(position2[0]);
		assertEquals(220, position2[0], EPSILON);
		assertEquals(40, position2[1], EPSILON);
	}
	
	@Test
	public void testThrust() throws ModelException {
		Ship ship = facade.createShip(100, 100, 30, -15, 20, 0);
		facade.thrust(ship,1);
		double[] velocity = facade.getShipVelocity(ship);
		assertNotNull(velocity);
//		System.out.print(velocity[0]);
		assertEquals(31, velocity[0], EPSILON);
		assertEquals(-15, velocity[1], EPSILON);
	}
		
	@Test
	public void testThrust2() throws ModelException {	
		Ship ship = facade.createShip(100, 100, Double.NaN, 10, 20, 0);
		facade.thrust(ship,1);
		double[] velocity = facade.getShipVelocity(ship);
		assertNotNull(velocity);
//		System.out.print(velocity[0]);
		assertEquals(Double.NaN, velocity[0], EPSILON);
		assertEquals(Double.NaN, velocity[1], EPSILON);
	}
	
	@Test
	public void testThrust3() throws ModelException {
		Ship ship = facade.createShip(100, 100, 30, -15, 20, 0);
		facade.thrust(ship,-1);
		double[] velocity = facade.getShipVelocity(ship);
		assertNotNull(velocity);
//		System.out.print(velocity[0]);
		assertEquals(30, velocity[0], EPSILON);
		assertEquals(-15, velocity[1], EPSILON);
	}
	
	@Test
	public void testTurn() throws ModelException {
		Ship ship = facade.createShip(100, 100, 30, -15, 20, Math.PI/2);
		facade.turn(ship, Math.PI);
		double orientation = facade.getShipOrientation(ship);
		assertNotNull(orientation);
//		System.out.print(orientation);
		assertEquals(3*Math.PI/2, orientation, EPSILON);
	}
	
	@Test
	public void testTurn2() throws ModelException {
		Ship ship = facade.createShip(100, 100, 30, -15, 20, Math.PI);
		facade.turn(ship, 3*Math.PI/2);
		double orientation = facade.getShipOrientation(ship);
		assertNotNull(orientation);
//		System.out.print(orientation);
		assertEquals(Math.PI/2, orientation, EPSILON);
	}
	
	@Test
	public void testTurn3() throws ModelException {
		Ship ship = facade.createShip(100, 100, 30, -15, 20, -Math.PI);
		facade.turn(ship, -3*Math.PI/2);
		double orientation = facade.getShipOrientation(ship);
		assertNotNull(orientation);
//		System.out.print(orientation);
		assertEquals(-Math.PI/2, orientation, EPSILON);
	}
	
	@Test
	public void testTurn4() throws ModelException {
		Ship ship = facade.createShip(100, 100, 30, -15, 20, Math.PI);
		facade.turn(ship, -2*Math.PI);
		double orientation = facade.getShipOrientation(ship);
		assertNotNull(orientation);
//		System.out.print(orientation);
		assertEquals(-Math.PI, orientation, EPSILON);
	}
	
	@Test
	public void testTurn5() throws ModelException {
		Ship ship = facade.createShip(100, 100, 30, -15, 20, Math.PI);
		facade.turn(ship, Math.PI);
		double orientation = facade.getShipOrientation(ship);
		assertNotNull(orientation);
//		System.out.print(orientation);
		assertEquals(0, orientation, EPSILON);
	}
}