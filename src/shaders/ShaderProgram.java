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
		this.vertexShaderID = loadShader(vertexFile, GL20.GL_VERTEX_SHADER);
		this.fragmentShaderID = loadShader(fragmentFile, GL20.GL_FRAGMENT_SHADER);
		this.programID = GL20.glCreateProgram();
		GL20.glAttachShader(this.programID, this.vertexShaderID);
		GL20.glAttachShader(this.programID, this.fragmentShaderID);
		GL20.glLinkProgram(this.programID);
		GL20.glValidateProgram(this.programID);
		bindAttributes();
	}
	
	public void start() {
		GL20.glUseProgram(this.programID);
	}
	
	public void stop() {
		GL20.glUseProgram(0);
	}
	
	public void cleanUp() {
		stop();
		GL20.glDetachShader(this.programID, this.vertexShaderID);
		GL20.glDetachShader(this.programID, this.fragmentShaderID);
		GL20.glDeleteShader(this.vertexShaderID);
		GL20.glDeleteShader(this.fragmentShaderID);
		GL20.glDeleteProgram(this.programID);
	}
	
	protected abstract void bindAttributes();
	
	protected void BindAttribute(int attribute, String variableName) {
		GL20.glBindAttribLocation(this.programID, attribute, variableName);
	}
	
	@SuppressWarnings("deprecation")
	private static int loadShader(String file, int type) {
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
		
		int shaderID = GL20.glCreateShader(type);
		GL20.glShaderSource(shaderID, shaderSource);
		GL20.glCompileShader(shaderID);
		if(GL20.glGetShader(shaderID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
			System.out.println(GL20.glGetShaderInfoLog(shaderID, 500));
			System.out.println("Unable to Compile Shader");
			System.exit(-1);
		}
		return shaderID;
	}

}
