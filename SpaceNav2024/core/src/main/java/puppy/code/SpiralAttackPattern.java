package puppy.code;

import com.badlogic.gdx.utils.Timer;

public class SpiralAttackPattern implements AttackPattern{
    private int cantidadBalas;
    private int numEspirales;

    public SpiralAttackPattern(int numEspirales, int cantidadBalas) {
        this.numEspirales = numEspirales;
        this.cantidadBalas = cantidadBalas;
    }

    @Override
    public void execute(BossShip boss, PantallaJuego juego, float bulletSpeed) {
        float totalAngle = 360f / numEspirales;
        float angleStep = totalAngle / cantidadBalas;


        for (int i = 0; i < cantidadBalas; i++) {
            final int index = i;

            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    for (int j = 0; j < numEspirales; j++) {
                        float angle = index * angleStep + j * totalAngle;


                        float xVel = bulletSpeed * (float) Math.cos(Math.toRadians(angle));
                        float yVel = bulletSpeed * (float) Math.sin(Math.toRadians(angle));


                        Bullet bullet = new Bullet(boss.getX() + boss.getSprite().getWidth() / 2,boss.getY() + boss.getSprite().getHeight() - 40,
                            xVel,yVel,boss.getBullet(),false);

                        bullet.setRotation(angle);
                        juego.agregarBala(bullet);
                    }
                }
            }, index * 0.1f);

        }
    }
}

