package game.scenes.input;

import java.util.HashMap;

public class Inputs {
	
	private HashMap<InputType, Float[]> inputs;
	private InputSource source;
	private InputStream inputStream;
	
	public Inputs(InputSource source) {
		inputs = new HashMap<>();
		for (InputType input : InputType.values()) {
			inputs.put(input, new Float[] {0f, 0f});
		}
		this.source = source;
		inputStream = new InputStream();
	}
	
	public void setValue(InputType input, float value, boolean post) {
		Float[] vals = inputs.get(input);
		inputs.put(input, new Float[] {post ? vals[0] : value, post ? value : vals[1]});
	}
	
	public float getValue(InputType input) {
		return inputs.get(input)[1];
	}
	
	public void updateInputs() {
		if (source != null) {
			try {
				source.updateInputs(this, false);
				pushChanges();
				source.updateInputs(this, true);
			} catch (Exception e) {
				System.out.println("Error reading inputs");
			}
		}
	}
	
	public InputStream getStream() {
		return inputStream;
	}
	
	public void setSource(InputSource source) {
		this.source = source;
	}
	
	public void pushChanges() {
		for (InputType input : inputs.keySet()) {
			Float[] vals = inputs.get(input);
			float[] vals2 = {vals[0], vals[1]};
			if (vals2[0] != vals2[1]
					&& !input.toString().contains("CONTROL")) {
				if (vals2[0] == 0f) {
					inputStream.add(input, InputState.RELEASED, vals2[0]);
				} else if (Math.abs(vals2[0]) == 1f) {
					inputStream.add(input, InputState.PRESSED, vals2[0]);
				} else {
					inputStream.add(input, InputState.CHANGED, vals2[0]);
				}
			}
		}
		Float[] x = inputs.get(InputType.CONTROL_X);
		float[] x2 = {x[0], x[1]};
		Float[] y = inputs.get(InputType.CONTROL_Y);
		float[] y2 = {y[0], y[1]};
		InputState stickState = getStickState(x2[0], y2[0]);
		if (stickState != getStickState(x2[1], y2[1])) {
			inputStream.add(InputType.CONTROL_STICK, stickState,
					stickState == InputState.RELEASED ? 0f : 1f);
		}
	}

	private InputState getStickState(float x, float y) {
		if (x >= 0.7 && Math.abs(y) < 0.45) {
			return InputState.STICK_RIGHT;
		}
		if (x >= 0.45 && y >= 0.45) {
			return InputState.STICK_RIGHT_UP;
		}
		if (Math.abs(x) < 0.45 && y >= 0.7) {
			return InputState.STICK_UP;
		}
		if (x <= -0.45 && y >= 0.45) {
			return InputState.STICK_LEFT_UP;
		}
		if (x <= -0.7 && Math.abs(y) < 0.45) {
			return InputState.STICK_LEFT;
		}
		if (x <= -0.45 && y <= -0.45) {
			return InputState.STICK_LEFT_DOWN;
		}
		if (Math.abs(x) < 0.45 && y <= -0.7) {
			return InputState.STICK_DOWN;
		}
		if (x >= 0.45 && y <= -0.45) {
			return InputState.STICK_RIGHT_DOWN;
		}
		return InputState.RELEASED;
	}
	
	public enum InputState {
		PRESSED,
		CHANGED,
		RELEASED,
		STICK_UP,
		STICK_RIGHT,
		STICK_DOWN,
		STICK_LEFT,
		STICK_RIGHT_UP,
		STICK_RIGHT_DOWN,
		STICK_LEFT_UP,
		STICK_LEFT_DOWN;
	}

}
