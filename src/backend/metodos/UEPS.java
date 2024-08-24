package backend.metodos;

public class UEPS extends Metodo {

    public void compra(Producto producto) {
        productos.add(producto);
        saldoStock += producto.getStock();
        saldoCosto += (producto.getPrecio() * producto.getStock());
    }

    public String venta(int cantidadAVender) {
        StringBuilder b = new StringBuilder();

        saldoStock -= cantidadAVender;
        int resto = cantidadAVender;
        salidaCosto = 0;

        for (int i = productos.size() - 1; i >= 0; i--) {
            Producto producto = productos.get(i);
            if (producto.getStock() > resto) {
                b.append(resto).append(" x ").append(producto.getPrecio());
                salidaCosto += (resto * producto.getPrecio());
                producto.setStock(producto.getStock() - resto);
                break;
            } else {
                b.append(producto.getStock()).append(" x ").append(producto.getPrecio()).append(" ");
                resto -= producto.getStock();
                salidaCosto += (producto.getPrecio() * producto.getStock());
                producto.setStock(0);
            }
        }
        saldoCosto -= salidaCosto;
        return b.toString();
    }


    public static void main(String[] args) {
        UEPS u = new UEPS();

        Metodo.productos.add(new Producto(100,5));
        Metodo.productos.add(new Producto(50,5));
        u.compra(new Producto(300,3));

        System.out.println(u.venta(120));

        Metodo.productos.forEach(System.out::println);
    }


}
