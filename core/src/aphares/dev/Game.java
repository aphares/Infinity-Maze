package aphares.dev;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class Game extends ApplicationAdapter {
	SpriteBatch batch;
    Maze map;
    Ball ball;
    boolean touching;
    Levels thisLevel;
    float time;

	@Override
	public void create () {

		batch = new SpriteBatch();
        thisLevel = new Levels();
        time = 0;
        ball = new Ball();
        map = new Maze();
        touching = false;

    }

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        thisLevel.getCamera().update();
        batch.setProjectionMatrix(thisLevel.getCamera().combined);

        //Sets up screen
        batch.begin();
        for (int c = 0; c < 3; c ++) {
            for (int i = 0; i < 34; i++) {
                for (int j = 3; j < 63; j++) {
                    batch.draw(map.getMaze(c).getMap()[i][j].getColour(),
                            map.getMaze(c).getMap()[i][j].getRect().x + map.getMaze(c).getDisplacement(),
                            map.getMaze(c).getMap()[i][j].getRect().y - 144);
                }
            }
        }

        batch.draw(ball.getColour(),ball.getRect().x,ball.getRect().y);
        batch.end();

        Vector3 touchPos = new Vector3();
        thisLevel.getCamera().unproject(touchPos);
        thisLevel.getCamera().translate(20 * Gdx.graphics.getDeltaTime(),0);
        //thisLevel.getCamera().zoom = 20;

       // Levels.setCamera(camera);


        Vector3 cam = thisLevel.getCamera().position;
        map.updateDisplacement(cam);

        /* @TODO The way to block movement on overlap is to dissect overlap and the ball movement according to their x and y components.
         */
        ball.ballMovement(touchPos,thisLevel.getCamera(),thisLevel.getLevel());
        time += Gdx.graphics.getDeltaTime();

        if ((time > 11 && time < 14) || (time > 20 && time < 23) || (time > 28 && time < 31) || (time > 36 && time < 39) || (time > 44 && time < 47) || (time > 52 && time < 55) | (time > 60 && time < 65)) {
            thisLevel.changeLevel(map);
        }
        else {
            thisLevel.setDegreesAndZoom(-1.6f, map);
        }

	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}
