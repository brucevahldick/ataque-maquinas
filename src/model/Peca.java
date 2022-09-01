package model;

import observer.Subject;

/**
 *
 * @author brucevahldick
 */
public abstract class Peca implements Subject, PecaAtacar, PecaSerAtacada {
    
    protected PecaEstado estado;
    protected int pontosVitoria;
    protected String nome;
    protected int modAtaque;
    protected int pontosVida;
    protected int movimento;
    protected int alcance;
    protected Terreno terreno;
    protected int jogador;
    
    public abstract String desenho();

    public void setEstado(PecaEstado estado) {
        this.estado = estado;
    }
    
    public String getDescricao(){
        return "";
    }
    
    public Terreno getTerreno() {
        return terreno;
    }

    public void setTerreno(Terreno terreno) {
        this.terreno = terreno;
        notificar();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPontosVitoria() {
        return pontosVitoria;
    }

    public void setPontosVitoria(int pontosVitoria) {
        this.pontosVitoria = pontosVitoria;
    }

    public int getModAtaque() {
        return modAtaque;
    }

    public void setModAtaque(int modAtaque) {
        this.modAtaque = modAtaque;
    }

    public int getPontosVida() {
        return pontosVida;
    }

    public void setPontosVida(int pontosVida) {
        this.pontosVida = pontosVida;
    }

    public int getMovimento() {
        return movimento;
    }

    public void setMovimento(int movimento) {
        this.movimento = movimento;
    }

    public int getAlcance() {
        return alcance;
    }

    public void setAlcance(int alcance) {
        this.alcance = alcance;
    }

    public int getJogador() {
        return jogador;
    }

    public void setJogador(int jogador) {
        this.jogador = jogador;
    }
    
    public boolean atacar(){
        return this.estado.doAtacar();
    }
    
   public int defender(){
       return terreno.getBonusTerreno();
   }
   
   public boolean mover(int quantidade){
       return this.estado.mover(quantidade);
   }
   
   @Override
    public void visitar(Peca peca) {
        peca.setPontosVida(peca.getPontosVida() - this.modAtaque + terreno.getBonusTerreno());
        pontosVida -= peca.defender();
        estado = new PecaAtacou(this);
    }

    @Override
    public void accept(PecaAtacar ataque) {
        ataque.visitar(this);
    }
}
