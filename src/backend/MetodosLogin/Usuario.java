package backend.MetodosLogin;

public record Usuario(String nombre, String contrasena) implements Comparable<Usuario> {


    @Override
    public int compareTo(Usuario o) {
        return Integer.compare(this.nombre.compareTo(o.nombre), 0);
    }

}
