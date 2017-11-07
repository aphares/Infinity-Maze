package aphares.dev;


import com.badlogic.gdx.math.Vector3;

public class Maze {


    private Lines[] maze = new Lines[3];


    public Maze() {
        maze[0] = new Lines(-64);
        maze[1] = new Lines(896);
        maze[2] = new Lines(1856);
    }

    public void updateDisplacement(Vector3 cam) {
        int newDisplacement;
        if (maze[0].getDisplacement() < cam.x - 1392) {
            newDisplacement = Math.round(cam.x) + 1392 + 92;
            maze[0].redrawMap(newDisplacement);
        }
        if (maze[1].getDisplacement() < cam.x - 1392) {
            newDisplacement = Math.round(cam.x) + 1392  + 92;
            maze[1].redrawMap(newDisplacement);
        }
        if (maze[2].getDisplacement() < cam.x - 1392) {
            newDisplacement = Math.round(cam.x) + 1392  + 92;
            maze[2].redrawMap(newDisplacement);
        }

    }

    public Lines getMaze(int index) {
        return maze[index];
    }
}
