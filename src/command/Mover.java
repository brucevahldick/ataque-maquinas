package command;

import model.Tile;

/**
 *
 * @author brucevahldick
 */
public class Mover implements Command {

    private Tile destino;
    private Tile partida;
    private int quantidade;

    public Mover(Tile destino, Tile partida, int quantidade) {
        this.destino = destino;
        this.partida = partida;
        this.quantidade = quantidade;
    }

    @Override
    public void executar() throws Exception {
        if (!partida.getPeca().mover(quantidade)) {
            throw new Exception("Esta peca nao consegue se mover tao longe");
        }

        destino.setPeca(partida.saiPeca());
    }

}
