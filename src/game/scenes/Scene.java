package game.scenes;

import engine.EngineConstants;
import game.scenes.input.ControllerSource;
import game.scenes.input.Inputs;
import game.scenes.input.KeyboardSource;
import game.scenes.screen.GameScreen;

public abstract class Scene {
	
	protected GameScreen gameScreen;
	protected SceneState state;
	private Thread logicThread;
	
	public Scene(GameScreen gameScreen) {
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
						continue;
/*					try {
						if (controller == null) {
							keyboard.updateInputs(primaryInputs);
						} else {
							controller.updateInputs(primaryInputs);
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}*/
					logicTick();
					lastUpdate = System.currentTimeMillis();
				}
			}

		}, "Logic Thread");
		logicThread.start();
	}
	
	public void close() {
		state = SceneState.CLOSING;
		logicThread.interrupt();
		onClose();
	}
	
	public GameScreen getGameScreen() {
		return gameScreen;
	}

}
