package game.scenes.actors;

import engine.collisions.Location;
import engine.physics.Vector;
import entities.OpenGLEntity;
import game.scenes.input.Inputs;

public class Actor {

	private Vector vector;
	private Vector gravity;
	private Location location;
	private Inputs inputs;
	
	private int hitlag;
	private boolean grounded;
	
	private OpenGLEntity glEntity;
	
	public Actor(OpenGLEntity glEntity) {
		this.glEntity = glEntity;
		vector = new Vector(0, 0);
		gravity = new Vector(0, 0);
		location = new Location(0, 0);
	}
	
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
	
	public OpenGLEntity getGLEntity() {
		return glEntity;
	}
	
	public int getWidth() {
		return 0;
	}
	
	public int getHeight() {
		return 0;
	}
	
	public int getX() {
		return 0;
	}
	
	public int getY() {
		return 0;
	}
	
	public void setInputs(Inputs inputs) {
		this.inputs = inputs;
	}
	
	public Inputs getInputs() {
		return inputs;
	}

}
