package backend.metodos;

public class CostoP extends Metodo {
    @Override
    public void compra(Producto producto) {
        productos.add(producto);
        saldoStock += producto.getStock();
        saldoCosto += (producto.getPrecio() * producto.getStock());
    }

    @Override
    public String venta(int cantidadAVender) {
        StringBuilder sb = new StringBuilder();
        //saldo de dinero / saldo de stock
        double precio =  saldoCosto / saldoStock;

        saldoStock -= cantidadAVender;
        int resto = cantidadAVender;
        salidaCosto = 0;


        for (Producto producto : productos) {
            if (producto.getStock() != 0) {
                if (producto.getStock() > resto) {
                    salidaCosto += resto * precio;
                    sb.append(resto).append(" x ").append(String.format("%.4f", precio));
                    producto.setStock(producto.getStock() - resto);
                    break;
                } else {
                    sb.append(producto.getStock()).append(" x ").append(String.format("%.4f", precio)).append(", ");
                    salidaCosto += (producto.getStock() * precio);
                    resto -= producto.getStock();
                    producto.setStock(0);
                }
            }
        }
        saldoCosto -= salidaCosto;
        return sb.toString();
    }
}
