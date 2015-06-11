package game;

import game.scenes.Scene;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class InputListener implements MouseListener, KeyListener {
	
	private Scene source;

	@Override
	public void keyPressed(KeyEvent e) {
		source.getInputHandler().keyDown(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		source.getInputHandler().keyUp(e);
	}

	@Override
	public void keyTyped(KeyEvent e) { }

	@Override
	public void mouseClicked(MouseEvent e) { 
	}

	@Override
	public void mouseEntered(MouseEvent e) { }

	@Override
	public void mouseExited(MouseEvent e) { }

	@Override
	public void mousePressed(MouseEvent e) {
		source.getInputHandler().mouseDown(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		source.getInputHandler().mouseUp(e);
	}
	
	public void setSource(Scene source) {
		this.source = source;
	}
	

}
