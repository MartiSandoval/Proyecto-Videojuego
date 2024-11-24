package puppy.code;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public interface ShipFactory {
    SpaceShip createEnemyShip(float x, float y, Texture texture, Texture txBullet,Sound sound);
    SpaceShip createBossShip(float x, float y, Texture texture, Texture txBullet, Sound sound);
}

