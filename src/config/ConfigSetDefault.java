package config;

import factory.*;
import java.io.*;

/**
 *
 * @author brucevahldick
 */
public class ConfigSetDefault implements ConfigSet {

    private File mapa;
    private DefaultSetFactory factory;

    public ConfigSetDefault() {
        factory = new DefaultSetFactory();
    }
    
    @Override
    public AbstractModuloFactory getFactory() {
        return factory;
    }

    @Override
    public String toString() {
        return "Modulo Padrao";
    }

    @Override
    public ConfigSet setMapa(File mapa) {
        this.mapa = mapa;
        return this;
    }

    @Override
    public File getMapa() {
        return mapa;
    }
}
