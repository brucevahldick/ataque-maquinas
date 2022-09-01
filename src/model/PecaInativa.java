package model;

/**
 *
 * @author brucevahldick
 */
public class PecaInativa extends PecaEstado {

    public PecaInativa(Peca peca) {
        super(peca);
    }

    @Override
    public boolean mover(int quantidade) {
        return false;
    }

    @Override
    public boolean doAtacar() {
        return false;
    }
    
    
}
