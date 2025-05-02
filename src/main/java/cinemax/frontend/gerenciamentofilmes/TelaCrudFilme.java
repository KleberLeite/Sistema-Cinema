package cinemax.frontend.gerenciamentofilmes;

import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cinemax.backend.core.Backend;
import cinemax.backend.filmes.Filme;
import cinemax.backend.filmes.IBancoDeDadosFilme;
import cinemax.backend.filmes.Sessao;
import cinemax.frontend.controller.ControladorDeApp;
import cinemax.frontend.vendadeingressos.TelaEscolhaPoltrona;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.ScrollPaneConstants;
import java.awt.Font;
import java.time.format.DateTimeFormatter;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class TelaCrudFilme extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ControladorDeApp app = ControladorDeApp.getInstancia();
	Backend bancos = app.getBackend();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCrudFilme frame = new TelaCrudFilme();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	//Methods utils --------------------------------------------------------------------------------
	
	private void atualizarListaDeFilmes(JPanel panelListaFilmes, Filme[] filmes) {
		panelListaFilmes.removeAll();
		
		for (Filme filme : filmes) {
		    JPanel card = new JPanel();
		    card.setLayout(null); 
		    card.setPreferredSize(new Dimension(1400, 50));
		    card.setMaximumSize(new Dimension(1400, 50));
		    card.setBackground(new Color(230, 210, 250));
		    card.setBorder(new EmptyBorder(10, 30, 10, 10));

		    JLabel lblNome = new JLabel(filme.getNome());
		    lblNome.setFont(new Font("Tahoma", Font.BOLD, 14));
		    lblNome.setBounds(20, 10, 400, 25);
		    card.add(lblNome);

		    JButton btnEditar = new JButton("Editar");
		    btnEditar.addActionListener(e -> {
	              
		    	 TelaEditarFilme telaEditarFilme = new TelaEditarFilme(filme);
		    	 telaEditarFilme.setLocationRelativeTo(null);
		    	 telaEditarFilme.setVisible(true);
		    	 
		    	 dispose();
	               
	        });
		    btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		    btnEditar.setBounds(500, 10, 80, 30);
		    card.add(btnEditar);

		    JButton btnExcluir = new JButton("Excluir");
		    btnExcluir.addActionListener(e -> {
		    	 if(!app.getBackend().getBancoFilmes().tentarRemoverFilme(filme.getId())) {
		    		 JOptionPane.showMessageDialog(null, "Falha ao tentar Remover o Filme, tente novamente!", "Aviso", JOptionPane.WARNING_MESSAGE); 
		    	 }else{
		    		 atualizarListaDeFilmes(panelListaFilmes, app.getBackend().getBancoFilmes().obterTodosFilmes());
		    	 }
	             
	        });
		    btnExcluir.setFont(new Font("Tahoma", Font.PLAIN, 12));
		    btnExcluir.setBounds(590, 10, 80, 30);
		    card.add(btnExcluir);

		    panelListaFilmes.add(Box.createRigidArea(new Dimension(0, 10))); // espaço entre os cards
		    panelListaFilmes.add(card);
		}
		
		panelListaFilmes.revalidate();
		panelListaFilmes.repaint();
	}
	
	//----------------------------------------------------------------------------------------------
	

	/**
	 * Create the frame.
	 */
	public TelaCrudFilme() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 64, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setBounds(10, 11, 764, 405);
		contentPane.add(panelPrincipal);
		panelPrincipal.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 85, 744, 274);
		panelPrincipal.add(scrollPane);
		
		JPanel panelListaFilmes = new JPanel();
		panelListaFilmes.setLayout(new BoxLayout(panelListaFilmes, BoxLayout.Y_AXIS));
		//panelListaFilmes.setBackground(new Color(0, 64, 128)); // mesma cor do fundo
		scrollPane.setViewportView(panelListaFilmes);
		
		JButton btnAdicionarFilme = new JButton("Adicionar");
		btnAdicionarFilme.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAdicionarFilme.setBounds(323, 364, 111, 30);
		panelPrincipal.add(btnAdicionarFilme);
		
		JLabel lblListaDosFilmes = new JLabel("Lista dos Filmes Cadastrados:");
		lblListaDosFilmes.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblListaDosFilmes.setBounds(10, 21, 513, 40);
		panelPrincipal.add(lblListaDosFilmes);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnVoltar.setBounds(10, 427, 89, 23);
		contentPane.add(btnVoltar);
		
		atualizarListaDeFilmes(panelListaFilmes,app.getBackend().getBancoFilmes().obterTodosFilmes());

	}
}
