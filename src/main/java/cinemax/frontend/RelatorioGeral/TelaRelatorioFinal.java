package cinemax.frontend.RelatorioGeral;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;

public class TelaRelatorioFinal extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private ControladorDeApp app = ControladorDeApp.getInstancia();
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
    //Poltrona
    //Ingresso
    //Filme - Sessao
    
    private Map<Alimento, Integer> gerarCompraRandomAlimento(Random random, Alimento[] alimentos, int n) {
		Map<Alimento, Integer> compra = new HashMap<>();
		for (int i = 0; i < n; i++) {
			
			int index = random.nextInt(alimentos.length);
			Alimento a = alimentos[index];
			compra.put(a, compra.getOrDefault(a, 0) + 1);
		}
		return compra;
	}


    public List<Ingresso> simularVendasFilmes() {
        List<Ingresso> ingressos = new ArrayList<>();

        for(Filme filme : app.getBackend().getBancoFilmes().obterTodosFilmes()) {
	        for (Sessao sessao : filme.obterTodasSessoes()) {
	            Sala sala = sessao.getSala();
	
	            // Vamos simular venda de 5 ingressos por sessão
	            for (int i = 0; i < 5; i++) {
	                int linha = i / sala.getColunas(); // Simplesmente para variar
	                int coluna = i % sala.getColunas();
	                
	                Ingresso ingresso = new Ingresso(sessao);
	                ingresso.setTipo(i % 2 == 0 ? TipoDeIngresso.Inteira : TipoDeIngresso.Meia);
	                ingresso.setRG("12345678" + i);
	
	                ingressos.add(ingresso);
	            }
	        }
        }
        return ingressos;
        
    }



    public TelaRelatorioFinal(Relatorio  relatorioDoDia) {
    	if(relatorioDoDia==null) relatorioDoDia = app.getBackend().getGerenciadorRelatorios().obterRelatorioDoDia();
    	
    	Relatorio relatorio = relatorioDoDia;
        RelatorioAlimentos relatorioAlimento = relatorio.getRelatorioAlimentos();
        RelatorioFilmes relatorioFilmes = relatorio.getRelatorioFilmes();
        
        
        /*relatorioFilmes.adicionarVendas(simularVendasFilmes());
    	
		app.getBackend().tentarFecharDia();
		
		for(Ingresso ingresso : simularVendasFilmes() ) {
			System.out.println(ingresso.toString());
		}*/

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 900, 600);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(2, 18, 27));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);        
        
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setBounds(10, 11, 864, 499);
        contentPane.add(panelPrincipal);
        panelPrincipal.setLayout(null);

        JLabel lblLinha = new JLabel("__________________________________________________________________________________________________________________________");
        lblLinha.setBounds(5, 412, 864, 14);
        panelPrincipal.add(lblLinha);

        // Painel para Alimentos
        JScrollPane scrollPaneAlimentos = new JScrollPane();
        scrollPaneAlimentos = Estilizador.estilizandoScrollBarVertEHori(scrollPaneAlimentos);
        scrollPaneAlimentos.setBounds(0, 28, 432, 350);
        scrollPaneAlimentos.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPaneAlimentos.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        panelPrincipal.add(scrollPaneAlimentos);

        JPanel panelPrincipalRelatórioAlimentos = new JPanel();
        panelPrincipalRelatórioAlimentos.setLayout(new BoxLayout(panelPrincipalRelatórioAlimentos, BoxLayout.Y_AXIS));
        scrollPaneAlimentos.setViewportView(panelPrincipalRelatórioAlimentos);

        // Preencher com dados dos alimentos
        for (Map.Entry<Alimento, Integer> entry : relatorioAlimento.obterVendas()) {
        	
            Alimento alimento = entry.getKey();
            Integer quantidade = entry.getValue();

            // Total parcial
            double totalParcial = alimento.getPreco() * quantidade;
            totalAlimentos += totalParcial;

            // Painel horizontal para cada alimento
            JPanel painelAlimento = new JPanel();
            painelAlimento.setLayout(new BoxLayout(painelAlimento, BoxLayout.X_AXIS));
            painelAlimento.setBackground(Color.WHITE);
            painelAlimento.setBorder(new EmptyBorder(5, 5, 5, 5));

            // Painel do nome
            JPanel painelNome = new JPanel();
            painelNome.setBackground(Color.LIGHT_GRAY);
            JLabel labelNome = new JLabel(alimento.getNome());
            labelNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
            painelNome.add(labelNome);

            // Painel do preço
            JPanel painelPreco = new JPanel();
            painelPreco.setBackground(new Color(200, 255, 200));
            JLabel labelPreco = new JLabel("R$" + String.format("%.2f", alimento.getPreco()));
            labelPreco.setFont(new Font("Tahoma", Font.PLAIN, 14));
            painelPreco.add(labelPreco);

            // Painel da quantidade
            JPanel painelQuantidade = new JPanel();
            painelQuantidade.setBackground(new Color(255, 230, 200));
            JLabel labelQuantidade = new JLabel(""+quantidade);
            labelQuantidade.setFont(new Font("Tahoma", Font.PLAIN, 14));
            painelQuantidade.add(labelQuantidade);

            // Painel do total parcial
            JPanel painelTotal = new JPanel();
            painelTotal.setBackground(new Color(220, 220, 255));
            JLabel labelTotal = new JLabel("Total: R$" + String.format("%.2f", totalParcial));
            labelTotal.setFont(new Font("Tahoma", Font.PLAIN, 14));
            painelTotal.add(labelTotal);

            // Adiciona os subpainéis ao painel principal do alimento
            painelAlimento.add(painelNome);
            painelAlimento.add(painelPreco);
            painelAlimento.add(painelQuantidade);
            painelAlimento.add(painelTotal);

            // Adiciona o painel do alimento ao painel geral
            panelPrincipalRelatórioAlimentos.add(painelAlimento);
        }
        
        JLabel lblNomeAlimento = new JLabel("Nome");
        lblNomeAlimento.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNomeAlimento.setHorizontalAlignment(SwingConstants.CENTER); // Centraliza horizontalmente
        lblNomeAlimento.setVerticalAlignment(SwingConstants.CENTER);   // (opcional) centraliza verticalmente
        lblNomeAlimento.setBounds(0, 0, 123, 30);
        panelPrincipal.add(lblNomeAlimento);
        
        JLabel lblQuantidadeAlimento = new JLabel("Quantidade");
        lblQuantidadeAlimento.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblQuantidadeAlimento.setHorizontalAlignment(SwingConstants.CENTER); // Centraliza horizontalmente
        lblQuantidadeAlimento.setVerticalAlignment(SwingConstants.CENTER);   // (opcional) centraliza verticalmente
        lblQuantidadeAlimento.setBounds(242, 0, 108, 30);
        panelPrincipal.add(lblQuantidadeAlimento);
        
        JLabel lblSubTotalAlimento = new JLabel("SubTotal");
        lblSubTotalAlimento.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblSubTotalAlimento.setHorizontalAlignment(SwingConstants.CENTER); // Centraliza horizontalmente
        lblSubTotalAlimento.setVerticalAlignment(SwingConstants.CENTER);   // (opcional) centraliza verticalmente
        lblSubTotalAlimento.setBounds(333, 0, 99, 30);
        panelPrincipal.add(lblSubTotalAlimento);
        
        JLabel lblPrecoAlimento = new JLabel("Preço");
        lblPrecoAlimento.setVerticalAlignment(SwingConstants.CENTER);
        lblPrecoAlimento.setHorizontalAlignment(SwingConstants.CENTER);
        lblPrecoAlimento.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblPrecoAlimento.setBounds(133, 0, 99, 30);
        panelPrincipal.add(lblPrecoAlimento);

        // Total geral fora do scroll
        JLabel labelTotalAlimentos = new JLabel("Total Geral das Vendas de Alimentos: R$"+String.format("%.2f", totalAlimentos));
        labelTotalAlimentos.setVerticalAlignment(SwingConstants.CENTER);
        labelTotalAlimentos.setHorizontalAlignment(SwingConstants.CENTER);
        labelTotalAlimentos.setFont(new Font("Tahoma", Font.BOLD, 16));
        labelTotalAlimentos.setForeground(Color.BLACK);
        labelTotalAlimentos.setBounds(0, 389, 432, 30);
        panelPrincipal.add(labelTotalAlimentos);
        
     // Painel para Alimentos
        JScrollPane scrollPaneFilmes = new JScrollPane();
        scrollPaneFilmes = Estilizador.estilizandoScrollBarVertEHori(scrollPaneFilmes);
        scrollPaneFilmes.setBounds(432, 28, 432, 350);
        scrollPaneFilmes.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPaneFilmes.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        panelPrincipal.add(scrollPaneFilmes);

        JPanel panelPrincipalRelatorioFilmes = new JPanel();
        panelPrincipalRelatorioFilmes.setLayout(new BoxLayout(panelPrincipalRelatorioFilmes, BoxLayout.Y_AXIS));
        scrollPaneFilmes.setViewportView(panelPrincipalRelatorioFilmes);

        // Preencher com dados dos alimentos
        for (VendasIngressos vendasIngressos : relatorioFilmes.obterVendas()) {
            Filme filme = vendasIngressos.getFilme();
            int qtdeInteiras = vendasIngressos.getQtdInteiras();
            int qtdeMeias = vendasIngressos.getQtdMeias();

            // Total parcial
            double totalParcial = qtdeInteiras*Ingresso.PRECO_INGRESSO + qtdeMeias*Ingresso.PRECO_INGRESSO/2;
            totalFilmes += totalParcial;

            // Painel horizontal para cada alimento
            JPanel painelFilme = new JPanel();
            painelFilme.setLayout(new BoxLayout(painelFilme, BoxLayout.X_AXIS));
            painelFilme.setBackground(Color.WHITE);
            painelFilme.setBorder(new EmptyBorder(5, 5, 5, 5));

            // Painel do nome
            JPanel painelNomeFilme = new JPanel();
            painelNomeFilme.setBackground(Color.LIGHT_GRAY);
            JLabel labelNomeFilme = new JLabel(filme.getNome());
            labelNomeFilme.setFont(new Font("Tahoma", Font.PLAIN, 14));
            painelNomeFilme.add(labelNomeFilme);

            // Painel do preço
            JPanel painelMeia = new JPanel();
            painelMeia.setBackground(new Color(212, 245, 220));
            JLabel labelMeia = new JLabel(String.format("%d", qtdeMeias));
            labelMeia.setFont(new Font("Tahoma", Font.PLAIN, 14));
            painelMeia.add(labelMeia);
            
            JPanel painelInteira = new JPanel();
            painelInteira.setBackground(new Color(200, 255, 200));
            JLabel labelInteira = new JLabel(String.format("%d", qtdeInteiras));
            labelInteira.setFont(new Font("Tahoma", Font.PLAIN, 14));
            painelInteira.add(labelInteira);


            // Painel do total parcial
            JPanel painelSubTotal = new JPanel();
            painelSubTotal.setBackground(new Color(220, 220, 255));
            JLabel labelSubTotal = new JLabel("Total: R$" + String.format("%.2f", totalParcial));
            labelSubTotal.setFont(new Font("Tahoma", Font.PLAIN, 14));
            painelSubTotal.add(labelSubTotal);

            // Adiciona os subpainéis ao painel principal do alimento
            painelFilme.add(painelNomeFilme);
            painelFilme.add(painelInteira);
            painelFilme.add(painelMeia);
            painelFilme.add(painelSubTotal);

            // Adiciona o painel do alimento ao painel geral
            panelPrincipalRelatorioFilmes.add(painelFilme);
        }

        
        
        JLabel lblNomeFilme = new JLabel("Nome");
        lblNomeFilme.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNomeFilme.setHorizontalAlignment(SwingConstants.CENTER); // Centraliza horizontalmente
        lblNomeFilme.setVerticalAlignment(SwingConstants.CENTER);   // (opcional) centraliza verticalmente
        lblNomeFilme.setBounds(432, 0, 123, 30);
        panelPrincipal.add(lblNomeFilme);
        
        JLabel lblQuantidadeInteira = new JLabel("Inteiras");
        lblQuantidadeInteira.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblQuantidadeInteira.setHorizontalAlignment(SwingConstants.CENTER); // Centraliza horizontalmente
        lblQuantidadeInteira.setVerticalAlignment(SwingConstants.CENTER);   // (opcional) centraliza verticalmente
        lblQuantidadeInteira.setBounds(565, 0, 115, 30);
        panelPrincipal.add(lblQuantidadeInteira);
        
        JLabel lblQuantidadeMeia = new JLabel("Meias");
        lblQuantidadeMeia.setVerticalAlignment(SwingConstants.CENTER);
        lblQuantidadeMeia.setHorizontalAlignment(SwingConstants.CENTER);
        lblQuantidadeMeia.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblQuantidadeMeia.setBounds(676, 0, 99, 30);
        panelPrincipal.add(lblQuantidadeMeia);
        
        JLabel lblSubTotalFilme = new JLabel("SubTotal");
        lblSubTotalFilme.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblSubTotalFilme.setHorizontalAlignment(SwingConstants.CENTER); // Centraliza horizontalmente
        lblSubTotalFilme.setVerticalAlignment(SwingConstants.CENTER);   // (opcional) centraliza verticalmente
        lblSubTotalFilme.setBounds(765, 0, 99, 30);//+89
        panelPrincipal.add(lblSubTotalFilme);
        
        // Total geral fora do scroll
        JLabel labelTotalFilmes = new JLabel("Total Geral das Vendas de Filmes: R$"+String.format("%.2f", totalFilmes));
        labelTotalFilmes.setVerticalAlignment(SwingConstants.CENTER);
        labelTotalFilmes.setHorizontalAlignment(SwingConstants.CENTER);
        labelTotalFilmes.setFont(new Font("Tahoma", Font.BOLD, 16));
        labelTotalFilmes.setForeground(Color.BLACK);
        labelTotalFilmes.setBounds(432, 389, 432, 30);
        panelPrincipal.add(labelTotalFilmes);
        
        JLabel lblTotalDeVendas = new JLabel("Total de Vendas no dia: R$"+String.format("%.2f", totalFilmes+totalAlimentos));
        lblTotalDeVendas.setVerticalAlignment(SwingConstants.CENTER);
        lblTotalDeVendas.setHorizontalAlignment(SwingConstants.CENTER);
        lblTotalDeVendas.setForeground(Color.BLACK);
        lblTotalDeVendas.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblTotalDeVendas.setBounds(0, 445, 864, 30);
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
