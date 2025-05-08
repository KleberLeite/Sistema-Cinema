package cinemax.frontend.gerenciamentofilmes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
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
import cinemax.frontend.vendadeingressos.TelaEscolhaPoltrona;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class TelaAdicionarFilme extends JFrame implements TelaManutencaoFilme{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ControladorDeApp app = ControladorDeApp.getInstancia();
	private int idFilme = -1;
	private JButton btnAdicionarSessao = new JButton("+");;
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
	Image imgIconeEditarParcial = iconeEditarParcial.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
	ImageIcon iconeEditar = new ImageIcon(imgIconeEditarParcial);

	ImageIcon iconeExcluirParcial = new ImageIcon(getClass().getResource("/img/Excluir.png"));
	Image imgIconeExcluirParcial = iconeExcluirParcial.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
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

			JButton btnEditar = new JButton(iconeEditar);
			btnEditar.setBounds(110, 10, 40, 40);
			btnEditar.addActionListener(e -> {
				telaEditarSessao = new TelaEditarSessao(sessao, TelaAdicionarFilme.this);
				telaEditarSessao.setLocationRelativeTo(null);
				telaEditarSessao.setVisible(true);

				
			});
			card.add(btnEditar);
			
			
			JButton btnExcluir = new JButton(iconeExcluir); // ou seu ícone
			btnExcluir.setBounds(160, 10, 40, 40);
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

	        checkBox.addItemListener(e -> {
	        	if (e.getStateChange() == ItemEvent.SELECTED) {
	        		checkBoxesGeneros.add(checkBox);
	            } else if (e.getStateChange() == ItemEvent.DESELECTED) {
	            	checkBoxesGeneros.remove(checkBox);
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

		textFieldNome = new JTextField();
		textFieldNome.setBounds(30, 38, 280, 26);
		panelPrincipal.add(textFieldNome);
		textFieldNome.setColumns(10);

		JLabel lblNewLabel = new JLabel("Nome");
		lblNewLabel.setBounds(30, 13, 46, 14);
		panelPrincipal.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Sinopse");
		lblNewLabel_1.setBounds(30, 76, 46, 14);
		panelPrincipal.add(lblNewLabel_1);

		textAreaSinopse = new JTextArea();
		// Ativa quebra automática de linha
		textAreaSinopse.setLineWrap(true);
		// Garante que a quebra respeite palavras (em vez de cortar no meio)
		textAreaSinopse.setWrapStyleWord(true);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(30, 101, 280, 74);
		panelPrincipal.add(scrollPane);

		scrollPane.setViewportView(textAreaSinopse);

		JLabel lblDurao = new JLabel("Duração(min)");
		lblDurao.setBounds(30, 186, 78, 14);
		panelPrincipal.add(lblDurao);

		textFieldDuracao = new JTextField();
		textFieldDuracao.setColumns(10);
		textFieldDuracao.setBounds(30, 211, 78, 26);
		panelPrincipal.add(textFieldDuracao);

		JLabel lblSessoes = new JLabel("Sessoes:");
		lblSessoes.setBounds(425, 13, 64, 14);
		panelPrincipal.add(lblSessoes);

		JScrollPane scrollPaneSessoes = new JScrollPane();
		scrollPaneSessoes.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneSessoes.setBounds(425, 38, 216, 196);
		panelPrincipal.add(scrollPaneSessoes);

		
		panelSessoes.setLayout(new BoxLayout(panelSessoes, BoxLayout.Y_AXIS)); // lista vertical
		panelSessoes.setBackground(Color.WHITE);
		panelSessoes.setBorder(new EmptyBorder(5, 5, 5, 5)); // margem geral

		scrollPaneSessoes.setViewportView(panelSessoes);

		//btnAdicionarSessao = new JButton("+");
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
		
		JLabel lblNewLabel_2 = new JLabel("Classificação Indicativa");
		lblNewLabel_2.setBounds(118, 186, 117, 14);
		panelPrincipal.add(lblNewLabel_2);
		
		comboBoxClassificacaoIndicativa = new JComboBox<>(ClassificacaoIndicativa.values());
		comboBoxClassificacaoIndicativa.setBounds(141, 211, 72, 26);
		panelPrincipal.add(comboBoxClassificacaoIndicativa);
		
		JLabel lblGenero = new JLabel("Gênero");
		lblGenero.setBounds(30, 248, 117, 14);
		panelPrincipal.add(lblGenero);

		JScrollPane scrollPaneGeneros = new JScrollPane();
		scrollPaneGeneros.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneGeneros.setBounds(30, 271, 120, 100); // ajuste o tamanho
		panelPrincipal.add(scrollPaneGeneros);
		
		panelGeneros = new JPanel();
		scrollPaneGeneros.setViewportView(panelGeneros);
		panelGeneros.setLayout(new BoxLayout(panelGeneros, BoxLayout.Y_AXIS));
		
		// Lista para guardar os checkboxes
		geraCheckBoxesGeneros();

		JButton btnAtualizar = new JButton("Atualizar");
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

