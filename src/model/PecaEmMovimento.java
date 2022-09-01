package model;

/**
 *
 * @author brucevahldick
 */
public class PecaEmMovimento extends PecaEstado {

    private int totalMovimento;

    public PecaEmMovimento(Peca peca) {
        super(peca);
    }

    @Override
    public boolean mover(int quantidade) {
        if (quantidade > peca.movimento - totalMovimento) {
            return false;
        } else {
            totalMovimento += quantidade;
            return true;
        }
    }
}
