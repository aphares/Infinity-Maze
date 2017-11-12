package aphares.dev;

import com.badlogic.gdx.graphics.OrthographicCamera;

public class Levels {

    float degrees = -1.6f;
    float changeinZoom;
    OrthographicCamera camera;
    int level;
    boolean hasChanged;
    int c;

    public Levels() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 400, 240);
        level = 0;
        c = 0;
    }



    public void changeLevel() {

        camera.zoom = ((float) Math.atan(degrees) / 18) + (1.056f + changeinZoom);
        degrees = degrees + 0.04f;
        c++;
        if (!hasChanged) {
            level++;
            hasChanged = true;
        }
    }

    public void setDegreesAndZoom(float newDegrees) {
        degrees = newDegrees;
        changeinZoom = camera.zoom - 1;
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
