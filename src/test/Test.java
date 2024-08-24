package test;

import backend.MetodosLogin.ArbolBinario;
import frontend.login.Frame;

public class Test {
    public static void main(String[] args) {

        ArbolBinario a = new ArbolBinario();
        a.cargarArchivo();
        new Frame(a);
    }
}
