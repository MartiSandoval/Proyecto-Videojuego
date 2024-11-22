package puppy.code;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class SpaceShipFactory implements ShipFactory {

    @Override
    public PlayerShip createPlayerShip(float x, float y, Texture texture, Sound sound, Texture txBullet,Sound soundBullet) {
        return new PlayerShip(x,y,texture,sound,txBullet,soundBullet);
    }

    @Override
    public EnemyShip createEnemyShip(float x, float y, Texture texture, Texture txBullet, Sound sound) {
        return new EnemyShip(x, y, texture, txBullet,sound);
    }

    @Override
    public BossShip createBossShip(float x, float y, Texture texture, Texture txBullet, Sound soundBullet) {
        return new BossShip(x, y , texture, txBullet, soundBullet);
    }
}
