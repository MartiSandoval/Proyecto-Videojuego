package puppy.code;

public class StarAttackPattern implements AttackPattern{

    private float rotacionAcumulativa = 0; // Variable para acumular la rotación de la estrella
    
    @Override
    public void execute(BossShip boss, PantallaJuego juego, float bulletSpeed) {
        
        float[] angles = new float[]{
            0,   
            144, 
            72, 
            288, 
            216  
        };

        
        int totalBalas = angles.length;

        
        for (int i = 0; i < totalBalas; i++) {
            float angleInDegrees = angles[i] + rotacionAcumulativa; 
            float angleInRadians = (float) Math.toRadians(angleInDegrees);

           
            float xVel = bulletSpeed * (float) Math.cos(angleInRadians);
            float yVel = bulletSpeed * (float) Math.sin(angleInRadians);

            
            Bullet bullet = new Bullet(
                boss.getX() + boss.getSprite().getWidth() / 2 - 5, boss.getY() + boss.getSprite().getHeight() - 40,
                xVel, yVel, boss.getBullet(), false);

            bullet.setRotation(angleInDegrees); 
            juego.agregarBala(bullet); 
        }

        
        rotacionAcumulativa += 90; 
    }
}
