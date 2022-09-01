package factory;

import decorator.*;
import java.util.Map;
import java.util.HashMap;
import model.Peca;
import model.PecaConcreta.PecaBuilder;

/**
 *
 * @author brucevahldick
 */
public class PecaFactory {
    
    private Map<Integer, Peca> pecas;

    public PecaFactory() {
        pecas = new HashMap<>();
        pecas.put(Pecas.Burrower.getId(), new PecaBuilder().pontosVitoria(1).nome(Pecas.Burrower.getNome()).alcance(1).pontosVida(4).movimento(2).modAtaque(2).build());
        pecas.put(Pecas.Grazer.getId(), new PecaBuilder().alcance(1).pontosVitoria(1).nome(Pecas.Grazer.getNome()).pontosVida(4).movimento(2).modAtaque(1).build());
        pecas.put(Pecas.Leaplasher.getId(), new PecaBuilder().pontosVida(3).movimento(4).alcance(1).modAtaque(1).nome(Pecas.Leaplasher.getNome()).pontosVitoria(1).build());
        pecas.put(Pecas.Scrapper.getId(), new PecaBuilder().pontosVitoria(2).pontosVida(4).movimento(2).alcance(2).modAtaque(3).nome(Pecas.Scrapper.getNome()).build());
        pecas.put(Pecas.Scrounger.getId(), new PecaBuilder().pontosVitoria(1).nome(Pecas.Scrounger.getNome()).pontosVida(5).movimento(3).alcance(1).modAtaque(2).build());
        pecas.put(Pecas.Spikesnout.getId(), new PecaBuilder().nome(Pecas.Spikesnout.getNome()).pontosVida(5).pontosVitoria(1).modAtaque(2).movimento(2).alcance(1).build());
        pecas.put(Pecas.Tracker.getId(), new Cavar(new PecaBuilder().nome(Pecas.Tracker.getNome()).pontosVida(4).movimento(2).pontosVitoria(2).modAtaque(2).alcance(1).build()));
        pecas.put(Pecas.Bellowback.getId(), new PecaBuilder().nome(Pecas.Bellowback.getNome()).pontosVida(7).movimento(2).alcance(2).modAtaque(3).pontosVitoria(3).build());
        pecas.put(Pecas.Bristleback.getId(), new PecaBuilder().nome(Pecas.Bristleback.getNome()).pontosVida(4).pontosVitoria(2).alcance(1).movimento(3).modAtaque(2).build());
        pecas.put(Pecas.Charger.getId(), new Galopar(new PecaBuilder().pontosVida(4).movimento(2).modAtaque(2).pontosVitoria(2).nome(Pecas.Charger.getNome()).alcance(2).build()));
    }
    
    public Peca getPeca(int chave){
        return pecas.get(chave);
    }

    public Map<Integer, Peca> getPecas() {
        return pecas;
    }
    
    public enum Pecas {
        Burrower(1, "Burrower"), Grazer(2, "Grazer"), Leaplasher(3, "Leaplasher"), Scrapper(4, "Scrapper"), Scrounger(5, "Scrounger"),
        Spikesnout(6, "Spikesnout"), Tracker(7, "Tracker"), Bellowback(8, "Bellowback"), Bristleback(9, "Bristleback"), Charger(10, "Charger");
        
        int id;
        String nome;
        
        Pecas(int id, String nome){
            this.id = id;
            this.nome = nome;
        }

        public int getId() {
            return id;
        }

        public String getNome() {
            return nome;
        }
    }
}
