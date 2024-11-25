package puppy.code;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;


public class PantallaJuego implements Screen {

	private SpaceNavigation game;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Sound explosionSound;
	private Music gameMusic;
	private int score;
	private int ronda;
	private int cantEnemies;
        private ShipFactory factory;

	private SpaceShip boss;

	private Array<SpaceShip> ships = new Array<>();
	private  ArrayList<Bullet> balas = new ArrayList<>();
    private ArrayList<SpecialAttack> PoderesEspeciales = new ArrayList<>();

	public PantallaJuego(SpaceNavigation game, int ronda, int vidas, int score, int cantEnemies) {
		this.game = game;
		this.ronda = ronda;
		this.score = score;

		this.cantEnemies = cantEnemies;
		Random ran = new Random();
                
                factory = new SpaceShipFactory();

		batch = game.getBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 640);

		//inicializar assets; musica de fondo y efectos de sonido
		explosionSound = Gdx.audio.newSound(Gdx.files.internal("explosion.ogg"));
		explosionSound.setVolume(1,0.1f);
		gameMusic = Gdx.audio.newMusic(Gdx.files.internal("DavidKBD - Electric Pulse - 09 - Vapor Trails Pursuit-short.wav")); //

		gameMusic.setLooping(true);
		gameMusic.setVolume(0.2f);
		gameMusic.play();

	    // cargar imagen de la nave, 64x64
		PlayerShip.getInstance().setPlayer(Gdx.graphics.getWidth()/2-50,30,new Texture(Gdx.files.internal("naveJugador.png")),
	    				Gdx.audio.newSound(Gdx.files.internal("hurt.ogg")),
	    				new Texture(Gdx.files.internal("disparo.png")),
	    				Gdx.audio.newSound(Gdx.files.internal("pop-sound.mp3")));
                PlayerShip.getInstance().setLifes(vidas);
        
        //crear naves enemigas
        if (ronda % 3 != 0) {
            for (int i = 0; i < cantEnemies; i++) {
                SpaceShip ship = factory.createEnemyShip(ran.nextInt((Gdx.graphics.getWidth() - 50) - 50 + 1) + 50, Gdx.graphics.getHeight() - (ran.nextInt((Gdx.graphics.getHeight() / 2) - 60 + 1) + 60), new Texture(Gdx.files.internal("naveEnemiga.png")),
                    new Texture(Gdx.files.internal("disparoEnemigo.png")),
                    Gdx.audio.newSound(Gdx.files.internal("pop-sound.mp3")));
                ships.add(ship);
            }
        }
       if (ronda % 3 == 0) {
            boss = factory.createBossShip(Gdx.graphics.getWidth()/2 - 250,Gdx.graphics.getHeight()/2 + 110,new Texture(Gdx.files.internal("boss.png")),
                new Texture(Gdx.files.internal("disparoEnemigo.png")),
                Gdx.audio.newSound(Gdx.files.internal("pop-sound.mp3")));
            ships.add(boss);
       }
	}

	public void dibujaEncabezado() {
		CharSequence str = "Vidas: "+ PlayerShip.getInstance().getLifes()+" Ronda: "+ronda;
		game.getFont().getData().setScale(2f);
		game.getFont().draw(batch, str, 10, 30);
        game.getFont().draw(batch, "Enemigos restantes: "+ships.size, Gdx.graphics.getWidth()/2-590, 80);
		game.getFont().draw(batch, "Score:"+this.score, Gdx.graphics.getWidth()-150, 30);
		game.getFont().draw(batch, "HighScore:"+game.getHighScore(), Gdx.graphics.getWidth()/2-100, 30);
	}
	@Override
	public void render(float delta) {
		  Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
          batch.begin();
		  dibujaEncabezado();
                  show();

	      if (PlayerShip.getInstance().isHurt()) {
	    	  //Collision between player bullets and enemy ships.
	       	  for(int i = 0; i < balas.size(); i++) {
	    		  Bullet bullet = balas.get(i);
	    		  bullet.update();

	    		  for(int j = 0; j < ships.size; j++) {
	    			  SpaceShip enemyShip = ships.get(j);

	    			  if(bullet.isPlayerBullet() && bullet.checkCollision(enemyShip)) {
	    				  explosionSound.play();

	    				  enemyShip.setLifes(enemyShip.getLifes() - 1);

	    				  if(enemyShip.getLifes() <= 0 && enemyShip != boss){
	    					  ships.removeIndex(j);
	    					  j--;
	    					  score += 10;
	    				  }
                          if (boss != null && boss.getLifes() <= 0){
                              score += 100;
                          }
	    			  }
	    		  }

	    		  if(bullet.isDestroyed()) {
    				  balas.remove(i);
    				  i--;
    			  }
	    	  }
	       	  if (boss == null) {
                  for (int i = 0; i < ships.size; i++) {
                      SpaceShip enemyShip = ships.get(i);
                      enemyShip.draw(batch, this);
                  }
              }

		      //Collisions between enemies.
		      for (int i = 0; i< ships.size - 1; i++) {
		    	SpaceShip ship = ships.get(i);
		        for (int j=0;j<ships.size - 1;j++) {
		          SpaceShip ship2 = ships.get(j);
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
	      //Draw bullets.
	      for (Bullet b : balas) {
	          b.draw(batch);
	      }
	      PlayerShip.getInstance().draw(batch, this);
          if (boss != null)
            boss.draw(batch, this);
	      //Collisions between player ship and enemy ships.
	      for (int i = 0; i < ships.size - 1; i++) {
	    	    EnemyShip enemyShip = (EnemyShip) ships.get(i);
	    	    enemyShip.showShip(batch);
	    	    if (PlayerShip.getInstance().checkCollision(enemyShip)) {
	    	    	explosionSound.play();
	    	    	ships.removeIndex(i);
	    	    	i--;
	    	    	if(PlayerShip.getInstance().getLifes() <= 0) {
	    	    		PlayerShip.getInstance().setLifes(0);
	    	    		break;
	    	    	}
                }
  	        }

	      //Checks if the player ships has been destroy.
	      if (PlayerShip.getInstance().isDestroy()) {
  			if (score > game.getHighScore())
  				game.setHighScore(score);
	    	Screen ss = new PantallaGameOver(game);
  			ss.resize(1200, 800);
  			game.setScreen(ss);
  			dispose();
  		  }
	      batch.end();

	      //nivel completado
	      if (ships.size == 0) {
              Screen ss = new PantallaJuego(game, ronda + 1, PlayerShip.getInstance().getLifes() + 1, score,
                  cantEnemies + 5);
              ss.resize(1200, 800);
              game.setScreen(ss);
              dispose();
          }
          if (boss != null && boss.getLifes() <= 0) {
              Screen ss = new PantallaJuego(game, ronda + 1, PlayerShip.getInstance().getLifes() + 1, score, cantEnemies + 5);
              ss.resize(1200, 800);
              game.setScreen(ss);
              dispose();
          }

	}

    public boolean agregarBala(Bullet bb) {
    	return balas.add(bb);
    }


	@Override
	public void show() {
		// TODO Auto-generated method stub
		gameMusic.play();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		this.explosionSound.dispose();
		this.gameMusic.dispose();
	}

}
