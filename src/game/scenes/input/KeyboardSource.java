package game.scenes.input;

import org.lwjgl.input.Keyboard;

public class KeyboardSource extends InputSource {
	
	public void updateInputs(Inputs inputHandler, boolean post) {
		inputHandler.setValue(InputType.CONTROL_Y, Keyboard.isKeyDown(Keyboard.KEY_W) ? 1 : Keyboard.isKeyDown(Keyboard.KEY_S) ? -1 : 0, post);
		inputHandler.setValue(InputType.CONTROL_X, Keyboard.isKeyDown(Keyboard.KEY_A) ? -1 : Keyboard.isKeyDown(Keyboard.KEY_D) ? 1 : 0, post);
		inputHandler.setValue(InputType.ATTACK_U, Keyboard.isKeyDown(Keyboard.KEY_I) ? 1 : 0, post);
		inputHandler.setValue(InputType.ATTACK_L, Keyboard.isKeyDown(Keyboard.KEY_J) ? 1 : 0, post);
		inputHandler.setValue(InputType.ATTACK_D, Keyboard.isKeyDown(Keyboard.KEY_K) ? 1 : 0, post);
		inputHandler.setValue(InputType.ATTACK_R, Keyboard.isKeyDown(Keyboard.KEY_L) ? 1 : 0, post);
		inputHandler.setValue(InputType.JUMP, Keyboard.isKeyDown(Keyboard.KEY_SPACE) ? 1 : 0, post);
		inputHandler.setValue(InputType.GUARD, Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) ? 1 : 0, post);
		inputHandler.setValue(InputType.GRAB, Keyboard.isKeyDown(Keyboard.KEY_TAB) ? 1 : 0, post);
	}

}
