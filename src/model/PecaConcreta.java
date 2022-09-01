package model;

import java.util.List;
import java.util.ArrayList;
import observer.ObservadorGeral;

/**
 *
 * @author brucevahldick
 */
public class PecaConcreta extends Peca {

    List<ObservadorGeral> observadores;

    public PecaConcreta(PecaBuilder pb) {
        this.pontosVitoria = pb.pontosVitoria;
        this.nome = pb.nome;
        this.modAtaque = pb.modAtaque;
        this.pontosVida = pb.pontosVida;
        this.movimento = pb.movimento;
        this.alcance = pb.alcance;
        this.estado = new PecaAtiva(this);

        observadores = new ArrayList<>();
    }

    @Override
    public String desenho() {
        String retorno = nome
                + "<br>PV: " + pontosVitoria
                + "<br>HP: " + pontosVida
                + "<br>Ataque: " + modAtaque
                + "<br>Alcance: " + alcance
                + "<br>Movimento: " + movimento;
        if (terreno != null) {
            retorno += "<br>Bonus de Terreno: " + terreno.getBonusTerreno();
        }
        return retorno;
    }

    @Override
    public void notificar() {
        for (ObservadorGeral observador : observadores) {
            observador.update();
        }
    }

    @Override
    public void addObservador(ObservadorGeral obs) {
        this.observadores.add(obs);
    }

    @Override
    public void removerObservador(ObservadorGeral obs) {
        this.observadores.remove(obs);
    }

    public static class PecaBuilder {

        private int pontosVitoria;
        private String nome;
        private int modAtaque;
        private int pontosVida;
        private int movimento;
        private int alcance;

        public void reset() {
            this.pontosVitoria = 0;
            this.nome = null;
            this.modAtaque = 0;
            this.pontosVida = 0;
            this.movimento = 0;
            this.alcance = 0;
        }

        public PecaBuilder pontosVitoria(int pontosVitoria) {
            this.pontosVitoria = pontosVitoria;
            return this;
        }

        public PecaBuilder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public PecaBuilder modAtaque(int modAtaque) {
            this.modAtaque = modAtaque;
            return this;
        }

        public PecaBuilder pontosVida(int pontosVida) {
            this.pontosVida = pontosVida;
            return this;
        }

        public PecaBuilder movimento(int movimento) {
            this.movimento = movimento;
            return this;
        }

        public PecaBuilder alcance(int alcance) {
            this.alcance = alcance;
            return this;
        }

        public PecaConcreta build() {
            return new PecaConcreta(this);
        }
    }
}
