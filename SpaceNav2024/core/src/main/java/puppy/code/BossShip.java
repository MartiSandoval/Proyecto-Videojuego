package puppy.code;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BossShip extends SpaceShip{
	
	public BossShip(float x, float y, Texture tx, Sound soundCollision, Texture txBullet, Sound soundBullet) {
			super(x, y, tx, soundCollision, txBullet, soundBullet);
		}

	@Override
	public void draw(SpriteBatch batch, PantallaJuego juego) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void attack(PantallaJuego juego) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean checkCollision(SpaceShip b) {
		// TODO Auto-generated method stub
		return false;
	}

}
