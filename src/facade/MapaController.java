package facade;

import observer.Subject;
import config.Config;
import java.io.*;
import factory.AbstractModuloFactory;
import java.util.List;
import java.util.ArrayList;
import model.*;
import command.*;
import observer.ObservadorGeral;

/**
 *
 * @author brucevahldick
 */
public class MapaController implements Subject {

    private List<ObservadorGeral> observadores;
    private Mapa mapa;
    private PecaEmJogo holdPeca;
    private int holdTileX;
    private int holdTileY;
    private CommandInvoker command;
    private List<PecaEmJogo> todasAsPecasJogando;

    public class PecaEmJogo {

        private Peca peca;
        private int id;

        public PecaEmJogo(int id, Peca peca) {
            this.peca = peca;
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public Peca getPeca() {
            return peca;
        }
    }

    public MapaController() {
        holdTileX = 9;
        holdTileY = 9;
        observadores = new ArrayList<>();
        command = new CommandInvoker();
        initTodasAsPecasJogando();
    }

    private void initTodasAsPecasJogando() {
        todasAsPecasJogando = new ArrayList<>();
        int contador = 0;
        for (int i = 1; i <= 2; i++) {
            for (Peca peca : Config.getInstancia().getPecasJogador(i)) {
                todasAsPecasJogando.add(new PecaEmJogo(contador, peca));
                contador++;
            }
        }
    }

    private void getMapaFromConfig() {
        AbstractModuloFactory tf = Config.getInstancia().getConfigSet().getFactory();
        mapa = new Mapa();
        try {
            BufferedReader br = new BufferedReader(new FileReader(Config.getInstancia().getConfigSet().getMapa()));
            String ln;
            int contLinhas = 0;
            while ((ln = br.readLine()) != null && contLinhas < 8) {
                for (int i = 0; i < 8; i++) {
                    Tile tile = new Tile(tf.getTerreno(Character.getNumericValue(ln.charAt(i))));
                    if (tile != null) {
                        mapa.posicionarTile(contLinhas, i, tile);
                    }
                }

                contLinhas++;
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Mapa abrirMapa() {
        if (mapa == null) {
            getMapaFromConfig();
        }
        return mapa;
    }

    public void entraPecaDeFora(int idPeca) throws Exception {
        if (todasAsPecasJogando.get(idPeca).getPeca().getJogador() != Config.getInstancia().getTurno()) {
            throw new Exception("Esta peca nao e sua!");
        }
        holdPeca = todasAsPecasJogando.get(idPeca);
    }

    public boolean hasHoldPeca() {
        return holdPeca != null;
    }

    public PecaEmJogo getHoldPeca() {
        return holdPeca;
    }

    public void clicaTile(int x, int y) throws Exception {
        if (holdTileX != 9 || holdPeca != null) {
            interagirComTile(x, y);
        } else {
            segurarTile(x, y);
        }
    }

    public List<PecaEmJogo> getTodasAsPecasJogando() {
        return todasAsPecasJogando;
    }

    private void segurarTile(int x, int y) {
        Tile tile = mapa.getTile(x, y);
        if (tile.hasPeca()) {
            if (tile.getPeca().getJogador() == Config.getInstancia().getTurno()) {
                holdTileX = x;
                holdTileY = y;
            }
        }
    }

    private void interagirComTile(int x, int y) throws Exception {
        if (holdPeca != null && !mapa.getTile(x, y).hasPeca()) {
            entraPeca(x, y);
        } else {
            if (mapa.getTile(x, y).hasPeca()) {
                atacar(x, y);
            } else {
                mover(x, y);
            }
        }
        notificar();
        holdTileX = 9;
        holdPeca = null;
    }

    private void entraPeca(int x, int y) throws Exception {
        if (Config.getInstancia().getTurno() == 1) {
            if (x != 0 && x != 1) {
                throw new Exception("Voce pode apenas posicionar suas pecas inicias nas linhas 0 e 1!");
            }
        } else {
            if (x != 6 && x != 7) {
                throw new Exception("Voce pode apenas posicionar suas pecas inicias nas linhas 6 e 7!");
            }
        }

        command.executar(new EntraPeca(holdPeca.peca, mapa.getTile(x, y)));
    }

    private void atacar(int x, int y) throws Exception {
        int alcance = 0;
        if (x > holdTileX) {
            if (y > holdTileY) {
                alcance = (x - holdTileX + y - holdTileY);
            } else {
                alcance = (x - holdTileX + holdTileY - y);
            }
        } else if (y > holdTileY) {

            alcance = (holdTileX - x + y - holdTileY);
        } else {
            alcance = (holdTileX - x + holdTileY - y);
        }
        command.executar(new Atacar(mapa.getTile(holdTileX, holdTileY), mapa.getTile(x, y), alcance));
    }

    private void mover(int x, int y) throws Exception {
        int quantidade = 0;
        if (x > holdTileX) {
            if (y > holdTileY) {
                quantidade = (x - holdTileX + y - holdTileY);
            } else {
                quantidade = (x - holdTileX + holdTileY - y);
            }
        } else if (y > holdTileY) {

            quantidade = (holdTileX - x + y - holdTileY);
        } else {
            quantidade = (holdTileX - x + holdTileY - y);
        }

        command.executar(new Mover(mapa.getTile(x, y), mapa.getTile(holdTileX, holdTileY), quantidade));
    }

    public void switchTurn() {
        holdTileX = 9;
        holdPeca = null;
        Config.getInstancia().switchTurn();
        resetarJogador();
        notificar();
    }

    private void resetarJogador() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (mapa.getTile(i, j).hasPeca()) {
                    if (mapa.getTile(i, j).getPeca().getJogador() == Config.getInstancia().getTurno()) {
                        mapa.getTile(i, j).getPeca().setEstado(new PecaAtiva(mapa.getTile(i, j).getPeca()));
                    }
                }
            }
        }
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
        for (ObservadorGeral o : observadores) {
            o.update();
        }
    }
}
