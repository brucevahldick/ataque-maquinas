package command;

import config.Config;
import model.*;

/**
 *
 * @author brucevahldick
 */
public class Atacar implements Command {

    private Tile atacante;
    private Tile defensora;
    private int alcance;

    public Atacar(Tile atacante, Tile defensora, int alcance) {
        this.atacante = atacante;
        this.defensora = defensora;
        this.alcance = alcance;
    }

    @Override
    public void executar() throws Exception {
        if (!atacante.getPeca().atacar()) {
            throw new Exception("Esta peca ja atacou");
        }

        if (alcance > atacante.getPeca().getAlcance()) {
            throw new Exception("O alvo esta fora do alcance");
        }

        defensora.getPeca().accept(atacante.getPeca());
        
        if (defensora.getPeca().getPontosVida() <= 0) {
            Config.getInstancia().setPontosDeVitoriaJogador(Config.getInstancia().getTurno(), defensora.getPeca().getPontosVitoria());
            defensora.saiPeca();
        }

        if (atacante.getPeca().getPontosVida() <= 0) {
            int jogador = Config.getInstancia().getTurno() == 1 ? 1 : 2;
            Config.getInstancia().setPontosDeVitoriaJogador(jogador, atacante.getPeca().getPontosVitoria());
            atacante.saiPeca();
        }
    }

}
