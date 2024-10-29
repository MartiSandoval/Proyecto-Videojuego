package puppy.code;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class SpaceShip {

	private boolean destroy = false;
	private float movementSpeed;
	private int lifes;
    private float xVel = 0;
    private float yVel = 0;
    private Sprite spr;
    private Sound soundHurt;
    private Sound soundBullet;
    private Texture txBullet;
    private boolean hurt = false;
    private int maxTimeHurt = 50;
    private int timeHurt;

    public SpaceShip(float x, float y, Texture tx, Sound soundCollision, Texture txBullet, Sound soundBullet) {
    	soundHurt = soundCollision;
    	this.soundBullet = soundBullet;
    	this.txBullet = txBullet;
    	spr = new Sprite(tx);
    	spr.setPosition(x, y);
    	spr.setBounds(x,  y,  45,  45);
    }
    public SpaceShip(float x, float y, Texture tx, Texture txBullet, Sound soundBullet) {
    }

	public abstract void draw(SpriteBatch batch, PantallaJuego juego);

	protected abstract void attack(PantallaJuego juego);

	public abstract boolean checkCollision(SpaceShip ship);

	public Rectangle getArea() {
		return spr.getBoundingRectangle();
	}
	public boolean isDestroy() {
		return !hurt && destroy;
	}
	public void gotDestroy(boolean destroy) {
		this.destroy = destroy;
	}

	public boolean isHurt() {
		return hurt;
	}
	public void gotHurt(boolean hurt) {
		this.hurt = hurt;
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
	public Sound getSoundHurt() {
		return soundHurt;
	}
	public void setTimeHurt(int timeHurt) {
		this.timeHurt = timeHurt;
	}
	public int getTimeHurt() {
		return timeHurt;
	}
	public int getMaxTimeHurt() {
		return maxTimeHurt;
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

	public int getLifes() {
		return lifes;
	}

	public void setLifes(int lifes) {
		this.lifes = lifes;
	}
}
