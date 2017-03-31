package asteroids.model;

import be.kuleuven.cs.som.annotate.*;

/**
 * A class of ships involving a x and y component of their position, a x and y component of their velocity, 
 * a radius and a orientation. 
 * 
 * @invar  The position of each Ship must be a valid position for any
 *         Ship.
 *       | isValidPosition(getxCoordinate(), getyCoordinate())
 * @invar  Each Ship can have its radius as radius.
 *       | isValidRadius(getRadius())
 * @invar  The Velocity of each Ship must be a valid Velocity for any
 *         Ship.
 *       | isValidVelocity(getVelocity())
 * @invar  The orientation of each Ship must be a valid orientation for any
 *         Ship.
 *       | isValidOrientation(getOrientation())
 *       
 * @version 1.0
 * @author Jasper Callaerts and Arno Goedhuys
 */

public class Ship {
		
	/**
	 * Initialize this new Ship with given Position, Velocity, radius and orientation.
	 *
	 * @param  xCoordinate,
	 *         The xCoordinate for this new Ship.
	 * @param  yCoordinate,
	 *         The yCoordinate for this new Ship.
	 * @param  xVelocity
	 *         The xVelocity for this new Ship.
	 * @param  yVelocity
	 *         The yVelocity for this new Ship.
	 * @param  radius
	 *         The radius for this new Ship.   
	 * @param  orientation
	 * 		   The orientation for this new Ship.    
	 * @pre    The given orientation must be a valid orientation for any Ship.
	 *       | isValidOrientation(orientation)
	 * @post   The orientation of this new Ship is equal to the given
	 *         orientation.
	 *       | new.getOrientation() == orientation 
	 * @effect The velocity of this ship is set to the given velocity.
	 * 	     | this.setVelocity(xVelocity, yVelocity)
	 * @post   The radius of this new Ship is equal to the given
	 *         radius.
	 *       | new.getRadius() == radius
	 * @effect The position of this new Ship is set to
	 *         the given position.
	 *       | this.setPosition(xCoordinate, yCoordinate)
	 * @throws IllegalArgumentException
	 *         This new Ship cannot have the given radius as its radius.
	 *       | ! isValidRadius(this.getRadius())
	 */
	public Ship(double xCoordinate, double yCoordinate, double xVelocity, double yVelocity, double radius, double orientation) throws IllegalArgumentException {
		setPosition(xCoordinate, yCoordinate);
		setVelocity(xVelocity, yVelocity);
		setOrientation(orientation);
		
		if (! isValidRadius(radius)){
			throw new IllegalArgumentException();
		}
		else{
			this.radius = radius;
		}
	}
	/**
	 * Initializes a new ship with default position, velocity, radius and orientation.
	 *  
	 * @throws IllegalArgumentException
	 * 		   The given ship cannot have this argument as its argument.
	 */
	
	public Ship() throws IllegalArgumentException{
		 this(0.0,0.0,0.0,0.0,15.0,0.0);
		 //testestest delete me TODO
	}
	
	
	/**
	 * Return the x component of the position of this Ship.
	 * 	  The x component of the position expresses where the center of the ship is located on an unbounded x axis
	 * 	  and is expressed in kilometers.
	 */
	@Basic @Raw
	public double getxCoordinate() {
		return this.xCoordinate;
	}
	
	/**
	 * Return the y component of the position of this Ship.
	 *	  The y component of the position expresses where the center of the ship is located on an unbounded y axis
	 * 	  and is expressed in kilometers.
	 */
	@Basic @Raw
	public double getyCoordinate() {
		return this.yCoordinate;
	}
	

