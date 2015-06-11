package game;

import game.scenes.Scene;

import java.awt.Graphics;

import javax.swing.JPanel;

public class DrawPanel extends JPanel {
	
	private Scene source;

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        source.getGameScreen().draw(g);
    }
	
	public void setSource(Scene source) {
		this.source = source;
	}
}
