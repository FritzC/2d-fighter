package game.scenes.input;

import org.lwjgl.input.Keyboard;

public class KeyboardSource extends InputSource {
	
	public void updateInputs(Inputs inputHandler) {
		inputHandler.setValue(Input.MOVE_U, Keyboard.isKeyDown(Keyboard.KEY_W) ? 1 : 0);
		inputHandler.setValue(Input.MOVE_L, Keyboard.isKeyDown(Keyboard.KEY_A) ? 1 : 0);
		inputHandler.setValue(Input.MOVE_D, Keyboard.isKeyDown(Keyboard.KEY_S) ? 1 : 0);
		inputHandler.setValue(Input.MOVE_R, Keyboard.isKeyDown(Keyboard.KEY_D) ? 1 : 0);
		inputHandler.setValue(Input.ATTACK_U, Keyboard.isKeyDown(Keyboard.KEY_I) ? 1 : 0);
		inputHandler.setValue(Input.ATTACK_L, Keyboard.isKeyDown(Keyboard.KEY_J) ? 1 : 0);
		inputHandler.setValue(Input.ATTACK_D, Keyboard.isKeyDown(Keyboard.KEY_K) ? 1 : 0);
		inputHandler.setValue(Input.ATTACK_R, Keyboard.isKeyDown(Keyboard.KEY_L) ? 1 : 0);
		inputHandler.setValue(Input.JUMP, Keyboard.isKeyDown(Keyboard.KEY_SPACE) ? 1 : 0);
		inputHandler.setValue(Input.GUARD, Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) ? 1 : 0);
		inputHandler.setValue(Input.GRAB, Keyboard.isKeyDown(Keyboard.KEY_TAB) ? 1 : 0);
	}

}
