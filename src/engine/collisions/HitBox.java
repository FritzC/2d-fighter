package engine.collisions;

import engine.physics.Vector;
import game.scenes.actors.Actor;

public class HitBox extends CollisionField {
	
	private Vector trajectory;
	private double priority;
	private String group;

	public HitBox(Actor owner, Location location, double priority, String group) {
		super(owner, location);
		this.priority = priority;
		this.group = group;
	}
	
	public Vector getTrajectory() {
		return trajectory;
	}
	
	public double getPriority() {
		return priority;
	}
	
	public String getGroup() {
		return group;
	}
}
