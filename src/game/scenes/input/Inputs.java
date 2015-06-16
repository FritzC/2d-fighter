package game.scenes.input;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.HashMap;

public class Inputs {
	
	private HashMap<Input, Float> inputs;
	private InputSource source;
	
	public Inputs(InputSource source) {
		inputs = new HashMap<Input, Float>();
		for (Input input : Input.values()) {
			inputs.put(input, 0f);
		}
		this.source = source;
	}
	
	public void setValue(Input input, float value) {
		inputs.put(input, value);
	}
	
	public float getValue(Input input) {
		return inputs.get(input);
	}
	
	public void updateInputs() {
		if (source != null) {
			try {
				source.updateInputs(this);
			} catch (Exception e) {
				System.out.println("Error reading inputs");
			}
		}
	}
	
	public void setSource(InputSource source) {
		this.source = source;
	}

}
