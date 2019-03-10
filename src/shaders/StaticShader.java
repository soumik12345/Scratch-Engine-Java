package shaders;

import org.lwjgl.util.vector.Matrix4f;

import entities.Camera;
import toolBox.Maths;

public class StaticShader extends ShaderProgram {
	
	private static final String VERTEX_FILE = "src/shaders/vertexShader.txt";
	private static final String FRAGMENT_FILE = "src/shaders/fragmentShader.txt";
	
	private int locationTransformationMatrix, locationProjectionMatrix, locationViewMatrix;

	public StaticShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	protected void bindAttributes() {
		super.BindAttribute(0, "position");
		super.BindAttribute(1, "textureCoords");
	}

	@Override
	protected void getAllUniformLocations() {
		locationTransformationMatrix = super.getUniformVariableLocation("transformationMatrix");
		locationProjectionMatrix = super.getUniformVariableLocation("projectionMatrix");
		locationViewMatrix = super.getUniformVariableLocation("viewMatrix");
	}
	
	public void loadTransformationMatrix(Matrix4f matrix) {
		super.loadMatrix(locationTransformationMatrix, matrix);
	}
	
	public void loadProjectionMatrix(Matrix4f matrix) {
		super.loadMatrix(locationProjectionMatrix, matrix);
	}
	
	public void loadViewMatrix(Camera camera) {
		Matrix4f matrix = Maths.createViewMatrix(camera);
		super.loadMatrix(locationViewMatrix, matrix);
	}

}
