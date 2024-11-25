package puppy.code;

public class NivelJefe extends Nivel{
    private BossShip jefe;
    public NivelJefe(int nivel, int score, int cantEnemigos) {
        super(nivel, score, cantEnemigos);
    }

    @Override
    public boolean nivelCompletado() {
        return jefe.getLifes() <= 0;
    }
}
