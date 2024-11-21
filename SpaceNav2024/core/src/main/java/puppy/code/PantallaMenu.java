package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;


public class PantallaMenu implements Screen {

    private SpaceNavigation game;
    private OrthographicCamera camera;
    private Texture backgroundTexture;

    public PantallaMenu(SpaceNavigation game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1200, 800);
        backgroundTexture = new Texture("fondo.png");
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0f, 1);
        camera.update();
        game.getBatch().setProjectionMatrix(camera.combined);

        game.getBatch().begin();
        game.getBatch().draw(backgroundTexture, 0, 0, 1200, 800);
        //game.getFont().draw(game.getBatch(), "¡Bienvenido a Space Navigation!", 400, 600);
        //game.getFont().draw(game.getBatch(), "Click en cualquier lado o presiona cualquier tecla para comenzar ...", 120, 300);

        game.getBatch().end();

        if (Gdx.input.isTouched() || Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY)) {
            Screen ss = new PantallaJuego(game,3,3,0,10);
            ss.resize(1200, 800);
            game.setScreen(ss);
            dispose();
        }
    }


    @Override
    public void show() {
        // TODO Auto-generated method stub

    }

    @Override
    public void resize(int width, int height) {
        // TODO Auto-generated method stub

    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub

    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub

    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub

    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub

    }

}
