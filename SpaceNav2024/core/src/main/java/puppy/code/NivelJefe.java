package puppy.code;

public class NivelJefe extends Nivel{
    public NivelJefe() {
        super();
    }

    @Override
    public void jugar() {
        System.out.println("Jugando nivel Boss");
    }

    @Override
    public boolean nivelCompletado() {
        return true;
    }
}
