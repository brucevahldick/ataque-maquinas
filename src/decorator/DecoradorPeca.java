package decorator;

import model.Peca;
import model.PecaAtacar;
import model.PecaEstado;
import model.Terreno;
import observer.ObservadorGeral;

/**
 *
 * @author brucevahldick
 */
public abstract class DecoradorPeca extends Peca {

    protected Peca peca;

    public DecoradorPeca(Peca peca) {
        this.peca = peca;
    }

    @Override
    public String getDescricao() {
        return peca.getDescricao();
    }
    
    @Override
    public int defender() {
        return peca.defender();
    }

    @Override
    public boolean atacar() {
        return peca.atacar();
    }

    @Override
    public void setAlcance(int alcance) {
        peca.setAlcance(alcance);
    }

    @Override
    public int getAlcance() {
        return peca.getAlcance();
    }

    @Override
    public void setMovimento(int movimento) {
        peca.setMovimento(movimento);
    }

    @Override
    public int getMovimento() {
        return peca.getMovimento();
    }

    @Override
    public void setPontosVida(int pontosVida) {
        peca.setPontosVida(pontosVida);
    }

    @Override
    public int getPontosVida() {
        return peca.getPontosVida();
    }

    @Override
    public void setModAtaque(int modAtaque) {
        peca.setModAtaque(modAtaque);
    }

    @Override
    public int getModAtaque() {
        return peca.getModAtaque();
    }

    @Override
    public void setPontosVitoria(int pontosVitoria) {
        peca.setPontosVitoria(pontosVitoria);
    }

    @Override
    public int getPontosVitoria() {
        return peca.getPontosVitoria();
    }

    @Override
    public void setNome(String nome) {
        peca.setNome(nome);
    }

    @Override
    public String getNome() {
        return peca.getNome();
    }

    @Override
    public void setTerreno(Terreno terreno) {
        super.setTerreno(terreno);
    }

    @Override
    public Terreno getTerreno() {
        return peca.getTerreno();
    }
    
    
    @Override
    public String desenho() {
        return peca.desenho();
    }
    
    @Override
    public void addObservador(ObservadorGeral obs) {
        peca.addObservador(obs);
    }

    @Override
    public void removerObservador(ObservadorGeral obs) {
        peca.removerObservador(obs);
    }

    @Override
    public void notificar() {
        peca.notificar();
    }
    
    @Override
    public void visitar(Peca peca) {
        this.peca.visitar(peca);
    }

    @Override
    public void accept(PecaAtacar ataque) {
        peca.accept(ataque);
    }

    @Override
    public boolean mover(int quantidade) {
        return peca.mover(quantidade);
    }

    @Override
    public void setJogador(int jogador) {
        peca.setJogador(jogador);
    }

    @Override
    public int getJogador() {
        return peca.getJogador();
    }

    @Override
    public void setEstado(PecaEstado estado) {
        peca.setEstado(estado);
    }
    
    
}
