package config;

import factory.AbstractModuloFactory;
import java.io.*;

/**
 *
 * @author brucevahldick
 */
public interface ConfigSet {
    AbstractModuloFactory getFactory();
    ConfigSet setMapa(File mapa);
    File getMapa();
}
