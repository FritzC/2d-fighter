package game;

import game.scenes.LoadScene;
import game.scenes.Scene;

import javax.swing.JApplet;


public class Main extends JApplet {
	
	private Scene currentScene;
	private InputListener listener;
	private DrawPanel drawPanel;
	
    public void init() {
        listener = new InputListener();
        drawPanel = new DrawPanel();
        setScene(new LoadScene());
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
    	currentScene = newScene;
    	listener.setSource(currentScene);
    	drawPanel.setSource(currentScene);
    }
	
}
