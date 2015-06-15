package engine.collisions;

import game.scenes.actors.Actor;

public abstract class CollisionField {

	private Actor owner;
	private Location location;
	
	public CollisionField(Actor owner, Location location) {
		this.owner = owner;
		this.location = location;
	}
	
	public Actor getOwner() {
		return owner;
	}
	
	public Location getLocation() {
		return location;
	}
	
	public void setLocation(Location location) {
		this.location = location;
	}
	
}
