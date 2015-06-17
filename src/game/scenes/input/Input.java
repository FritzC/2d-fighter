package game.scenes.input;

import game.scenes.input.Inputs.InputState;

public class Input {

	private InputType type;
	private InputState state;
	private float strength;
	private long lastInputSplit;
	
	public Input(InputType type, InputState state, float strength, long lastInputSplit) {
		this.type = type;
		this.state = state;
		this.strength = strength;
		this.lastInputSplit = lastInputSplit;
	}
	
	public float getStrength() {
		return strength;
	}
	
	public long getInputSplit() {
		return lastInputSplit;
	}
	
	public InputType getType() {
		return type;
	}
	
	public InputState getState() {
		return state;
	}
	
	@Override
	public String toString() {
		return type.toString() + ": " + state.toString() + " [" + strength + "] ~" + lastInputSplit;
	}
}
