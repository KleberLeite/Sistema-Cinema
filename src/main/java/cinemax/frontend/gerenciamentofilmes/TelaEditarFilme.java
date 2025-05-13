package cinemax.frontend.gerenciamentofilmes;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cinemax.backend.core.Backend;
import cinemax.backend.filmes.ClassificacaoIndicativa;
import cinemax.backend.filmes.Filme;
import cinemax.backend.filmes.GeneroFilme;
import cinemax.backend.filmes.Sessao;
import cinemax.frontend.controller.ControladorDeApp;
import cinemax.frontend.estilizacao.Estilizador;
import cinemax.frontend.estilizacao.EstiloBotao;
import cinemax.frontend.estilizacao.JTextFieldEstilizado;
import cinemax.frontend.vendadeingressos.TelaEscolhaPoltrona;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class TelaEditarFilme extends JFrame  implements TelaManutencaoFilme{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ControladorDeApp app = ControladorDeApp.getInstancia();
	Backend bancos = app.getBackend();
	Filme filme = bancos.getBancoFilmes().obterFilmePorId(0);
	private List<JCheckBox> checkBoxesGeneros;
	private JPanel panelGeneros;
	private JTextField textFieldNome;
	private JTextField textFieldDuracao;
	private JPanel panelSessoes = new JPanel();
	private TelaAdicionarSessao telaAdicionarSessao;
	private TelaEditarSessao telaEditarSessao;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaEditarFilme frame = new TelaEditarFilme(null);
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					frame.setTitle("Cinemax");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------------------------
	// Criando e preparando os icones já redimensionando-os
	// ------------------------------------------------------------------------------------------------------------------

	ImageIcon iconeEditarParcial = new ImageIcon(getClass().getResource("/img/Editar.png"));
	Image imgIconeEditarParcial = iconeEditarParcial.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
	ImageIcon iconeEditar = new ImageIcon(imgIconeEditarParcial);

	ImageIcon iconeExcluirParcial = new ImageIcon(getClass().getResource("/img/Excluir.png"));
	Image imgIconeExcluirParcial = iconeExcluirParcial.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
	ImageIcon iconeExcluir = new ImageIcon(imgIconeExcluirParcial);

	// --------------------------------------------------------------------------------------------------------------------------------------------------------------
	// --------------------------------------------------------------------------------------------------------------------------------------------------------------

	// Methods utils -------------------------------------------------------
	
	private void altualizarFilme(
			Filme filme, 
			String novoNome, 
			String novaSinopse, 
			String novaDuracaoTexto, 
			ClassificacaoIndicativa classificacaoIndicativa,
			GeneroFilme[] generosSelecionados) {

		// Validação entra de durancao em numeros
		int novaDuracao = 0;
		try {
			novaDuracao = Integer.parseInt(novaDuracaoTexto);
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(null, "A duração precisa ser um número inteiro!", "Erro", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		boolean sucesso = (app.getBackend().getBancoFilmes().tentarAlterarNome(filme.getId(), novoNome) && 
				app.getBackend().getBancoFilmes().tentarAlterarSinopse(filme.getId(), novaSinopse) &&
				app.getBackend().getBancoFilmes().tentarAlterarDuracao(filme.getId(), novaDuracao) && 
				app.getBackend().getBancoFilmes().tentarAlterarClassificacaoIndicativa(filme.getId(), classificacaoIndicativa) &&
				app.getBackend().getBancoFilmes().alterarGeneros(filme.getId(), generosSelecionados)
				);
		if (sucesso) {
			JOptionPane.showMessageDialog(null, "Filme atualizado com sucesso!");
		} else {
			JOptionPane.showMessageDialog(null, "Erro ao atualizar filme.", "Erro", JOptionPane.ERROR_MESSAGE);
		}

	}
	
	public void atualizarListaDeSessoesPosEdicaoOuAdicao() {
	    atualizarListaDeSessoes(panelSessoes, this.filme); // já existente
	}

	public void atualizarListaDeSessoes(JPanel panelSessoes, Filme filme) {
	    panelSessoes.removeAll(); // limpa as sessões antigas

	    for (Sessao sessao : filme.obterTodasSessoes()) {
	        JPanel card = Estilizador.criarPainelArredondado(new Color(2, 17, 28), 10);
	        card.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5)); // alinhamento à esquerda, espaçamento entre itens
	        card.setPreferredSize(new Dimension(200, 40));
	        card.setMaximumSize(new Dimension(200, 40));
	        card.setBorder(new EmptyBorder(5, 10, 5, 10));

	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM HH:mm");
	        String sessaoFormatada = sessao.getInicio().format(formatter);

	        JLabel lblSessao = new JLabel(sessaoFormatada);
	        lblSessao.setForeground(Color.WHITE);
	        lblSessao.setFont(new Font("Tahoma", Font.BOLD, 14));

	        JButton btnEditar = new JButton(iconeEditar);
	        btnEditar.setPreferredSize(new Dimension(24, 24));
	        btnEditar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	        btnEditar.setBorderPainted(false);
	    	btnEditar.setContentAreaFilled(false);
	    	btnEditar.setFocusPainted(false);
	        btnEditar.addActionListener(e -> {
	            telaEditarSessao = new TelaEditarSessao(sessao, TelaEditarFilme.this);
	            telaEditarSessao.setLocationRelativeTo(null);
	            telaEditarSessao.setVisible(true);
	        });

	        JButton btnExcluir = new JButton(iconeExcluir);
	        btnExcluir.setPreferredSize(new Dimension(24, 24));
	        btnExcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	        btnExcluir.setBorderPainted(false);
	        btnExcluir.setContentAreaFilled(false);
	        btnExcluir.setFocusPainted(false);
	        btnExcluir.addActionListener(e -> {
	            boolean sucesso = app.getBackend().getBancoFilmes().tentarRemoverSessao(sessao.getId(), filme.getId());
	            if (!sucesso) {
	                JOptionPane.showMessageDialog(null, "Falha ao tentar Remover a sessão, tente novamente!", "Aviso",
	                        JOptionPane.WARNING_MESSAGE);
	            } else {
	                atualizarListaDeSessoes(panelSessoes, filme); // atualiza após excluir
	            }
	        });

	        // adiciona os componentes ao card
	        card.add(lblSessao);
	        card.add(btnEditar);
	        card.add(btnExcluir);

	        // adiciona o card à lista
	        panelSessoes.add(Box.createRigidArea(new Dimension(0, 10)));
	        panelSessoes.add(card);
	    }

	    panelSessoes.revalidate();
	    panelSessoes.repaint();
	}
	
	private GeneroFilme[] pegaOsGeneros(List<JCheckBox> checkBoxesGeneros) {
		List<GeneroFilme> generosSelecionados = new ArrayList<>();

		for (JCheckBox checkBox : checkBoxesGeneros) {
		    if (checkBox.isSelected()) {
		        generosSelecionados.add(GeneroFilme.valueOf(checkBox.getActionCommand()));
		    }
		}
		return  generosSelecionados.toArray(new GeneroFilme[0]);
	}
	
	private void geraCheckBoxesGeneros() {
	    checkBoxesGeneros = new ArrayList<>();

	    for (GeneroFilme genero : GeneroFilme.values()) {
	        JCheckBox checkBox = new JCheckBox(genero.toString());
	        checkBox.setActionCommand(genero.name()); 
	        Estilizador.estilizarCheckBox(checkBox, new Color(240,240,240), Color.BLACK, new Font("Tahoma", Font.BOLD, 13));
	        checkBoxesGeneros.add(checkBox);

	        checkBox.addItemListener(e -> {
	            long selecionados = checkBoxesGeneros.stream()
	                                    .filter(AbstractButton::isSelected)
	                                    .count();

	            if (selecionados > 3) {
	                checkBox.setSelected(false);
	                JOptionPane.showMessageDialog(null, "Você só pode selecionar até 3 gêneros.", "Limite atingido", JOptionPane.WARNING_MESSAGE);
	            }
	        });
	    }

	    for (JCheckBox checkBox : checkBoxesGeneros) {
	        panelGeneros.add(checkBox);
	    }
	}
	
	private void marcarGenerosSelecionados(Filme filme) {
		 List<GeneroFilme> generosDoFilme = Arrays.asList(filme.getGeneros());

	    for (JCheckBox checkBox : checkBoxesGeneros) {
	        GeneroFilme generoDoCheck = GeneroFilme.valueOf(checkBox.getActionCommand());

	        if (generosDoFilme.contains(generoDoCheck)) {
	            checkBox.setSelected(true);
	        }
	    }
	}
	

	// ----------------------------------------------------------------------

	/**
	 * Create the frame.
	 */
	public TelaEditarFilme(Filme filmeAtual) {
		if (filmeAtual != null) {
			this.filme = filmeAtual;
		}
		
		Estilizador.estilizarCheckBoxCinza();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(2, 18, 27));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setBackground(new Color(255, 255, 255));
		panelPrincipal.setBounds(10, 11, 764, 405);
		contentPane.add(panelPrincipal);
		panelPrincipal.setLayout(null);

		textFieldNome = new JTextFieldEstilizado(filme.getNome());
		textFieldNome.setBounds(30, 38, 280, 26);
		panelPrincipal.add(textFieldNome);

		JLabel lblNewLabel = new JLabel("Nome:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(30, 13, 117, 26);
		panelPrincipal.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Sinopse:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(30, 76, 117, 26);
		panelPrincipal.add(lblNewLabel_1);

		JTextArea textAreaSinopse = new JTextArea(filme.getSinopse());
		Estilizador.estilizarTextArea(textAreaSinopse);

		JScrollPane scrollPane = new JScrollPane(textAreaSinopse);
		Estilizador.estilizarScrollPane(scrollPane);

		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(30, 101, 280, 74);
		panelPrincipal.add(scrollPane);

		scrollPane.setViewportView(textAreaSinopse);

		String duracao = Integer.toString(filme.getDuracaoEmMinutos());

		JLabel lblDurao = new JLabel("Duração(min):");
		lblDurao.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDurao.setBounds(30, 186, 104, 26);
		panelPrincipal.add(lblDurao);

		textFieldDuracao = new JTextFieldEstilizado(duracao);
		textFieldDuracao.setBounds(30, 211, 78, 26);
		panelPrincipal.add(textFieldDuracao);

		JLabel lblSessoes = new JLabel("Sessoes:");
		lblSessoes.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSessoes.setBounds(425, 13, 78, 26);
		panelPrincipal.add(lblSessoes);

		panelSessoes = Estilizador.criarPainelArredondado(new Color( 240, 240, 240), 20);
		panelSessoes.setLayout(new BoxLayout(panelSessoes, BoxLayout.Y_AXIS)); // lista vertical
		panelSessoes.setBorder(new EmptyBorder(5, 5, 5, 5)); // margem geral

		JScrollPane scrollPaneSessoes = new JScrollPane(panelSessoes);
		Estilizador.estilizarScrollPane(scrollPaneSessoes);
		scrollPaneSessoes.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneSessoes.setOpaque(false);
		scrollPaneSessoes.setBorder(BorderFactory.createLineBorder(new Color( 0, 23, 10)));
		scrollPaneSessoes.setBounds(425, 38, 216, 196);
		panelPrincipal.add(scrollPaneSessoes);

		
		scrollPaneSessoes.setViewportView(panelSessoes);

		atualizarListaDeSessoes(panelSessoes, filme);

		JButton btnAdicionarSessao = new JButton("+");
		Estilizador.aplicarEstiloBotao(btnAdicionarSessao, EstiloBotao.PADRAO_ESCURECIDO);
		btnAdicionarSessao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				telaAdicionarSessao = new TelaAdicionarSessao(TelaEditarFilme.this,filme.getId());
				telaAdicionarSessao.setLocationRelativeTo(null);
				telaAdicionarSessao.setVisible(true);
				
				
				
			}
		});
		btnAdicionarSessao.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnAdicionarSessao.setBounds(651, 103, 59, 59);
		panelPrincipal.add(btnAdicionarSessao);
		
		JComboBox<ClassificacaoIndicativa> comboBoxClassificacaoIndicativa = new JComboBox<>(ClassificacaoIndicativa.values());
		comboBoxClassificacaoIndicativa.setBounds(186, 211, 72, 26);
		Estilizador.estilizarComboBoxClassificacaoIndicativa(comboBoxClassificacaoIndicativa);
		panelPrincipal.add(comboBoxClassificacaoIndicativa);
		
		JLabel lblGenero = new JLabel("Gênero:");
		lblGenero.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblGenero.setBounds(30, 248, 117, 26);
		panelPrincipal.add(lblGenero);

		panelGeneros = Estilizador.criarPainelArredondado(new Color( 240, 240, 240), 20);
		panelGeneros.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelGeneros.setLayout(new BoxLayout(panelGeneros, BoxLayout.Y_AXIS));

		JScrollPane scrollPaneGeneros = new JScrollPane(panelGeneros);
		Estilizador.estilizarScrollPane(scrollPaneGeneros);
		scrollPaneGeneros.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneGeneros.setBounds(30, 271, 145, 100); 
		scrollPaneGeneros.setViewportView(panelGeneros);
		panelPrincipal.add(scrollPaneGeneros);
		
		
		// Lista para guardar os checkboxes
		geraCheckBoxesGeneros();
		
		marcarGenerosSelecionados(filme);
		
		JLabel lblNewLabel_2 = new JLabel("Classificação Indicativa:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setBounds(155, 186, 155, 26);
		panelPrincipal.add(lblNewLabel_2);

		JButton btnAtualizar = new JButton("Atualizar");
		Estilizador.aplicarEstiloBotao(btnAtualizar, EstiloBotao.PADRAO_ESCURECIDO);
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String novoNome = textFieldNome.getText();
				String novaSinopse = textAreaSinopse.getText();
				String duracaoTexto = textFieldDuracao.getText();
				ClassificacaoIndicativa classificacaoIndicativaSelecionada = (ClassificacaoIndicativa) comboBoxClassificacaoIndicativa.getSelectedItem();
				GeneroFilme[] generosSelecionados = pegaOsGeneros(checkBoxesGeneros);
				
				altualizarFilme(filme,novoNome,novaSinopse,duracaoTexto,classificacaoIndicativaSelecionada,generosSelecionados);
				
				TelaCrudFilme telaCrudFilme = new TelaCrudFilme();
				telaCrudFilme.setLocationRelativeTo(null);
				telaCrudFilme.setVisible(true);
				
				dispose();
			}
		});
		btnAtualizar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAtualizar.setBounds(299, 322, 164, 31);
		panelPrincipal.add(btnAtualizar);
		
		/*
		JButton btnTeste = new JButton("Teste");
		btnTeste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("============================================================");
				for(Sessao sessao : filme.obterTodasSessoes()) {
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
					String data = sessao.getInicio().format(formatter);
					System.out.println("-----------------------------------------------------");
					System.out.println("Id Sessao: "+sessao.getId());
					System.out.println("Id Filme: "+sessao.getFilme().getId());
					System.out.println("Sala: "+sessao.getSala().getIdSala());
					System.out.println("Inicio: "+data);
					System.out.println("Filme: "+ sessao.getFilme().getNome());
					System.out.println("-----------------------------------------------------");
				}
				System.out.println("============================================================");
			}
		});
		btnTeste.setBounds(317, 245, 89, 23);
		panelPrincipal.add(btnTeste);*/
		
		JButton btnVoltar = new JButton("Voltar");
		Estilizador.aplicarEstiloBotao(btnVoltar, EstiloBotao.CLARO_UNIFICADO);
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TelaCrudFilme telaCrudFilme = new TelaCrudFilme();
				telaCrudFilme.setLocationRelativeTo(null);
				telaCrudFilme.setVisible(true);
				
				dispose();
				
				if (telaAdicionarSessao != null && telaAdicionarSessao.isDisplayable()) {
				    telaAdicionarSessao.dispose();
				}
				if (telaEditarSessao != null && telaEditarSessao.isDisplayable()) {
					telaEditarSessao.dispose();
				}

				
			}
		});
		btnVoltar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnVoltar.setBounds(10, 427, 89, 23);
		contentPane.add(btnVoltar);
		
		

	}
}
