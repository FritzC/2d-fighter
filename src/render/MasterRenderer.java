package render;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lights.Light;
import models.TexturedModel;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector4f;

import shaders.StaticShader;
import entities.Camera;
import entities.Entity;

public class MasterRenderer {

	private static final float FOV = 70;
	private static final float NEAR_PLANE = 0.1f;
	private static final float FAR_PLANE = 10000;
	
	private static final float RED = 0.5444f;
	private static final float GREEN = 0.62f;
	private static final float BLUE = 0.69f;
	
	private Matrix4f projectionMatrix;
	
	private EntityRenderer renderer;
	
	/*private TerrainRenderer terrainRenderer;
	private TerrainShader terrainShader = new TerrainShader();*/
	
	private StaticShader shader = new StaticShader();
	
	private Map<TexturedModel, List<Entity>> entities = new HashMap<TexturedModel, List<Entity>>();
	
	//private List<Terrain> terrains = new ArrayList<Terrain>();
	
	/*private SkyboxRenderer skyRenderer;*/
	
	public MasterRenderer(Loader loader){
		createProjectionMatrix();
		renderer = new EntityRenderer(shader, projectionMatrix);
		//terrainRenderer = new TerrainRenderer(terrainShader, projectionMatrix);
		//skyRenderer = new SkyboxRenderer(loader, projectionMatrix);
	}
	
	public Matrix4f getProjectionMatrix(){
		return projectionMatrix;
	}
	
	public void cleanUp(){
		shader.cleanUp();
	//	terrainShader.cleanUp();
	}
	
	public static void enableCulling(){
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glCullFace(GL11.GL_BACK);
	}
	
	public static void disableCulling(){
		GL11.glDisable(GL11.GL_CULL_FACE);
	}
	
	public void render(List<Light> lights, Camera camera, Vector4f clipPlane){
		prepare();
		shader.start();
		shader.loadClipPlane(clipPlane);
		shader.loadSkyColour(RED, GREEN, BLUE);
		shader.loadLights(lights);
		shader.loadViewMatrix(camera);
		renderer.render(entities);
		shader.stop();
/*		terrainShader.start();
		terrainShader.loadClipPlane(clipPlane);
		terrainShader.loadSkyColour(RED, GREEN, BLUE);
		terrainShader.loadLights(lights);
		terrainShader.loadViewMatrix(camera);
		terrainRenderer.render(terrains);
		terrainShader.stop();
		terrains.clear();*/
		//skyRenderer.render(camera, RED, GREEN, BLUE);

		entities.clear();
	}
	
/*	public void processTerrain(Terrain terrain){
		terrains.add(terrain);
	}
	*/
	public void processEntity(Entity entity){
		TexturedModel entityModel = entity.getModel();
		List<Entity> batch = entities.get(entityModel);
		if(batch != null){
			batch.add(entity);
		} else {
			List<Entity> newBatch = new ArrayList<Entity>();
			newBatch.add(entity);
			entities.put(entityModel, newBatch);
		}
	}
	
	public void prepare(){
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glClearColor(RED, GREEN, BLUE, 0);
	}
	
	private void createProjectionMatrix(){
        float aspectRatio = (float) Display.getWidth() / (float) Display.getHeight();
        float y_scale = (float) ((1f / Math.tan(Math.toRadians(FOV / 2F))) * aspectRatio);
        float x_scale = y_scale / aspectRatio;
        float frustum_length = FAR_PLANE - NEAR_PLANE;
       
        projectionMatrix = new Matrix4f();
        projectionMatrix.m00 = x_scale;
        projectionMatrix.m11 = y_scale;
        projectionMatrix.m22 = -((FAR_PLANE + NEAR_PLANE) / frustum_length);
        projectionMatrix.m23 = -1;
        projectionMatrix.m32 = -((2 * FAR_PLANE * NEAR_PLANE) / frustum_length);
        projectionMatrix.m33 = 0;
	}

	public void renderScene(List<Light> lights,/* List<Terrain> terrains,*/ List<Entity> entities, Camera camera, Vector4f clipPlane) {
		/*for(Terrain terrain : terrains){
			processTerrain(terrain);
		}	*/
		for(Entity entity: entities){
			processEntity(entity);
		}
		render(lights, camera, clipPlane);		
	}
	
}