	/**
	 * Check whether the given position is a valid position for
	 * any Ship.
	 *  
	 * @param  xCoordinate,
	 *         The xCoordinate to check.
	 * @param  yCoordinate,
	 *         The yCoordinate to check.
	 * @return 
	 *       | result == (! java.lang.Double.isNaN(xCoordinate) && !java.lang.Double.isNaN(yCoordinate));
	*/
	public static boolean isValidPosition(double xCoordinate, double yCoordinate) {
		return (! java.lang.Double.isNaN(xCoordinate) && !java.lang.Double.isNaN(yCoordinate));
	}

	/**
	 * Set the position of this Ship to the given position.
	 * 
	 * @param  xCoordinates,
	 *         The new x coordinate for this Ship.
	 * @param  yCoordinate
	 * 		   The new y coordinate for this ship
	 * @post   If the position is a valid position for any ship.
	 * 		   Then the position of this new Ship is equal to
	 *         the given position.
	 *       | new.getxCoordinate() == xCoordinate
	 *       | new.getyCoordinate() == yCoordinate
	 * @throws IllegalArgumentException()
	 *         The given position is not a valid position for any
	 *         Ship.
	 *       | ! isValidPosition(getxCoordinate(), getyCoordinate())
	 */
	@Raw
	public void setPosition(double xCoordinate, double yCoordinate)	throws IllegalArgumentException {
		if (! isValidPosition(xCoordinate, yCoordinate)){
			throw new IllegalArgumentException();
		}
		else{
			this.xCoordinate = xCoordinate;
			this.yCoordinate = yCoordinate;	
		}
	}
	
	/**
	 * Variable registering the xCoordinate of this Ship.
	 */
	private double xCoordinate;
	
	/**
	 * Variable registering the yCoordinate of this Ship.
	 */	
	private double yCoordinate;

	/**
	 * Move the ship to its new position after the given duration.
	 * 
	 * @param duration
	 * 		  The amount of time the ship moves.
	 * @post  If the given duration is a valid duration,
	 * 		  the x and y component of the position of the ship will 
	 * 		  be set to the new coordinates that the ship will have after 	
	 * 		  the given duration under influence of their respective velocities.
	 * 		  Else the position will remain unchanged.
	 * 		  | if (!IsValidDuration(duration) || (Math.sqrt(getxVelocity()*getxVelocity() + getyVelocity()*getyVelocity())) == 0){
	 *		  |		setPosition(getxCoordinate(), getyCoordinate())
	 *		  |	else{
	 *		  |		setPosition(getxCoordinate() + getxVelocity()*duration, getyCoordinate() + getyVelocity()*duration)
	 * 			 
	 * @throws IllegalArgumentException
	 *         The new position is not a valid position for any
	 *         Ship.
	 *        | ! isValidPosition(new.getxCoordinate(), new.getyCoordinate())
	 */
	
	public void move(double duration) throws IllegalArgumentException {
		if (!IsValidDuration(duration) || (Math.sqrt(getxVelocity()*getxVelocity() + getyVelocity()*getyVelocity())) == 0){
			setPosition(getxCoordinate(), getyCoordinate());
		}
		else{
			setPosition(getxCoordinate() + getxVelocity()*duration, getyCoordinate() + getyVelocity()*duration);
		}
	}
	

	/**
	 *  Check whether the given duration is a valid duration for
	 *  any Ship.
	 * @param duration
	 * 		  The duration to check.
	 * @return True if and only if the duration is positive
	 * 		   | result == (duration > 0)
	 */
	
	public static boolean IsValidDuration(double duration) {
		return (duration > 0);
	}
	

	/**
	 * Return the distance between two ships measured as the distance between their edges.
	 * 
	 * @param other
	 * 		  The other ship to which the distance shall be measured.
	 * @return The distance between the two ships
	 * 		   | result == Math.sqrt((getxCoordinate() - other.getxCoordinate())*(getxCoordinate() - other.getxCoordinate()) 	
	 * 		   | + (getyCoordinate() - other.getyCoordinate())*(getyCoordinate() - other.getyCoordinate())) - 	
	 * 		   | (getRadius() + other.getRadius()) 
	 */
	
