package game.scenes.screen;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import lights.Light;
import models.TexturedModel;
import objConverter.ModelData;
import objConverter.OBJFileLoader;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

import render.DisplayManager;
import render.Loader;
import render.MasterRenderer;
import textures.ModelTexture;
import engine.collisions.Box;
import entities.Camera;
import entities.OpenGLEntity;
import entities.Player;
import game.Settings;
import game.scenes.FightScene;
import game.scenes.actors.Actor;

public class FightScreen extends GameScreen {
	
	private Thread glThread;
	private boolean isRunning = false;
	private final Object lock = new Object();
	
	public Loader loader;
	public MasterRenderer renderer;
	public Camera camera;
	private Player player;
	
	public List<Light> lights = new ArrayList<Light>();
	private FightScene fightScene;
	private HashMap<String, OpenGLEntity> entities = new HashMap<String, OpenGLEntity>();

	public FightScreen(FightScene fightScene) {
		this.fightScene = fightScene;
		setPreferredSize(new Dimension(1000, 750));
		setIgnoreRepaint(true);
		try {
			Display.setParent(this);
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void draw(Graphics g) {
		g.fillRect(100,100, 100, 100);
	}
	
	@Override
	public void addNotify() {
		super.addNotify();
		startGL();
	}

	@Override
	public void removeNotify() {
		stopGL();
		super.removeNotify();
	}

	private boolean isRunning() {
		synchronized (lock) {
			return isRunning;
		}
	}
	
	private void startGL() {
		glThread = new Thread(new Runnable() {
			@Override
			public void run() {
				setIsRunning(true);
				DisplayManager.createDisplay(Settings.gameWidth, Settings.gameHeight);
				initGame();
				while (isRunning()) {
					processMovement(player);
					camera.move();
					GL11.glEnable(GL30.GL_CLIP_DISTANCE0);
					renderer.renderScene(lights, fightScene.actors, camera, new Vector4f(0, 1, 0, 50));
					GL11.glDisable(GL30.GL_CLIP_DISTANCE0);
					for(Actor entity : fightScene.actors){
						renderer.processEntity(entity.getGLEntity());
					}
					DisplayManager.updateDisplay();
				}
				renderer.cleanUp();
				loader.cleanUp();
				DisplayManager.closeDisplay();
			}
		}, "LWJGL Thread");
		glThread.start();
	}
	
	private void processMovement(Player player) {
		Box box1 = player.getData().getBox();
		for(Actor entity2 : fightScene.actors){
			Box box2 = entity2.getGLEntity().getData().getBox();
			if(box1.getMinX() < box2.getMaxX() && box1.getMaxX() > box2.getMinX() &&
					box1.getMinY() < box2.getMaxY() && box1.getMaxY() > box2.getMinY()){
				player.setColliding(true);
				entity2.getGLEntity().setColliding(true);
				//System.out.println("Player and Entity colliding");
				//player.increasePosition(0.5f, 0, 0);
			} else {
				player.move();
				player.setColliding(false);
				entity2.getGLEntity().setColliding(false);
			}
		}
	}

	private void initGame() {
		loader = new Loader();
		TexturedModel man = new TexturedModel(loader.loadToVAO(OBJFileLoader.loadOBJ("person")),
				new ModelTexture(loader.loadTexture("person")));
		player = new Player(man, new Vector3f(0, 0, 0), 0, 0, 0, 1);

		camera = new Camera(player);
		renderer = new MasterRenderer(loader);
		lights.add(new Light(new Vector3f(20000, 20000, 2000), new Vector3f(1,
				1, 1)));
		TexturedModel stage = new TexturedModel(loader.loadToVAO(OBJFileLoader.loadOBJ("stage")),
				new ModelTexture(loader.loadTexture("texture")));
		
		entities.put("stage0", new OpenGLEntity(stage, new Vector3f(0, 0, 0), 0,
				0, 0, 8));
		entities.put("stage1", new OpenGLEntity(stage, new Vector3f(4, 30, 0), 0,
				0, 0, 4));
		entities.put("player", player);
		fightScene.loadActors();
	}

	private void stopGL() {
		setIsRunning(false);
		try {
			glThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void setIsRunning(boolean isRunning) {
		synchronized (lock) {
			this.isRunning = isRunning;
		}
	}
	
	public OpenGLEntity getEntity(String identifier) {
		return entities.get(identifier);
	}

}
