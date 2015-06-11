package game.scenes;

import game.scenes.input.InputHandler;
import game.scenes.screen.GameScreen;

public abstract class Scene {
	
	protected final InputHandler inputHandler;
	protected GameScreen gameScreen;
	protected SceneState state;
	
	public Scene(InputHandler inputHandler) {
		this.inputHandler = inputHandler;
		state = SceneState.LOADING;
	}
	
	public abstract void load();
	
	public abstract void process();
	
	public abstract void close();

}
