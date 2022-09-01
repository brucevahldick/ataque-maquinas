package componentes;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author brucevahldick
 */
public class BotaoPeca extends JButton {

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void setText(String text) {
        super.setText("<html><body>" + text + "</body></html>");
    }
}
