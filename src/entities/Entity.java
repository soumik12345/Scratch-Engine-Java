package entities;

import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.opengl.Texture;

import models.TexturedModel;

public class Entity {
	
	private TexturedModel model;
	private Vector3f position;
	private float rotationX, rotationY, rotationZ;
	private float scale;
	
	public Entity(TexturedModel model, Vector3f position, float rotationX, float rotationY, float rotationZ, float scale) {
		this.model = model;
		this.position = position;
		this.rotationX = rotationX;
		this.rotationY = rotationY;
		this.rotationZ = rotationZ;
		this.scale = scale;
	}
	
	public void increasePosition(float dx, float dy, float dz) {
		this.position.x += dx;
		this.position.y += dy;
		this.position.z += dz;
	}
	
	public void increaseRotation(float dx, float dy, float dz) {
		this.rotationX += dx;
		this.rotationY += dy;
		this.rotationZ += dz;
	}

	public TexturedModel getModel() {
		return model;
	}

	public Vector3f getPosition() {
		return position;
	}

	public float getRotationX() {
		return rotationX;
	}

	public float getRotationY() {
		return rotationY;
	}

	public float getRotationZ() {
		return rotationZ;
	}

	public float getScale() {
		return scale;
	}
	
	

}
