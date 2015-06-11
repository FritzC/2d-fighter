package engine.physics;

/**
 * Handles math with Vectors
 * 
 * @author Fritz (6/10/15)
 */
public class VectorMath {
	
	/**
	 * Gets the cross product of two vectors
	 * @param v1 - Vector 1
	 * @param v2 - Vector 2
	 * @return Cross product of the vectors
	 */
	public static double crossProduct(Vector v1, Vector v2) {
		return v1.getX() * v2.getX() + v1.getY() * v2.getY();
	}
	
	/**
	 * Gets the sum of an array of vectors
	 * @param vectors - Array of vectors
	 * @return Sum of the vectors
	 */
	public static Vector sum(Vector ... vectors) {
		double x = 0;
		double y = 0;
		for (Vector v : vectors) {
			x += v.getX();
			y += v.getY();
		}
		return new Vector(x, y);
	}
	
	/**
	 * Gets the difference between two vectors
	 * @param v1 - Vector 1
	 * @param v2 - Vector 2
	 * @return Difference between two vectors
	 */
	public static Vector difference(Vector v1, Vector v2) {
		return new Vector(v1.getX() - v2.getX(), v1.getY() - v2.getY());
	}
	
	/**
	 * Get the resultant vector of a collision between a vector and a surface
	 * @param trajectory - Vector of moving object
	 * @param collide - Vector tangent to surface of stationary object
	 * @return Resultant vector
	 */
	public Vector bounceVector(Vector trajectory, Vector collide) {
		Vector nn = collide.getNormal().getNormalized();
		double dot2 = 2 * crossProduct(nn, trajectory);
		return difference(trajectory, new Vector(nn.getX() * dot2, nn.getY() * dot2));
	}
	
}
