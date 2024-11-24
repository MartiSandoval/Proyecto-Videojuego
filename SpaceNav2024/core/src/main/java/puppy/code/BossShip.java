package puppy.code;

import java.util.Random;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class BossShip extends SpaceShip { 
    private int bulletSpeed = 2; 
    private int attackCounter = 0;
    
    private Array<AttackPattern> attackPatterns;
    private AttackPattern currentAttack;
    
    public BossShip(float x, float y, Texture tx, Texture txBullet, Sound soundBullet) {
        super(x, y, tx, txBullet, soundBullet);
        setLifes(4);
        attackPatterns = new Array<>();
        attackPatterns.add(new CircularAttackPattern(10));
        attackPatterns.add(new SpiralAttackPattern(3, 10));
        attackPatterns.add(new WaveAttackPattern(10));
        attackPatterns.add(new SweepAttackPattern(0, 180, 10));
        attackPatterns.add(new StarAttackPattern());

        selectRandomAttackPattern();
    }

    @Override
    public void draw(SpriteBatch batch, PantallaJuego juego) {
        float x = getX();
        float y = getY();

        getSprite().setPosition(x + getXVel(), y + getYVel());
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
            setCooldown(getCooldown() - 0.1f);
    }

}

