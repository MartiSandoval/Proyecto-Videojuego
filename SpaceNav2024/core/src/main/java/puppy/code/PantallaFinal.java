package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;


public class PantallaFinal implements Screen {

    private SpaceNavigation game;
    private OrthographicCamera camera;
    private Music gameMusic;

    public PantallaFinal(SpaceNavigation game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1200, 800);/*
        gameMusic = Gdx.audio.newMusic(Gdx.files.internal("game_over_music.wav")); //
        gameMusic.setLooping(true);
        gameMusic.setVolume(0.5f);
        gameMusic.play();*/
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0f, 1);

        camera.update();
        game.getBatch().setProjectionMatrix(camera.combined);

        game.getBatch().begin();
        game.getFont().draw(game.getBatch(), "Felicitaciones, terminaste el juego :D !!! ", 120, 400,400,1,true);
        game.getFont().draw(game.getBatch(), "Pincha en cualquier lado para reiniciar ...", 100, 300);

        game.getBatch().end();

        if (Gdx.input.isTouched() || Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY)) {
            Screen ss = new PantallaJuego(game,1,3,0,10);
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
