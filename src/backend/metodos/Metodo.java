package backend.metodos;

import java.util.ArrayList;

public abstract class Metodo {
    public static int saldoStock = 0;
    public static double saldoCosto = 0;
    public static double salidaCosto;
    public static ArrayList<Producto> productos = new ArrayList<>();

    public abstract void compra(Producto Producto);

    public abstract String venta(int cantidad);

    public ArrayList<Producto> getProductos() {
        return new ArrayList<>(productos);
    }

}