	public double getDistanceBetween(Ship other) {
		if (this == other) {
			return 0.0;
		}
		else{
			return Math.sqrt((getxCoordinate() - other.getxCoordinate())*(getxCoordinate() - other.getxCoordinate()) + (getyCoordinate() - other.getyCoordinate())*(getyCoordinate() - other.getyCoordinate())) - (getRadius() + other.getRadius());
		}
	}
	
	/**
	 * Check whether two ships overlap.
	 * 
	 * @param other
	 * 		  The other ship to check.
	 * @return True if and only if the distance between two ships is negative.
	 * 		   | result == (getDistanceBetween(other) < 0)
	 * 		
	 */
	
	public boolean overlap(Ship other) {
		return (getDistanceBetween(other) < 0);
	}
	
	/**
	 * Return the amount of time before the two ships will collide.
	 * @param other
	 * 		  The other ship
	 * @return The amount of time before two ships overlap, 	
	 * 		   if and only if the calculated amount of time is finite.
	 * 		   | if (dvdr >= 0 && d <=0)
	 * 		   |	- (dvdr + Math.sqrt(d)) / (dvdv)
	 */
	
	public double getTimeToCollision(Ship other) {
		
		double dvdr = (getxVelocity() - other.getxVelocity()) * (getxCoordinate() - other.getxCoordinate()) + (getyVelocity()-other.getyVelocity()) * (getyCoordinate() - other.getyCoordinate());
		double dvdv = (getxVelocity()-other.getxVelocity())*(getxVelocity()-other.getxVelocity()) + (getyVelocity()-other.getyVelocity()) * (getyVelocity() - other.getyVelocity());
		double drdr = (getxCoordinate()-other.getxCoordinate())*(getxCoordinate()-other.getxCoordinate()) + (getyCoordinate()-other.getyCoordinate()) * (getyCoordinate() - other.getyCoordinate());
		double sigmaSquare = (getRadius()+other.getRadius())*(getRadius()+other.getRadius());
		double d = dvdr*dvdr - dvdv * (drdr - sigmaSquare);
		
		if (dvdr >= 0){
			return Double.POSITIVE_INFINITY;
		}
		if (d <= 0){
			return Double.POSITIVE_INFINITY;
		}
		else{
			return - (dvdr + Math.sqrt(d)) / (dvdv);
		}
	}
	
	/**
	 * Return the x and y component of the position where to two ships will collide.
	 * @param other
	 * 		  The other ship 
	 * @return If and only if the time to collision is finite,	
	 * 		   the coordinates where the collision will occur after	
	 * 		   the finite amount of time.
	 * 		   | if (getTimeToCollision(other) == Double.POSITIVE_INFINITY)
	 *		   |	return null
	 *		   | double FirstxCoordinate = getxCoordinate() + getxVelocity()*duration
	 *		   | double FirstyCoordinate = getyCoordinate() + getyVelocity()*duration
	 *		   | double SecondxCoordinate = other.getxCoordinate() + other.getxVelocity()*duration
	 *		   | double SecondyCoordinate = other.getyCoordinate() + other.getyVelocity()*duration
	 *	       |
	 * 		   | double CollisionxCoordinate = FirstxCoordinate + (SecondxCoordinate - FirstxCoordinate) * getRadius() / (getRadius() + other.getRadius())
	 *	       | double CollisionyCoordinate = FirstyCoordinate + (SecondyCoordinate - FirstyCoordinate) * getRadius() / (getRadius() + other.getRadius())
	 *		   | double[] CollisionCoordinates =  {CollisionxCoordinate, CollisionyCoordinate}
 	 *		   | return CollisionCoordinates
	 */
	
