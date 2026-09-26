public abstract class Equipo {
    private int code;
    private String brand;
    private String model;
    private double dailyCost;
    private boolean available;

    public Equipo(int code, String brand, String model, double dailyCost, boolean available) {
        this.code = code;
        this.brand = brand;
        this.model = model;
        this.dailyCost = dailyCost;
        this.available = available;
    }

    public double calculateCost(int days) {
        return dailyCost * days + calculateExtraCost(days);
    }
    
    protected abstract double calculateExtraCost(int days);
}
