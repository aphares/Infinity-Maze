package aphares.dev.theMaze;

import com.badlogic.gdx.math.Rectangle;

public class Block {

    private Rectangle block;
    private boolean isWay;
    private int x,y;

    public Block(int x, int y, boolean isPath) {
        isWay = isPath;
        this.x = x;
        this.y = y;
        block = new Rectangle();
        makeRect();
    }

    public void makeRect() {
        block.x = x;
        block.y = y;
        block.width = 16;
        block.height = 16;
    }

    public void setColor(boolean isPath) {
        isWay = isPath;
    }

    public boolean getWay(){
        return isWay;
    }


    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
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
}
