package game.scenes.actors.stick;

import java.awt.Point;

public class LineSegment {

	private LineSegment trunk;
	
	private double length;
	private double angle;
	
	public Point getStartPoint() {
		if (trunk != null)
			return trunk.getEndPoint();
		return new Point(0, 0);
	}
	
	public Point getEndPoint() {
		Point start = getStartPoint();
		int x = (int) Math.round(start.getX() + length * Math.cos(angle));
		int y = (int) Math.round(start.getY() + length * Math.sin(angle));
		return new Point(x, y);
	}
	
	public void setAngle(double angle) {
		this.angle = angle;
	}
	
	public void setLength(double length) {
		this.length = length;
	}
}
