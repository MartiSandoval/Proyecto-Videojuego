package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

public class NaveEnemiga implements PoderEspecial{
    private boolean destruida = false;
    private int vidas = 3;
    private float xVel = 0;
    private float yVel = 0;
    private float cooldown = 0;
    private Sprite spr;
    private Sound sonidoDestruido;
    private Sound soundBala;
    private Texture txBala;

    public NaveEnemiga(int x, int y, Texture tx, Sound soundChoque, Texture txBala, Sound soundBala) {
        sonidoDestruido = soundChoque;
        this.soundBala = soundBala;
        this.txBala = txBala;
        spr = new Sprite(tx);
        spr.setPosition(x, y);
        //spr.setOriginCenter();
        spr.setBounds(x, y, 45, 45);

    }

    public void draw(SpriteBatch batch, PantallaJuego juego){
        float x =  spr.getX();
        float y =  spr.getY();

            // que se mantenga dentro de los bordes de la ventana
            if (x+xVel < 0 || x+xVel+spr.getWidth() > Gdx.graphics.getWidth())
                xVel*=-1;
            if (y+yVel < 0 || y+yVel+spr.getHeight() > Gdx.graphics.getHeight())
                yVel*=-1;

            spr.setPosition(500, 500);

            spr.draw(batch);
    }

    @Override
    public void activarPoder() {
        System.out.println("Poder activado!!!");
    }
}
