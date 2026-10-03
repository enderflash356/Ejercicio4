public class EquipoSonido extends Equipo {
    private double potenciaKW;

    public EquipoSonido(int codigo, String marca, String modelo, double tarifaDiaria, boolean disponible, double potenciaKW) {
        super(codigo, marca, modelo, tarifaDiaria, disponible);
        this.potenciaKW = potenciaKW;
    }

    @Override
    protected double calcularRecargoExtra(int dias) {
        return potenciaKW * 100 * dias;
    }

    public double getPotenciaKW() {
        return potenciaKW;
    }
    
}
