package engine.physics;

/**
 * Vector class for use in physics engine
 * 
 * @author Fritz (06/10/15)
 *
 */
public class Vector {

	private final double x;
	private final double y;

	/**
	 * Creates a vector with specified speed and angle
	 * 
	 * @param speed - Speed of vector
	 * @param angle - Angle of vector
	 */
	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Creates a vector using polar coordinates
	 * @param magnitude - Magnitude
	 * @param angle - Angle in radians
	 * @return Vector created
	 */
	public Vector polarVector(double magnitude, double angle) {
		return new Vector(magnitude * Math.cos(angle), magnitude * Math.sin(angle));
	}

	/**
	 * Creates a vector of the same magnitude but new angle
	 * @param angle - Angle of new vector in radians
	 * @return Vector created
	 */
	public Vector withAngle(double angle) {
		double magnitude = getMagnitude();
		double newAngle = getAngle() + angle;

		if (angle > 2 * Math.PI)
			angle -= 2 * Math.PI;

		if (angle < 0)
			angle += 2 * Math.PI;

		return polarVector(magnitude, newAngle);
	}
	
	/**
	 * Gets the normalized vector
	 * @return Normalized vector
	 */
	public Vector getNormalized() {
		return new Vector(x / getMagnitude(), y / getMagnitude());
	}

	/**
	 * Gets the normal vector (tangent)
	 * @return Normal vector
	 */
	public Vector getNormal() {
		return new Vector(-y, x);
	}
	
	/**
	 * Gets the magnitude of the vector
	 * @return Magnitude of the vector
	 */
	public double getMagnitude() {
		return Math.hypot(x, y);
	}

	/**
	 * Gets the angle of the vector in radians
	 * @return Angle of the vector
	 */
	public double getAngle() {
		return Math.atan2(y, x);
	}
	
	/**
	 * Gets the X component of the vector
	 * @return X component of the vector
	 */
	public double getX() {
		return x;
	}
	
	/**
	 * Gets the Y component of the vector
	 * @return Y component of the vector
	 */
	public double getY() {
		return y;
	}
	
}
