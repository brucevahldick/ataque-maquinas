package model;

/**
 *
 * @author brucevahldick
 */
public abstract class PecaEstado {
    
    protected Peca peca;

    public PecaEstado(Peca peca) {
        this.peca = peca;
    }
    
    public boolean doAtacar(){
        return true;
    }
    
    public boolean mover(int quantidade){
        peca.setEstado(new PecaEmMovimento(peca));
        return peca.mover(quantidade);
    }
}
