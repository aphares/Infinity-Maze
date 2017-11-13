package aphares.dev;

//import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class Game extends com.badlogic.gdx.Game {

    public SpriteBatch batch;
    public BitmapFont font;


    public void create() {
        batch = new SpriteBatch();

        font = new BitmapFont(Gdx.files.internal("goth.fnt"));
        this.setScreen(new aphares.dev.Menus.MainMenu(this));
    }

    public void render() {
        super.render(); // important!
    }

    public void dispose() {
        batch.dispose();
        font.dispose();
    }

}