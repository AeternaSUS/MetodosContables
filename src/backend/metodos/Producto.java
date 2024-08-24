package backend.metodos;

public class Producto {

    private int stock;
    private final int precio;

    public Producto(int stock, int precio) {
        this.stock = stock;
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getPrecio() {
        return precio;
    }

    @Override
    public String toString() {
        return stock + " " + precio;
    }
}
