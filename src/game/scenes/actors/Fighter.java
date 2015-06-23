package game.scenes.actors;

import entities.OpenGLEntity;
import game.scenes.input.Inputs;

public class Fighter extends Actor {
	
	public Fighter(Inputs inputs, OpenGLEntity glEntity) {
		super(glEntity);
		setInputs(inputs);
	}

	@Override
	public int getWidth() {
		return 0;
	}

	@Override
	public int getHeight() {
		return 0;
	}

	@Override
	public int getX() {
		return 0;
	}

	@Override
	public int getY() {
		return 0;
	}

	@Override
	public boolean isFighter() {
		return true;
	}
}
