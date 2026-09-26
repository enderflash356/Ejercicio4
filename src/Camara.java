public class Camara extends Equipo {
    private int resolution;

    public Camara(int code, String brand, String model, double dailyCost, boolean available, int resolution) {
        super(code, brand, model, dailyCost, available);
        this.resolution = resolution;
    }
    @Override
    protected double calculateExtraCost(int days) {
        if (resolution > 1080){
            return 75;
        }
        return 0;
    }
}
