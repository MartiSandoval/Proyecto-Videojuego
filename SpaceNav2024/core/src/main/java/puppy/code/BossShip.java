package puppy.code;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class BossShip extends SpaceShip {
    private int bulletSpeed = 7;
    private int attackCounter = 0;
    private Sprite sprBoss;

    private Array<AttackPattern> attackPatterns;
    private AttackPattern currentAttack;

    public BossShip(float x, float y, Texture tx, Texture txBullet, Sound soundBullet) {
        super(x, y, tx, txBullet, soundBullet);
        sprBoss = new Sprite(tx);
        sprBoss.setBounds(x, y, 80, 80);
        setLifes(50);
        //setMovementSpeed(2);
        attackPatterns = new Array<>();
        attackPatterns.add(new CircularAttackPattern(12));
        attackPatterns.add(new SpiralAttackPattern(3, 12));
        attackPatterns.add(new WaveAttackPattern(12));
        attackPatterns.add(new SweepAttackPattern(0, 180, 12));
        attackPatterns.add(new StarAttackPattern());

        selectRandomAttackPattern();
    }

    @Override
    public void draw(SpriteBatch batch, PantallaJuego juego) {
        float x = getX();
        float y = getY();

        //movement();
        getSprite().draw(batch);

        attack(juego);
    }

    private void selectRandomAttackPattern() {
    	Random ran = new Random();
    	currentAttack = attackPatterns.get(ran.nextInt(attackPatterns.size));
    }

    @Override
    protected void attack(PantallaJuego juego) {
        if (getCooldown() <= 0 && currentAttack != null) {
        	currentAttack.execute(this, juego, bulletSpeed);
        	attackCounter++;
        	if(attackCounter >= 4) {
        		selectRandomAttackPattern();
        		attackCounter = 0;
        	}
        	getSoundBullet().play();
            setCooldown(10f);
        } else
            setCooldown(getCooldown() - 0.45f);
    }
    /*private void movement() {
        setXVel(getMovementSpeed());
        if (getX() + getXVel() < 0 || getX() + getXVel() + getSprite().getWidth() > Gdx.graphics.getWidth())
            setMovementSpeed(getMovementSpeed() * -1);
        getSprite().setPosition(getX() + getXVel(), getY());
    }*/
}

