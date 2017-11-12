package aphares.dev.theMaze;



import com.badlogic.gdx.math.Vector3;

public class Maze {


    private Lines[] maze = new Lines[3];
    private int buffer;

    public Maze() {
        buffer = 10;
        Block temp = new Block(32,272,true);
        maze[0] = new Lines(-48,temp,buffer);
        buffer--;
        maze[1] = new Lines(992,maze[0].getTemp(),buffer);
        buffer--;
        maze[2] = new Lines(2032,maze[1].getTemp(),buffer);
    }

    public void updateDisplacement(Vector3 cam) {
        int newDisplacement;
        if (maze[0].getDisplacement() <= cam.x - 1560) {
            newDisplacement = Math.round(cam.x) + 1560 - 1;
            maze[0] = new Lines(newDisplacement,maze[2].getTemp(),buffer);
        }
        if (maze[1].getDisplacement() <= cam.x - 1560) {
            newDisplacement = Math.round(cam.x) + 1560 - 1;
            maze[1] = new Lines(newDisplacement,maze[0].getTemp(),buffer);
        }
        if (maze[2].getDisplacement() < cam.x - 1560) {
            newDisplacement = Math.round(cam.x) + 1560 - 1;
            maze[2] = new Lines(newDisplacement,maze[1].getTemp(),buffer);
        }

    }
    public Lines getMaze(int index) {
        return maze[index];
    }

    public int getBuffer() {
        return buffer;
    }

    public void setBuffer(int newBuffer) {
        buffer = newBuffer;
    }
}

