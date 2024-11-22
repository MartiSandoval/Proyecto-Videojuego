package puppy.code;

public class NivelEnemigos extends Nivel{
    public NivelEnemigos() {
        super();
    }

    @Override
    public void jugar() {
        System.out.println("Jugando nivel 1");
    }

    @Override
    public boolean nivelCompletado() {
        return true;
    }

}
