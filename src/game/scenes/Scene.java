package game.scenes;

import game.scenes.input.InputHandler;
import game.scenes.screen.GameScreen;

public abstract class Scene {
	
	protected final InputHandler inputHandler;
	protected GameScreen gameScreen;
	protected SceneState state;
	
	public Scene(InputHandler inputHandler, GameScreen gameScreen) {
		this.inputHandler = inputHandler;
		this.gameScreen = gameScreen;
		state = SceneState.LOADING;
	}
	
	public abstract void load();
	
	public abstract void process();
	
	public abstract void close();
	
	public InputHandler getInputHandler() {
		return inputHandler;
	}
	
	public GameScreen getGameScreen() {
		return gameScreen;
	}

}
