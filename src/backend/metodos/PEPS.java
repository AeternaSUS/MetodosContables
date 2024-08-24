package backend.metodos;
public class PEPS extends Metodo {

    public void compra(Producto producto) {
        productos.add(producto);
        saldoStock += producto.getStock();
        saldoCosto += (producto.getPrecio() * producto.getStock());
    }


    public String venta(int cantidadAVender) {
        saldoStock -= cantidadAVender;
        StringBuilder b = new StringBuilder();

        int resto = cantidadAVender;
        salidaCosto = 0;

        for(Producto producto: productos){
            if (producto.getStock() != 0) {
                if (producto.getStock() > resto) {
                    salidaCosto += resto * producto.getPrecio();
                    b.append(resto).append(" x ").append(producto.getPrecio());
                    producto.setStock(producto.getStock() - resto);
                    break;
                } else {
                    b.append(producto.getStock()).append(" x ").append(producto.getPrecio()).append(" ");
                    salidaCosto += (producto.getStock() * producto.getPrecio());
                    resto -= producto.getStock();
                    producto.setStock(0);
                }
            }
        }
        saldoCosto -= salidaCosto;
        return b.toString();
    }

}
