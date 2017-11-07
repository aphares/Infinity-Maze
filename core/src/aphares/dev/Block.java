package aphares.dev;


import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Block {

    private Rectangle block;
    private boolean isWay;
    private Texture Colour;
    private Pixmap pixmap;
    private int x,y;

    public Block(int x, int y, boolean isWay) {
        this.x = x;
        this.y = y;
        block = new Rectangle();
        makeRect();
        if (isWay) {
            setColor(0);
        }
        else {
            setColor(10);
        }


    }

    public void makeRect() {
        block.x = x;
        block.y = y;
        block.width = 16;
        block.height = 16;
    }

    public void setColor(float r, float g, float b) {
        pixmap = new Pixmap(16, 16, Pixmap.Format.RGBA8888);
        pixmap.setColor(r,g,b,0.8f);
        pixmap.fillRectangle(0, 0, 16, 16);
        Colour = new Texture(pixmap);
        pixmap.dispose();
    }

    public void setColor(int color) {
        pixmap = new Pixmap(16, 16, Pixmap.Format.RGBA8888);
        switch (color) {
            case 0:
                pixmap.setColor(0.3f, 0.6f, 0.95f, 0.8f);
                isWay = true;
                break;
            case 1:
                pixmap.setColor(0.3f, 0.95f, 0.85f, 0.8f);
                isWay = true;
                break;
            case 2:
                pixmap.setColor(0.3f, 0.9f, 0.5f, 0.8f);
                isWay = true;
                break;
            case 3:
                pixmap.setColor(0.65f, 0.9f, 0.6f, 0.8f);
                isWay = true;
                break;
            case 4:
                pixmap.setColor(0.7f, 0.8f, 0.4f, 0.8f);
                isWay = true;
                break;
            case 5:
                pixmap.setColor(0.75f, 0.5f, 0.25f, 0.8f);
                isWay = true;
                break;
            case 6:
                pixmap.setColor(0.8f, 0.4f, 0.2f, 0.8f);
                isWay = true;
                break;
            case 7:
                pixmap.setColor(0.85f, 0.2f, 0.2f, 0.8f);
                isWay = true;
                break;
            default:
                pixmap.setColor(0, 0, 0, 1f);
                isWay = false;
                break;
        }

            pixmap.fillRectangle(0, 0, 16, 16);
            Colour = new Texture(pixmap);
            pixmap.dispose();

    }


    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Texture getColour() {
        return Colour;
    }
    public Rectangle getRect() {
        return block;
    }
    public int getX() {
        return x/16;
    }
    public int getY() {
        return y/16;
    }
    public boolean getWay(){
        return isWay;
    }


}
