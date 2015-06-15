package entities;

import models.TexturedModel;

import org.lwjgl.util.vector.Vector3f;

public class Player extends Entity {

	protected static final float RUN_SPEED = 40;
	protected static final float TURN_SPEED = 160;
	protected static final float GRAVITY = -50;
	protected static final float JUMP_POWER = 30;

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

	/*
	 * public void move(Terrain terrain, boolean you){ checkInputs();
	 * super.increaseRotation(0, currentTurnSpeed *
	 * DisplayManager.getFrameTimeSeconds(), 0); float distance = currentSpeed *
	 * DisplayManager.getFrameTimeSeconds(); float dx = (float) (distance
	 * *Math.sin(Math.toRadians(super.getRotY()))); float dz = (float) (distance
	 * *Math.cos(Math.toRadians(super.getRotY()))); super.increasePosition(dx,
	 * 0, dz); upwardsSpeed += GRAVITY *DisplayManager.getFrameTimeSeconds();
	 * super.increasePosition(0, upwardsSpeed *
	 * DisplayManager.getFrameTimeSeconds(), 0); float terrainHeight =
	 * terrain.getHeightOfTerrain(super.getPosition().x, super.getPosition().z);
	 * if(super.getPosition().getY() < terrainHeight){ upwardsSpeed = 0; isInAir
	 * = false; super.getPosition().setY(terrainHeight); } }
	 */

}
