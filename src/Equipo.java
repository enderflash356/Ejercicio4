public abstract class Equipo {
    private int codigo;
    private String marca;
    private String modelo;
    private double tarifaDiaria;
    private boolean disponible;

    public Equipo(int codigo, String marca, String modelo, double tarifaDiaria, boolean disponible) {
        this.codigo = codigo;
        this.marca = marca;
        this.modelo = modelo;
        this.tarifaDiaria = tarifaDiaria;
        this.disponible = disponible;
    }

    public double calcularCosto(int dias) {
        return tarifaDiaria * dias + calcularRecargoExtra(dias);
    }
    
    protected abstract double calcularRecargoExtra(int dias);

    public boolean alquilar(){
        if (disponible){
            disponible = false;
            return true;
        }
        return false;
    }

    public boolean devolver(){
        if (!disponible){
            disponible = true;
            return true;
        }
        return false;
    }

    public int getCodigo(){
        return codigo;
    }

    public String getMarca(){
        return marca;
    }

    public String getModelo(){
        return modelo;
    }

    public double getTarifaDiaria(){
        return tarifaDiaria;
    }

    public boolean isDisponible(){
        return disponible;
    }
}
