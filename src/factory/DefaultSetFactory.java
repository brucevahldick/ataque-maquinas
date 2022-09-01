package factory;

import model.Terreno;
import java.util.HashMap;
import java.util.Map;
import model.Terreno.TerrenoBuilder;

/**
 *
 * @author brucevahldick
 */
public class DefaultSetFactory implements AbstractModuloFactory {

    private Map<Integer, Terreno> terrenos;

    public DefaultSetFactory() {
        terrenos = new HashMap<>();
        terrenos.put(Tipos.Pasto.getId(), new TerrenoBuilder().color(Tipos.Pasto.getCor()).idRef(Tipos.Pasto.getId()).build());
        terrenos.put(Tipos.Pantano.getId(), new TerrenoBuilder().bonusTerreno(-1).color(Tipos.Pantano.getCor()).idRef(Tipos.Pantano.getId()).build());
        terrenos.put(Tipos.Montanha.getId(), new TerrenoBuilder().color(Tipos.Montanha.getCor()).bonusTerreno(3).idRef(Tipos.Montanha.getId()).build());
        terrenos.put(Tipos.Floresta.getId(), new TerrenoBuilder().color(Tipos.Floresta.getCor()).bonusTerreno(1).idRef(Tipos.Floresta.getId()).build());
        terrenos.put(Tipos.Colina.getId(), new TerrenoBuilder().bonusTerreno(2).color(Tipos.Colina.getCor()).idRef(Tipos.Colina.getId()).build());
        terrenos.put(Tipos.Abismo.getId(), new TerrenoBuilder().bonusTerreno(-2).color(Tipos.Abismo.getCor()).idRef(Tipos.Abismo.getId()).build());
    }

    @Override
    public Terreno getTerreno(int chave) {
        return terrenos.get(chave);
    }
}
