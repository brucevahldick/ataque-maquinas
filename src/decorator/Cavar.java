package decorator;

import config.Config;
import model.Peca;

/**
 *
 * @author brucevahldick
 */
public class Cavar extends DecoradorPeca {

    public Cavar(Peca peca) {
        super(peca);
    }

    @Override
    public boolean atacar() {
        if (getTerreno().getIdRef() > 1) {
            peca.setTerreno(Config.getInstancia().getConfigSet().getFactory().getTerreno(getTerreno().getIdRef() - 1));
        }
        return super.atacar();
    }

    @Override
    public String getDescricao() {
        return super.getDescricao() + "Quando esta peca ataca, ela diminui a altura de seu terreno em 1. ";
    }
}
