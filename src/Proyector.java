public class Proyector extends Equipo {
    private int lumenes;
    private boolean inalambrico;

    public Proyector(int codigo, String marca, String modelo, double tarifaDiaria, boolean disponible, int lumenes, boolean inalambrico) {
        super(codigo, marca, modelo, tarifaDiaria, disponible);
        this.lumenes = lumenes;
        this.inalambrico = inalambrico;
    }
    @Override
    protected double calcularRecargoExtra(int dias) {
        if (inalambrico){
            return 50.0 * dias;
        }

        return 0.0;
    }

    public int getLumenes() {
        return lumenes;
    }

    public boolean isInalambrico() {
        return inalambrico;
    }
    
}
