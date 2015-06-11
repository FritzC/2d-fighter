package game.scenes;

import engine.EngineConstants;
import game.scenes.input.InputHandler;
import game.scenes.screen.GameScreen;

public abstract class Scene {
	
	protected final InputHandler inputHandler;
	protected GameScreen gameScreen;
	protected SceneState state;
	private Thread logicThread;
	
	public Scene(InputHandler inputHandler, GameScreen gameScreen) {
		this.inputHandler = inputHandler;
		this.gameScreen = gameScreen;
		state = SceneState.LOADING;
	}
	
	public abstract void onLoad();
	
	public abstract void logicTick();
	
	public abstract void onClose();
	
	public void load() {
		state = SceneState.LOADING;
		onLoad();
	}
	
	public void startLogic() {
		state = SceneState.PROCESSING;
		logicThread = new Thread(new Runnable() {

			long lastUpdate;

			@Override
			public void run() {
				while (!Thread.currentThread().isInterrupted()) {
					if (System.currentTimeMillis() - lastUpdate < 1000d / EngineConstants.LPS)
						return;
					logicTick();
					lastUpdate = System.currentTimeMillis();
				}
			}

		});
	}
	
	public void close() {
		state = SceneState.CLOSING;
		logicThread.interrupt();
		onClose();
	}
	
	public InputHandler getInputHandler() {
		return inputHandler;
	}
	
	public GameScreen getGameScreen() {
		return gameScreen;
	}

}
