package frontend.ventanas_metodos;

import backend.metodos.CostoP;
import backend.metodos.PEPS;
import backend.metodos.UEPS;
import frontend.login.Boton;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.InputStream;
public class VentanaIngreso extends JFrame {

    JPanel sideMenu = new JPanel();
    JPanel contenido = new JPanel();
    JPanel fondo = new JPanel();
    Font fuenteMenu = new Font("Arial", Font.BOLD, 18);
    Dimension panelesMenu = new Dimension(200, 50);

    final int WIDTH = 800;
    final int HEIGHT = 500;
    JLayeredPane layered = new JLayeredPane();
    JLayeredPane layeredContenido = new JLayeredPane();
    JLabel imagenMenu = new JLabel();

    InputStream inputImage = getClass().getResourceAsStream("/imagenes/contabilidad.jpg");
    ImageIcon icon;

    Boton cerrar = new Boton();
    Boton minimizar = new Boton();

    Tabla t = new Tabla();
    IngresarPanel ingresarPanel;

    public VentanaIngreso(boolean peps, boolean ueps, boolean cp) {
        if (peps) {
            ingresarPanel = new IngresarPanel(new PEPS(), t);
        } else if (ueps) {
            ingresarPanel = new IngresarPanel(new UEPS(), t);
        } else if (cp) {
            ingresarPanel = new IngresarPanel(new CostoP(), t);
        }

        this.setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setUndecorated(true);
        this.setBackground(new Color(0, 0, 0, 0));
        this.setResizable(false);

        setFondo();
        setMenu();

        setBotones();
        mostrarPaneles(ingresarPanel);

        this.setVisible(true);
    }

    private void setFondo() {
        fondo.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        fondo.setLayout(new BorderLayout());
        this.getContentPane().add(fondo);

        contenido.setLayout(null);
        contenido.setSize(new Dimension(600, 500));

        layeredContenido.setPreferredSize(new Dimension(600, 500));
        layeredContenido.setLayout(null);
        layeredContenido.add(contenido, Integer.valueOf(0));


        fondo.add(layered, BorderLayout.WEST);
        fondo.add(layeredContenido, BorderLayout.CENTER);
    }

    private void setMenu() {
        layered.setPreferredSize(new Dimension(200, 500));
        layered.setLayout(null);

        sideMenu.setLayout(new GridBagLayout());
        sideMenu.setBounds(0, 0, 200, 500);
        sideMenu.setOpaque(false);

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;

        JPanel ingresarP = new JPanel();
        ingresarP.setLayout(new GridBagLayout());
        ingresarP.setPreferredSize(panelesMenu);
        ingresarP.setOpaque(false);
        // panelMenu.setBackground(sideMenu.getBackground());
        JLabel label1 = new JLabel("Ingresar");
        label1.setFont(fuenteMenu);
        label1.setForeground(Color.white);
        ingresarP.add(label1);
        sideMenu.add(ingresarP, c);
        hooverPaneles(ingresarP, label1);

        c.gridy++;
        JPanel tablaP = new JPanel();
        tablaP.setLayout(new GridBagLayout());
        tablaP.setPreferredSize(panelesMenu);
        tablaP.setOpaque(false);
        tablaP.setBackground(sideMenu.getBackground());
        JLabel label = new JLabel("Tabla");
        label.setFont(fuenteMenu);
        label.setForeground(Color.white);
        tablaP.add(label);
        sideMenu.add(tablaP, c);
        hooverPaneles(tablaP, label);

        c.gridy++;
        JPanel gArchivo = new JPanel();
        gArchivo.setLayout(new GridBagLayout());
        gArchivo.setPreferredSize(panelesMenu);
        gArchivo.setOpaque(false);
        gArchivo.setBackground(sideMenu.getBackground());
        JLabel label2 = new JLabel("Guardar Archivo");
        label2.setFont(fuenteMenu);
        label2.setForeground(Color.white);
        gArchivo.add(label2);
        sideMenu.add(gArchivo, c);
        hooverPaneles(gArchivo, label2);

        c.gridy++;
        JPanel cArchivo = new JPanel();
        cArchivo.setLayout(new GridBagLayout());
        cArchivo.setPreferredSize(panelesMenu);
        cArchivo.setOpaque(false);
        cArchivo.setBackground(sideMenu.getBackground());
        JLabel label3 = new JLabel("Cargar Archivo");
        label3.setFont(fuenteMenu);
        label3.setForeground(Color.white);
        cArchivo.add(label3);
        sideMenu.add(cArchivo, c);
        hooverPaneles(cArchivo, label3);


        imagenMenu.setBounds(0, 0, 200, 500);
        try {
            icon = new ImageIcon(ImageIO.read(inputImage));
            imagenMenu.setIcon(icon);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        label1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mostrarPaneles(ingresarPanel);
            }
        });

        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mostrarTabla(t);
            }
        });


        tablaP.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mostrarTabla(t);
            }
        });
        ingresarP.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mostrarPaneles(ingresarPanel);
            }
        });

        label2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                t.guardarDatos();
            }
        });

        gArchivo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                t.guardarDatos();
            }
        });

        label3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                t.cargarArchivo();
            }
        });

        cArchivo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                t.cargarArchivo();
            }
        });
        layered.add(imagenMenu, Integer.valueOf(0));
        layered.add(sideMenu, Integer.valueOf(1));
    }

    private void mostrarPaneles(JPanel panel) {
        this.setSize(new Dimension(WIDTH, HEIGHT));
        panel.setSize(600, 500);
        panel.setLocation(0, 0);

        contenido.removeAll();
        contenido.add(panel);
        contenido.revalidate();
        contenido.repaint();
        cerrar.setLocation(565, 13);
        minimizar.setLocation(535, 13);
    }

    private void mostrarTabla(JPanel panel) {
        this.setSize(new Dimension(1000, 500));
        contenido.setSize(new Dimension(800, 500));
        panel.setSize(800, 500);
        panel.setLocation(0, 0);

        contenido.removeAll();
        contenido.add(panel);
        contenido.revalidate();
        contenido.repaint();

        cerrar.setLocation(765, 13);
        minimizar.setLocation(735, 13);
    }

    private void setBotones() {
        cerrar.setBounds(565, 13, 22, 22);
        cerrar.setBackground(Color.decode("#8E0E00"));

        minimizar.setBounds(535, 13, 22, 22);
        minimizar.setBackground(Color.decode("#B0B510"));

        cerrar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                System.exit(0);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                cerrar.setBackground(Color.red);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                cerrar.setBackground(Color.decode("#8E0E00"));
            }
        });

        minimizar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                minimizar();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                minimizar.setBackground(Color.yellow);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                minimizar.setBackground(Color.decode("#B0B510"));
            }
        });
        layeredContenido.add(minimizar, Integer.valueOf(1));
        layeredContenido.add(cerrar, Integer.valueOf(1));
    }

    private void minimizar() {
        this.setExtendedState(ICONIFIED);
    }

    private void hooverPaneles(JPanel panel, JLabel label) {
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                panel.setOpaque(true);
                panel.setBackground(new Color(0, 0, 0, 0.3f));
                panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                panel.setOpaque(false);
                panel.setBackground(new Color(0, 0, 0, 0));
                panel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

            }
        });

        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                panel.setOpaque(true);
                panel.setBackground(new Color(0, 0, 0, 0.3f));
                panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                panel.setOpaque(false);
                panel.setBackground(new Color(0, 0, 0, 0));
                panel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

            }
        });
    }
}
