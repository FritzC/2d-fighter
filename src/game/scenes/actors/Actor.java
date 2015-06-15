package game.scenes.actors;

import engine.collisions.Location;
import engine.physics.Vector;

public abstract class Actor {
	
	private Vector vector;
	private Location location;
	
	public abstract int getWidth();
	
	public abstract int getHeight();
	
	public abstract int getX();
	
	public abstract int getY();
	
	public void setLocation(Location location) {
		this.location = location;
	}

}
