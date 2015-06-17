package game.scenes.input;

import java.util.ArrayList;
import java.util.HashMap;

import org.lwjgl.input.Controller;
import org.lwjgl.input.Controllers;

public class ControllerSource extends InputSource {
	
	private HashMap<String, Controller> activeControllers;
	
	public ControllerSource() {
		activeControllers = new HashMap<String, Controller>();
	}

	public static ArrayList<Controller> getControllers() {
		ArrayList<Controller> controllers = new ArrayList<Controller>();
		Controllers.poll();
		for (int i = 0; i < Controllers.getControllerCount(); i++) {
			controllers.add(Controllers.getController(i));
		}
		return controllers;
	}
	
	public void updateInputs(Inputs inputs, boolean post) {
		// TODO: Add these
	}
}
