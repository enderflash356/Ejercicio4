public class Main {
    public static void main(String[] args) {
        Equipo c = new Camara(101, "Sony", "DSC-HX90V",100, true, 1080);
        Equipo p = new Proyector(102, "Panasonic", "GH5", 100, true, 10, true);

        System.out.println("Costo Cámara (2 dias): Q" +c.calculateCost(2));
        System.out.println("Costo Proyector (2 dias): Q" +p.calculateCost(2));
    }
}