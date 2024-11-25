
package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.Random;
import java.util.ArrayList;

public abstract class  Nivel {
    private int nivel;
    private int score;
    private int cantEnemigos;
    private ArrayList<SpaceShip> naves = new ArrayList<>();
    private ArrayList<Bullet> balas = new ArrayList<>();
    private SpriteBatch batch;
    private Sound explosionSound;


    public Nivel(int nivel, int score, int cantEnemigos) {
        this.nivel = nivel;
        this.score = score;
        this.cantEnemigos = cantEnemigos;
        batch = new SpriteBatch();
        explosionSound = Gdx.audio.newSound(Gdx.files.internal("pop-sound.mp3"));
        explosionSound.setVolume(1,0.1f);
    }

    public void jugarNivel(){
        inicializar();
        while (!nivelCompletado()) {
            jugar();
        }
        nivel += 1;
    }

    public void inicializar(){
        Random ran = new Random();

        PlayerShip.getInstance().setPlayer(Gdx.graphics.getWidth()/2-50,30,new Texture(Gdx.files.internal("naveJugador.png")),
            Gdx.audio.newSound(Gdx.files.internal("hurt.ogg")),
            new Texture(Gdx.files.internal("disparo.png")),
            Gdx.audio.newSound(Gdx.files.internal("pop-sound.mp3")));

        if (nivel % 3 != 0) {
            for (int i = 0; i < cantEnemigos; i++) {
                SpaceShip naveEnemiga = new EnemyShip(ran.nextInt((Gdx.graphics.getWidth() - 50) - 50 + 1) + 50, Gdx.graphics.getHeight() - (ran.nextInt((Gdx.graphics.getHeight() / 2) - 60 + 1) + 60), new Texture(Gdx.files.internal("naveEnemiga.png")),
                    new Texture(Gdx.files.internal("disparoEnemigo.png")),
                    Gdx.audio.newSound(Gdx.files.internal("pop-sound.mp3")));
                naves.add(naveEnemiga);
            }
        }
        if (nivel % 3 == 0) {
            SpaceShip jefe = new BossShip(Gdx.graphics.getWidth()/2 - 250,Gdx.graphics.getHeight()/2 + 110,new Texture(Gdx.files.internal("boss.png")),
                new Texture(Gdx.files.internal("disparoEnemigo.png")),
                Gdx.audio.newSound(Gdx.files.internal("pop-sound.mp3")));
            naves.add(jefe);
        }
    }
    public void jugar() {
        if (!PlayerShip.getInstance().isHurt()) {
            //Collision between player bullets and enemy ships.
            for(int i = 0; i < balas.size(); i++) {
                Bullet bullet = balas.get(i);
                bullet.update();

                for (int j = 0; j < naves.size(); j++) {
                    SpaceShip enemyShip = naves.get(j);

                    if (bullet.isPlayerBullet() && bullet.checkCollision(enemyShip)) {
                        naves.remove(i);
                        explosionSound.play();

                        enemyShip.setLifes(enemyShip.getLifes() - 1);
                        if (enemyShip.getLifes() <= 0) {
                            naves.remove(j);
                            j--;
                            score += 10;
                        }
                    }
                }

                if (bullet.isDestroyed()) {
                    balas.remove(i);
                    i--;
                }
            }

            //Collisions between enemies.
            for (int i = 0; i< naves.size() - 1; i++) {
                SpaceShip ship = naves.get(i);
                for (int j=0;j<naves.size() - 1;j++) {
                    SpaceShip ship2 = naves.get(j);
                    if (i<j)
                        ((EnemyShip)ship).checkCollision(ship2);
                }
            }

            for(int i = 0; i < balas.size(); i++) {
                Bullet bullet = balas.get(i);
                if(!bullet.isPlayerBullet() && PlayerShip.getInstance().checkCollision(bullet)) {
                    explosionSound.play();
                    balas.remove(i);
                    i--;
                    break;
                }
            }
        }
        for (int i = 0; i < naves.size() - 1; i++) {
            EnemyShip enemyShip = (EnemyShip) naves.get(i);
            enemyShip.showShip(batch);
            if (PlayerShip.getInstance().checkCollision(enemyShip)) {
                explosionSound.play();
                naves.remove(i);
                i--;
                if(PlayerShip.getInstance().getLifes() <= 0) {
                    PlayerShip.getInstance().setLifes(0);
                    break;
                }
            }
        }
    }
    public abstract boolean nivelCompletado();

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getCantEnemigos() {
        return cantEnemigos;
    }

    public void setCantEnemigos(int cantEnemigos) {
        this.cantEnemigos = cantEnemigos;
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public void setBatch(SpriteBatch batch) {
        this.batch = batch;
    }

    public Sound getExplosionSound() {
        return explosionSound;
    }

    public void setExplosionSound(Sound explosionSound) {
        this.explosionSound = explosionSound;
    }
}