package game;

import engine.EngineConstants;
import game.scenes.Scene;

import java.awt.Graphics;

import javax.swing.JPanel;

public class DrawPanel extends JPanel {
	
	private Scene source;
	private long lastUpdate;

    public void paintComponent(Graphics g) {
    	if (System.currentTimeMillis() - lastUpdate < 1000d / EngineConstants.FPS)
    		return;
        super.paintComponent(g);
        source.getGameScreen().draw(g);
        lastUpdate = System.currentTimeMillis();
    }
	
	public void setSource(Scene source) {
		this.source = source;
	}
}
