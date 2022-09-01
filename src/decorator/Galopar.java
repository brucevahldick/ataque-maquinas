package decorator;

import factory.Tipos;
import model.Peca;

/**
 *
 * @author brucevahldick
 */
public class Galopar extends DecoradorPeca {

    public Galopar(Peca peca) {
        super(peca);
    }

    @Override
    public int getModAtaque() {
        int ataque = super.getModAtaque();
        if (checkTerreno()) {
            ataque += 1;
        }
        return ataque;
    }

    private boolean checkTerreno() {
        if (terreno != null) {
            return this.terreno.getColor().equals(Tipos.Pasto.getCor());
        } else {
            return false;
        }
    }

    @Override
    public String getDescricao() {
        return super.getDescricao() + "Esta peca ganha um bonus de +1 de modificador de ataque quando esta em terren de pasto. ";
    }
}
