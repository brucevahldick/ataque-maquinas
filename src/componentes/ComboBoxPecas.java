package componentes;

import javax.swing.*;
import observer.*;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author brucevahldick
 */
public class ComboBoxPecas extends JComboBox<String> implements Subject {

    private List<ObservadorGeral> observadores;
    
    public ComboBoxPecas() {
        observadores = new ArrayList<>();
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

