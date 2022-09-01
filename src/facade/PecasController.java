package facade;

import config.Config;
import observer.Subject;
import factory.PecaFactory;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import model.Peca;
import observer.ObservadorSelecaoPecas;
import observer.ObservadorGeral;

/**
 *
 * @author brucevahldick
 */
public class PecasController implements Subject {

    private List<ObservadorSelecaoPecas> observadores;

    public PecasController() {
        observadores = new ArrayList<>();
    }

    public Map<Integer, Peca> getPecasDisponiveis() {
        return new PecaFactory().getPecas();
    }

    public List<Peca> requestPecas(int idJogador) {
        return Config.getInstancia().getPecasJogador(idJogador);
    }

    @Override
    public void addObservador(ObservadorGeral obs) {
        observadores.add((ObservadorSelecaoPecas) obs);
    }

    @Override
    public void removerObservador(ObservadorGeral obs) {
        observadores.remove((ObservadorSelecaoPecas) obs);
    }

    @Override
    public void notificar() {
        for (ObservadorGeral o : observadores) {
            o.update();
        }
    }

    public void notificarEntraPeca(int idJogador, String peca) {
        for (ObservadorSelecaoPecas o : observadores) {
            o.updateJogador(idJogador, peca);
        }
    }

    public void renderMapa() {
        notificar();
    }

    public void adicionarPecaJogador(int idPeca, int idJogador) throws Exception {
        PecaFactory factory = new PecaFactory();
        Peca peca = factory.getPeca(idPeca);
        int somaPontosVitoria = Config.getInstancia().somarPontosDeVitoriaJogador(idJogador);

        if (somaPontosVitoria + peca.getPontosVitoria() <= 10) {
            Config.getInstancia().addPecaJogador(peca, idJogador);
            peca.setJogador(idJogador);
        } else {
            throw new Exception("Jogador atualmente possui "
                    + somaPontosVitoria
                    + " pontos de vitoria, a peca "
                    + peca.getNome()
                    + ", que oferece "
                    + peca.getPontosVitoria()
                    + " pontos de vitoria, nao pode entrar");
        }
        notificarEntraPeca(idJogador, peca.getNome());
    }
}
