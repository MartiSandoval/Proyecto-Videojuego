package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;


public class Ball2 {
	private double x;
    private double y;
    private double xSpeed;
    private double ySpeed;
    private Sprite spr;

    public Ball2(double x, double y, int size, double xSpeed, double ySpeed, Texture tx) {
    	spr = new Sprite(tx);
    	this.x = x;

        //validar que borde de esfera no quede fuera
    	if (x-size < 0) this.x = x+size;
    	if (x+size > Gdx.graphics.getWidth())this.x = x-size;
        this.y = y;

        //validar que borde de esfera no quede fuera
    	if (y-size < 0) this.y = y+size;
    	if (y+size > Gdx.graphics.getHeight())this.y = y-size;

        spr.setPosition((float) x, (float) y);
        this.setXSpeed(xSpeed);
        this.setySpeed(ySpeed);
    }
    public void update() {
        x += getXSpeed();
        y += getySpeed();

        if (x+getXSpeed() < 0 || x+getXSpeed()+spr.getWidth() > Gdx.graphics.getWidth())
        	setXSpeed(getXSpeed() * -1);
        if (y+getySpeed() < 0 || y+getySpeed()+spr.getHeight() > Gdx.graphics.getHeight())
        	setySpeed(getySpeed() * -1);
        spr.setPosition((float)x, (float)y);
    }

    public Rectangle getArea() {
    	return spr.getBoundingRectangle();
    }
    public void draw(SpriteBatch batch) {
    	spr.draw(batch);
    }

    public void checkCollision(Ball2 b2) {
        if(spr.getBoundingRectangle().overlaps(b2.spr.getBoundingRectangle())){
        	// rebote
            if (getXSpeed() == 0) setXSpeed(getXSpeed());
            if (b2.getXSpeed() == 0) b2.setXSpeed(b2.getXSpeed());
        	setXSpeed(- getXSpeed());
            b2.setXSpeed(-b2.getXSpeed());

            if (getySpeed() ==0) setySpeed(getySpeed());
            if (b2.getySpeed() ==0) b2.setySpeed(b2.getySpeed());
            setySpeed(- getySpeed());
            b2.setySpeed(- b2.getySpeed());
        }
    }
	public double getXSpeed() { return xSpeed; }
	public void setXSpeed(double xSpeed) { this.xSpeed = xSpeed;
	}
	public double getySpeed() {
		return ySpeed;
	}
	public void setySpeed(double ySpeed) {
		this.ySpeed = ySpeed;
	}


}
