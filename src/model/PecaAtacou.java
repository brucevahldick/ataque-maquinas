package model;

/**
 *
 * @author brucevahldick
 */
public class PecaAtacou extends PecaEstado {

    public PecaAtacou(Peca peca) {
        super(peca);
    }

    @Override
    public boolean doAtacar() {
        return false;
    }

    @Override
    public boolean mover(int quantidade) {
        return false;
    }
}
