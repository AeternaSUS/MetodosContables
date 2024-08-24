package backend.MetodosLogin;

import java.io.*;

public class ArbolBinario {

    private static class Nodo {
        Usuario usuario;
        Nodo izquierdo;
        Nodo derecho;

        public Nodo(Usuario usuario) {
            this.usuario = usuario;
        }
    }

    private Nodo raiz;

    // este metodo solo se usa cuando se quiere registrar
    public boolean existeNombre(String nombre) {
        return existeNombre(nombre, raiz);
    }

    private boolean existeNombre(String nombre, Nodo raiz) {
        if (raiz == null) {
            return false;
        } else if (nombre.equals(raiz.usuario.nombre())) {
            return true;
        } else if (nombre.compareTo(raiz.usuario.nombre()) > 0) {
            return existeNombre(nombre, raiz.derecho);
        } else {
            return existeNombre(nombre, raiz.izquierdo);
        }
    }

    public boolean verificar(String nombre, String contrasena) {
        return verificar(nombre, contrasena, raiz);
    }

    private boolean verificar(String nombre, String contrasena, Nodo raiz) {
        if (raiz == null) {
            return false;
        } else if (nombre.equals(raiz.usuario.nombre()) && contrasena.equals(raiz.usuario.contrasena())) {
            return true;
        } else if (nombre.compareTo(raiz.usuario.nombre()) > 0) {
            return verificar(nombre, contrasena, raiz.derecho);
        } else {
            return verificar(nombre, contrasena, raiz.izquierdo);
        }
    }

    public void insertar(Usuario usuario) {
        raiz = insertarAux(usuario, raiz);
    }

    private Nodo insertarAux(Usuario usuario, Nodo raiz) {
        if (raiz == null) {
            raiz = new Nodo(usuario);
        } else {
            if (usuario.compareTo(raiz.usuario) > 0) {
                raiz.derecho = insertarAux(usuario, raiz.derecho);
            } else if (usuario.compareTo(raiz.usuario) < 0) {
                raiz.izquierdo = insertarAux(usuario, raiz.izquierdo);
            }
        }
        return raiz;
    }

    public void cargarArchivo() {
        String directorio = System.getProperty("user.home");
        String nombreTxt = "Usuarios.txt";
        try {

            File f = new File(directorio + File.separator + nombreTxt);
            BufferedReader bf = new BufferedReader(new FileReader(f));
            String linea = bf.readLine();
            String[] separado;

            while (linea != null) {
                separado = linea.split(" ");
                insertar(new Usuario(separado[0], separado[1]));
                linea = bf.readLine();
            }
            bf.close();

        } catch (Exception e) {
            System.out.println("error al cargar archivo + " + e.getMessage());
        }
    }

    public void insertarTxt(String nombre, String contra) {
        String directorio = System.getProperty("user.home");
        String nombreTxt = "Usuarios.txt";
        try {
            File f = new File(directorio + File.separator + nombreTxt);
            FileWriter fw = new FileWriter(f, true);

            fw.write(nombre + " " + contra + "\n");
            fw.close();
        } catch (Exception e) {
            System.out.println("error al unsertar al txt " + e.getMessage());
        }
    }

    public String toString(char[] contrasena) {
        StringBuilder stringBuilder = new StringBuilder();

        for (char c : contrasena) {
            stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }
}
