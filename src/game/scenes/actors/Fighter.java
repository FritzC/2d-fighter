package game.scenes.actors;

import entities.OpenGLEntity;

public class Fighter extends Actor {
	
	public Fighter(OpenGLEntity glEntity) {
		super(glEntity);
	}

	private int hitstun;
	public void decrementHistun() {
		hitstun--;
	}
	
	public int getHistun() {
		return hitstun;
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
