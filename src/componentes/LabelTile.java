package componentes;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author brucevahldick
 */
public class LabelTile extends JLabel {

    private int linha;
    private int coluna;

    public LabelTile(String string) {
        super(string);
    }

    public int getLinha() {
        return linha;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }

    public int getColuna() {
        return coluna;
    }

    public void setColuna(int coluna) {
        this.coluna = coluna;
    }

    @Override
    public void setText(String text) {
        super.setText("<html><body>" + text + "</body></html>");
    }
}
