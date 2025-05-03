package cinemax.frontend.gerenciamentoFilmes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cinemax.backend.core.Backend;
import cinemax.backend.filmes.ClassificacaoIndicativa;
import cinemax.backend.filmes.Filme;
import cinemax.backend.filmes.Sessao;
import cinemax.frontend.controller.ControladorDeApp;
import cinemax.frontend.vendaDeIngressos.TelaEscolhaPoltrona;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class TelaEditarFilme extends JFrame  implements TelaManutencaoFilme{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ControladorDeApp app = ControladorDeApp.getInstancia();
	Backend bancos = app.getBackend();
	Filme filme = bancos.getBancoFilmes().obterFilmePorId(0);
	private JTextField textFieldNome;
	private JTextField textFieldDuracao;
	private JPanel panelSessoes = new JPanel();

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
	
	private void altualizarFilme(
			Filme filme, 
			String novoNome, 
			String novaSinopse, 
			String novaDuracaoTexto, 
			ClassificacaoIndicativa classificacaoIndicativa) {

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
				app.getBackend().getBancoFilmes().tentarAlterarClassificacaoIndicativa(filme.getId(), classificacaoIndicativa)
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

			JButton btnEditar = new JButton(iconeEditar); // ou seu ícone
			btnEditar.setBounds(110, 10, 40, 40);
			btnEditar.addActionListener(e -> {
				TelaEditarSessao telaEditarSessao = new TelaEditarSessao(sessao, TelaEditarFilme.this);
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

	// ----------------------------------------------------------------------

	/**
	 * Create the frame.
	 */
	public TelaEditarFilme(Filme filmeAtual) {
		if (filmeAtual != null) {
			this.filme = filmeAtual;
		}

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

		textFieldNome = new JTextField(filme.getNome());
		textFieldNome.setBounds(30, 38, 280, 26);
		panelPrincipal.add(textFieldNome);
		textFieldNome.setColumns(10);

		JLabel lblNewLabel = new JLabel("Nome");
		lblNewLabel.setBounds(30, 13, 46, 14);
		panelPrincipal.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Sinopse");
		lblNewLabel_1.setBounds(30, 76, 46, 14);
		panelPrincipal.add(lblNewLabel_1);

		JTextArea textAreaSinopse = new JTextArea(filme.getSinopse());
		// Ativa quebra automática de linha
		textAreaSinopse.setLineWrap(true);
		// Garante que a quebra respeite palavras (em vez de cortar no meio)
		textAreaSinopse.setWrapStyleWord(true);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(30, 101, 280, 74);
		panelPrincipal.add(scrollPane);

		scrollPane.setViewportView(textAreaSinopse);

		String duracao = Integer.toString(filme.getDuracaoEmMinutos());

		JLabel lblDurao = new JLabel("Duração(min)");
		lblDurao.setBounds(30, 186, 78, 14);
		panelPrincipal.add(lblDurao);

		textFieldDuracao = new JTextField(duracao);
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

		atualizarListaDeSessoes(panelSessoes, filme);

		JButton btnAdicionarSessao = new JButton("+");
		btnAdicionarSessao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TelaAdicionarSessao telaAdicionarSessao = new TelaAdicionarSessao(TelaEditarFilme.this,filme.getId());
				telaAdicionarSessao.setLocationRelativeTo(null);
				telaAdicionarSessao.setVisible(true);
				
				
				
			}
		});
		btnAdicionarSessao.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnAdicionarSessao.setBounds(651, 103, 59, 59);
		panelPrincipal.add(btnAdicionarSessao);
		
		JComboBox<ClassificacaoIndicativa> comboBoxClassificacaoIndicativa = new JComboBox<>(ClassificacaoIndicativa.values());
		comboBoxClassificacaoIndicativa.setSelectedItem(filme.getClassificacaoIndicativa());
		comboBoxClassificacaoIndicativa.setBounds(155, 211, 92, 26);
		panelPrincipal.add(comboBoxClassificacaoIndicativa);
		
		JLabel lblNewLabel_2 = new JLabel("Classificação Indicativa");
		lblNewLabel_2.setBounds(155, 186, 155, 14);
		panelPrincipal.add(lblNewLabel_2);

		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String novoNome = textFieldNome.getText();
				String novaSinopse = textAreaSinopse.getText();
				String duracaoTexto = textFieldDuracao.getText();
				ClassificacaoIndicativa classificacaoIndicativaSelecionada = (ClassificacaoIndicativa) comboBoxClassificacaoIndicativa.getSelectedItem();
				
				
				altualizarFilme(filme,novoNome,novaSinopse,duracaoTexto,classificacaoIndicativaSelecionada);
				
				TelaCrudFilme telaCrudFilme = new TelaCrudFilme();
				telaCrudFilme.setLocationRelativeTo(null);
				telaCrudFilme.setVisible(true);
				
				dispose();
			}
		});
		btnAtualizar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAtualizar.setBounds(299, 322, 164, 31);
		panelPrincipal.add(btnAtualizar);
		
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
		panelPrincipal.add(btnTeste);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TelaCrudFilme telaCrudFilme = new TelaCrudFilme();
				telaCrudFilme.setLocationRelativeTo(null);
				telaCrudFilme.setVisible(true);
				
				dispose();
				
			}
		});
		btnVoltar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnVoltar.setBounds(10, 427, 89, 23);
		contentPane.add(btnVoltar);
		
		

	}
}
