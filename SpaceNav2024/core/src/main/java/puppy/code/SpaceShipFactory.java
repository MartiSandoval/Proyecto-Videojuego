package puppy.code;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class SpaceShipFactory implements ShipFactory {
    @Override
    public SpaceShip createEnemyShip(float x, float y, Texture texture, Texture txBullet, Sound sound) {
        return new EnemyShip(x, y, texture, txBullet,sound);
    }

    @Override
    public SpaceShip createBossShip(float x, float y, Texture texture, Texture txBullet, Sound soundBullet) {
        return new BossShip(x, y , texture, txBullet, soundBullet);
    }
}
