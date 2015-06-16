package game.scenes;

import entities.OpenGLEntity;
import entities.Player;
import game.components.Stage;
import game.scenes.actors.Actor;
import game.scenes.input.FightInput;
import game.scenes.screen.FightScreen;

import java.util.ArrayList;

import models.TexturedModel;
import objConverter.ModelData;
import objConverter.OBJFileLoader;

import org.lwjgl.util.vector.Vector3f;

import render.Loader;
import textures.ModelTexture;

public class FightScene extends Scene {
	
	public ArrayList<Actor> actors;
	private Stage stage;
	private FightScreen fightScreen;
	
	public FightScene() {
		super(new FightInput(), null);
		actors = new ArrayList<Actor>();
		gameScreen = new FightScreen(this);
		fightScreen = (FightScreen) gameScreen;
	}

	@Override
	public void onLoad() {
	}

	@Override
	public void logicTick() {
	}

	@Override
	public void onClose() {
		// TODO Auto-generated method stub
		
	}
	
	public void loadActors() {
		actors.add(new Actor(fightScreen.getEntity("player")));
		actors.add(new Actor(fightScreen.getEntity("stage0")));
		actors.add(new Actor(fightScreen.getEntity("stage1")));
	}

}
