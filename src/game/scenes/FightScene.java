package game.scenes;

import game.components.Stage;
import game.scenes.actors.Fighter;
import game.scenes.input.FightInput;
import game.scenes.screen.FightScreen;

import java.util.ArrayList;

public class FightScene extends Scene {
	
	private ArrayList<Fighter> fighters = new ArrayList<Fighter>();
	private Stage stage;
	
	public FightScene() {
		super(new FightInput(), new FightScreen());
	}

	@Override
	public void onLoad() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void logicTick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClose() {
		// TODO Auto-generated method stub
		
	}

}
