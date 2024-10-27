package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;


public class Bullet {

	private int xSpeed;
	private int ySpeed;
	private boolean destroyed = false;
	private boolean isPlayerBullet;
	private Sprite spr;
	    
	    public Bullet(float x, float y, int xSpeed, int ySpeed, Texture tx, boolean isPlayerBullet) {
	    	spr = new Sprite(tx);
	    	spr.setPosition(x, y);
	        this.xSpeed = xSpeed;
	        this.ySpeed = ySpeed;
	        
	        this.isPlayerBullet = isPlayerBullet;
	    }
	    public void update() {
	        spr.setPosition(spr.getX()+xSpeed, spr.getY()+ySpeed);
	        if (spr.getX() < 0 || spr.getX()+spr.getWidth() > Gdx.graphics.getWidth()) {
	            destroyed = true;
	        }
	        if (spr.getY() < 0 || spr.getY()+spr.getHeight() > Gdx.graphics.getHeight()) {
	        	destroyed = true;
	        }
	        
	    }
	    
	    public void setRotation(int rotation) {
	    	spr.setRotation(rotation);
	    }
	    
	    public void draw(SpriteBatch batch) {
	    	spr.draw(batch);
	    }
	    public boolean isPlayerBullet() {
	    	return isPlayerBullet;
	    }
	    public Rectangle getArea() {
	    	return spr.getBoundingRectangle();
	    }
	    public int getXSpeed() {
	    	return xSpeed;
	    }
	    public int getYSpeed() {
	    	return ySpeed;
	    }
	    public void setXSpeed(int xSpeed) {
	    	this.xSpeed = xSpeed;
	    }
	    public void setYSpeed(int ySpeed) {
	    	this.ySpeed = ySpeed;
	    }
	    
	    public boolean checkCollision(SpaceShip ship) {
	        if(spr.getBoundingRectangle().overlaps(ship.getArea())){
	        	// Se destruyen ambos
	            this.destroyed = true;
	            return true;
	
	        }
	        return false;
	    }
	    
	    public boolean isDestroyed() {return destroyed;}
	
}
