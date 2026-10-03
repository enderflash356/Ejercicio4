public class CamaraVideo extends Equipo {
    private int resolucionVertical;

    public CamaraVideo(int codigo, String marca, String modelo, double tarifaDiaria, boolean disponible, int resolucionVertical) {
        super(codigo, marca, modelo, tarifaDiaria, disponible);
        this.resolucionVertical = resolucionVertical;
    }
    @Override
    protected double calcularRecargoExtra(int dias) {
        if (resolucionVertical > 1080){
            return 75.0;
        }
        return 0.0;
    }

    public int getResolucionVertical() {
        return resolucionVertical;
    }
}

