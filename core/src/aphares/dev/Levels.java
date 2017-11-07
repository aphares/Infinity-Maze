package aphares.dev;

import com.badlogic.gdx.graphics.OrthographicCamera;

public class Levels {

    float degrees = -1.6f;
    float changeinZoom;
    OrthographicCamera camera;
    int level;
    boolean hasChanged;
    float r,g,b;
    int c;

    public Levels() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 400, 240);
        level = 0;
        r = 0.3f;
        g = 0.6f;
        b = 0.95f;
        c = 0;
    }



    public void changeLevel(Maze map) {

        camera.zoom = ((float) Math.atan(degrees) / 18) + (1.056f + changeinZoom);
        degrees = degrees + 0.1f;
        if (!hasChanged) {
            level++;
            hasChanged = true;

        }
        if (c < 18) {
            switch (level) {
                case 1:
                    g += (0.35f / 18);
                    b -= (0.1f / 18);
                    map.getMaze(0).changeColor(r, g, b);
                    break;
                case 2:
                    g -= (0.05f / 18);
                    b -= (0.35f / 18);
                    map.getMaze(0).changeColor(r, g, b);
                    break;
                case 3:
                    r += (0.35f / 18);
                    b += (0.1f / 18);
                    map.getMaze(0).changeColor(r, g, b);
                    break;
                case 4:
                    r += (0.05f / 18);
                    g -= (0.1f / 18);
                    b -= (0.2f / 18);
                    map.getMaze(1).changeColor(r, g, b);
                    break;
                case 5:
                    r += (0.05f / 18);
                    g -= (0.3f / 18);
                    b -= (0.15f / 18);
                    map.getMaze(1).changeColor(r, g, b);
                    break;
                case 6:
                    r += (0.05f / 18);
                    g -= (0.1f / 18);
                    b -= (0.05f / 18);
                    map.getMaze(1).changeColor(r, g, b);
                    break;
                case 7:
                    r += (0.05f / 18);
                    g -= (0.2f / 18);
                    map.getMaze(1).changeColor(r, g, b);
                    break;
            }
        c++;
    }



    }
    public void setDegreesAndZoom(float newDegrees, Maze map) {
        degrees = newDegrees;
        changeinZoom = camera.zoom - 1;
        if (hasChanged) {
            map.getMaze(0).changeColor(r, g, b);
            map.getMaze(1).changeColor(r, g, b);
            map.getMaze(2).changeColor(r, g, b);
        }

        hasChanged = false;

        c=0;


    }

    public int getLevel() {
        return level;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }


}
