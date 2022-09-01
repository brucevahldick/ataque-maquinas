package factory;

import model.Terreno;

/**
 *
 * @author brucevahldick
 */
public interface AbstractModuloFactory {
    public abstract Terreno getTerreno(int chave);
}