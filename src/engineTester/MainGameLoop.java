package engineTester;

import java.util.ArrayList;

import renderEngine.DisplayManager;
import renderEngine.RawModel;
import renderEngine.Renderer;
import shaders.StaticShader;

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
		
		int[] indices = {0, 1, 3, 3, 1, 2};
		
		ArrayList<RawModel> modelList = new ArrayList<RawModel>();
		
		modelList.add(new RawModel(vertices, indices));
		
		for(RawModel model : modelList)
			model.loadModel();
		
		// Game Loop
		while(!DisplayManager.isCloseRequested()) {
			
			renderer.prepare();
			shader.start();
			renderer.render(modelList);
			shader.stop();
			DisplayManager.updateDisplay();
		}
		
		shader.cleanUp();
		
		for(RawModel model : modelList)
			model.cleanUp();
		
		DisplayManager.closeDisplay();
		
	}

}