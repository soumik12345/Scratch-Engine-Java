package shaders;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.omg.CosNaming.BindingIteratorPOA;

public abstract class ShaderProgram {
	
	private int programID, vertexShaderID, fragmentShaderID;
	
	public ShaderProgram(String vertexFile, String fragmentFile) {
		// Load Shaders
		this.vertexShaderID = loadShader(vertexFile, GL20.GL_VERTEX_SHADER);
		this.fragmentShaderID = loadShader(fragmentFile, GL20.GL_FRAGMENT_SHADER);
		// Create Program
		this.programID = GL20.glCreateProgram();
		// Attach Shaders to the Program
		GL20.glAttachShader(this.programID, this.vertexShaderID);
		GL20.glAttachShader(this.programID, this.fragmentShaderID);
		// Link and validate Program
		GL20.glLinkProgram(this.programID);
		GL20.glValidateProgram(this.programID);
		// Bind Attributes (VAO) to Program
		bindAttributes();
	}
	
	// Start Using Program
	public void start() {
		GL20.glUseProgram(this.programID);
	}
	
	// Stop Using Program
	public void stop() {
		GL20.glUseProgram(0);
	}
	
	// Cleanup Program (Memory Management)
	public void cleanUp() {
		// Stop Using Program
		stop();
		// Detach Shaders from Program
		GL20.glDetachShader(this.programID, this.vertexShaderID);
		GL20.glDetachShader(this.programID, this.fragmentShaderID);
		// Delete Shaders
		GL20.glDeleteShader(this.vertexShaderID);
		GL20.glDeleteShader(this.fragmentShaderID);
		// Delete Program
		GL20.glDeleteProgram(this.programID);
	}
	
	protected abstract void bindAttributes();
	
	protected void BindAttribute(int attribute, String variableName) {
		GL20.glBindAttribLocation(this.programID, attribute, variableName);
	}
	
	// load shader from file
	@SuppressWarnings("deprecation")
	private static int loadShader(String file, int type) {
		// Read shader program from file
		StringBuilder shaderSource = new StringBuilder();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			while((line = reader.readLine()) != null)
				shaderSource.append(line).append("\n");
			reader.close();
		} catch(IOException e) {
			System.out.println("Unable to read Shader File " + file);
			e.printStackTrace();
			System.exit(-1);
		}
		// Create Shader
		int shaderID = GL20.glCreateShader(type);
		// Set Shader Source
		GL20.glShaderSource(shaderID, shaderSource);
		// Compile Shader
		GL20.glCompileShader(shaderID);
		if(GL20.glGetShader(shaderID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
			System.out.println(GL20.glGetShaderInfoLog(shaderID, 500));
			System.out.println("Unable to Compile Shader");
			System.exit(-1);
		}
		return shaderID;
	}

}
