package entities;

import models.TexturedModel;
import objConverter.ModelData;

import org.lwjgl.util.vector.Vector3f;

public class OpenGLEntity {

	protected TexturedModel model;
	protected Vector3f position;
	protected float rotX, rotY, rotZ;
	protected float scale;

	protected boolean isColliding;
	
	private int index = 0;
	
	protected ModelData data;

	public OpenGLEntity(TexturedModel model, Vector3f position, float rotX,
			float rotY, float rotZ, float scale) {
		this.model = model;
		this.position = position;
		this.rotX = rotX;
		this.rotY = rotY;
		this.rotZ = rotZ;
		this.scale = scale;
		this.data = model.getRawModel().getData();
	}

	public OpenGLEntity(TexturedModel model, int index, Vector3f position,
			float rotX, float rotY, float rotZ, float scale) {
		this.model = model;
		this.index = index;
		this.position = position;
		this.rotX = rotX;
		this.rotY = rotY;
		this.rotZ = rotZ;
		this.scale = scale;
		this.data = model.getRawModel().getData();
	}

	public float getTextureXOffset() {
		int column = index % model.getTexture().getNumberofRows();
		return (float) column / (float) model.getTexture().getNumberofRows();
	}

	public float getTextureYOffset() {
		int row = index % model.getTexture().getNumberofRows();
		return (float) row / (float) model.getTexture().getNumberofRows();
	}

	public void increasePosition(float dx, float dy, float dz) {
		this.position.x += dx;
		this.position.y += dy;
		this.position.z += dz;
	}

	public void increaseRotation(float dx, float dy, float dz) {
		this.rotX += dx;
		this.rotY += dy;
		this.rotZ += dz;
	}

	public TexturedModel getModel() {
		return model;
	}

	public void setModel(TexturedModel model) {
		this.model = model;
	}

	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public float getRotX() {
		return rotX;
	}

	public void setRotX(float rotX) {
		this.rotX = rotX;
	}

	public float getRotY() {
		return rotY;
	}

	public void setRotY(float rotY) {
		this.rotY = rotY;
	}

	public float getRotZ() {
		return rotZ;
	}

	public void setRotZ(float rotZ) {
		this.rotZ = rotZ;
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}
	
	public ModelData getData() {
		return data;
	}
	
	public boolean isColliding() {
		return isColliding;
	}

	public void setColliding(boolean isColliding) {
		this.isColliding = isColliding;
	}

}
