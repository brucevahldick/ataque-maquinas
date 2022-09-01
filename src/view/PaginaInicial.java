package view;

import config.*;
import java.awt.*;
import javax.swing.*;
import facade.*;
import java.awt.event.ActionEvent;
import java.io.*;
import observer.ObservadorGeral;

/**
 *
 * @author brucevahldick
 */
public class PaginaInicial extends JFrame implements ObservadorGeral {

    private JPanel painelPrincipal;
    private ConfigController controller;

    public PaginaInicial() {
        controller = new ConfigController();
        controller.addObservador(this);
        setTitle(Config.getInstancia().getAppTitle());
        this.setSize(400, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        painelPrincipal = new JPanel(new BorderLayout());
        createComponents();
        add(painelPrincipal);
    }

    private void createComponents() {
        initModelSelection();
        addTitle();
    }

    private void initModelSelection() {

        JComboBox<ConfigSet> cbModulos = createComboConfigSelection();

        createMapSelectionButton().addActionListener((ActionEvent ae) -> {
            JFileChooser fc = new JFileChooser();
            fc.setCurrentDirectory(new File(".\\src\\mapas"));
            if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                controller.setConfiguracao(((ConfigSet) cbModulos.getSelectedItem()).setMapa(fc.getSelectedFile()));
            }

        });

    }

    private void addTitle() {
        JPanel generalPanel = new JPanel();
        generalPanel.setBackground(Color.white);
        generalPanel.add(new JLabel(Config.getInstancia().getAppTitle()));
        painelPrincipal.add(generalPanel, BorderLayout.NORTH);
    }

    private JButton createMapSelectionButton() {
        JPanel mapSelection = new JPanel();
        mapSelection.setBackground(Color.white);
        JButton button = new JButton("Selecione o mapa");
        painelPrincipal.add(mapSelection, BorderLayout.SOUTH);
        mapSelection.add(button);
        return button;
    }

    private JComboBox<ConfigSet> createComboConfigSelection() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.white);
        JComboBox<ConfigSet> cbModulos = new JComboBox<>();
        cbModulos.addItem(new ConfigSetDefault());
        buttonPanel.add(cbModulos);
        painelPrincipal.add(buttonPanel, BorderLayout.CENTER);
        return cbModulos;
    }

    @Override
    public void update() {
        setVisible(false);
        new SelecaoPecas().setVisible(true);
    }

    public static void main(String[] args) {
        new PaginaInicial().setVisible(true);
    }

}
