package puppy.code;


public class WaveAttackPattern implements AttackPattern {

	private float rotationAngle = 0; 
    private final float arcAngle = 180f; 
    private int cantidadBalas;
    
    public WaveAttackPattern(int cantidadBalas) {
    	this.cantidadBalas = cantidadBalas;
    }

    @Override
    public void execute(BossShip boss, PantallaJuego juego, float bulletSpeed) {
        float angleIncrement = arcAngle / cantidadBalas; 

        for (int i = 0; i < cantidadBalas; i++) {
            
            float angle = rotationAngle + (i * angleIncrement);

            
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

        
        rotationAngle += 90; 
        if (rotationAngle >= 360) {
            rotationAngle -= 360; 
        }
    }
}