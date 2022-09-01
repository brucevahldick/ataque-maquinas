package componentes;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author brucevahldick
 */
public class PainelInfoPeca extends JPanel {

    private JLabel pontosVitoria;
    private JLabel nome;
    private JLabel ataque;
    private JLabel pontosVida;
    private JLabel alcance;
    private JLabel movimento;
    private JLabel descricao;

    public PainelInfoPeca() {
        setLayout(new GridLayout(7, 1));
        
        pontosVitoria = new JLabel("Pontos de Vitoria: ", JLabel.CENTER);
        add(pontosVitoria);
        nome = new JLabel("Nome: ", JLabel.CENTER);
        add(nome);
        ataque = new JLabel("Ataque: ", JLabel.CENTER);
        add(ataque);
        pontosVida = new JLabel("Pontos de Vida: ", JLabel.CENTER);
        add(pontosVida);
        alcance = new JLabel("Alcance: ", JLabel.CENTER);
        add(alcance);
        movimento = new JLabel("Movimento: ", JLabel.CENTER);
        add(movimento);
        descricao = new JLabel("", JLabel.CENTER);
        add(descricao);
    }

    public void setPontosVitoria(String pontosVitoria) {
        this.pontosVitoria.setText("Pontos de Vitoria: " + pontosVitoria);
    }

    public void setNome(String nome) {
        this.nome.setText("Nome: " + nome);
    }

    public void setAtaque(String ataque) {
        this.ataque.setText("Ataque: " + ataque);
    }

    public void setPontosVida(String pontosVida) {
        this.pontosVida.setText("Pontos de Vida: " + pontosVida);
    }

    public void setAlcance(String alcance) {
        this.alcance.setText("Alcance: " + alcance);
    }

    public void setMovimento(String movimento) {
        this.movimento.setText("Movimento: " + movimento);
        
    }

    public void setDescricao(String descricao) {
        this.descricao.setText(descricao);
    }

}
