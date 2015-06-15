package engine.collisions;

import game.scenes.actors.Actor;

import java.awt.List;
import java.awt.Rectangle;
import java.util.ArrayList;

public class QuadTree {

	/**
	 * Max Actors per node
	 */
	private final static int MAX_PER_NODE = 10;
	
	/**
	 * Max levels of the tree
	 */
	private final static int MAX_LEVELS = 5;
	
	private QuadTree[] nodes;  
	private Rectangle bounds;
	private int level;
	private ArrayList<Actor> objects;
	
	/**
	 * Constructs part of a QuadTree
	 * @param level - Depth in the tree
	 * @param bounds Bounding area of the node
	 */
	public QuadTree(int level, Rectangle bounds) {
		this.bounds = bounds;
		this.level = level;
		nodes = new QuadTree[4];
		objects = new ArrayList<Actor>();
	}
	
	/**
	 * Clears the current node and all child nodes
	 */
	public void clear() {
		objects.clear();
		for (QuadTree node : nodes) {
			if (node != null) {
				node.clear();
				node = null;
			}
		}
	}

	/**
	 * Adds another level to the tree splitting into 4 more quadrents
	 */
	private void split() {
		int subWidth = (int) (bounds.getWidth() / 2);
		int subHeight = (int) (bounds.getHeight() / 2);
		int x = (int) bounds.getX();
		int y = (int) bounds.getY();
		nodes[0] = new QuadTree(level + 1, new Rectangle(x + subWidth, y,
				subWidth, subHeight));
		nodes[1] = new QuadTree(level + 1, new Rectangle(x, y, subWidth,
				subHeight));
		nodes[2] = new QuadTree(level + 1, new Rectangle(x, y + subHeight,
				subWidth, subHeight));
		nodes[3] = new QuadTree(level + 1, new Rectangle(x + subWidth, y
				+ subHeight, subWidth, subHeight));
	}
	
	/**
	 * Helper method to get theindex of the subnode an Actor belongs to
	 * @param obj - Actor being checked
	 * @return Index of subnode object belongs in
	 */
	private int getIndex(Actor obj) {
		int midX = (int) (bounds.getY() + bounds.getHeight() / 2);
		int midY = (int) (bounds.getY() + bounds.getHeight() / 2);
		boolean horizontalFit = obj.getWidth() < bounds.getWidth() / 2;
		boolean verticalFit = obj.getHeight() < bounds.getY() + bounds.getHeight() / 2;
		 // I don't think this is necessary, maybe can remove
		boolean inBounds = obj.getX() >= bounds.getX() && obj.getY() >= bounds.getY() 
				&& obj.getX() + obj.getWidth() <= bounds.getX() + bounds.getWidth()
				&& obj.getY() + obj.getHeight() <= bounds.getY() + bounds.getHeight();
		if (!verticalFit || !horizontalFit || !inBounds) {
			return -1;
		}
		if (obj.getX() + obj.getWidth() <= midX && obj.getY() + obj.getHeight() <= midY) {
			return 0;
		}
		if (obj.getX() > midX && obj.getY() + obj.getHeight() <= midY) {
			return 1;
		}
		if (obj.getX() + obj.getWidth() <= midX && obj.getY() > midY) {
			return 2;
		}
		if (obj.getX() > midX && obj.getY() > midY) {
			return 3;
		}
		return -1;
	}
	
	/**
	 * Inserts an actor into the tree
	 * @param obj - The actor being added to the tree
	 */
	public void insert(Actor obj) {
		if (nodes[0] != null) {
			int index = getIndex(obj);
			if (index != -1) {
				nodes[index].insert(obj);
				return;
			}
		}
		objects.add(obj);
		if (objects.size() > MAX_PER_NODE && level < MAX_LEVELS) {
			if (nodes[0] == null) {
				split();
			}
			int i = 0;
			while (i < objects.size()) {
				int index = getIndex(objects.get(i));
				if (index != -1) {
					nodes[index].insert(objects.remove(i));
				} else {
					i++;
				}
			}
		}
	}
	
	public ArrayList<Actor> getPotentialCollisions(Actor obj) {
		return retrieve(new ArrayList<Actor>(), obj);
	}

	public ArrayList<Actor> retrieve(ArrayList<Actor> returnObjects, Actor obj) {
		int index = getIndex(obj);
		if (index != -1 && nodes[0] != null) {
			nodes[index].retrieve(returnObjects, obj);
		}
		returnObjects.addAll(objects);
		return returnObjects;
	}

}
