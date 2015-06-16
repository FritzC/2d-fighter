package game.scenes.screen;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
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
import entities.Camera;
import entities.Entity;
import entities.Player;
import game.Settings;

public class FightScreen extends GameScreen {
	
	private Thread glThread;
	private boolean isRunning = false;
	private final Object lock = new Object();
	
	private Loader loader;
	private MasterRenderer renderer;
	private Camera camera;
	private Player player;
	
	private List<Light> lights = new ArrayList<Light>();
	private List<Entity> entities = new ArrayList<Entity>();

	public FightScreen() {
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
		final FightScreen canvas = this;
		glThread = new Thread(new Runnable() {
			@Override
			public void run() {
				setIsRunning(true);
				DisplayManager.createDisplay(Settings.gameWidth, Settings.gameHeight);
				initGame();
				while (isRunning()) {
					camera.move();
					player.move();
					GL11.glEnable(GL30.GL_CLIP_DISTANCE0);
					renderer.renderScene(lights, entities, camera, new Vector4f(0, 1, 0, 50));
					GL11.glDisable(GL30.GL_CLIP_DISTANCE0);
					for(Entity entity : entities){
						renderer.processEntity(entity);
					}
					renderer.processEntity(player);
					DisplayManager.updateDisplay();
				}
				renderer.cleanUp();
				loader.cleanUp();
				DisplayManager.closeDisplay();
			}
		}, "LWJGL Thread");
		glThread.start();
	}
	
	private void initGame() {
		loader = new Loader();
		ModelData manData = OBJFileLoader.loadOBJ("person");
		TexturedModel man = new TexturedModel(loader.loadToVAO(manData.getVertices(), manData.getTextureCoords(), manData.getNormals(), manData.getIndices()),
				new ModelTexture(loader.loadTexture("person")));
		
		player = new Player(man, new Vector3f(0, 0, 0), 0, 0, 0, 1);
		camera = new Camera(player);
		renderer = new MasterRenderer(loader);
		lights.add(new Light(new Vector3f(20000, 20000, 2000), new Vector3f(1,1,1)));

		ModelData stagedata = OBJFileLoader.loadOBJ("stage");
		TexturedModel stage = new TexturedModel(loader.loadToVAO(stagedata.getVertices(), stagedata.getTextureCoords(), stagedata.getNormals(), stagedata.getIndices()),
				new ModelTexture(loader.loadTexture("floor")));
		
		
		entities.add(new Entity(stage, new Vector3f(0, 0, 0), 0, 0, 0, 8));
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

}
