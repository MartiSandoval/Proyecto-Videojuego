package puppy.code;

public abstract class  Nivel {
    private int nivel;
    public Nivel() {
        this.nivel = 1;
    }

    public void jugarNivel(){
        inicializar();
        while (!nivelCompletado()) {
            jugar();
        }
        aumentarNivel();
    }
    public int aumentarNivel() {
        nivel += 1;
        return nivel;
    }
    public void inicializar(){
        System.out.println("Inicializando nivel");
    }
    public abstract void jugar();
    public abstract boolean nivelCompletado();

}
