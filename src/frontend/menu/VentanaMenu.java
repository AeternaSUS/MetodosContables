package frontend.menu;

import frontend.login.Boton;
import frontend.ventanas_metodos.VentanaIngreso;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.InputStream;

public class VentanaMenu extends JFrame {

    InputStream imageStream;
    Boton salir = new Boton();
    Boton minimizar = new Boton();
    JPanel fondo = new JPanel();
    JPanel PEPS = new JPanel();
    JPanel UEPS = new JPanel();
    JPanel CP = new JPanel();
    JLayeredPane layered = new JLayeredPane();

    public VentanaMenu() {

        this.setSize(1100, 700);
        this.setUndecorated(true);
        this.setBackground(new Color(0, 0, 0, 0));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        layered.setSize(this.getWidth(), this.getHeight());
        layered.setLayout(null);
        this.getContentPane().add(layered);

        fondo.setSize(this.getWidth(), this.getHeight());
        fondo.setBackground(Color.white);
        fondo.setLayout(new BorderLayout());
        layered.add(fondo, Integer.valueOf(0));

        panelPEPS();
        panelCP();
        panelUEPS();
        botonSalir();
        botonMinimizar();

        this.setVisible(true);


    }

    public void botonSalir() {
        salir.setBounds(20, 20, 25, 25);
        salir.setBackground(Color.decode("#8E0E00"));
        layered.add(salir, Integer.valueOf(1));

        salir.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                salir.setBackground(Color.red);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                salir.setBackground(Color.decode("#8E0E00"));
            }
        });


    }

    public void minimizar() {
        this.setExtendedState(ICONIFIED);
    }

    public void botonMinimizar() {
        minimizar.setBounds(55, 20, 25, 25);
        minimizar.setBackground(Color.decode("#ffc500"));
        layered.add(minimizar, Integer.valueOf(1));

        minimizar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                minimizar();
            }


            @Override
            public void mouseEntered(MouseEvent e) {
                minimizar.setBackground(Color.yellow);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                minimizar.setBackground(Color.decode("#ffc500"));
            }
        });

    }

    public void eventos(JPanel panel) {
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {

                panel.setBackground(Color.decode("#212121"));
                panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {

                panel.setBackground(Color.decode("#171717"));
                panel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
    }

    public void panelPEPS() {
        imageStream = getClass().getClassLoader().getResourceAsStream("imagenes/imagen1.png");


        try {
            assert imageStream != null;
            ImageIcon imagen1 = new ImageIcon(ImageIO.read(imageStream));


            PEPS.setBackground(Color.decode("#171717"));
            PEPS.setPreferredSize(new Dimension(this.getWidth() / 3, this.getHeight()));
            PEPS.setLayout(null);

            JLabel TP = new JLabel("<html>Metodo PEPS</html>");
            TP.setBounds(140, 200, 100, 60);
            TP.setFont(new Font("Arial", Font.BOLD, 26));
            TP.setForeground(Color.white);
            PEPS.add(TP);

            JLabel IP = new JLabel();
            IP.setBounds(0, this.getHeight() - 378, 400, 400);
            IP.setIcon(imagen1);
            PEPS.add(IP);


            eventos(PEPS);
            fondo.add(PEPS, BorderLayout.WEST);
            PEPS.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    disposeActual();
                    new VentanaIngreso(true, false, false);

                }
            });
        } catch (Exception e) {
            System.out.println("Error al cargar la imagen");


        }
    }

    public void panelUEPS() {
        imageStream = getClass().getClassLoader().getResourceAsStream("imagenes/imagen2.png");

        try {

            assert imageStream != null;
            ImageIcon imagen2 = new ImageIcon(ImageIO.read(imageStream));

            UEPS.setBackground(Color.decode("#171717"));
            UEPS.setPreferredSize(new Dimension(this.getWidth() / 3, this.getHeight()));
            UEPS.setLayout(null);

            JLabel TP = new JLabel("<html>Metodo UEPS</html>");
            TP.setBounds(140, 200, 100, 60);
            TP.setFont(new Font("Arial", Font.BOLD, 26));
            TP.setForeground(Color.white);
            UEPS.add(TP);


            JLabel IP = new JLabel();
            IP.setBounds(0, this.getHeight() - 378, 378, 378);
            IP.setIcon(imagen2);
            UEPS.add(IP);

            crearBorde(UEPS);
            eventos(UEPS);

            UEPS.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    disposeActual();
                    new VentanaIngreso(false, true, false);
                }
            });
            fondo.add(UEPS, BorderLayout.CENTER);
        } catch (Exception e) {
            System.out.println("Error al carga imagen 2");
        }

    }

    public void panelCP() {
        imageStream = getClass().getClassLoader().getResourceAsStream("imagenes/imagen3.png");

        try {

            assert imageStream != null;
            ImageIcon imagen2 = new ImageIcon(ImageIO.read(imageStream));

            CP.setBackground(Color.decode("#171717"));
            CP.setPreferredSize(new Dimension(this.getWidth() / 3, this.getHeight()));
            CP.setLayout(null);

            JLabel TP = new JLabel("<html>Costo Promedio</html>");
            TP.setBounds(100, 200, 250, 60);
            TP.setFont(new Font("Arial", Font.BOLD, 26));
            TP.setForeground(Color.white);
            CP.add(TP);


            JLabel IP = new JLabel();
            IP.setBounds(0, this.getHeight() - 390, 378, 378);
            IP.setIcon(imagen2);
            CP.add(IP);

            eventos(CP);
            fondo.add(CP, BorderLayout.EAST);

            CP.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    disposeActual();
                    new VentanaIngreso(false, false, true);
                }
            });
        } catch (Exception e) {
            System.out.println("Error al cargar imagen 3");
        }

    }

    public void crearBorde(JPanel panel) {
        Border borde = BorderFactory.createLineBorder(Color.BLACK, 2);
        panel.setBorder(borde);
    }

    public void disposeActual() {
        this.dispose();
    }

}
