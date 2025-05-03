package cinemax.frontend.vendadeingressos;

import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cinemax.backend.core.Backend;
import cinemax.backend.filmes.Filme;
import cinemax.backend.filmes.Sessao;
import cinemax.frontend.compra.Carrinho;
import cinemax.frontend.compra.Ingresso;
import cinemax.frontend.controller.ControladorDeApp;
import cinemax.frontend.gerenciamentofilmes.TelaAdicionarFilme;
import cinemax.frontend.gerenciamentofilmes.TelaEditarFilme;
import cinemax.frontend.gerenciamentofilmes.TelaEditarSessao;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.Font;
import java.time.format.DateTimeFormatter;

public class TelaConclusaoDeCompra extends JFrame {

	private static final long serialVersionUID = 1L;
	private ControladorDeApp app = ControladorDeApp.getInstancia();
	private JPanel contentPane;
	Backend bancos = app.getBackend();
	private Sessao sessao = bancos.getBancoFilmes().obterFilmePorId(0).obterSessao(0);
	private Carrinho carrinho = new Carrinho();
	private int qtdeMeias;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaConclusaoDeCompra frame = new TelaConclusaoDeCompra(null,null);
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void atualizarListaDeRGs(JPanel panelRGs, int qtdeMeias) {
		panelRGs.removeAll(); // limpa as sessões antigas

		for (int i = 0 ; i < qtdeMeias ; i++) {
			JPanel card = new JPanel();
			card.setLayout(null);
			card.setPreferredSize(new Dimension(400, 50));
			card.setMaximumSize(new Dimension(400, 50));
			card.setBackground(new Color(230, 210, 250));
			card.setBorder(new EmptyBorder(5, 5, 5, 5));

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
			String sessaoFormatada = sessao.getInicio().format(formatter);

			JLabel lblSessao = new JLabel(sessaoFormatada);
			lblSessao.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblSessao.setBounds(20, 10, 400, 25);
			card.add(lblSessao);

			

			panelRGs.add(Box.createRigidArea(new Dimension(0, 10)));
			panelRGs.add(card);
		}

		panelRGs.revalidate();
		panelRGs.repaint();
	}

	/**
	 * Create the frame.
	 */
	public TelaConclusaoDeCompra(Sessao sessaoAtual, Carrinho carrinho) {
		this.sessao = sessaoAtual;
		this.carrinho = carrinho;
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 64, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPanePrincipal = new JScrollPane();
		scrollPanePrincipal.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPanePrincipal.setBounds(10, 11, 764, 388);
		contentPane.add(scrollPanePrincipal);
		
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
		scrollPanePrincipal.setViewportView(panelPrincipal);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnVoltar.setBounds(10, 416, 89, 23);
		contentPane.add(btnVoltar);
		
		JButton btnNewButton = new JButton("Imprimir");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.setBounds(685, 416, 89, 23);
		contentPane.add(btnNewButton);
		
		for (Ingresso ingresso : carrinho.getIngressos()) {
			JPanel card = new JPanel();
			card.setLayout(null); 
			card.setPreferredSize(new Dimension(1355, 100));
			card.setMaximumSize(new Dimension(1355, 100));
			card.setBackground(new Color(230, 230, 250));

			// ADICIONA BORDA PRA DESTACAR CADA RETÂNGULO
			card.setBorder(new EmptyBorder(10, 10, 10, 10)); // espaçamento interno

			JLabel lblNome = new JLabel(ingresso.getRG());
			lblNome.setBounds(20, 10, 400, 25); // mais largura pro nome
			card.add(lblNome);

			JLabel lblDuracao = new JLabel("Poltrona: " + ingresso.getPoltrona());
			lblDuracao.setBounds(20, 35, 200, 20);
			card.add(lblDuracao);

			JLabel lblTituloSessoes = new JLabel("Sessões:");
			lblTituloSessoes.setBounds(20, 60, 100, 20);
			card.add(lblTituloSessoes);

		    
		    panelPrincipal.add(Box.createRigidArea(new Dimension(0, 10))); // espaço entre os cards
		    panelPrincipal.add(card);
		}
	}
}
