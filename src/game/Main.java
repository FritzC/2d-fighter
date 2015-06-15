package game;

import game.scenes.FightScene;
import game.scenes.Scene;

import javax.swing.JApplet;


public class Main extends JApplet {
	
	private Scene currentScene;
	private InputListener listener;
	private DrawPanel drawPanel;
	
    public void init() {
        listener = new InputListener();
        drawPanel = new DrawPanel();
        setScene(new FightScene());
        addMouseListener(listener);
        addKeyListener(listener);
        add(drawPanel);
        setVisible(true);
    }

    public void start() {
        
    }

    public void stop() {
        
    }
    
    public void setScene(Scene newScene) {
    	if (currentScene != null) {
    		currentScene.close();
    	}
    	currentScene = newScene;
    	newScene.load();
    	listener.setSource(currentScene);
    	drawPanel.setSource(currentScene);
    }
	
}
