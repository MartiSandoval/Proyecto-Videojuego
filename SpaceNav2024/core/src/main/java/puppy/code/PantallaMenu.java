package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class PantallaMenu implements Screen {

    private SpaceNavigation game;
    private OrthographicCamera camera;
    private Stage stage;
    private Skin skin;

    public PantallaMenu(SpaceNavigation game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1200, 800);

        // Configuración de la interfaz gráfica
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        // Carga de la skin para los botones y etiquetas
        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

        // Crear el título
        Label titleLabel = new Label("Space Navigation", skin, "default");
        titleLabel.setFontScale(2);  // Tamaño del título

        // Crear botones
        TextButton playButton = new TextButton("Jugar", skin);
        TextButton tutorialButton = new TextButton("Tutorial", skin);
        TextButton exitButton = new TextButton("Salir", skin);

        // Añadir listeners a los botones
        playButton.addListener(event -> {
            if (Gdx.input.isTouched()) {
                game.setScreen(new PantallaJuego(game, 1, 3, 0, 10));
                dispose();
                return true;
            }
            return false;
        });

        tutorialButton.addListener(event -> {
            if (Gdx.input.isTouched()) {
                //game.setScreen(new PantallaTutorial(game));
                dispose();
                return true;
            }
            return false;
        });

        exitButton.addListener(event -> {
            if (Gdx.input.isTouched()) {
                Gdx.app.exit();
                return true;
            }
            return false;
        });

        // Organizar elementos en una tabla
        Table table = new Table();
        table.setFillParent(true);
        table.top();
        table.add(titleLabel).padBottom(50).row();
        table.add(playButton).padBottom(20).row();
        table.add(tutorialButton).padBottom(20).row();
        table.add(exitButton);

        // Añadir la tabla al escenario
        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0f, 1);

        // Actualizar y dibujar el escenario
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void show() {}

    @Override
    public void hide() {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
}
