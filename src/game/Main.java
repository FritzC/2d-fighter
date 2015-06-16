package game;

import game.scenes.FightScene;
import game.scenes.Scene;
import game.scenes.input.ControllerSource;
import game.scenes.input.KeyboardSource;

import java.awt.Dimension;

import javax.swing.JFrame;


public class Main extends JFrame {
	
	private Scene currentScene;
	private KeyboardSource keyHandler;
	private ControllerSource controllerHandler;
	private DrawPanel drawPanel;
	
    public static void main(String args[]) {
    	Main frame = new Main();
    	frame.init();
    }

    public void init() {
    	keyHandler = new KeyboardSource();
    	controllerHandler = new ControllerSource();
        drawPanel = new DrawPanel();
        setScene(new FightScene());
        getContentPane().add(drawPanel);
        setSize(new Dimension(1000, 750));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
    
    public void setScene(Scene newScene) {
    	if (currentScene != null) {
    		currentScene.close();
    	}
    	currentScene = newScene;
    	newScene.load();
    	drawPanel.setSource(currentScene);
    	newScene.startLogic();
    }
	
}
