package game;

import game.scenes.FightScene;
import game.scenes.Scene;

import javax.swing.JApplet;
import javax.swing.JFrame;


public class Main extends JFrame {
	
	private Scene currentScene;
	private InputListener listener;
	private DrawPanel drawPanel;
	
    public static void main(String args[]) {
    	Main frame = new Main();
    	frame.init();
    }

    public void init() {
        listener = new InputListener();
        drawPanel = new DrawPanel();
        setScene(new FightScene());
        addMouseListener(listener);
        addKeyListener(listener);
        add(drawPanel);
        setVisible(true);
        setSize(1000, 750);
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
