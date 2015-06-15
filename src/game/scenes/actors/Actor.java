package game.scenes.actors;

import engine.collisions.Location;
import engine.physics.Vector;

public abstract class Actor {

	private Vector vector;
	private Vector gravity;
	private Location location;
	private int hitlag;
	private boolean grounded;
	
	public abstract int getWidth();
	
	public abstract int getHeight();
	
	public abstract int getX();
	
	public abstract int getY();
	
	public void move() {
		location.changeX(vector.getX());
		location.changeY(vector.getY());
	}

	public void setVector(Vector vector) {
		this.vector = vector;
	}
	
	public void setLocation(Location location) {
		this.location = location;
	}

	public void setGravity(Vector gravity) {
		this.gravity = gravity;
	}

	public Vector getVector() {
		return vector;
	}

	public Vector getGravity() {
		return gravity;
	}
	
	public boolean isFighter() {
		return false;
	}
	
	public boolean isGrounded() {
		return grounded;
	}
	
	public int getHitlag() {
		return hitlag;
	}
	
	public void decrementHitlag() {
		hitlag--;
	}

}
