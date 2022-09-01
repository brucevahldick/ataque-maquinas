package model;

import observer.ObservadorGeral;

/**
 *
 * @author brucevahldick
 */
public class Tile implements ObservadorGeral {

    private Terreno terreno;
    private Peca peca;

    public Tile(Terreno terreno) {
        this.terreno = terreno;
        this.peca = null;
    }

    public String render() {
        if (hasPeca()) {
            return peca.desenho();
        }
        return "";
    }

    public Terreno getTerreno() {
        return terreno;
    }

    public void setTerreno(Terreno terreno) {
        this.terreno = terreno;
    }

    public Peca getPeca() {
        return peca;
    }

    public void setPeca(Peca peca) {
        peca.setTerreno(terreno);
        peca.addObservador(this);
        this.peca = peca;
    }

    public boolean hasPeca() {
        return peca != null;
    }

    public Peca saiPeca() {
        Peca pecaSaindo = peca;
        peca.removerObservador(this);
        peca = null;
        return pecaSaindo;
    }

    @Override
    public void update() {
        setTerreno(peca.getTerreno());
    }
}
