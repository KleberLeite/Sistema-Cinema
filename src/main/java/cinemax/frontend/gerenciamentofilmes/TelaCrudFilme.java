package cinemax.frontend.gerenciamentofilmes;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cinemax.backend.filmes.Filme;
import cinemax.frontend.PaginasGeranteeFuncionario.PaginaPrincipal;
import cinemax.frontend.controller.ControladorDeApp;
import cinemax.frontend.estilizacao.Estilizador;
import cinemax.frontend.estilizacao.EstiloBotao;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.ScrollPaneConstants;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaCrudFilme extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ControladorDeApp app = ControladorDeApp.getInstancia();

	// Launch the application.
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCrudFilme frame = new TelaCrudFilme();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					frame.setTitle("Cinemax");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private JPanel gerarCard() {
		JPanel card = Estilizador.criarPainelArredondado(new Color( 192, 192, 192), 10);
	    card.setLayout(null); 
	    card.setPreferredSize(new Dimension(1400, 50));
	    card.setMaximumSize(new Dimension(1400, 50));
	    card.setBackground(new Color(192, 192, 192));
	    card.setBorder(new EmptyBorder(10, 30, 10, 10));
	    return card;
	}
	
	private void adicionarLabelNomeFilme(JPanel card, Filme filme) {
		JLabel lblNome = new JLabel(filme.getNome());
	    lblNome.setFont(new Font("Tahoma", Font.BOLD, 14));
	    lblNome.setBounds(20, 10, 400, 25);
	    card.add(lblNome);
	}
	
	private void adicionarBotaoEditar(JPanel card, Filme filme) {
		JButton btnEditar = new JButton("Editar");
		Estilizador.aplicarEstiloBotao(btnEditar, EstiloBotao.PADRAO_ESCURECIDO);
	    btnEditar.addActionListener(e -> {
              
	    	 TelaEditarFilme telaEditarFilme = new TelaEditarFilme(filme);
	    	 telaEditarFilme.setLocationRelativeTo(null);
	    	 telaEditarFilme.setVisible(true);
	    	 
	    	 dispose();
               
        });
	    btnEditar.setFont(new Font("Tahoma", Font.BOLD, 12));
	    btnEditar.setBounds(500, 10, 80, 30);
	    card.add(btnEditar);
	}
	
	private void adicionarBotaoExcluir(JPanel card, JPanel panelListaFilmes, Filme filme) {
		JButton btnExcluir = new JButton("Excluir");
		Estilizador.aplicarEstiloBotao(btnExcluir, EstiloBotao.PADRAO_ESCURECIDO);
	    btnExcluir.addActionListener(e -> {
	    	 if(!app.getBackend().getBancoFilmes().tentarRemoverFilme(filme.getId())) {
	    		 JOptionPane.showMessageDialog(null, "Falha ao tentar Remover o Filme, tente novamente!", "Aviso", JOptionPane.WARNING_MESSAGE); 
	    	 }else{
	    		 atualizarListaDeFilmes(panelListaFilmes, app.getBackend().getBancoFilmes().obterTodosFilmes());
	    	 }
             
        });
	    btnExcluir.setFont(new Font("Tahoma", Font.BOLD, 12));
	    btnExcluir.setBounds(590, 10, 80, 30);
	    card.add(btnExcluir);
	}
	
	private void atualizarListaDeFilmes(JPanel panelListaFilmes, Filme[] filmes) {
		panelListaFilmes.removeAll();
		
		for (Filme filme : filmes) {
		    JPanel card = gerarCard();

		    adicionarLabelNomeFilme(card, filme);		    
		    adicionarBotaoEditar(card, filme);
		    adicionarBotaoExcluir(card, panelListaFilmes, filme);
		    
		    // Espaço entre os cards
		    panelListaFilmes.add(Box.createRigidArea(new Dimension(0, 10))); 
		    panelListaFilmes.add(card);
		}
		
		panelListaFilmes.revalidate();
		panelListaFilmes.repaint();
	}

	// Create the frame.
	public TelaCrudFilme() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		
		contentPane = gerarContentPane();
		JPanel panelPrincipal = gerarPanelPrincipal(contentPane);
		JScrollPane scrollPane = gerarScrollPane(panelPrincipal);
		scrollPane = Estilizador.estilizandoScrollBarVertEHori(scrollPane);
		JPanel panelListaFilmes = gerarPanelListaFilmes(scrollPane);
		
		gerarBotaoAdicionarFilme(panelPrincipal);
		adicionarLabel(panelPrincipal);
		adicionarBotaoVoltar();
		
		atualizarListaDeFilmes(panelListaFilmes,app.getBackend().getBancoFilmes().obterTodosFilmes());
	}
	
	private JPanel gerarContentPane() {
		JPanel contentPanel = new JPanel();
		contentPanel.setBackground(new Color(2, 18, 27));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPanel);
		contentPanel.setLayout(null);
		

		JButton btnGerenciamentoDeSalas = new JButton("Gerenciamento de Salas");
		Estilizador.aplicarEstiloBotao(btnGerenciamentoDeSalas, EstiloBotao.CLARO_UNIFICADO);
		btnGerenciamentoDeSalas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
				TelaEscolhaSalaBloquear telaEscolhaSalaBloquear = new TelaEscolhaSalaBloquear();
				telaEscolhaSalaBloquear.setVisible(true);
				telaEscolhaSalaBloquear.setLocationRelativeTo(null);
					
				dispose();
			}
		});
		btnGerenciamentoDeSalas.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnGerenciamentoDeSalas.setBounds(577, 429, 197, 23);
		contentPanel.add(btnGerenciamentoDeSalas);
		
		
		return contentPanel;
	}
	
	private JPanel gerarPanelPrincipal(JPanel contentPanel) {
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setBackground(new Color(255, 255, 255));
		panelPrincipal.setBounds(10, 11, 764, 405);
		contentPanel.add(panelPrincipal);
		panelPrincipal.setLayout(null);
		
		return panelPrincipal;
	}
	
	private JScrollPane gerarScrollPane(JPanel panelPrincipal) {
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 85, 744, 274);
		panelPrincipal.add(scrollPane);
		
		return scrollPane;
	}
	
	private JPanel gerarPanelListaFilmes(JScrollPane scrollPane) {
		JPanel panelListaFilmes = new JPanel();
		panelListaFilmes.setBackground(new Color(255, 255, 255));
		panelListaFilmes.setLayout(new BoxLayout(panelListaFilmes, BoxLayout.Y_AXIS));
		scrollPane.setViewportView(panelListaFilmes);
		
		return panelListaFilmes;
	}
	
	private void gerarBotaoAdicionarFilme(JPanel panelPrincipal) {
		JButton btnAdicionarFilme = new JButton("Adicionar");
		Estilizador.aplicarEstiloBotao(btnAdicionarFilme, EstiloBotao.PADRAO_ESCURECIDO);
		btnAdicionarFilme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaAdicionarFilme telaAdicionarFilme = new TelaAdicionarFilme();
				telaAdicionarFilme.setLocationRelativeTo(null);
				telaAdicionarFilme.setVisible(true);
				
				dispose();
			}
		});
		btnAdicionarFilme.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAdicionarFilme.setBounds(323, 364, 111, 30);
		panelPrincipal.add(btnAdicionarFilme);
	}
	
	private void adicionarLabel(JPanel panelPrincipal) {
		JLabel lblListaDosFilmes = new JLabel("Lista dos Filmes Cadastrados:");
		lblListaDosFilmes.setBackground(new Color(255, 255, 255));
		lblListaDosFilmes.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblListaDosFilmes.setBounds(10, 21, 513, 40);
		panelPrincipal.add(lblListaDosFilmes);
	}
	
	private void adicionarBotaoVoltar() {
		JButton btnVoltar = new JButton("Voltar");
		Estilizador.aplicarEstiloBotao(btnVoltar, EstiloBotao.CLARO_UNIFICADO);
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PaginaPrincipal.abrirPaginaPrincipal();
				dispose();
				
			}
		});
		btnVoltar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnVoltar.setBounds(10, 427, 89, 23);
		contentPane.add(btnVoltar);
	}
}
