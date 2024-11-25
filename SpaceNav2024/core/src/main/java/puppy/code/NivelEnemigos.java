package puppy.code;

import com.badlogic.gdx.utils.Array;


public class NivelEnemigos extends Nivel{
    private Array<SpaceShip> naves = new Array<>();

    public NivelEnemigos(int nivel, int score, int cantEnemigos) {
        super(nivel, score, cantEnemigos);
    }

    @Override
    public boolean nivelCompletado() {
        return naves.size == 0;
    }

}