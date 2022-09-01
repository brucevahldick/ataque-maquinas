package command;

import model.*;

/**
 *
 * @author brucevahldick
 */
public class EntraPeca implements Command {

    private Peca peca;
    private Tile tile;

    public EntraPeca(Peca peca, Tile tile) {
        this.peca = peca;
        this.tile = tile;
    }

    @Override
    public void executar() throws Exception {
        peca.setEstado(new PecaInativa(peca));
        tile.setPeca(peca);
    }

}
