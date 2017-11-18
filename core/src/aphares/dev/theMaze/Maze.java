package aphares.dev.theMaze;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;

public class Maze {


    private Lines[] maze = new Lines[2];
    private int buffer;

    public Maze() {
        buffer = 10;
        Block temp = new Block(32,272,true);

        maze[0] = new Lines(-64/*1st block begins at x = 0*/,temp,buffer);

        buffer--;
        maze[1] = new Lines(928/*1st block begins at x = 992*/,maze[0].getTemp(),buffer);


       /* buffer--;
        maze[2] = new Lines(1920,maze[1].getTemp(),buffer);*/
    }

    public float updateDisplacement(boolean isOne) {

        if (isOne) {
            maze[0].setDisplacement(-64);
            maze[1] = new Lines(928/*1st block begins at x = 992*/,maze[0].getTemp(),buffer);
            return 240;
        }
        else {
            maze[1].setDisplacement(-64);
            maze[0] = new Lines(928/*1st block begins at x = 992*/,maze[1].getTemp(),buffer);
            return 240;
        }

        /*if (maze[0].getDisplacement() <= cam.x - 1488) {
            newDisplacement = Math.round(cam.x) + 1488 - 2;
            maze[0] = new Lines(newDisplacement,maze[2].getTemp(),buffer);
        }
        if (maze[1].getDisplacement() <= cam.x - 1488) {
            newDisplacement = Math.round(cam.x) + 1488 - 2;
            maze[1] = new Lines(newDisplacement,maze[0].getTemp(),buffer);
        }
        if (maze[2].getDisplacement() < cam.x - 1488) {
            newDisplacement = Math.round(cam.x) + 1488 - 3;
            maze[2] = new Lines(newDisplacement,maze[1].getTemp(),buffer);
        }*/
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

