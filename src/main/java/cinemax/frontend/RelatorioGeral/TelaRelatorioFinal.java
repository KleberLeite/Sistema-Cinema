package cinemax.frontend.RelatorioGeral;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import cinemax.frontend.PaginasGeranteeFuncionario.TelaGerente;
import cinemax.frontend.controller.ControladorDeApp;
import cinemax.frontend.estilizacao.Estilizador;
import cinemax.frontend.estilizacao.EstiloBotao;
import cinemax.backend.alimentos.Alimento;
import cinemax.backend.filmes.Filme;
import cinemax.backend.filmes.Sessao;
import cinemax.backend.relatorios.Relatorio;
import cinemax.backend.relatorios.alimentos.RelatorioAlimentos;
import cinemax.backend.relatorios.filmes.Ingresso;
import cinemax.backend.relatorios.filmes.RelatorioFilmes;
import cinemax.backend.relatorios.filmes.TipoDeIngresso;
import cinemax.backend.relatorios.filmes.VendasIngressos;
import cinemax.backend.salas.Sala;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;

public class TelaRelatorioFinal extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private ControladorDeApp app = ControladorDeApp.getInstancia();
    private DefaultTableModel modeloTabelaFilme;
    private DefaultTableModel modeloTabelaAlimento;
    private double totalAlimentos;
    private double totalFilmes;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TelaRelatorioFinal frame = new TelaRelatorioFinal(null);
                    frame.setVisible(true);
                    frame.setLocationRelativeTo(null);
                    frame.setTitle("Cinemax");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public TelaRelatorioFinal(Relatorio  relatorioDoDia) {
    	if(relatorioDoDia==null) relatorioDoDia = app.getBackend().getGerenciadorRelatorios().obterRelatorioDoDia();
    	
    	Relatorio relatorio = relatorioDoDia;
        RelatorioAlimentos relatorioAlimento = relatorio.getRelatorioAlimentos();
        RelatorioFilmes relatorioFilmes = relatorio.getRelatorioFilmes();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1200, 600); // aumentada a largura
        contentPane = new JPanel();
        contentPane.setBackground(new Color(2, 18, 27));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null); 

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setBackground(new Color(255, 255, 255));
        panelPrincipal.setBounds(10, 353, 1164, 157); // aumentada
        contentPane.add(panelPrincipal);
        panelPrincipal.setLayout(null);

        JLabel lblLinha = new JLabel("__________________________________________________________________________________________________________________________________________________________________________________");
        lblLinha.setBounds(5, 50, 1164, 14);
        panelPrincipal.add(lblLinha);

        // Painel para Alimentos
        JScrollPane scrollPaneAlimentos = new JScrollPane();
        scrollPaneAlimentos = Estilizador.estilizarScrollPane(scrollPaneAlimentos);
        scrollPaneAlimentos.setBounds(10, 0, 580, 350); // aumentada
        scrollPaneAlimentos.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPaneAlimentos.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        contentPane.add(scrollPaneAlimentos);

        Color corFundoCabelho = new Color(2, 17, 28);
        JTable TabelaAlimento = new javax.swing.JTable();
        TabelaAlimento.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][] { { null, null, null, null }, { null, null, null, null },
                    { null, null, null, null }, { null, null, null, null } },
            new String[] { "Item", "Preço", "Quantidade", "Subtotal" }) {
        	@Override
    		public boolean isCellEditable(int row, int column) {
    			return false; // Impede a edição da célula
    		}
        });

        modeloTabelaAlimento = (DefaultTableModel) TabelaAlimento.getModel();
        modeloTabelaAlimento.setColumnIdentifiers(new String[] { "Item", "Preço", "Quantidade", "Subtotal" });
        modeloTabelaAlimento.setRowCount(0);

        for (Map.Entry<Alimento, Integer> entry : relatorioAlimento.obterVendas()) {
            Alimento alimento = entry.getKey();
            Integer quantidade = entry.getValue();
            double totalParcial = alimento.getPreco() * quantidade;
            totalAlimentos += totalParcial;
            modeloTabelaAlimento.addRow(new Object[] { alimento.getNome(), String.format("R$ %.2f", alimento.getPreco()),
                quantidade, String.format("R$ %.2f", totalParcial) });
        }
        Estilizador.estilizarTabela(TabelaAlimento, corFundoCabelho);
        scrollPaneAlimentos.setViewportView(TabelaAlimento);

        JLabel labelTotalAlimentos = new JLabel("Total Geral das Vendas de Alimentos: R$"+String.format("%.2f", totalAlimentos));
        labelTotalAlimentos.setVerticalAlignment(SwingConstants.CENTER);
        labelTotalAlimentos.setHorizontalAlignment(SwingConstants.CENTER);
        labelTotalAlimentos.setFont(new Font("Tahoma", Font.BOLD, 16));
        labelTotalAlimentos.setForeground(Color.BLACK);
        labelTotalAlimentos.setBounds(5, 21, 580, 30); // ajustado
        panelPrincipal.add(labelTotalAlimentos);

        // Painel para Filmes
        JScrollPane scrollPaneFilmes = new JScrollPane();
        scrollPaneFilmes = Estilizador.estilizarScrollPane(scrollPaneFilmes);
        scrollPaneFilmes.setBounds(600, 0, 574, 350); // ajustado
        scrollPaneFilmes.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPaneFilmes.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        contentPane.add(scrollPaneFilmes);

        Color corFundoCabelho2 = new Color(2, 17, 28);
        JTable TabelaFilme = new javax.swing.JTable();
        TabelaFilme.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][] { { null, null, null, null }, { null, null, null, null },
                    { null, null, null, null }, { null, null, null, null } },
            new String[] { "Filme", "Inteiras", "Meias", "Subtotal" }) {
        	@Override
    		public boolean isCellEditable(int row, int column) {
    			return false; // Impede a edição da célula
    		}
        });

        modeloTabelaFilme = (DefaultTableModel) TabelaFilme.getModel();
        modeloTabelaFilme.setColumnIdentifiers(new String[] { "Filme", "Inteiras", "Meias", "Subtotal" });
        modeloTabelaFilme.setRowCount(0);

        for (VendasIngressos vendasIngressos : relatorioFilmes.obterVendas()) {
            Filme filme = vendasIngressos.getFilme();
            int qtdeInteiras = vendasIngressos.getQtdInteiras();
            int qtdeMeias = vendasIngressos.getQtdMeias();
            double totalParcial = qtdeInteiras*Ingresso.PRECO_INGRESSO + qtdeMeias*Ingresso.PRECO_INGRESSO/2;
            totalFilmes += totalParcial;
            modeloTabelaFilme.addRow(new Object[] { filme.getNome(), qtdeInteiras, qtdeMeias, String.format("R$ %.2f", totalParcial) });
        }
        Estilizador.estilizarTabela(TabelaFilme, corFundoCabelho2);
        scrollPaneFilmes.setViewportView(TabelaFilme);

        JLabel labelTotalFilmes = new JLabel("Total Geral das Vendas de Filmes: R$"+String.format("%.2f", totalFilmes));
        labelTotalFilmes.setVerticalAlignment(SwingConstants.CENTER);
        labelTotalFilmes.setHorizontalAlignment(SwingConstants.CENTER);
        labelTotalFilmes.setFont(new Font("Tahoma", Font.BOLD, 16));
        labelTotalFilmes.setForeground(Color.BLACK);
        labelTotalFilmes.setBounds(590, 23, 574, 30); // ajustado
        panelPrincipal.add(labelTotalFilmes);

        JLabel lblTotalDeVendas = new JLabel("Total de Vendas no dia: R$"+String.format("%.2f", totalFilmes+totalAlimentos));
        lblTotalDeVendas.setVerticalAlignment(SwingConstants.CENTER);
        lblTotalDeVendas.setHorizontalAlignment(SwingConstants.CENTER);
        lblTotalDeVendas.setForeground(Color.BLACK);
        lblTotalDeVendas.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblTotalDeVendas.setBounds(0, 75, 1164, 30); // ajustado
        panelPrincipal.add(lblTotalDeVendas);

        // Botão Voltar
        JButton btnVoltar = new JButton("Voltar");
        Estilizador.aplicarEstiloBotao(btnVoltar, EstiloBotao.CLARO_UNIFICADO);
        btnVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TelaEscolhaRelatorio telaEscolhaRelatorio = new TelaEscolhaRelatorio();
                telaEscolhaRelatorio.setVisible(true);
                telaEscolhaRelatorio.setLocationRelativeTo(null);
                dispose();
            }
        });
        btnVoltar.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnVoltar.setBounds(10, 527, 89, 23);
        contentPane.add(btnVoltar);


    }
}
