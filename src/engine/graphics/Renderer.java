package engine.graphics;

import org.lwjgl.opengl.*;

public class Renderer {
	private Shader shader;
	public Renderer(Shader shader) {
		this.shader = shader;
	}
	public void renderMesh(Mesh mesh) {
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL30.glBindVertexArray(mesh.getVao());
		GL33.glEnableVertexAttribArray(0);
		GL33.glEnableVertexAttribArray(1);
		GL33.glEnableVertexAttribArray(2);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER,mesh.getIbo());
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		mesh.getMaterial().setTextureID();
		GL13.glBindTexture(GL11.GL_TEXTURE_2D, mesh.getMaterial().getTextureID());

		shader.bind();
		//GL11.glPixelStorei(GL11.GL_UNPACK_ALIGNMENT, 1);
		GL11.glDrawElements(GL11.GL_TRIANGLES, mesh.getIndices().length,GL11.GL_UNSIGNED_INT,0);
		shader.unbind();
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
		GL33.glDisableVertexAttribArray(0);
		GL33.glDisableVertexAttribArray(1);
		GL33.glDisableVertexAttribArray(2);
		GL30.glBindVertexArray(0);
	}
	public void setShader(Shader shader) {
		this.shader = shader;
	}
	
	
}
