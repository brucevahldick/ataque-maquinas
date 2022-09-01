package componentes;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author brucevahldick
 */
public class PainelPecasJogador extends JPanel {

    private JLabel mainLabel;
    private JLabel pecas;
    private String nomePecas;

    public PainelPecasJogador() {
        setLayout(new BorderLayout());
        mainLabel = new JLabel();
        add(mainLabel, BorderLayout.NORTH);
        nomePecas = "";
        pecas = new JLabel("<html><body></body></html>");
        add(pecas, BorderLayout.CENTER);
    }

    public void setMainLabel(String text) {
        mainLabel.setText(text);
    }

    public void addPeca(String peca) {
        nomePecas += "<p>" + peca + "</p>";
        pecas.setText("<html><body>" + nomePecas + "</body></html>");
    }
}
