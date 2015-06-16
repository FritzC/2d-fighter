package models;

import objConverter.ModelData;

public class RawModel {

	private ModelData data;
	private int vaoID, vertexCount;

	public RawModel(int vaoID, int vertexCount) {
		this.vaoID = vaoID;
		this.vertexCount = vertexCount;
	}
	
	public RawModel(int vaoID, int vertexCount, ModelData data) {
		this.vaoID = vaoID;
		this.vertexCount = vertexCount;
		this.data = data;
	}

	public int getVertexCount() {
		return vertexCount;
	}

	public int getVaoID() {
		return vaoID;
	}
	
	public ModelData getData() {
		return data;
	}
	
}
