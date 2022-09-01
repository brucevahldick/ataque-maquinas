package config;

import java.util.List;
import java.util.ArrayList;
import model.Peca;

/**
 *
 * @author brucevahldick
 */
public class Config {

    private static Config instancia;
    private ConfigSet configSet;
    private List<Peca> jogadorUm;
    private List<Peca> jogadorDois;
    private int pontosVitoriaJogadorUm;
    private int pontosVitoriaJogadorDois;
    private int turno;

    private Config() {
        jogadorUm = new ArrayList<>();
        jogadorDois = new ArrayList<>();
        turno = 1;
    }

    public void addPecaJogador(Peca peca, int idJogador) {
        if (idJogador == 1) {
            jogadorUm.add(peca);
        } else {
            jogadorDois.add(peca);
        }
    }

    public List<Peca> getPecasJogador(int idJogador) {
        if (idJogador == 1) {
            return jogadorUm;
        }
        return jogadorDois;
    }

    public int getPontosVitoriaJogador(int idJogador) {
        if (idJogador == 1) {
            return pontosVitoriaJogadorUm;
        }

        return pontosVitoriaJogadorDois;
    }

    public void setPontosDeVitoriaJogador(int idJogador, int pontosVitoria) {
        if (idJogador == 1) {
            pontosVitoriaJogadorUm += pontosVitoria;
        } else {
            pontosVitoriaJogadorDois += pontosVitoria;
        }
    }

    public int somarPontosDeVitoriaJogador(int idJogador) {
        List<Peca> jogador = idJogador == 1 ? jogadorUm : jogadorDois;
        int soma = 0;
        for (Peca p : jogador) {
            soma += p.getPontosVitoria();
        }

        return soma;
    }

    public static Config getInstancia() {
        if (instancia == null) {
            instancia = new Config();
        }
        return instancia;
    }

    public ConfigSet getConfigSet() {
        return configSet;
    }

    public void setConfigSet(ConfigSet configSet) {
        this.configSet = configSet;
    }

    public String getAppTitle() {
        return "Ataque das Maquinas - Bruce Vahldick";
    }

    public String getColorJogadorUm() {
        return "#0905ed";
    }

    public String getColorJogadorDois() {
        return "#f5111d";
    }

    public void switchTurn() {
        turno = turno == 1 ? 2 : 1;
    }

    public int getTurno() {
        return turno;
    }
}
