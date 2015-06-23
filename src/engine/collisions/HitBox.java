package engine.collisions;

import engine.physics.Vector;
import game.scenes.actors.Actor;

public class HitBox extends CollisionField {
	
	private Vector trajectory;
	private double priority;
	private String group;
	private double damage;

	public HitBox(Actor owner, Location location, double priority, double damage, String group) {
		super(owner, location);
		this.priority = priority;
		this.damage = damage;
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
	
	public double getDamage() {
		return damage;
	}
}
