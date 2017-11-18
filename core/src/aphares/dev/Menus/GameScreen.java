package aphares.dev.Menus;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.Screen;

import aphares.dev.Ball;
import aphares.dev.Game;
import aphares.dev.Levels;
import aphares.dev.theMaze.Maze;

public class GameScreen implements Screen {
    public SpriteBatch batch;
    private Maze map;
    private Ball ball;
    private boolean[] isOpen;
    private Levels thisLevel;
    private int level;
    private int ballCord[];
    private float time;
    private Texture[] colours;
    private Vector3 touchPos;
    final Game game;
    private boolean isOne,firsttime;
    private int mazeNum, currentDisplacement;


  public GameScreen(final Game maze) {
        game = maze;
        colours = new Texture[]{new Texture("s1.png"), new Texture("s2.png"), new Texture("s3.png"), new Texture("s4.png"),
                new Texture("s5.png"), new Texture("s6.png"), new Texture("s7.png"), new Texture("s8.png"), new Texture("black.png")};
        batch = new SpriteBatch();
        thisLevel = new Levels();
        time = 0;
        level = 2;
        ballCord = new int[]{16, 16, 1};
        ball = new Ball();
        map = new Maze();
        isOpen = new boolean[]{true, true, true, true};
        touchPos = new Vector3();
        firsttime = true;
        isOne = false;
        mazeNum = 0;
        currentDisplacement = 0;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        thisLevel.getCamera().update();
        batch.setProjectionMatrix(thisLevel.getCamera().combined);

        //This is unacceptable to call every frame. I need to draw this once when Map is created, and draw that as a SINGLE texture every frame.
        batch.begin();
        for (int c = 0; c < 2; c++) {
            for (int i = 0; i < 34; i++) {
                for (int j = 3; j < 65; j++) {
                    if (map.getMaze(c).getMap()[i][j].getWay()) {
                        batch.draw
                                (colours[level], map.getMaze(c).getMap()[i][j].getRect().x + map.getMaze(c).getDisplacement(), map.getMaze(c).getMap()[i][j].getRect().y - 160);
                    }
                }
            }
        }
        batch.draw(ball.getColour(), ball.getX(), ball.getY(), 16, 16);
        batch.end();

        if (time > 4) {
            thisLevel.getCamera().translate(20 * Gdx.graphics.getDeltaTime(), 0);
        }
        //thisLevel.getCamera().zoom = 20;

        Vector3 cam = thisLevel.getCamera().position;

        //if the camera passes one maze and enter into half of it, then switches the two mazes around so as to be infinite
        if (cam.x > 992 + 240 + 128) {
            cam.x = map.updateDisplacement(isOne) + 128;
            if (isOne) {
                isOne = false;
            }
            else {
                isOne = true;
            }
            ball.setX(ball.getX() - 992);
            currentDisplacement = 0;
        }


        //Movement of Ball decided by: calling method which checks squares to the right, top, left, and bottom of ball whenever displacement = 16. Whichever directions contain walls are blocked for movement.
        if (ballCord[0] < (16/ball.getBallSpeed()) && ballCord[0] > (-16/ball.getBallSpeed()) && ballCord[1] < (16/ball.getBallSpeed()) && ballCord[1] > (-16/ball.getBallSpeed())) {
            ballCord = ball.ballMovement(touchPos, thisLevel.getCamera(), isOpen, ballCord, firsttime);
            if (ballCord[0] != 0 || ballCord[1] != 0) {
                firsttime = false;
            }
        } else {
            openDirections();
            firsttime = true;
            ballCord[0] = 0;
            ballCord[1] = 0;

            //Sets direction priority (e.g. if ball is moving to left or right, and there's up input, it will choose up.
            if (ballCord[2] == 1) {
                ballCord[2] = 2;
            }
            else {
                ballCord[2] = 1;
            }
        }

        time += Gdx.graphics.getDeltaTime();
        if (time > 50 && time < 54 && level != 7) {
            thisLevel.changeLevel();
            if (time > 51) {
                level = thisLevel.getLevel();

            }
        }
        else {
            if (time > 53 && map.getBuffer() != 2) {
                if (ball.getBallSpeed()!=8 && level > 5) {
                    ball.setBallSpeed(ball.getBallSpeed() * 2);
                }
                map.setBuffer(map.getBuffer() - 1);
                time = 5;
            }
            thisLevel.setDegreesAndZoom(-1.6f);
        }

    }

