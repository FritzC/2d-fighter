package engine.collisions;

public class Location {

	private double x;
	private double y;
	
	public Location(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double getX() {
		return x;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setY(double y) {
		this.y = y;
	}

	public void changeX(double sum) {
		x += sum;
	}
	
	public void changeY(double sum) {
		y += sum;
	}
	
}
