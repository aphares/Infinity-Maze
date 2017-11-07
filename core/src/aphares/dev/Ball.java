package aphares.dev;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Ball {
    private Rectangle ball;
    private Pixmap pixmap;
    private Texture Colour;

    public Ball() {
        ball = new Rectangle();
        makeBall();
    }

    public void makeBall() {

        ball.x = 3*8;
        ball.y = 3*8;
        ball.width = 6;
        ball.height = 6;

        pixmap= new Pixmap( 6, 6, Pixmap.Format.RGBA8888 );
        pixmap.setColor(0, 0, 1, 1f);
        pixmap.fillCircle(3,3,3);
        Colour = new Texture(pixmap);
        pixmap.dispose();

    }
    public void ballMovement(Vector3 touchPos, OrthographicCamera camera, int level){
        if (Gdx.input.isTouched()) {
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            if (touchPos.x > ball.x) {
                ball.x += 20 * Gdx.graphics.getDeltaTime();
            }
            if (touchPos.x < ball.x) {
                ball.x -= 20 * Gdx.graphics.getDeltaTime();
            }
            if (touchPos.y > ball.y) {
                ball.y += 20 * Gdx.graphics.getDeltaTime();
            }
            if (touchPos.y < ball.y) {
                ball.y -= 20 * Gdx.graphics.getDeltaTime();
            }

        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
            ball.x -= 200 * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            ball.x += 200 * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
            ball.y -= 200 * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.UP))
            ball.y += 200 * Gdx.graphics.getDeltaTime();

        // Makes sure ball doesn't leave screen.
        Vector3 cam = camera.position;


        if (ball.x < (cam.x-200  - (level * 28)))
            ball.x = (cam.x-200  - (level * 28));
        if (ball.x > (cam.x+190  + (level * 28)))
            ball.x = (cam.x+190  + (level * 28));
        if (ball.y < (cam.y-120  - (level * 16)))
            ball.y = (cam.y-120  - (level * 16));
        if (ball.y > (cam.y+110  + (level * 16)))
            ball.y = (cam.y+110  + (level * 16));
    }

    public Texture getColour() {
        return Colour;
    }

    public Rectangle getRect() {
        return ball;
    }
}

