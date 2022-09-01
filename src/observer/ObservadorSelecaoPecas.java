package observer;

/**
 *
 * @author brucevahldick
 */
public interface ObservadorSelecaoPecas extends ObservadorGeral {

    void updateJogador(int idJogador, String peca);
}
