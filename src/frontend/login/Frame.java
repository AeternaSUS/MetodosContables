package frontend.login;

import backend.MetodosLogin.ArbolBinario;
import backend.MetodosLogin.Usuario;
import frontend.menu.VentanaMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

public class Frame extends JFrame implements Runnable {


    final int WIDTH = 800;
    final int HEIGHT = 500;

    ArbolBinario a;
    Boton botonSalir = new Boton();
    Bordeado bordeado = new Bordeado();
    Degradado degradado;
    JLayeredPane layered;
    //Timer izquierdo, derecho;


    // variables para iniciar sesion
    JLabel entrar;
    JTextField ingresarUsuario, ingresarContra;

    // variables de creacion de cuenta
    JLabel registrar;
    JTextField crearNombre;
    JPasswordField crearContra;
    JPasswordField confirContra;
    Thread hilo;


    public Frame(ArbolBinario a) {
        this.a = a;
        this.setSize(WIDTH, HEIGHT);
        this.setUndecorated(true);
        this.setBackground(new Color(0, 0, 0, 0));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setFocusable(true);

        setFondo();
        bordeado.setDoubleBuffered(true);
        //degradado.setBounds(0, 0, this.getWidth() / 2, this.getHeight());

        eventosTextField();
        eventosBotones();

        degradado = new Degradado();
        layered.add(degradado, Integer.valueOf(1));

        hilo = new Thread(this);
        hilo.start();


        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void setFondo() {
        layered = new JLayeredPane();
        layered.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        layered.setLayout(null);
        this.getContentPane().add(layered);

        bordeado.setBounds(0, 0, getWidth(), getHeight());
        bordeado.setLayout(null);
        bordeado.setBackground(Color.white);

        layered.add(bordeado, Integer.valueOf(0));
        layered.add(botonSalir, Integer.valueOf(2));

        botonSalir.setBounds(15, 15, 25, 25);
        botonSalir.setBackground(Color.decode("#468847"));


        botonSalir.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                System.exit(0);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                botonSalir.setBackground(Color.decode("#f83600"));
                botonSalir.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botonSalir.setBackground(Color.decode("#468847"));
                botonSalir.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });


