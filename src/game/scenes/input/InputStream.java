package game.scenes.input;

import java.awt.List;
import java.util.ArrayList;

public class InputStream {
	
	private ArrayList<Input> inputs;
	private int index;
	
	public InputStream(String identifier) {
		inputs = new ArrayList<Input>();
	}
	
	public void input(Input input) {
		inputs.add(input);
	}

}