    public void openDirections() {

        pathstoLeft();
        pathstoRight();
        pathstoLeft();

        //To Top
        if (!map.getMaze(mazeNum).getMap()[(int)(ball.getY()/16) + 11][(int)((ball.getX() - currentDisplacement)/16) + 4].getWay()) {

            isOpen[1] = false;
        }
        else {
            isOpen[1] = true;

        }

        //To Bottom
        if (!map.getMaze(mazeNum).getMap()[(int)(ball.getY()/16) + 9][(int)((ball.getX() - currentDisplacement)/16) + 4].getWay()) {
            isOpen[3] = false;
        }
        else {
            isOpen[3] = true;
        }
        ball.setIntX(ball.getX());
        ball.setIntY(ball.getY());
    }
    public void pathstoLeft() {

        //To Left (lower if is true if ball is on edge of 2d array)
        if ((int)((ball.getX()-992)/16) + 5 == 4 && (int)((ball.getX())/16) + 5 >= 65) {
            //Is this going from the first to second array, or second to first?
            if (isOne) {
                //resets mazeNum if ball is coming in from left
                mazeNum = 0;
                currentDisplacement = 928 + 64;
                if (!map.getMaze(mazeNum + 1).getMap()[(int) (ball.getY() / 16 + 10)][(int) ((ball.getX() - 922) / 16) + 3].getWay()) {
                    isOpen[2] = false;
                }
                else {
                    isOpen[2] = true;
                }
            }
            else {
                mazeNum = 1;
                currentDisplacement = 928 + 64;
                //Is the block to the left black? (on the other maze)
                if (!map.getMaze(mazeNum - 1).getMap()[(int) (ball.getY() / 16 + 10)][(int) ((ball.getX()) / 16) + 3].getWay()) {
                    isOpen[2] = false;
                }
                else {
                    isOpen[2] = true;
                }
            }
        }
        else {
            if (!map.getMaze(mazeNum).getMap()[(int) (ball.getY() / 16 + 10)][(int) ((ball.getX() - currentDisplacement)/ 16) + 3].getWay()) {
                isOpen[2] = false;
            } else {
                isOpen[2] = true;
            }
        }

    }

    public void pathstoRight() {
        //To Right (lower if is true if ball is on edge of 2d array)
        if ((int)(ball.getX()/16) + 5 == 65 && (int)((ball.getX() - 992) / 16) + 5 <= 3) {

            //Is this going from the first to second array, or second to first?
            if (isOne) {
                //resets mazeNum if ball is coming in from right (the suckers are backtracking)
                mazeNum = 1;
                currentDisplacement = 0;
                if (!map.getMaze(mazeNum - 1).getMap()[(int) (ball.getY() / 16 + 10)][(int) ((ball.getX() - 922) / 16) + 1].getWay()) {
                    isOpen[0] = false;

                }
                else {
                    isOpen[0] = true;

                }
            }
            else {
                mazeNum = 0;
                currentDisplacement = 0;
                if (!map.getMaze(mazeNum + 1).getMap()[(int) (ball.getY() / 16 + 10)][(int) ((ball.getX() - 922)  / 16)+1].getWay()) {
                    isOpen[0] = false;
                }
                else {
                    isOpen[0] = true;
                }
            }
        }
        else {
            if (!map.getMaze(mazeNum).getMap()[(int) (ball.getY() / 16 + 10)][(int) ((ball.getX()  - currentDisplacement) / 16) + 5].getWay()) {
                isOpen[0] = false;
            } else {
                isOpen[0] = true;
            }
        }

    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose () {
        batch.dispose();

    }
}