        registrarseFondo();
        iniciarSesionFondo();


    }

    private void iniciarSesionFondo() {
        JSeparator separador = new JSeparator();
        JSeparator separador2 = new JSeparator();
        Boton boton2 = new Boton();
        Boton botonEntrar = new Boton();

        JLabel l1 = new JLabel("Iniciar Sesion");
        l1.setFont(new Font("Arial", Font.BOLD, 24));
        l1.setBounds(450, 75, 200, 30);

        ingresarUsuario = new JTextField("Ingrese Usuario");
        ingresarUsuario.setFont(new Font("Arial", Font.PLAIN, 20));
        ingresarUsuario.setForeground(Color.gray);
        ingresarUsuario.setBounds(450, 135, 200, 30);
        ingresarUsuario.setBorder(null);

        separador.setBackground(Color.black);
        separador.setBounds(450, 165, 300, 2);

        ingresarContra = new JPasswordField("ingrese contraseña");
        ingresarContra.setFont(new Font("Arial", Font.PLAIN, 20));
        ingresarContra.setForeground(Color.gray);
        ingresarContra.setBounds(450, 195, 200, 30);
        ingresarContra.setBorder(null);

        separador2.setBackground(Color.black);
        separador2.setBounds(450, 225, 300, 2);

        entrar = new JLabel("Entrar");
        entrar.setFont(new Font("Arial", Font.BOLD, 18));

        botonEntrar.setLayout(new FlowLayout(FlowLayout.CENTER));
        botonEntrar.setBackground(Color.white);
        botonEntrar.add(entrar);
        botonEntrar.setBounds(550, 265, 100, 30);

        JLabel registro = new JLabel("No tengo cuenta");
        registro.setFont(new Font("Arial", Font.BOLD, 16));

        boton2.setLayout(new FlowLayout(FlowLayout.CENTER));
        boton2.add(registro);
        boton2.setBounds(500, 320, 200, 30);
        boton2.setBackground(Color.white);
        hooverBotones(entrar, botonEntrar);
        hooverBotones(registro, boton2);

        bordeado.add(botonEntrar);
        bordeado.add(separador2);
        bordeado.add(ingresarContra);
        bordeado.add(separador);
        bordeado.add(ingresarUsuario);
        bordeado.add(l1);
        bordeado.add(boton2);

        registro.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                //izquierdo.start();
                degradado.moverHaciaIz = true;
            }
        });
    }

    private void registrarseFondo() {
        JLabel crearCuenta = new JLabel("Crear Cuenta");
        crearCuenta.setFont(new Font("Arial", Font.BOLD, 24));
        crearCuenta.setBounds(50, 75, 200, 40);

        crearNombre = new JTextField("Cree un nombre");
        crearNombre.setFont(new Font("Arial", Font.PLAIN, 20));
        crearNombre.setBounds(50, 135, 300, 40);
        crearNombre.setForeground(Color.GRAY);
        crearNombre.setBorder(null);

        JSeparator separador = new JSeparator();
        separador.setBackground(Color.black);
        separador.setBounds(50, 175, 300, 5);

        crearContra = new JPasswordField("Ingrese contraseña");
        crearContra.setFont(new Font("Arial", Font.PLAIN, 20));
        crearContra.setForeground(Color.gray);
        crearContra.setBorder(null);
        crearContra.setBounds(50, 200, 300, 40);

        JSeparator separador2 = new JSeparator();
        separador2.setBackground(Color.black);
        separador2.setBounds(50, 240, 300, 5);

        JLabel confir = new JLabel("Confirmar contraseña");
        confir.setFont(new Font("Arial", Font.PLAIN, 20));
        confir.setBounds(50, 260, 300, 40);

        confirContra = new JPasswordField("Ingrese contraseña");
        confirContra.setFont(new Font("Arial", Font.PLAIN, 20));
        confirContra.setForeground(Color.gray);
        confirContra.setBorder(null);
        confirContra.setBounds(50, 300, 300, 40);

        JSeparator separador3 = new JSeparator();
        separador3.setBackground(Color.black);
        separador3.setBounds(50, 340, 300, 5);

        Boton boton = new Boton();
        boton.setBackground(Color.white);
        boton.setLayout(new FlowLayout(FlowLayout.CENTER));
        boton.setBounds(150, 375, 100, 30);

        registrar = new JLabel("Crear");
        registrar.setFont(new Font("Arial", Font.BOLD, 18));
        boton.add(registrar);
        hooverBotones(registrar, boton);

        Boton boton2 = new Boton();
        boton2.setBackground(Color.white);
        boton2.setLayout(new FlowLayout(FlowLayout.CENTER));
        boton2.setBounds(100, 420, 200, 30);

        JLabel log = new JLabel("Ya tienes cuenta?");
        log.setFont(new Font("Arial", Font.BOLD, 16));
        boton2.add(log);
        hooverBotones(log, boton2);

        bordeado.add(boton2);
        bordeado.add(boton);
        bordeado.add(separador3);
        bordeado.add(separador2);
        bordeado.add(confirContra);
        bordeado.add(confir);
        bordeado.add(crearContra);
        bordeado.add(separador);
        bordeado.add(crearNombre);
        bordeado.add(crearCuenta);

        log.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                //derecho.start();
                degradado.moverHaciaDe = true;
            }
        });
    }

    private void hooverBotones(JLabel text, JPanel panel) {

        text.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                text.setForeground(Color.white);
                panel.setBackground(Color.black);
                text.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                text.setForeground(Color.black);
                panel.setBackground(Color.white);
                text.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
    }

    private void eventosTextField() {

        ingresarUsuario.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                ingresarUsuario.setText("");
                ingresarUsuario.setForeground(Color.BLACK);
            }
        });

        ingresarContra.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                ingresarContra.setText("");
                ingresarContra.setForeground(Color.BLACK);
            }
        });

        ingresarContra.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    iniciarSesion();
                }
            }
        });

        crearNombre.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                crearNombre.setText("");
                crearNombre.setForeground(Color.BLACK);
            }
        });

        crearNombre.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {

                if (!crearNombre.getText().isEmpty()) {
                    char caracter = crearNombre.getText().charAt(0);

                    if (caracter == ' ' || Character.isDigit(caracter) || !Character.isLetter(caracter)) {
                        crearNombre.setText("");
                    }
                }
            }
        });

        crearContra.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                crearContra.setText("");
                crearContra.setForeground(Color.BLACK);
            }
        });
        crearContra.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                char[] contrasena = crearContra.getPassword();
                if (contrasena[0] == ' ') {
                    crearContra.setText("");
                }
            }
        });

        confirContra.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                confirContra.setText("");
                confirContra.setForeground(Color.BLACK);
            }
        });

        confirContra.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                char[] pass = confirContra.getPassword();
                char[] pass2 = crearContra.getPassword();

                if (!Arrays.equals(pass, pass2)) {
                    confirContra.setForeground(Color.red);
                } else {
                    confirContra.setForeground(Color.BLACK);
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    crearCuenta();
                }
            }
        });
    }

    private void eventosBotones() {
        entrar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                iniciarSesion();
            }
        });

        registrar.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                crearCuenta();
            }
        });
    }

    private void iniciarSesion() {
        if (a.verificar(ingresarUsuario.getText(), ingresarContra.getText())) {
            dispose();
            new VentanaMenu();
        } else {
            JOptionPane.showMessageDialog(null, "Credenciales incorrectas :c");
        }
    }

    private void crearCuenta() {
        final char[] pass = confirContra.getPassword();
        final char[] pass2 = crearContra.getPassword();
        if (crearNombre.getText().charAt(0) != ' ' &&
                !crearNombre.getText().isEmpty() &&
                !Arrays.toString(pass).isEmpty()) {

            if (a.existeNombre(crearNombre.getText())) {
                JOptionPane.showMessageDialog(null, "El nombre que creo ya esta en uso");
            } else {

                if (Arrays.equals(pass, pass2)) {
                    a.insertarTxt(crearNombre.getText(), a.toString(pass2));
                    a.insertar(new Usuario(crearNombre.getText(), a.toString(pass2)));
                    JOptionPane.showMessageDialog(null, "Usuario creado");
                    //derecho.start();
                }
            }

        } else {
            JOptionPane.showMessageDialog(null, "Por favor ingrese datos validos");
        }
    }

    public void moverDegradado() {
        if (degradado.moverHaciaIz) {
            degradado.crearCuentaMoverPanel();
        }
        if (degradado.moverHaciaDe) {
            degradado.iniciarSesionMoverPanel();
        }
    }

    @Override
    public void run() {
        double drawInterbal = 1_000_000_000 / 60D;
        double lastTime = System.nanoTime();
        double delta = 0;
        double currentTime;

        while (hilo != null) {

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterbal;
            lastTime = currentTime;

            if (delta >= 1) {
                moverDegradado();
                repaint();
                delta--;
            }
        }
    }
}
