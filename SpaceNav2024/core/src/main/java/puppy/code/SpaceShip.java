package puppy.code;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class SpaceShip {
	
	private float movementSpeed;
	private int lifes;
    private float xVel = 0;
    private float yVel = 0;
    private Sprite spr;

    private Sound soundBullet;
    private Texture txBullet;
    private float cooldown = 0;
	
    public SpaceShip(float x, float y, Texture tx, Texture txBullet, Sound soundBullet) {
    	this.soundBullet = soundBullet;
    	this.txBullet = txBullet;
    	spr = new Sprite(tx);
    	spr.setPosition(x, y);
    	spr.setBounds(x,  y,  45,  45);
    }
    public SpaceShip() {
    }
    
	public abstract void draw(SpriteBatch batch, PantallaJuego juego);
	
	protected abstract void attack(PantallaJuego juego);
	
	public Rectangle getArea() {
		return spr.getBoundingRectangle();
	}

	public float getX() {
		return spr.getX();
	}
	public float getY() {
		return spr.getY();
	}
	public Texture getBullet() {
		return txBullet;
	}
	public Sprite getSprite() {
		return spr;
	}
	public Sound getSoundBullet() {
		return soundBullet;
	}

	public void setXVel(float xVel) {
		this.xVel = xVel;
	}
	public float getXVel() {
		return xVel;
	}
	public void setYVel(float yVel) {
		this.yVel = yVel;
	}
	public float getYVel() {
		return yVel;
	}
	
	public void setMovementSpeed(float movementSpeed) {
		this.movementSpeed = movementSpeed;
	}
	public float getMovementSpeed() {
		return movementSpeed;
	}
	public void setCooldown(float cooldown) {
		this.cooldown = cooldown;
	}
	public float getCooldown() {
		return cooldown;
	}
	
	public int getLifes() {
		return lifes;
	}
	
	public void setLifes(int lifes) {
		this.lifes = lifes;
	}
}
