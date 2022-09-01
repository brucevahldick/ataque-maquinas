package observer;

import observer.ObservadorGeral;

/**
 *
 * @author brucevahldick
 */
public interface Subject {
    void addObservador(ObservadorGeral obs);

    void removerObservador(ObservadorGeral obs);
    
    void notificar();
}
