package puppy.code;

public class CircularAttackPattern implements AttackPattern{
    private float rotacionAcumulativa = 0; 
    private int cantidadBalas;
    
    public CircularAttackPattern(int cantidadBalas) {
    	this.cantidadBalas = cantidadBalas;
    }

    @Override
    public void execute(BossShip boss, PantallaJuego juego, float bulletSpeed) {
        float angleIncrement = 360f / cantidadBalas;

        for (int i = 0; i < cantidadBalas; i++) {
            
            float angle = i * angleIncrement + rotacionAcumulativa;
            float angleInRadians = (float) Math.toRadians(angle);

            float xVel = bulletSpeed * (float) Math.cos(angleInRadians);
            float yVel = bulletSpeed * (float) Math.sin(angleInRadians);

            Bullet bullet = new Bullet(
                boss.getX() + boss.getSprite().getWidth() / 2 - 5,
                boss.getY() + boss.getSprite().getHeight() - 40,
                xVel,
                yVel,
                boss.getBullet(),
                false
            );

            bullet.setRotation(angle); 
            juego.agregarBala(bullet); 
        }


        rotacionAcumulativa += 90; 
    }
}
