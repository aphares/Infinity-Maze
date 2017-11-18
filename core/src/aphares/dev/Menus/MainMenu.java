package aphares.dev.Menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

import aphares.dev.Game;

public class MainMenu implements Screen {
    final Game game;
    OrthographicCamera camera;
    float r,g,b;
    boolean temp;

    public MainMenu(final Game gam) {
        game = gam;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        g = 0.25f;
        b = 0.75f;
        r = 0.1f;
        temp = true;

    }

    @Override
    public void render(float delta) {
        if (g < 0.75 && temp) {
            g+=0.0005;
            b-=0.0005;
            r-=0.0001;
        }
        else if (g > 0.25) {
            g-=0.0005;
            b+=0.0005;
            r+=0.0001;
            temp = false;
        }
        else {
            temp = true;
        }

        Gdx.gl.glClearColor(r, g, b, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.font.setColor(0,0,0,1);
        game.font.draw(game.batch, "I N F I N I T Y    M A Z E", 200, 400);
        game.font.draw(game.batch, "Tap anywhere to begin!", 220, 150);
        game.batch.end();

        if (Gdx.input.isTouched()) {
            game.setScreen(new GameScreen(game));
            dispose();
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
    public void dispose() {
    }
}