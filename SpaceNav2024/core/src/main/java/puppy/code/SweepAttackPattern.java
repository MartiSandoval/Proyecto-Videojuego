package puppy.code;

import com.badlogic.gdx.utils.Timer; 

public class SweepAttackPattern implements AttackPattern{
    private float startAngle;    
    private float arcWidth;   
    private int cantidadBalas;
      
    public SweepAttackPattern(float startAngle, float arcWidth, int cantidadBalas) {
        this.startAngle = startAngle; 
        this.arcWidth = arcWidth; 
        this.cantidadBalas = cantidadBalas;
 
    }
    @Override
    public void execute(BossShip boss, PantallaJuego juego, float bulletSpeed) {
        for (int i = 0; i < cantidadBalas; i++) {
            final int index = i; 
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    float angle = startAngle + ((arcWidth / (cantidadBalas - 1)) * index);
                    float angleInRadians = (float) Math.toRadians(angle);

                    float xVel = bulletSpeed * (float) Math.cos(angleInRadians);
                    float yVel = bulletSpeed * (float) Math.sin(angleInRadians);

                    Bullet bullet = new Bullet(boss.getX() + boss.getSprite().getWidth() / 2 - 5, 
                            boss.getY() + boss.getSprite().getHeight() - 40, xVel, -yVel, boss.getBullet(), false);
                    
                    bullet.setRotation(angle);
                    juego.agregarBala(bullet); 
                }
            }, i * 0.08f); 
        }
    }
}


