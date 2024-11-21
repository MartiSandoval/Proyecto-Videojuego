package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

public class PlayerShip extends SpaceShip implements PoderEspecial{
    private int maxTimeHurt = 50;
    private int timeHurt;
    private Sound soundHurt;
    private boolean hurt = false;
    private boolean destroy = false;
    private Sprite sprNav;

    public PlayerShip(float x, float y, Texture tx, Sound soundCollision, Texture txBullet, Sound soundBullet) {
        super(x, y, tx, txBullet, soundBullet);
        sprNav = new Sprite(tx);
        sprNav.setBounds(x, y, 45, 45);

        setMovementSpeed(7);
        this.soundHurt = soundCollision;
        setLifes(3);
    }

    @Override
    public void draw(SpriteBatch batch, PantallaJuego juego) {
        // TODO Auto-generated method stub
        // TODO Auto-generated method stub
        float x = getX();
        float y = getY();

        if(!isHurt()) {
            movement();

            if(x + getXVel() < 0 || x + getXVel() + getSprite().getWidth() > Gdx.graphics.getWidth())
                setXVel(-1 * getXVel());
            if(y + getYVel() < 0 || y + getYVel() + getSprite().getHeight() > Gdx.graphics.getHeight())
                setYVel(-1 * getYVel());

            getSprite().setPosition(x + getXVel(), y + getYVel());
            getSprite().draw(batch);
        }else {
            getSprite().setX(getSprite().getX() + MathUtils.random(-2, 2));
            getSprite().draw(batch);

            getSprite().setX(x);
            setTimeHurt(getTimeHurt() - 1);
            if(getTimeHurt() <= 0)
                gotHurt(false);
        }

        attack(juego);
    }

    @Override
    protected void attack(PantallaJuego juego) {
        // TODO Auto-generated method stub
        if(getCooldown() <= 0) {
            if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                Bullet bullet = new Bullet(getSprite().getX()+getSprite().getWidth()/2-5,getSprite().getY()+ getSprite().getHeight()-5,0,4,getBullet(), true);
                juego.agregarBala(bullet);
                getSoundBullet().play();
                setCooldown(10f);
            }
        }
        else
            setCooldown(getCooldown() - 0.52f);
    }

    private void movement() {
        // TODO Auto-generated method stub
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
            setXVel(-getMovementSpeed());
        else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            setXVel(getMovementSpeed());
        else
            setXVel(0);
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN))
            setYVel(-getMovementSpeed());
        else if(Gdx.input.isKeyPressed(Input.Keys.UP))
            setYVel(getMovementSpeed());
        else
            setYVel(0);
    }


    public boolean checkCollision(SpaceShip ship) {
        if(!isHurt() && ship.getArea().overlaps(getSprite().getBoundingRectangle())){
            if (getXVel() == 0)
                setXVel(getXVel() + ship.getXVel() / 2);
            if (ship.getXVel() == 0)
                ship.setXVel(ship.getXVel() + (int)getXVel()/2);
            setXVel(-getXVel());
            ship.setXVel(-ship.getXVel());

            if (getYVel() ==0)
                setYVel(getYVel() + ship.getYVel() / 2);
            if (ship.getYVel() ==0)
                ship.setYVel(ship.getYVel() + (int)getYVel()/2);

            setYVel(-getYVel());
            ship.setYVel(- ship.getYVel());

            setLifes(getLifes() - 1);
            gotHurt(true);
            setTimeHurt(getMaxTimeHurt());
            getSoundHurt().play();
            if (getLifes() <=0)
                gotDestroy(true);
            return true;
        }
        return false;
    }

    public boolean checkCollision(Bullet bullet) {
        if(!isHurt() && bullet.getArea().overlaps(getSprite().getBoundingRectangle())){
            if (getXVel() == 0)
                setXVel(getXVel() + bullet.getXSpeed() / 2);
            if (bullet.getXSpeed() == 0)
                bullet.setXSpeed(bullet.getXSpeed() + (int)getXVel()/2);
            setXVel(-getXVel());
            bullet.setXSpeed(-bullet.getXSpeed());

            if (getYVel() ==0)
                setYVel(getYVel() + bullet.getYSpeed() / 2);
            if (bullet.getYSpeed() ==0)
                bullet.setYSpeed(bullet.getYSpeed() + (int)getYVel()/2);

            setYVel(-getYVel());
            bullet.setYSpeed(- bullet.getYSpeed());

            setLifes(getLifes() - 1);
            gotHurt(true);
            setTimeHurt(getMaxTimeHurt());
            getSoundHurt().play();
            if (getLifes() <=0)
                gotDestroy(true);
            return true;
        }
        return false;
    }
    @Override
    public void activarPoder() {
        System.out.println("Se activa el poder");
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
}