	public double[] getCollisionPosition(Ship other) {
		
		if (getTimeToCollision(other) == Double.POSITIVE_INFINITY){
			return null;
		}
		
		double duration = getTimeToCollision(other);
		
		double FirstxCoordinate = getxCoordinate() + getxVelocity()*duration;
		double FirstyCoordinate = getyCoordinate() + getyVelocity()*duration;
		double SecondxCoordinate = other.getxCoordinate() + other.getxVelocity()*duration;
		double SecondyCoordinate = other.getyCoordinate() + other.getyVelocity()*duration;
		
		double CollisionxCoordinate = FirstxCoordinate + (SecondxCoordinate - FirstxCoordinate) * getRadius() / (getRadius() + other.getRadius());
		double CollisionyCoordinate = FirstyCoordinate + (SecondyCoordinate - FirstyCoordinate) * getRadius() / (getRadius() + other.getRadius());
		double[] CollisionCoordinates =  {CollisionxCoordinate, CollisionyCoordinate};
		
		return CollisionCoordinates;
	}
	
	/**
	 * Return the x component of the velocity of this Ship.
	 * 		The x component of the velocity expresses the ships velocity projected on the x axis	
	 * 		and is expressed in kilometers/second.
	 */
	@Basic @Raw
	public double getxVelocity() {
		return this.xVelocity;
	}
	/**
	 * Return the y value of the velocity of this Ship.
	 * 		The y component of the velocity expresses the ships velocity projected on the y axis	
	 * 		and is expressed in kilometers/second.
	 */
	@Basic @Raw
	public double getyVelocity() {
		return this.yVelocity;
	}
	
	/**
	 * Check whether the given Velocity is a valid Velocity for
	 * any Ship.
	 *  
	 * @param  xVelocity
	 *         The xVelocity to check.
	 * @param  yVelocity
	 *         The yVelocity to check.
	 * @return 
	 *       | result ==  (Math.sqrt(xVelocity*xVelocity+yVelocity*yVelocity) <= 300000)
	*/
	public static boolean isValidVelocity(double xVelocity, double yVelocity) {
		return (Math.sqrt(xVelocity*xVelocity+yVelocity*yVelocity) <= 300000);
	}
	
	/**
	 * Set the Velocity of this Ship to the given Velocity.
	 * 
	 * @param  xVelocity
	 *         The new x component of the velocity for this ship
	 * @param  yVelocity
	 * 		   The new y component of the velocity for this ship
	 * @post   If the given Velocity is a valid Velocity for any Ship,
	 *         the Velocity of this new Ship is equal to the given
	 *         Velocity.
	 *         Otherwise the velocity will have the same direction 	
	 *         but the size will be set to 300000.
	 *       | if (isValidVelocity(xVelocity))
	 *       |   then new.getxVelocity() == xVelocity
	 *       		  new.getyVelocity() == yVelocity
	 *       | else
	 *       |		new.getxVelocity = xVelocity/(Math.sqrt(xVelocity*xVelocity+yVelocity*yVelocity))*300000
	 *		 |		new.getyVelocity = yVelocity/(Math.sqrt(xVelocity*xVelocity+yVelocity*yVelocity))*300000
	 */
	@Raw
	public void setVelocity(double xVelocity, double yVelocity) {
		if (isValidVelocity(xVelocity, yVelocity)){
			this.xVelocity = xVelocity;
			this.yVelocity = yVelocity;
		}
		else{
			this.xVelocity = xVelocity/(Math.sqrt(xVelocity*xVelocity+yVelocity*yVelocity))*300000;
			this.yVelocity = yVelocity/(Math.sqrt(xVelocity*xVelocity+yVelocity*yVelocity))*300000;
		}
	}
	
	/**
	 * Variable registering the x component of the Velocity of this Ship.
	 */
	private double xVelocity;
	
	/**
	* Variable registering the x component of the Velocity of this Ship.
	*/
	private double yVelocity;

