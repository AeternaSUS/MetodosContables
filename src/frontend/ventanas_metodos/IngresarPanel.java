package frontend.ventanas_metodos;

import backend.metodos.Metodo;
import backend.metodos.Producto;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class IngresarPanel extends JPanel {
    JComboBox<String> comboBox = new JComboBox<>();
    JTextField cantidadArticulos = new JTextField();
    JTextField precioUnidad = new JTextField();
    JButton boton = new JButton();
    Metodo p;
    Tabla tabla;

    public IngresarPanel(Metodo p, Tabla tabla) {
        this.setLayout(null);
        this.p = p;
        this.tabla = tabla;
        this.setBackground(Color.decode("#212121"));
        setContenido();
        eventosTxt();
        eventosComponentes();
        macro();

    }

    private void setContenido() {
        Font fuenteLabel = new Font("Arial", Font.BOLD, 16);
        Font fuenteText = new Font("Arial", Font.PLAIN, 16);
        this.setPreferredSize(new Dimension(600, 500));

        JLabel label = new JLabel("Cantidad de unidades");
        label.setFont(fuenteLabel);
        label.setForeground(Color.white);
        label.setBounds(80, 80, 200, 30);
        this.add(label);

        cantidadArticulos.setBounds(280, 80, 200, 30);
        cantidadArticulos.setFont(fuenteText);
        this.add(cantidadArticulos);


        JLabel label1 = new JLabel("Seleccione concepto");
        label1.setBounds(80, 180, 200, 30);
        label1.setFont(fuenteLabel);
        label1.setForeground(Color.white);
        this.add(label1);


        comboBox.setBounds(280, 180, 200, 30);
        comboBox.addItem("Compra");
        comboBox.addItem("Venta");
        eventoCombo();
        this.add(comboBox);

        JLabel label2 = new JLabel("Precio por unidad");
        label2.setBounds(80, 280, 200, 30);
        label2.setFont(fuenteLabel);
        label2.setForeground(Color.white);
        this.add(label2);

        precioUnidad.setBounds(280, 280, 200, 30);
        precioUnidad.setFont(fuenteText);
        this.add(precioUnidad);

        boton.setBounds(230, 360, 100, 30);
        boton.setFont(fuenteText);
        boton.setText("Ingresar");
        this.add(boton);
    }

    private void eventosComponentes() {
        boton.addActionListener(e -> eventoMovimiento());

        precioUnidad.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    eventoMovimiento();
                }
            }
        });

        cantidadArticulos.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    eventoMovimiento();
                }
            }
        });
    }

    private void eventoMovimiento() {
        String cantidad = cantidadArticulos.getText();
        String precio = precioUnidad.getText();
        String fecha = crearFecha();
        String concepto = (String) comboBox.getSelectedItem();
        String[] datos = new String[9];

        assert concepto != null;

        if(verificarTxt(cantidad) && verificarTxt(precio)){
            if (concepto.equals("Compra")) {
                eventoCompra(datos, cantidad, precio, fecha, concepto);

            } else if (concepto.equals("Venta")) {
                eventoVenta(datos, fecha, concepto, cantidad);

            }
        }else{
            JOptionPane.showMessageDialog(null, "Ingresa los datos correctamente");
        }
    }

    private boolean verificarTxt(String cantidad){
        for(char c: cantidad.toCharArray()){
            if(!Character.isDigit(c)){
                return false;
            }
        }
        return true;
    }

    private void eventoVenta(String[] datos, String fecha, String concepto, String cantidad) {

        if (!cantidad.isEmpty()) {
            if (Integer.parseInt(cantidad) <= Metodo.saldoStock) {
                String costo = p.venta(Integer.parseInt(cantidad));
                datos[0] = fecha;
                datos[1] = concepto;
                datos[3] = cantidad;
                datos[4] = String.valueOf(Metodo.saldoStock);
                datos[5] = costo;
                datos[7] = String.valueOf(String.format("%.2f", Metodo.salidaCosto));
                datos[8] = String.valueOf(String.format("%.2f", Metodo.saldoCosto));
                tabla.agregarMovimiento(datos);
                tabla.actualizarTabla(p);

                JOptionPane.showMessageDialog(null, "Venta realizada con exito");

            } else {
                JOptionPane.showMessageDialog(null, "No hay stock suficiente");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Por favor ingrese la cantidad a vender");
        }


    }

    private void eventoCompra(String[] datos, String cantidad, String precio, String fecha, String concepto) {
        if (!cantidad.isEmpty() && !precio.isEmpty()) {
            p.compra(new Producto(Integer.parseInt(cantidad), Integer.parseInt(precio)));
            datos[0] = fecha;
            datos[1] = concepto;
            datos[2] = cantidad;
            datos[4] = String.valueOf(Metodo.saldoStock);
            datos[5] = precio;
            datos[6] = String.valueOf(Integer.parseInt(precio) * Integer.parseInt(cantidad));
            datos[8] = String.valueOf(Metodo.saldoCosto);
            tabla.agregarMovimiento(datos);

            JOptionPane.showMessageDialog(null, "compra realizada con exito");
        } else {
            JOptionPane.showMessageDialog(null, "Por favor ingrese la cantidad y precio");
        }

    }

    private String crearFecha() {
        Date fecha = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd 'de' MMMM");
        return formato.format(fecha);
    }

    private void eventoCombo() {
        comboBox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String opcion = (String) comboBox.getSelectedItem();
                if (opcion != null && opcion.equals("Venta")) {
                    precioUnidad.setEnabled(false);
                    precioUnidad.setBackground(Color.gray);
                } else {
                    precioUnidad.setEnabled(true);
                    precioUnidad.setBackground(Color.white);
                }
            }
        });
    }

    private void eventosTxt() {
        cantidadArticulos.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (!cantidadArticulos.getText().isEmpty()) {
                    char c = cantidadArticulos.getText().charAt(0);
                    if (!Character.isDigit(c)) {
                        cantidadArticulos.setText("");
                    }
                }

            }
        });

        precioUnidad.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (!precioUnidad.getText().isEmpty()) {
                    char c = precioUnidad.getText().charAt(0);
                    if (!Character.isDigit(c)) {
                        precioUnidad.setText("");
                    }
                }
            }
        });
    }

    public void macro() {
        InputMap input = this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap action = this.getActionMap();

        input.put(KeyStroke.getKeyStroke(KeyEvent.VK_I, KeyEvent.CTRL_DOWN_MASK), "eventoCompra");
        action.put("eventoCompra", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comboBox.setSelectedIndex(0);
            }
        });


        input.put(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK), "eventoVenta");
        action.put("eventoVenta", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comboBox.setSelectedIndex(1);
            }
        });

    }

}
