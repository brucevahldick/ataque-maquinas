package view;

import componentes.*;
import config.Config;
import facade.MapaController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import javax.swing.border.Border;
import java.util.HashMap;
import java.util.Map;
import observer.ObservadorGeral;

/**
 *
 * @author brucevahldick
 */
public class MapaView extends JFrame implements ObservadorGeral {

    private GridTabuleiro painel;
    private PecasJogador pecasJogadorUm;
    private PecasJogador pecasJogadorDois;
    private MapaController controller;
    private JButton botaoTurno;

    private void updateBotaoTurno() {
        botaoTurno.setText("Finalizar turno (Jogador " + Config.getInstancia().getTurno() + ")");
    }

    public MapaView() {
        controller = new MapaController();
        controller.addObservador(this);
        setTitle(Config.getInstancia().getAppTitle());
        setLayout(new BorderLayout());
        botaoTurno = new JButton();
        updateBotaoTurno();
        botaoTurno.addActionListener((ActionEvent ae) -> {
            controller.switchTurn();
        });
        add(botaoTurno, BorderLayout.NORTH);

        painel = new GridTabuleiro();
        painel.setLayout(new GridLayout(8, 8));
        painel.setBackground(Color.white);
        painel.setSize(1000, 1000);
        add(painel, BorderLayout.CENTER);

        pecasJogadorUm = new PecasJogador();
        pecasJogadorUm.setSize(125, 1000);
        pecasJogadorUm.setLayout(new GridLayout(getPecasJogador(1, pecasJogadorUm), 1));
        pecasJogadorUm.setIdJogador(1);
        controller.addObservador(pecasJogadorUm);
        add(pecasJogadorUm, BorderLayout.WEST);

        pecasJogadorDois = new PecasJogador();
        pecasJogadorDois.setSize(125, 1000);
        pecasJogadorDois.setLayout(new GridLayout(getPecasJogador(2, pecasJogadorDois), 1));
        pecasJogadorDois.setIdJogador(2);
        controller.addObservador(pecasJogadorDois);
        add(pecasJogadorDois, BorderLayout.EAST);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1500, 1250);
        desenharMapa();
    }

    private void desenharMapa() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                LabelTile label = new LabelTile(controller.abrirMapa().getTile(i, j).render());
                label.setLinha(i);
                label.setColuna(j);
                label.setForeground(Color.white);
                label.setOpaque(true);
                Border border = BorderFactory.createLineBorder(Color.decode(controller.abrirMapa().getTile(i, j).getTerreno().getColor()), 5);
                label.setBorder(border);
                label.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent me) {
                        clicaTile(label.getLinha(), label.getColuna());
                    }

                    @Override
                    public void mousePressed(MouseEvent me) {
                    }

                    @Override
                    public void mouseReleased(MouseEvent me) {
                    }

                    @Override
                    public void mouseEntered(MouseEvent me) {
                    }

                    @Override
                    public void mouseExited(MouseEvent me) {
                    }
                });
                painel.addLabelTile(label, i, j);
            }
        }
    }

    private void updateCelulas() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                painel.getFromLinhaColuna(i, j).setText(controller.abrirMapa().getTile(i, j).render());
                if (controller.abrirMapa().getTile(i, j).hasPeca()) {
                    String cor = controller.abrirMapa().getTile(i, j).getPeca().getJogador() == 1
                            ? Config.getInstancia().getColorJogadorUm() : Config.getInstancia().getColorJogadorDois();
                    painel.getFromLinhaColuna(i, j).setBackground(Color.decode(cor));
                } else {
                    painel.getFromLinhaColuna(i, j).setBackground(Color.white);
                }
            }
        }
    }

    // retorna o numero de pecas
    private int getPecasJogador(int idJogador, PecasJogador componente) {
        int size = controller.getTodasAsPecasJogando().size();
        for (int i = 0; i < size; i++) {
            if (controller.getTodasAsPecasJogando().get(i).getPeca().getJogador() == idJogador) {
                BotaoPeca bp = new BotaoPeca();
                bp.setText(controller.getTodasAsPecasJogando().get(i).getPeca().desenho());
                bp.setId(i);
                bp.addActionListener((ActionEvent ae) -> {
                    entraPecaDeFora(bp.getId());
                });
                componente.add(bp);
                componente.addButton(i, bp);
            }
        }

        return size;
    }

    private void entraPecaDeFora(int id) {
        try {
            controller.entraPecaDeFora(id);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    e.getMessage(),
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    private void clicaTile(int x, int y) {
        try {
            controller.clicaTile(x, y);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    e.getMessage(),
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    @Override
    public void update() {
        updateCelulas();
        updateBotaoTurno();
    }

    private class GridTabuleiro extends JPanel {

        private LabelTile[][] labels;

        public GridTabuleiro() {
            labels = new LabelTile[8][8];
        }

        private void addLabelTile(LabelTile tile, int linha, int coluna) {
            labels[linha][coluna] = tile;
            add(labels[linha][coluna]);
        }

        public LabelTile getFromLinhaColuna(int linha, int coluna) {
            return labels[linha][coluna];
        }
    }

    private class PecasJogador extends JPanel implements ObservadorGeral {

        private Map<Integer, JButton> buttons;
        private int idJogador;
        private JLabel contadorDePontos;

        public PecasJogador() {
            buttons = new HashMap<>();
            contadorDePontos = new JLabel();
            setupLabel();
            add(contadorDePontos);
        }

        public void addButton(int idPeca, JButton button) {
            buttons.put(idPeca, button);
        }

        public int getIdJogador() {
            return idJogador;
        }

        public void setIdJogador(int idJogador) {
            this.idJogador = idJogador;
        }
        
        private void setupLabel(){
            contadorDePontos.setText("<html><body>Jogador "
                    + idJogador
                    + "<br>Pontos de vitoria: "
                    + Config.getInstancia().getPontosVitoriaJogador(idJogador)
                    + "</body></html>");
        }

        @Override
        public void update() {
            if (controller.hasHoldPeca()) {
                int id = controller.getHoldPeca().getId();
                if (buttons.containsKey(id)) {
                    buttons.get(id).setText("");
                    buttons.get(id).setVisible(false);
                    buttons.remove(id);
                }
            }
            
            setupLabel();
        }
    }
}
