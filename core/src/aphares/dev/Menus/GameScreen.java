package aphares.dev.Menus;

import com.badlogic.gdx.ApplicationAdapter;
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


  public GameScreen(final Game maze) {
        game = maze;
        colours = new Texture[9];
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

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        thisLevel.getCamera().update();
        batch.setProjectionMatrix(thisLevel.getCamera().combined);

        //Sets up screen
        batch.begin();
        for (int c = 0; c < 3; c++) {
            for (int i = 0; i < 34; i++) {
                for (int j = 3; j < 65; j++) {
                    if (!map.getMaze(c).getMap()[i][j].getWay()) {
                        batch.draw
                                (colours[8], map.getMaze(c).getMap()[i][j].getRect().x + map.getMaze(c).getDisplacement(), map.getMaze(c).getMap()[i][j].getRect().y - 160);
                    } else {
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
        // thisLevel.getCamera().zoom = 20;

        Vector3 cam = thisLevel.getCamera().position;
        map.updateDisplacement(cam);




        //Movement of Ball decided by: calling method which checks squares to the right, top, left, and bottom of ball whenever displacement = 16. Whichever directions contain walls are blocked for movement.
        if (ballCord[0] < (16/ball.getBallSpeed()) && ballCord[0] > (-16/ball.getBallSpeed()) && ballCord[1] < (16/ball.getBallSpeed()) && ballCord[1] > (-16/ball.getBallSpeed())) {
            ballCord = ball.ballMovement(touchPos, thisLevel.getCamera(), isOpen, ballCord);
        } else {
            openDirections(0);
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
        if (time > 95 && time < 99 && level != 7) {
            thisLevel.changeLevel();
            if (time > 96) {
                level = thisLevel.getLevel();

            }
        }
        else {
            if (time > 98 && map.getBuffer() != 2) {
                if (ball.getBallSpeed()!=8 && level > 5) {
                    ball.setBallSpeed(ball.getBallSpeed() * 2);
                }
                map.setBuffer(map.getBuffer() - 1);
                time = 5;
            }
            thisLevel.setDegreesAndZoom(-1.6f);
        }

    }

    public void openDirections(int mazeNum) {
        //To Right
        if (!map.getMaze(mazeNum).getMap()[(int)(ball.getY()/16 + 10)][(int)(ball.getX()/16) + 5].getWay()) {
            isOpen[0] = false;
        }
        else {
            isOpen[0] = true;
        }

        //To Top
        if (!map.getMaze(mazeNum).getMap()[(int)(ball.getY()/16) + 11][(int)(ball.getX()/16) + 4].getWay()) {
            isOpen[1] = false;
        }
        else {
            isOpen[1] = true;

        }

        //To Left
        if (!map.getMaze(mazeNum).getMap()[(int)(ball.getY()/16 + 10)][(int)(ball.getX()/16) + 3].getWay()) {
            isOpen[2] = false;
        }
        else {
            isOpen[2] = true;
        }

        //To Bottom
        if (!map.getMaze(mazeNum).getMap()[(int)(ball.getY()/16) + 9][(int)(ball.getX()/16) + 4].getWay()) {
            isOpen[3] = false;
        }
        else {
            isOpen[3] = true;
        }
        ball.setIntX(ball.getX());
        ball.setIntY(ball.getY());
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
