package game;

import engine.collisions.Location;
import game.scenes.actors.Actor;

import java.util.ArrayList;

public class GameArea {

	private int width;
	private int height;
	private double scale;
	
	private ArrayList<Actor> actors;
	
	/**
	 * Creates a game area
	 * @param width - Width of the area
	 * @param height - Height of the area
	 * @param scale - Size of actors modifier
	 */
	public GameArea(int width, int height, double scale) {
		this.width = width;
		this.height = height;
		this.scale = scale;
	}
	
	/**
	 * Adds an actor to the area
	 * @param obj - Actor to add
	 * @param placement - Location to add actor
	 */
	public void insert(Actor obj, Location placement) {
		obj.setLocation(placement);
		actors.add(obj);
	}
	
	/**
	 * Removes an actor from the area
	 * @param obj - Actor to remove
	 */
	public void remove(Actor obj) {
		actors.remove(obj);
	}
}
