package engineTester;

import java.util.ArrayList;

import renderEngine.DisplayManager;
import renderEngine.RawModel;
import renderEngine.Renderer;

public class MainGameLoop {

	public static void main(String[] args) {
		
		DisplayManager.createDisplay();
		
		Renderer renderer = new Renderer();
		
		float[] vertices = {
				-0.5f, 0.5f, 0f,
				-0.5f, -0.5f, 0f,
				0.5f, -0.5f, 0f,
				0.5f, -0.5f, 0f,
				0.5f, 0.5f, 0f,
				-0.5f, 0.5f, 0f,
		};
		
		ArrayList<RawModel> modelList = new ArrayList<RawModel>();
		
		modelList.add(new RawModel(vertices));
		
		for(RawModel model : modelList)
			model.loadModel();
		
		// Game Loop
		while(!DisplayManager.isCloseRequested()) {
			
			renderer.prepare();
			renderer.render(modelList);
			DisplayManager.updateDisplay();
		}
		
		for(RawModel model : modelList)
			model.cleanUp();
		
		DisplayManager.closeDisplay();
		
	}

}
