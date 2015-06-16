package game;

import game.scenes.FightScene;
import game.scenes.Scene;

import java.awt.BorderLayout;
import java.awt.Dimension;

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
        getContentPane().add(drawPanel);
        setSize(new Dimension(1000, 750));
        pack();
        setVisible(true);
    }
    
    public void setScene(Scene newScene) {
    	if (currentScene != null) {
    		currentScene.close();
    	}
    	currentScene = newScene;
    	newScene.load();
    	listener.setSource(currentScene);
    	drawPanel.setSource(currentScene);
    	newScene.startLogic();
    }
	
}
