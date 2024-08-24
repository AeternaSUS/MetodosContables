package frontend.login;

import javax.swing.*;
import java.awt.*;

public class Degradado extends JPanel {

    // variables del fondo naranja
    JLabel saludo, crear;
    JLabel instruccion, instruccion2;

    int velocidadX = 14;
    int x;
    float alphaIn;
    float alphaOut = 1;
    float velocidadFade = 0.04f;
    int saludoY = 200;

    boolean moverHaciaIz = false;
    boolean moverHaciaDe = false;

    public Degradado() {
        this.setOpaque(false);
        this.setSize(new Dimension(400, 500));
        this.setDoubleBuffered(true);
        setPanelNaranja();
    }


    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gradientPaint = new GradientPaint(0, 0, Color.decode("#48a259"), getWidth(), getHeight(), Color.white);
        g2.setPaint(gradientPaint);
        // g2.setColor(Color.decode("#70b578"));
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
        super.paintComponent(g);
    }

    private void setPanelNaranja() {

        this.setLayout(null);

        Fuente f = new Fuente();
        Font forte = f.cargarFuente("fuentes/Forte.otf");
        forte = forte.deriveFont(Font.PLAIN, 24f);

        Font kristen = f.cargarFuente("fuentes/Kristen ITC.TTF");
        kristen = kristen.deriveFont(Font.PLAIN,16f);


        saludo = new JLabel("Hola, Bienvenido!");
        saludo.setFont(forte);
        saludo.setForeground(Color.BLACK);
        saludo.setBounds((this.getWidth() / 2) - 100, saludoY, 240, 40);

        instruccion = new JLabel("Por favor ingresa tus datos");
        instruccion.setFont(kristen);
        instruccion.setBounds((this.getWidth() / 2) - 105, saludoY + 50, 250, 40);
        instruccion.setForeground(Color.BLACK);

        crear = new JLabel("Tu viaje comienza aqui");
        crear.setFont(forte);
        crear.setForeground(new Color(0, 0, 0, 0));
        crear.setBounds(60, saludoY, 330, 40);

        instruccion2 = new JLabel("Crea tu cuenta para descubir mas");
        instruccion2.setFont(kristen);
        instruccion2.setBounds((this.getWidth() / 2) - 135, saludoY + 50, 300, 40);
        instruccion2.setForeground(new Color(0, 0, 0, 0));

        this.add(saludo);
        this.add(instruccion);
        this.add(crear);
        this.add(instruccion2);

    }

    public void crearCuentaMoverPanel() {
        moverIzquierdo();
        if (moverHaciaIz) {
            fadeOutSaludo();
            fadeInCrear();
        }
    }

    public void iniciarSesionMoverPanel() {
        moverDerecho();
        if (moverHaciaDe) {
            fadeOutCrear();
            fadeInSaludo();
        }
    }


    private void fadeInSaludo() {
        alphaIn += velocidadFade;

        if (alphaIn >= 1) {
            alphaIn = 1;
        }
        instruccion.setForeground(new Color(0, 0, 0, alphaIn));
        saludo.setForeground(new Color(0, 0, 0, alphaIn));
    }


    private void fadeOutSaludo() {
        alphaOut -= velocidadFade;

        if (alphaOut <= 0) {
            alphaOut = 0;
        }
        instruccion.setForeground(new Color(0, 0, 0, alphaOut));
        saludo.setForeground(new Color(0, 0, 0, alphaOut));
    }

    private void fadeInCrear() {
        alphaIn += velocidadFade;

        if (alphaIn >= 1) {
            alphaIn = 1;
        }
        instruccion2.setForeground(new Color(0, 0, 0, alphaIn));
        crear.setForeground(new Color(0, 0, 0, alphaIn));
    }

    private void fadeOutCrear() {
        alphaOut -= velocidadFade;

        if (alphaOut <= 0) {
            alphaOut = 0;
        }

        instruccion2.setForeground(new Color(0, 0, 0, alphaOut));
        crear.setForeground(new Color(0, 0, 0, alphaOut));
    }

    private void moverIzquierdo() {
        this.setLocation(x, 0);
        x += velocidadX;
        if (this.getX() >= 400) {
            this.setLocation(400, 0);
            moverHaciaIz = false;
            alphaOut = 1;
            alphaIn = 0;
        }

    }

    private void moverDerecho() {
        this.setLocation(x, 0);
        x -= velocidadX;

        if (this.getX() <= 0) {
            this.setLocation(0, 0);
            moverHaciaDe = false;
            alphaOut = 1;
            alphaIn = 0;
        }

    }

}
