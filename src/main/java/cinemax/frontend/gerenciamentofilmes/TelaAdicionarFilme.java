package cinemax.frontend.gerenciamentofilmes;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import cinemax.backend.filmes.ClassificacaoIndicativa;
import cinemax.backend.filmes.Filme;
import cinemax.backend.filmes.GeneroFilme;
import cinemax.backend.filmes.Sessao;
import cinemax.frontend.controller.ControladorDeApp;
import cinemax.frontend.estilizacao.Estilizador;
import cinemax.frontend.estilizacao.EstiloBotao;
import cinemax.frontend.estilizacao.JTextFieldEstilizado;

public class TelaAdicionarFilme extends JFrame implements TelaManutencaoFilme{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ControladorDeApp app = ControladorDeApp.getInstancia();
	private int idFilme = -1;
	private JButton btnAdicionarSessao;
	private JTextField textFieldNome;
	private JTextField textFieldDuracao;
	private JTextArea textAreaSinopse;
	private JComboBox<ClassificacaoIndicativa> comboBoxClassificacaoIndicativa;
	private List<JCheckBox> checkBoxesGeneros;
	private JPanel panelGeneros;
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
					TelaAdicionarFilme frame = new TelaAdicionarFilme();
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
	
	private void adicionarFilme(
			Filme filme, 
			String novoNome, 
			String novaSinopse, 
			String novaDuracaoTexto, 
			ClassificacaoIndicativa classificacaoIndicativa,
			GeneroFilme[] generoFilmeSelecionado) {

		// Validação entra de durancao em numeros
		int novaDuracao = 0;
		try {
			novaDuracao = Integer.parseInt(novaDuracaoTexto);
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(null, "A duração precisa ser um número inteiro!", "Erro",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		int idOuFalse = app.getBackend().getBancoFilmes().tentarAdicionarFilme(novoNome, novaSinopse, generoFilmeSelecionado, novaDuracao, classificacaoIndicativa);
		
		if (idOuFalse == -1) {//Deu erro ao adicionar o filme
			JOptionPane.showMessageDialog(null, "Erro ao adicionar filme.", "Erro", JOptionPane.ERROR_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "Filme criado com sucesso, não é possivel mais alterá-lo por aqui!");
			this.idFilme = idOuFalse;
			btnAdicionarSessao.setEnabled(true);
			textFieldNome.setEnabled(false);
			textFieldDuracao.setEnabled(false);
			textAreaSinopse.setEnabled(false);
			comboBoxClassificacaoIndicativa.setEnabled(false);
			desativarCheckBoxes();
			JOptionPane.showMessageDialog(null, "Adicione sessões ao filme!");
		}

	}
	
	public void atualizarListaDeSessoesPosEdicaoOuAdicao() {
	    atualizarListaDeSessoes(panelSessoes, app.getBackend().getBancoFilmes().obterFilmePorId(idFilme)); // já existente
	}

	public void atualizarListaDeSessoes(JPanel panelSessoes, Filme filme) {
		panelSessoes.removeAll(); // limpa as sessões antigas

		for (Sessao sessao : filme.obterTodasSessoes()) {
			JPanel card = Estilizador.criarPainelArredondado(new Color(2, 17, 28), 10);
			card.setLayout(null);
			card.setPreferredSize(new Dimension(400, 30));
			card.setMaximumSize(new Dimension(400, 30));
			card.setBorder(new EmptyBorder(5, 5, 5, 5));

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM HH:mm");
			String sessaoFormatada = sessao.getInicio().format(formatter);

			JLabel lblSessao = new JLabel(sessaoFormatada);
			lblSessao.setForeground(new Color(255,255,255));
			lblSessao.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblSessao.setBounds(20, 2, 400, 25);
			card.add(lblSessao);

			JButton btnEditar = new JButton(iconeEditar);
			btnEditar.setBounds(125, 4, 20, 20);
			btnEditar.addActionListener(e -> {
				telaEditarSessao = new TelaEditarSessao(sessao, TelaAdicionarFilme.this);
				telaEditarSessao.setLocationRelativeTo(null);
				telaEditarSessao.setVisible(true);

				
			});
			card.add(btnEditar);
			
			
			JButton btnExcluir = new JButton(iconeExcluir);
			btnExcluir.setBounds(160, 4, 20, 20);
			btnExcluir.addActionListener(e -> {
				boolean sucesso = app.getBackend().getBancoFilmes().tentarRemoverSessao(sessao.getId(), filme.getId());
				if (!sucesso) {
					JOptionPane.showMessageDialog(null, "Falha ao tentar Remover a sessão, tente novamente!", "Aviso",
							JOptionPane.WARNING_MESSAGE);
				} else {
					atualizarListaDeSessoes(panelSessoes, filme); // atualiza após excluir
				}
			});
			card.add(btnExcluir);

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
	
	private void desativarCheckBoxes() {
		for (JCheckBox checkBox : checkBoxesGeneros) {
		    checkBox.setEnabled(false);
		}
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


	// ----------------------------------------------------------------------

	/**
	 * Create the frame.
	 */
	public TelaAdicionarFilme() {
		Estilizador.estilizarCheckBoxCinza();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(2, 18, 27));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setBounds(10, 11, 764, 405);
		panelPrincipal.setBackground(new Color(255, 255, 255));
		contentPane.add(panelPrincipal);
		panelPrincipal.setLayout(null);

		textFieldNome = new JTextFieldEstilizado(null);
		textFieldNome.setBounds(30, 38, 280, 26);
		panelPrincipal.add(textFieldNome);
		textFieldNome.setColumns(10);

		JLabel lblNewLabel = new JLabel("Nome:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(30, 13, 120, 19);
		panelPrincipal.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Sinopse:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(30, 74, 141, 26);
		panelPrincipal.add(lblNewLabel_1);

		JTextArea textAreaSinopse = new JTextArea();
		Estilizador.estilizarTextArea(textAreaSinopse);

		JScrollPane scrollPane = new JScrollPane(textAreaSinopse);
		Estilizador.estilizarScrollPane(scrollPane);

		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(30, 101, 280, 74);
		panelPrincipal.add(scrollPane);

		scrollPane.setViewportView(textAreaSinopse);

		JLabel lblDurao = new JLabel("Duração(min):");
		lblDurao.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDurao.setBounds(30, 186, 103, 26);
		panelPrincipal.add(lblDurao);

		textFieldDuracao = new JTextFieldEstilizado(null);
		textFieldDuracao.setColumns(10);
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

		btnAdicionarSessao = new JButton("+");
		Estilizador.aplicarEstiloBotao(btnAdicionarSessao, EstiloBotao.PADRAO_ESCURECIDO);
		btnAdicionarSessao.setEnabled(false);
		btnAdicionarSessao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				telaAdicionarSessao = new TelaAdicionarSessao(TelaAdicionarFilme.this, idFilme);
				telaAdicionarSessao.setLocationRelativeTo(null);
				telaAdicionarSessao.setVisible(true);
				
			}
		});
		btnAdicionarSessao.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnAdicionarSessao.setBounds(651, 103, 59, 59);
		panelPrincipal.add(btnAdicionarSessao);
		
		JLabel lblNewLabel_2 = new JLabel("Classificação Indicativa:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setBounds(143, 186, 164, 26);
		panelPrincipal.add(lblNewLabel_2);
		
		try {
		    UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
		    e.printStackTrace();
		}

		comboBoxClassificacaoIndicativa = new JComboBox<>(ClassificacaoIndicativa.values());
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

		JButton btnAtualizar = new JButton("Atualizar");
		Estilizador.aplicarEstiloBotao(btnAtualizar, EstiloBotao.PADRAO_ESCURECIDO);
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String novoNome = textFieldNome.getText();
				String novaSinopse = textAreaSinopse.getText();
				String duracaoTexto = textFieldDuracao.getText();
				ClassificacaoIndicativa classificacaoIndicativaSelecionada = (ClassificacaoIndicativa) comboBoxClassificacaoIndicativa.getSelectedItem();
				GeneroFilme[] generosSelecionados = pegaOsGeneros(checkBoxesGeneros);
				
				
				if(!btnAdicionarSessao.isEnabled()) { 
					adicionarFilme(app.getBackend().getBancoFilmes().obterFilmePorId(idFilme),
							novoNome,
							novaSinopse,
							duracaoTexto,
							classificacaoIndicativaSelecionada,
							generosSelecionados);
				}else {
					JOptionPane.showMessageDialog(null, "Filme e sessões adicionados com sucesso");
					
					TelaCrudFilme telaCrudFilme = new TelaCrudFilme();
					telaCrudFilme.setLocationRelativeTo(null);
					telaCrudFilme.setVisible(true);
					
					dispose();
				}
			}
		});
		btnAtualizar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAtualizar.setBounds(299, 322, 164, 31);
		panelPrincipal.add(btnAtualizar);
		
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

