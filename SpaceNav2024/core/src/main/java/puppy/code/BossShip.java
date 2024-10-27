package puppy.code;

import java.util.Random;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class BossShip extends SpaceShip {
    private float cooldown = 0f; 
    private int bulletSpeed = 2; 
    
    private int attackCounter = 0;
    
    private Array<AttackPattern> attackPatterns;
    private AttackPattern currentAttack;
    
    public BossShip(float x, float y, Texture tx, Sound soundCollision, Texture txBullet, Sound soundBullet) {
        super(x, y, tx, soundCollision, txBullet, soundBullet);
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
        if (cooldown <= 0 && currentAttack != null) {
        	currentAttack.execute(this, juego, bulletSpeed);
        	attackCounter++;
        	if(attackCounter >= 4) {
        		selectRandomAttackPattern();
        		attackCounter = 0;
        	}
        	getSoundBullet().play();
            cooldown = 10f;
        } else 
            cooldown -= 0.1f;
    }

}

