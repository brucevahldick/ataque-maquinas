package view;

import config.Config;
import facade.PecasController;
import observer.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import componentes.*;

/**
 *
 * @author brucevahldick
 */
public class SelecaoPecas extends JFrame implements ObservadorSelecaoPecas {

    private PecasController controller;
    private JPanel painelPrincipal;
    private PainelCentral centerPanel;

    public SelecaoPecas() {
        controller = new PecasController();
        controller.addObservador(this);
        setTitle(Config.getInstancia().getAppTitle());
        this.setSize(1000, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createComponents();
    }

    private void createComponents() {
        initPainelPrincipal();
        initCenterPannel();
        initButtonStartMap();
    }

    private void initPainelPrincipal() {
        painelPrincipal = new JPanel(new BorderLayout());
        add(painelPrincipal);
    }

    private void initCenterPannel() {
        centerPanel = new PainelCentral();
        for (int i = 1; i <= 2; i++) {
            initPecasJogador(i);
        }
        centerPanel.setComboAndDisplay(getComboAndDisplay());
        painelPrincipal.add(centerPanel, BorderLayout.CENTER);
    }
    
    private void initPecasJogador(int idJogador) {
        PainelPecasJogador jogador = new PainelPecasJogador();
        jogador.setMainLabel("    Jogador " + idJogador + " de Ataque das Maquinas    ");
        if (idJogador == 1) {
            centerPanel.setJogadorUm(jogador);
        } else {
            centerPanel.setJogadorDois(jogador);
        }
    }
    
    private ComboAndDisplay getComboAndDisplay(){
        ComboAndDisplay comboAndDisplay = new ComboAndDisplay();
        comboAndDisplay.setComboBoxPecas(initListPecasNoJogo());
        comboAndDisplay.setDisplay(displayPeca());
        return comboAndDisplay;
    }

    private PainelInfoPeca displayPeca() {
        PainelInfoPeca painel = new PainelInfoPeca();
        painel.setBackground(Color.white);
        return painel;
    }

    private ComboBoxPecas initListPecasNoJogo() {
        ComboBoxPecas cb = new ComboBoxPecas();
        for (int i = 1; i <= controller.getPecasDisponiveis().size(); i++) {
            cb.addItem(controller.getPecasDisponiveis().get(i).getNome());
        }
        return cb;
    }

    private void initButtonStartMap() {
        JButton button = new JButton("Abrir Mapa");
        button.addActionListener((ActionEvent ae) -> {
            controller.renderMapa();
        });

        painelPrincipal.add(new JPanel().add(button), BorderLayout.SOUTH);
    }

    @Override
    public void update() {
        setVisible(false);
            new MapaView().setVisible(true);
    }

    @Override
    public void updateJogador(int idJogador, String peca) {
        if (idJogador == 1) {
            centerPanel.getJogadorUm().addPeca(peca);
        } else {
            centerPanel.getJogadorDois().addPeca(peca);
        }
    }

    private class PainelCentral extends JPanel {

        private PainelPecasJogador jogadorUm;
        private ComboAndDisplay comboAndDisplay;
        private PainelPecasJogador jogadorDois;

        public PainelCentral() {
            setLayout(new BorderLayout());
        }

        public PainelPecasJogador getJogadorUm() {
            return jogadorUm;
        }

        public void setJogadorUm(PainelPecasJogador jogadorUm) {
            this.jogadorUm = jogadorUm;
            add(this.jogadorUm, BorderLayout.WEST);
        }

        public ComboAndDisplay getComboAndDisplay() {
            return comboAndDisplay;
        }

        public void setComboAndDisplay(ComboAndDisplay comboAndDisplay) {
            this.comboAndDisplay = comboAndDisplay;
            add(this.comboAndDisplay, BorderLayout.CENTER);
        }

        public PainelPecasJogador getJogadorDois() {
            return jogadorDois;
        }

        public void setJogadorDois(PainelPecasJogador jogadorDois) {
            this.jogadorDois = jogadorDois;
            add(this.jogadorDois, BorderLayout.EAST);
        }
    }

    private class ComboAndDisplay extends JPanel implements ObservadorGeral {

        private ComboBoxPecas comboBoxPecas;
        private PainelInfoPeca display;

        public ComboAndDisplay() {
            setLayout(new BorderLayout());
            createPainelBotoes();
        }

        public ComboBoxPecas getComboBoxPecas() {
            return comboBoxPecas;
        }

        public void setComboBoxPecas(ComboBoxPecas comboBoxPecas) {
            comboBoxPecas.addObservador(this);
            comboBoxPecas.addActionListener((ActionEvent ae) -> {
                comboBoxPecas.notificar();
            });
            this.comboBoxPecas = comboBoxPecas;
            add(this.comboBoxPecas, BorderLayout.NORTH);
        }

        public PainelInfoPeca getDisplay() {
            return display;
        }

        public void setDisplay(PainelInfoPeca display) {
            this.display = display;
            add(this.display, BorderLayout.CENTER);
            if (comboBoxPecas != null) {
                rerunDisplay();
            }
        }

        public void createPainelBotoes() {
            JPanel botoes = new JPanel(new BorderLayout());
            add(botoes, BorderLayout.SOUTH);
            JButton botaoEsquerda = new JButton("<- Selecionar Esta Peça");
            botaoEsquerda.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    actionAdicionarPeca(1);
                }
            });
            
            botoes.add(botaoEsquerda, BorderLayout.WEST);
            
            JButton botaoDireita = new JButton("Selecionar Esta Peça ->");
            botaoDireita.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    actionAdicionarPeca(2);
                }
            });
            
            botoes.add(botaoDireita, BorderLayout.EAST);
        }

        private void actionAdicionarPeca(int idJogador) {
            try {
                controller.adicionarPecaJogador(comboBoxPecas.getSelectedIndex() + 1, idJogador);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,
                        e.getMessage(),
                        "Warning",
                        JOptionPane.WARNING_MESSAGE);
            }
        }

        private void rerunDisplay() {
            int indexPeca = comboBoxPecas.getSelectedIndex() + 1;
            display.setAlcance(Integer.toString(controller.getPecasDisponiveis().get(indexPeca).getAlcance()));
            display.setAtaque(Integer.toString(controller.getPecasDisponiveis().get(indexPeca).getModAtaque()));
            display.setNome(controller.getPecasDisponiveis().get(indexPeca).getNome());
            display.setMovimento(Integer.toString(controller.getPecasDisponiveis().get(indexPeca).getMovimento()));
            display.setPontosVida(Integer.toString(controller.getPecasDisponiveis().get(indexPeca).getPontosVida()));
            display.setPontosVitoria(Integer.toString(controller.getPecasDisponiveis().get(indexPeca).getPontosVitoria()));
            display.setDescricao(controller.getPecasDisponiveis().get(indexPeca).getDescricao());
        }

        @Override
        public void update() {
            rerunDisplay();
        }

    }

}
