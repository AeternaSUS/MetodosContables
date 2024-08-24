package frontend.login;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class Fuente {

    public Font cargarFuente(String path) {
        InputStream input = getClass().getClassLoader().getResourceAsStream(path);
        if (input != null) {
            try {
                return Font.createFont(Font.TRUETYPE_FONT, input);
            } catch (FontFormatException | IOException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }
}
