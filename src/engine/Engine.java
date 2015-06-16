package engine;

import engine.physics.Vector;
import game.scenes.actors.Actor;
import game.scenes.input.Input;

import java.util.ArrayList;

public class Engine {
	
	private long stepCount = 0;
	
	public void run(ArrayList<Actor> actors) {
		for (Actor actor : actors) {
			if (actor.getInputs() != null) {
				actor.getInputs().updateInputs();
				System.out.println(actor.getInputs().getValue(Input.JUMP));
			}
			
			// Rebounding off ground
			if (actor.getVector().getY() > 0 && actor.isGrounded()) {
				actor.setVector(new Vector(actor.getVector().getX(), -actor.getVector().getY()));
			}
			
			// Gravity
			if (actor.isGrounded()) {
				actor.setGravity(EngineConstants.ZERO_VECTOR);
			} else {
				if (actor.getGravity().getY() < EngineConstants.DEFAULT_TERM_VEL) {
					actor.setGravity(new Vector(0, actor.getGravity().getY() + EngineConstants.DEFAULT_ACCEL));
				}
			}
			
			// TODO: Collisions
			
			// Move actors
			if (actor.getHitlag() > 0) {
				if (stepCount % 4 == 0) {
					actor.move();
				}
				actor.decrementHitlag();
			} else {
				actor.move();
			}
		}
		stepCount++;
	}

}