	/**
	 * Increase the velocity by a given amount.
	 * 
	 * @param Amount
	 * 		  The amount that the velocity has to be increased by.
	 * @post  If the amount is a valid amount for any ship,	
	 * 		  the ships components of its velocity will increase proportionally	
	 * 		  with the given amount according to its orientation. 	
	 * 		  Otherwise the velocity will remain unchanged.	
	 * 		  | if (!isValidAmount(Amount)) 
	 *		  |	else
	 *		  |		setVelocity(getxVelocity() + Amount*Math.cos(getOrientation()), getyVelocity() + Amount*Math.sin(getOrientation()))
	 */
	
	public void thrust(double Amount) {
		if (!isValidAmount(Amount)) {
		}
		else{
			setVelocity(getxVelocity() + Amount*Math.cos(getOrientation()), getyVelocity() + Amount*Math.sin(getOrientation()));
		}
	}

	/**
	 * Check whether the given amount is a valid amount for any ship. 
	 * @param Amount
	 * 		  The amount to check.
	 * @return True if and only if the amount is not negative.
	 * 		   | result == (Amount >= 0)
	 */
	
	public static boolean isValidAmount(double Amount) {
		return (Amount >= 0);
	}
	
	/**
	 * Return the radius of this Ship.
	 * 		The radius expresses the radius of the circular ships and is expressed in kilometers.
	 */
	@Basic @Raw @Immutable
	public double getRadius() {
		return this.radius;
	}
	
	/**
	 * Check whether this Ship can have the given radius as its radius.
	 *  
	 * @param  radius
	 *         The radius to check.
	 * @return 
	 *       | result == (radius > 10)
	*/
	@Raw
	public boolean isValidRadius(double radius) {
		return (radius > 10);
	}
	
	/**
	 * Variable registering the radius of this Ship.
	 */
	private final double radius;
	
	/**
	 * Return the orientation of this Ship.
	 * 		The orientation expresses a certain direction the ship is facing and is expressed as an angle in radians.
	 */
	@Basic @Raw
	public double getOrientation() {
		return this.orientation;
	}

	/**
	 * Check whether the given orientation is a valid orientation for
	 * any Ship.
	 *  
	 * @param  orientation
	 *         The orientation to check.
	 * @return 
	 *       | result == (Math.abs(orientation) >= 0 && Math.abs(orientation) <= 2*Math.PI)
	*/
	public static boolean isValidOrientation(double orientation) {
		return (Math.abs(orientation) >= 0 && Math.abs(orientation) <= 2*Math.PI);
	}
	
	/**
	 * Set the orientation of this Ship to the given orientation.
	 * 
	 * @param  orientation
	 *         The new orientation for this Ship.
	 * @pre    The given orientation must be a valid orientation for any
	 *         Ship.
	 *       | isValidOrientation(orientation)
	 * @post   The orientation of this Ship is equal to the given
	 *         orientation.
	 *       | new.getOrientation() == orientation
	 */
	@Raw
	public void setOrientation(double orientation) {
		assert isValidOrientation(orientation);
		this.orientation = orientation;
	}
	
	/**
	 * Variable registering the orientation of this Ship.
	 */
	private double orientation;
	
	/**
	 * Turn the ship by changing its orientation over a given angle. 
	 * @param Angle
	 * 		  The angle by which the ships orientation has to change.
	 * @pre   The given angle must be a valid angle for any ship.
	 * 		  | isValidOrientation(angle)
	 * @post  The orientation of the ship equals the sum of its original 	
	 * 		  orientation and the given angle.
	 * 		  If this sum exceeds two Pi the orientation equals modulus of two Pi.
	 * 		  
	 */
	
	public void turn(double Angle) {
		assert isValidOrientation(Angle);
		if (Math.abs(getOrientation() + Angle) >= 2*Math.PI){
			if (getOrientation() + Angle > 0){
				setOrientation(getOrientation() + Angle - 2*Math.PI);
			}
			else{
				setOrientation(getOrientation() + Angle + 2*Math.PI);
			}
		}
		else{
			setOrientation(getOrientation() + Angle);
		}
	}
}
