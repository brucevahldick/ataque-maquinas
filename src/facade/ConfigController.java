package facade;

import observer.Subject;
import config.*;
import java.util.List;
import java.util.ArrayList;
import observer.ObservadorGeral;

/**
 *
 * @author brucevahldick
 */
public class ConfigController implements Subject {

    private List<ObservadorGeral> observadores;

    public ConfigController() {
        observadores = new ArrayList<>();
    }
    
    public void setConfiguracao(ConfigSet config){
        Config.getInstancia().setConfigSet(config);
        notificar();
    }
    
    @Override
    public void addObservador(ObservadorGeral obs) {
        observadores.add(obs);
    }

    @Override
    public void removerObservador(ObservadorGeral obs) {
        observadores.remove(obs);
    }

    @Override
    public void notificar() {
        for(ObservadorGeral o : observadores){
            o.update();
        }
    }
}
