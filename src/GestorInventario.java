import java.util.ArrayList;

public class GestorInventario {
    private ArrayList<Equipo> inventario;
    private double ingresosTotales;

    public GestorInventario() {
        this.inventario = new ArrayList<>();
        this.ingresosTotales = 0.0;
    }

    public Equipo buscarPorCodigo(int codigo) {
        for (Equipo eq : inventario) {
            if (eq.getCodigo() == codigo) {
                return eq;
            }
        }
        return null;
    }


    public boolean registrarEquipo(Equipo equipo) {
        if (equipo == null || equipo.getCodigo() <= 0) {
            return false;
        }
        if (buscarPorCodigo(equipo.getCodigo()) != null) {
            return false;
        }
        inventario.add(equipo);
        return true;
    }


    public double cotizar(int codigo, int dias) {
        Equipo eq = buscarPorCodigo(codigo);
        if (eq != null && dias > 0) {
            return eq.calcularCosto(dias);
        }
        return -1.0;
    }

    public boolean confirmarAlquiler(int codigo, int dias) {
        Equipo eq = buscarPorCodigo(codigo);
        if (eq != null && eq.isDisponible() && dias > 0) {
            double costoTotal = eq.calcularCosto(dias);
            if(eq.alquilar()){
                ingresosTotales += costoTotal;
                return true;
            }
        }
        return false;
    }

    public boolean devolverEquipo(int codigo) {
        Equipo eq = buscarPorCodigo(codigo);
        if (eq != null && !eq.isDisponible()) {
            return eq.devolver();
        }
        return false;
    }

    public void generarReporte(){
        System.out.println("Reporte general de ENESCENA");
        System.out.println("Total de equipos registrados: " + inventario.size());

        int proyDisp = 0, proyAlq= 0;
        int camDisp = 0, camAlq = 0;
        int sonDisp = 0, sonAlq = 0;

        for (Equipo eq : inventario) {
            if (eq instanceof Proyector) {
                if (eq.isDisponible()) proyDisp++; else proyAlq++;
            } else if (eq instanceof CamaraVideo) {
                if (eq.isDisponible()) camDisp++; else camAlq++;
            } else if (eq instanceof EquipoSonido) {
                if (eq.isDisponible()) sonDisp++; else sonAlq++;
            }
    }

    System.out.println("Detalle por categoría: ");
    System.out.println("Proyectores: " + proyDisp + " disponibles, " + proyAlq + " alquilados");
    System.out.println("Camaras: " + camDisp + " disponibles, " + camAlq + " alquilados");
    System.out.println("Sonidos: " + sonDisp + " disponibles, " + sonAlq + " alquilados");
    System.out.println("Ingresos totales: Q" + String.format("%.2f", ingresosTotales));
}

    public double getIngresosTotales() {
        return ingresosTotales;
    }
}
