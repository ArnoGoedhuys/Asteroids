package asteroids.facade;
import asteroids.model.Ship;
import asteroids.part1.facade.IFacade;
import asteroids.util.ModelException;

public class Facade implements IFacade{

	@Override
	public Ship createShip() throws ModelException {
		// TODO Auto-generated method stub
		Ship ship = new Ship();
		return ship;
	}

	@Override
	public Ship createShip(double x, double y, double xVelocity, double yVelocity, double radius, double orientation) throws ModelException {
		// TODO Auto-generated method stub
		try{
			Ship ship = new Ship(x, y, xVelocity, yVelocity, radius, orientation);			
			return ship;
		}catch(IllegalArgumentException ex){
			throw new ModelException(ex);
		}
	}

	@Override
	public double[] getShipPosition(Ship ship) throws ModelException {
		return new double[]{ship.getxCoordinate(),ship.getyCoordinate()};
	}

	@Override
	public double[] getShipVelocity(Ship ship) throws ModelException {
		// TODO Auto-generated method stub
		return new double[]{ship.getxVelocity(),ship.getyVelocity()};
	}

	@Override
	public double getShipRadius(Ship ship) throws ModelException {
		// TODO Auto-generated method stub
		return ship.getRadius();
	}

	@Override
	public double getShipOrientation(Ship ship) throws ModelException {
		// TODO Auto-generated method stub
		return ship.getOrientation();
	}

	@Override
	public void move(Ship ship, double dt) throws ModelException {
		// TODO Auto-generated method stub
		try{
			ship.move(dt);
		}catch(IllegalArgumentException ex){
			throw new ModelException(ex);
		}
	}

	@Override
	public void thrust(Ship ship, double amount) throws ModelException {
		// TODO Auto-generated method stub
		ship.thrust(amount);
	}

	@Override
	public void turn(Ship ship, double angle) throws ModelException {
		// TODO Auto-generated method stub
		ship.turn(angle);
	}

	@Override
	public double getDistanceBetween(Ship ship, Ship other) throws ModelException {
		// TODO Auto-generated method stub
		return ship.getDistanceBetween(other);
	}

	@Override
	public boolean overlap(Ship ship, Ship other) throws ModelException {
		// TODO Auto-generated method stub
		return ship.overlap(other);
	}

	@Override
	public double getTimeToCollision(Ship ship, Ship other) throws ModelException {
		// TODO Auto-generated method stub
		return ship.getTimeToCollision(other);
	}

	@Override
	public double[] getCollisionPosition(Ship ship, Ship other) throws ModelException {
		// TODO Auto-generated method stub
		return ship.getCollisionPosition(other);
	}

}