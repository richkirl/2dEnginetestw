package engine.graphics;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL11C;
import org.lwjgl.opengl.GL13;
import org.newdawn.slick.opengl.PNGDecoder;
import java.io.IOException;
public class Material {
    private String path;
    private PNGDecoder decoder;
    private float width, height;
    private int textureID;
    public Material(String path) throws IOException {
        this.decoder = new PNGDecoder(Material.class.getResourceAsStream(path));
        width = decoder.getWidth();
        height = decoder.getHeight();
        this.textureID = 0;
    }

    public void create()  {

        //texture = TextureLoader.getTexture(path.split("[.]")[1], this.decoder.toString(), GL11.GL_LINEAR);
        //width = texture.getWidth();
        //height = texture.getHeight();
        //textureID = texture.getTextureID();

    }
    public void destroy() {
        GL13.glDeleteTextures(textureID);
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public int getTextureID() {
        return textureID;
    }
    public void setTextureID() {
        this.textureID = GL11.glGenTextures();
    }
}
