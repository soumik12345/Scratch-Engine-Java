package engineTester;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector3f;

import entities.Entity;
import models.RawModel;
import models.TexturedModel;
import renderEngine.DisplayManager;
import renderEngine.Renderer;
import shaders.StaticShader;
import textures.ModelTexture;

public class MainGameLoop {

	public static void main(String[] args) {
		
		DisplayManager.createDisplay();
		
		Renderer renderer = new Renderer();
		
		StaticShader shader = new StaticShader();
		
		float[] vertices = {
				-0.5f, 0.5f, 0f,
				-0.5f, -0.5f, 0f,
				0.5f, -0.5f, 0f,
				0.5f, 0.5f, 0f,
		};
		
		float[] textureCoordinates = {
				0, 0,
				0, 1,
				1, 1,
				1, 0
		};
		
		int[] indices = {0, 1, 3, 3, 1, 2};
		
		
		ArrayList<RawModel> modelList = new ArrayList<>();
		ArrayList<ModelTexture> textureList = new ArrayList<>();
		ArrayList<TexturedModel> texturedModelList = new ArrayList<>();
		ArrayList<Entity> entityList = new ArrayList<>();
		
		modelList.add(new RawModel(vertices, textureCoordinates, indices));
		textureList.add( new ModelTexture(modelList.get(0).loadTexture("marble_texture")));
		texturedModelList.add(new TexturedModel(modelList.get(0), textureList.get(0)));
		entityList.add(new Entity(texturedModelList.get(0), new Vector3f(-1, 0, 0), 0, 0, 0, 1));
		
		
		for(RawModel model : modelList)
			model.loadModel();
		
		// Game Loop
		while(!DisplayManager.isCloseRequested()) {
			
			entityList.get(0).increasePosition(0.002f, 0, 0);
			entityList.get(0).increaseRotation(0, 1, 0);
			
			renderer.prepare();
			shader.start();
			renderer.render(entityList, shader);
			shader.stop();
			DisplayManager.updateDisplay();
		}
		
		shader.cleanUp();
		
		for(RawModel model : modelList)
			model.cleanUp();
		
		DisplayManager.closeDisplay();
		
	}

}