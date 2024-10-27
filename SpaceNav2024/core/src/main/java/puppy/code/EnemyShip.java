package puppy.code;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class EnemyShip extends SpaceShip{
	private float cooldown = 0;
	private Random ran = new Random();
	
	public EnemyShip(float x, float y, Texture tx, Sound soundCollision, Texture txBullet, Sound soundBullet) {
		super(x, y, tx, soundCollision, txBullet, soundBullet);
		setLifes(2);
		setMovementSpeed((ran.nextFloat(4 - 2 + 1)) + 2);
	}
	
	@Override
	public void draw(SpriteBatch batch, PantallaJuego juego) {
		// TODO Auto-generated method stub
		float x = getX();
		float y = getY();
		
		movement();
		
		getSprite().setPosition(x + getXVel(), y + getYVel());
		
		getSprite().draw(batch);

		attack(juego);
	}

	@Override
	protected void attack(PantallaJuego juego) {
		// TODO Auto-generated method stub
		if(cooldown <= 0) {
			//Bullet bullet = new Bullet(getSprite().getX()+getSprite().getWidth()/2-5, getSprite().getY() - 20,0,-3,getBullet(), false);
			//bullet.setRotation(180);
			
			//juego.agregarBala(bullet);
			getSoundBullet().play();
			cooldown = ran.nextFloat(15 - 10 + 1) + 10;
		}
		else
			cooldown -= 0.1f;
	}

	private void movement() {
		// TODO Auto-generated method stub
		setXVel(getMovementSpeed());
		if(getX() + getXVel() < 0 || getX() + getXVel() + getSprite().getWidth() > Gdx.graphics.getWidth())
			setMovementSpeed(getMovementSpeed() * -1);
	}
	public void showShip(SpriteBatch batch) {
		getSprite().draw(batch);
	}

	@Override
	public boolean checkCollision(SpaceShip ship) {
		// TODO Auto-generated method stub
		if(ship.getSprite().getBoundingRectangle().overlaps(getArea())) {
			if (getXVel() == 0) 
				setXVel(getXVel() + ship.getXVel()/2);
			if (ship.getXVel() == 0) 
				ship.setXVel(-(ship.getXVel() + (int)getXVel()/2));
			setMovementSpeed(this.getMovementSpeed() * -1);
            ship.setMovementSpeed(ship.getMovementSpeed() * -1);
            
            return true;
		}
		
		return false;
	}

}
