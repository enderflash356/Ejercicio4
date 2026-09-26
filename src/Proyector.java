public class Proyector extends Equipo {
    private int lumenes;
    private boolean wireless;

    public Proyector(int code, String brand, String model, double dailyCost, boolean available, int lumenes, boolean wireless) {
        super(code, brand, model, dailyCost, available);
        this.lumenes = lumenes;
        this.wireless = wireless;
    }
    @Override
    protected double calculateExtraCost(int days) {
        if (wireless){
            return 50 * days;
        }

        return 0;
    }
    
}
