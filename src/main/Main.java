package main;

import engine.graphics.*;
import engine.math.Vector2f;
import org.lwjgl.glfw.GLFW;

import engine.io.Input;
import engine.io.Window;
import engine.math.Vector3f;

import java.io.IOException;


public class Main implements Runnable {
	public Thread game;
	public Window window;
	public Renderer renderer;
	public Shader shader;
	public final int WIDTH = 800, HEIGHT = 600;
	
	public Mesh mesh = new Mesh(new Vertex[] {
	new Vertex(new Vector3f(-0.5f,   0.5f,  0.0f),new Vector3f(  1.0f, 0.0f, 0.0f), new Vector2f(0.0f,0.0f)),
	new Vertex(new Vector3f(-0.5f,  -0.5f,  0.0f),new Vector3f(  0.0f, 1.0f, 0.0f), new Vector2f(0.0f,1.0f)),
	new Vertex(new Vector3f( 0.5f,  -0.5f,  0.0f),new Vector3f(  0.0f, 0.0f, 1.0f), new Vector2f(1.0f,1.0f)),
	new Vertex(new Vector3f( 0.5f,   0.5f,  0.0f),new Vector3f(  1.0f, 1.0f, 0.0f), new Vector2f(1.0f,0.0f))
	}, new int[] {
	0, 1, 2,
	0, 3, 2
	}, new Material("/textures/graywg.png"));

	public Main() throws IOException {
	}


	public void start() {
		game = new Thread(this, "game");
		game.start();
	}
	
	public void init() {
		window = new Window(WIDTH, HEIGHT, "Game");
		shader = new Shader("resources/shaders/mainVertex.glsl","resources/shaders/mainFragment.glsl");
		renderer = new Renderer(shader);
		//renderer.setShader(shader);
		window.setBackground(1.0f, 0.0f, 0.0f);
		
		window.create();
		mesh.create();
		shader.create();
	}
	
	public void run() {
		init();

		while (!window.shouldClose() && !Input.isKeyDown(GLFW.GLFW_KEY_ESCAPE)) {
			update();
			render();
			if (Input.isKeyDown(GLFW.GLFW_KEY_F11)) window.setFullscreen(!window.isFullscreen());
			
		}
		close();
	}
	
	private void update() {
		window.update();
		if (Input.isButtonDown(GLFW.GLFW_MOUSE_BUTTON_LEFT)) System.out.println("X: " + Input.getMouseX() + ", Y: " + Input.getMouseY());
	}
	
	private void render() {
		renderer.renderMesh(mesh);
		window.swapBuffers();
	}
	
	private void close() {
		window.destroy();
		mesh.destroy();
		shader.destroy();
	}
	
	public static void main(String[] args) throws IOException {
		new Main().start();
	}
}
