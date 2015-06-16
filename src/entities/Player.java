package entities;

import models.TexturedModel;
import objConverter.ModelData;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

import render.DisplayManager;

public class Player extends OpenGLEntity {

	protected static final float RUN_SPEED = 40;
	protected static final float TURN_SPEED = 160;
	protected static final float GRAVITY = -50;
	protected static final float JUMP_POWER = 80;

	protected float currentSpeed = 0;
	protected float currentTurnSpeed = 0;
	protected float upwardsSpeed;

	protected boolean isInAir;
	

	public Player(TexturedModel model, Vector3f position, float rotX,
			float rotY, float rotZ, float scale) {
		super(model, position, rotX, rotY, rotZ, scale);
	}

	protected void jump() {
		if (!isInAir) {
			this.upwardsSpeed = JUMP_POWER;
			isInAir = true;
		}
	}

	public void move(){
		if(!isColliding){
			checkInputs();
			super.increaseRotation(0, currentTurnSpeed * DisplayManager.getFrameTimeSeconds(), 0);
			float distance = currentSpeed * DisplayManager.getFrameTimeSeconds();
			float dx = (float) (distance * Math.sin(Math.toRadians(super.getRotY())));
			float dz = (float) (distance * Math.cos(Math.toRadians(super.getRotY())));
			super.increasePosition(dx,0, dz);
			upwardsSpeed += GRAVITY * DisplayManager.getFrameTimeSeconds();
			super.increasePosition(0, upwardsSpeed * DisplayManager.getFrameTimeSeconds(), 0);
			float floorHeight = -40;
			if(super.getPosition().getY() < floorHeight){
				upwardsSpeed = 0;
				isInAir = false;
				super.getPosition().setY(floorHeight);
			}
		}
	}

	private void checkInputs() {
		if(Keyboard.isKeyDown(Keyboard.KEY_W)){
			this.currentSpeed = RUN_SPEED;
		} else if(Keyboard.isKeyDown(Keyboard.KEY_S)){
			this.currentSpeed = -RUN_SPEED;
		} else {
			this.currentSpeed = 0;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_D)){
			this.currentTurnSpeed = -TURN_SPEED;
		} else if(Keyboard.isKeyDown(Keyboard.KEY_A)){
			this.currentTurnSpeed = TURN_SPEED;
		} else {
			this.currentTurnSpeed = 0;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)){
			jump();
		}
	}

}
