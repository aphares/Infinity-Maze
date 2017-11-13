package aphares.dev;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector3;

public class Ball {
    private Texture Colour;
    private Circle ball;
    private float intX,intY;
    private int ballSpeed;


    public Ball() {
        ball = new Circle(8,8,8);
        Colour = new Texture(Gdx.files.internal("Circle.png"));
        intX = 32;
        ball.x = intX;
        intY = 144;
        ball.y = intY;
        ballSpeed = 2;
    }

    public int[] ballMovement(Vector3 touchPos, OrthographicCamera camera, boolean[] isTouching, int[] ballCord) {


        if (Gdx.input.isTouched()) {
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);

                if (touchPos.x > ball.x && isTouching[0] && (ballCord[2] == 1) && Math.abs(touchPos.x - ball.x) > 10) {
                    ball.x += ballSpeed;
                    ballCord[0]++;
                    ballCord[2] = 1;
                }
                else if (touchPos.x < ball.x && isTouching[2]  && (ballCord[2] == 1) && Math.abs(touchPos.x - ball.x) > 10) {
                    ball.x -= ballSpeed;
                    ballCord[0]--;
                    ballCord[2] = 1;
                }
                else if (touchPos.y > ball.y && isTouching[1]  && (ballCord[2] == 2) && Math.abs(touchPos.y - ball.y) > 10) {
                    ball.y += ballSpeed;
                    ballCord[1]++;
                    ballCord[2] = 2;
                }
                else if (touchPos.y < ball.y && isTouching[3]  && (ballCord[2] == 2) && Math.abs(touchPos.y - ball.y) > 10) {
                    ball.y -= ballSpeed;
                    ballCord[1]--;
                    ballCord[2] = 2;
            }
            else {
                    if (ballCord[2] == 1) {
                        ballCord[2] = 2;
                    }
                    else {
                        ballCord[2] = 1;
                    }
                }
        }
        else {
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && isTouching[0] && (ballCord[2] == 1)) {
                ball.x += ballSpeed;
                ballCord[0]++;
                ballCord[2] = 1;
            }
            else if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && isTouching[2] && (ballCord[2] == 1)) {
                ball.x -= ballSpeed;
                ballCord[0]--;
                ballCord[2] = 1;
            }
            else if (Gdx.input.isKeyPressed(Input.Keys.UP) && isTouching[1] && (ballCord[2] == 2)) {
                ball.y += ballSpeed;
                ballCord[1]++;
                ballCord[2] = 2;
            }
            else if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && isTouching[3] && (ballCord[2] == 2)) {
                ball.y -= ballSpeed;
                ballCord[1]--;
                ballCord[2] = 2;
            }
            else {
                if (ballCord[2] == 1) {
                    ballCord[2] = 2;
                }
                else {
                    ballCord[2] = 1;
                }
            }
        }





        return ballCord;

    }


    public int whichMaze() {
        if (ball.x <= 2912) {
            if (ball.x <= 928) {
                return 0;
            }
            else if (ball.x <= 1920) {
                return 1;
            }
            else {
                return 2;
            }
        }
        else {
            if ((ball.x-2912) / 1488 <= 1) {
                return 0;
            }
            if ((ball.x-2912) / 1488 <= 2) {
                return 1;
            }
            else return 2;
        }
    }

    public Texture getColour() {
        return Colour;
    }

    public Circle getCircle() {
        return ball;
    }

    public float getX() {
        return ball.x;
    }

    public float getY() {
        return ball.y;
    }

    public void setIntX(float x) {
        intX = x;
    }

    public void setIntY(float y) {
        intY = y;
    }

    public int getBallSpeed() {
        return ballSpeed;
    }

    public void setBallSpeed(int newSpeed) {
        ballSpeed = newSpeed;
    }
}

