package game.scenes;

import engine.Engine;
import game.components.Stage;
import game.scenes.actors.Actor;
import game.scenes.actors.Fighter;
import game.scenes.input.Inputs;
import game.scenes.input.KeyboardSource;
import game.scenes.screen.FightScreen;

import java.util.ArrayList;

public class FightScene extends Scene {
	
	public ArrayList<Actor> actors;
	private Stage stage;
	private FightScreen fightScreen;
	private Engine engine;
	
	public FightScene() {
		super(null);
		actors = new ArrayList<Actor>();
		gameScreen = new FightScreen(this);
		fightScreen = (FightScreen) gameScreen;
		engine = new Engine();
	}

	@Override
	public void onLoad() {
	}

	@Override
	public void logicTick() {
		engine.run(actors);
	}

	@Override
	public void onClose() {
		// TODO Auto-generated method stub
		
	}
	
	public void loadActors() {
		actors.add(new Fighter(new Inputs(new KeyboardSource()), fightScreen.getEntity("player")));
		actors.add(new Actor(fightScreen.getEntity("stage0")));
		actors.add(new Actor(fightScreen.getEntity("stage1")));
	}

}
