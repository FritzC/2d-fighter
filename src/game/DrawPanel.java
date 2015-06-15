package game;

import engine.EngineConstants;
import game.scenes.Scene;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.JPanel;

public class DrawPanel extends JPanel {
	
	private Scene source;
	private long lastUpdate;
	
	public DrawPanel() {
		setLayout(new BorderLayout());
	}

    public void paintComponent(Graphics g) {
    	if (System.currentTimeMillis() - lastUpdate < 1000d / EngineConstants.FPS)
    		return;
        super.paintComponent(g);
        source.getGameScreen().draw(g);
        lastUpdate = System.currentTimeMillis();
    }
	
	public void setSource(Scene source) {
		if (this.source != null) {
			remove(this.source.getGameScreen());
		}
		this.source = source;
		source.getGameScreen().setPreferredSize(new Dimension(1000, 750));
		add(source.getGameScreen());
	}
}
