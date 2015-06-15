import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

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

public class Main {

	private final Object lock = new Object();
	private boolean isRunning = false;

	private int frameWidth = 1280;
	private int frameHeight = 800;

	private Thread glThread;
	
	private Loader loader;
	private MasterRenderer renderer;
	private Camera camera;
	private Player player;
	
	private List<Light> lights = new ArrayList<Light>();
	private List<Entity> entities = new ArrayList<Entity>();

	public static void main(String[] args) {
		new Main().run();
	}

	private void run() {
		final JFrame frame = new JFrame("Fighter");
		frame.setSize(frameWidth, frameHeight);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				int result = JOptionPane.showConfirmDialog(frame,
						"Do you want to quit?");
				if (result == JOptionPane.OK_OPTION) {
					frame.setVisible(false);
					frame.dispose();
				}
			}
		});
		JPanel mainPanel = new JPanel(null);

		Canvas canvas = new Canvas() {
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
		};
		canvas.setPreferredSize(new Dimension(frameWidth, frameHeight));
		canvas.setIgnoreRepaint(true);

		try {
			Display.setParent(canvas);
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		JPanel canvasPanel = new JPanel();
		canvasPanel.add(canvas);
		mainPanel.add(canvasPanel, BorderLayout.SOUTH);
		frame.getContentPane().add(mainPanel);

		// frame.pack();
		frame.setVisible(true);
	}

	private void startGL() {
		glThread = new Thread(new Runnable() {
			@Override
			public void run() {
				setIsRunning(true);
				DisplayManager.createDisplay(frameWidth, frameHeight);
				initGame();
				while (isRunning()) {
					camera.move();
					GL11.glEnable(GL30.GL_CLIP_DISTANCE0);
					renderer.renderScene(lights, entities, camera, new Vector4f(0, 1, 0, 50));
					GL11.glDisable(GL30.GL_CLIP_DISTANCE0);
					for(Entity entity : entities){
						renderer.processEntity(entity);
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
	
	private void initGame() {
		loader = new Loader();
		ModelData manData = OBJFileLoader.loadOBJ("person");
		TexturedModel man = new TexturedModel(loader.loadToVAO(manData.getVertices(), manData.getTextureCoords(), manData.getNormals(), manData.getIndices()),
				new ModelTexture(loader.loadTexture("person")));
		
		player = new Player(man, new Vector3f(0, 0, 0), 0, 0, 0, 1);
		camera = new Camera(player);
		renderer = new MasterRenderer(loader);
		lights.add(new Light(new Vector3f(20000, 20000, 2000), new Vector3f(1,1,1)));
		entities.add(new Entity(man, new Vector3f(0, 0, 0), 0, 0, 0, 1));
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

	private boolean isRunning() {
		synchronized (lock) {
			return isRunning;
		}
	}

}