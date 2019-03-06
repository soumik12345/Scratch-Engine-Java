package engineTester;

import javax.management.modelmbean.ModelMBeanAttributeInfo;

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
		
		RawModel model = new RawModel(vertices);
		
		// Game Loop
		while(!DisplayManager.isCloseRequested()) {
			
			renderer.prepare();
			renderer.render(model);
			DisplayManager.updateDisplay();
		}
		
		model.cleanUp();
		DisplayManager.closeDisplay();
		
	}

}
