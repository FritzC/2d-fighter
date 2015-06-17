package game.scenes.input;

import game.scenes.input.Inputs.InputState;

import java.util.ArrayList;

public class InputStream {
	
	private ArrayList<Input> inputs;
	private int index;
	private long lastInput;
	
	public InputStream() {
		inputs = new ArrayList<>();
		lastInput = System.currentTimeMillis();
	}
	
	public void add(InputType type, InputState state, float strength) {
		inputs.add(new Input(type, state, strength, System.currentTimeMillis() - lastInput));
		lastInput = System.currentTimeMillis();
	}
	
	private boolean hasNewInput() {
		return index < inputs.size();
	}
	
	private Input getNewInput() {
		return inputs.get(index++);
	}
	
	public ArrayList<Input> getNewInputs() {
		ArrayList<Input> newInputs = new ArrayList<>();
		while (hasNewInput()) {
			newInputs.add(getNewInput());
		}
		return newInputs;
	}

}
