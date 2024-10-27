package puppy.code;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BossShip extends SpaceShip {
    private float cooldown = 0f; // Número de balas en el patrón circular
    private int bulletSpeed = 2; // Velocidad de las balas
    private AttackPattern attack;
    
    public BossShip(float x, float y, Texture tx, Sound soundCollision, Texture txBullet, Sound soundBullet) {
        super(x, y, tx, soundCollision, txBullet, soundBullet);
        setLifes(4);
        attack = new SpiralAttackPattern(3, 10);
    }

    @Override
    public void draw(SpriteBatch batch, PantallaJuego juego) {
        float x = getX();
        float y = getY();

        getSprite().setPosition(x + getXVel(), y + getYVel());
        getSprite().draw(batch);

        attack(juego);
    }

    @Override
    protected void attack(PantallaJuego juego) {
        if (cooldown <= 0) {
        	attack.execute(this, juego, bulletSpeed);
            getSoundBullet().play();
            cooldown = 10f;
        } else {
            cooldown -= 0.1f;
        }
    }

    @Override
    public boolean checkCollision(SpaceShip b) {
        return false;
    }
}

