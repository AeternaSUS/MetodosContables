package frontend.ventanas_metodos;

import backend.metodos.Metodo;
import backend.metodos.Producto;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Tabla extends JPanel {
    JTable tabla;
    DefaultTableModel modelo;
    File archivoExistente;
    FileNameExtensionFilter filtro = new FileNameExtensionFilter(".xlsx", "xlsx");

    public Tabla() {
        this.setPreferredSize(new Dimension(800, 500));
        this.setBackground(Color.decode("#212121"));
        this.setLayout(null);

        crearTabla();
        setFormatoTabla();
    }

    private void crearTabla() {
        String[] cabecera = {"Fecha", "Concepto", "Entrada", "Salida", "Saldo", "Precio", "Entrada", "Salida", "Saldo"};
        modelo = new DefaultTableModel(cabecera, 0);
        tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla);


        scroll.setBounds(10, 50, 780, 440);
        this.add(scroll);
    }

    private void setFormatoTabla() {
        tabla.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        tabla.getTableHeader().setBackground(Color.decode("#212121"));
        tabla.getTableHeader().setForeground(Color.white);
        tabla.setFont(new Font("Arial", Font.PLAIN, 16));

        tabla.setRowHeight(30);

        tabla.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (row % 2 == 0) {
                    c.setBackground(Color.lightGray);
                } else {
                    c.setBackground(Color.white);
                }
                return c;
            }
        });
    }

    public void agregarMovimiento(String[] datos) {
        modelo.addRow(datos);
    }

    public void actualizarTabla(Metodo p) {
        ArrayList<Producto> compras = p.getProductos();
        int c = 0;
        for (int i = 0; i < modelo.getRowCount(); i++) {
            if (modelo.getValueAt(i, 1).toString().equalsIgnoreCase("compra")) {
                modelo.setValueAt(String.valueOf(compras.get(c++).getStock()), i, 2);
            }
        }
    }



    //Metodos para guardar datos en excel
    public void guardarDatos() {

        int filas = modelo.getRowCount();
        int columnas = modelo.getColumnCount();
        String[][] datos = new String[filas][columnas];

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                datos[i][j] = modelo.getValueAt(i, j) == null ? " " : String.valueOf(modelo.getValueAt(i, j));
            }
        }

        if (archivoExistente != null) {
            actualizarArchivo(datos);
        } else {
            comprobarArchivo(datos);
        }
    }


    private void comprobarArchivo(String[][] datos) {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar como");
        fileChooser.setFileFilter(filtro);

        int r = fileChooser.showSaveDialog(this);

        if (r == JFileChooser.APPROVE_OPTION) {

            File archivoNuevo = fileChooser.getSelectedFile();
            String extension = archivoNuevo.getAbsolutePath();


            //no tiene extension .xlsx
            if (!extension.endsWith(".xlsx")) {
                
                archivoNuevo = new File(extension.concat(".xlsx"));
                guardarArchivo(datos, archivoNuevo);
            } else {
                //tiene la extension .xlsx
                guardarArchivo(datos, archivoNuevo);
            }
        }
    }

    private void guardarArchivo(String[][] datos, File ArchivoNuevo) {
        Workbook libro = new XSSFWorkbook();
        Sheet hoja = libro.createSheet("Resultados");
        Row fila;
        Cell celda;

        for (int i = 0; i < datos.length; i++) {
            fila = hoja.createRow(i);
            for (int j = 0; j < datos[i].length; j++) {
                celda = fila.createCell(j);
                celda.setCellValue(datos[i][j]);
            }
        }

        try {
            OutputStream output = new FileOutputStream(ArchivoNuevo);

            libro.write(output);
            libro.close();
            output.close();
            JOptionPane.showMessageDialog(null, "Archivo guardado con exito");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void actualizarArchivo(String[][] datos) {
        try {
            
            Workbook libro = new XSSFWorkbook();
            Sheet hoja = libro.createSheet("Resultados");
            Row fila;
            Cell celda;

            for (int i = 0; i < datos.length; i++) {
                fila = hoja.createRow(i);
                for (int j = 0; j < datos[i].length; j++) {
                    celda = fila.createCell(j);
                    celda.setCellValue(datos[i][j]);
                }
            }

            OutputStream output = new FileOutputStream(archivoExistente);

            libro.write(output);
            libro.close();
            output.close();

            JOptionPane.showMessageDialog(null, "Cambios guardados con exito");
        } catch (Exception e) {
            System.out.println(e.getMessage() + " ERROR en actualizar archivo");
        }
    }

    public void cargarArchivo() {
        try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Abrir archivo");
            fileChooser.setFileFilter(filtro);

            int r = fileChooser.showOpenDialog(this);

            if (r == JFileChooser.APPROVE_OPTION) {
                archivoExistente = fileChooser.getSelectedFile();

                if (archivoExistente.getName().endsWith(".xlsx")) {
                    leerArchivo();
                } else {
                    JOptionPane.showMessageDialog(null, "El archivo no es compatible\npor favor asegurese que sea un archivo txt");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage() + " ERROR al leer archivo");
        }
    }

    private void leerArchivo() {
        try {
            Workbook libro = new XSSFWorkbook(archivoExistente);
            Sheet hoja = libro.getSheetAt(0);
            Iterator<Row> filas = hoja.rowIterator();
            Iterator<Cell> celdas;
            String[] datos;
            int i = 0;

            while (filas.hasNext()) {
                datos = new String[9];
                Row fila = filas.next();
                celdas = fila.cellIterator();

                while (celdas.hasNext()) {
                    Cell celda = celdas.next();

                    if (celda.getStringCellValue().equalsIgnoreCase("compra")) {
                        Metodo.productos.add(new Producto(
                                Integer.parseInt(fila.getCell(2).getStringCellValue()),
                                Integer.parseInt(fila.getCell(5).getStringCellValue())));
                    }

                    datos[i++] = celda.getStringCellValue();

                }
                modelo.addRow(datos);
                i = 0;
            }

            libro.close();


            //seteamos saldos a la clase almacen
            int ultimaFila = modelo.getRowCount() - 1;
            Metodo.saldoStock = Integer.parseInt((String) modelo.getValueAt(ultimaFila, 4));
            Metodo.saldoCosto = Double.parseDouble((String) modelo.getValueAt(ultimaFila, 8));

            JOptionPane.showMessageDialog(null, "Archivo leido con exito");
        } catch (Exception e) {
            System.out.println(e.getMessage() + " ERROR al leer archivo");
        }
    }
}
