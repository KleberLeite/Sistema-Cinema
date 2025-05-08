package cinemax.frontend.vendadeingressos;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import cinemax.backend.filmes.Filme;
import cinemax.backend.filmes.GeneroFilme;
import cinemax.backend.filmes.IBancoDeDadosFilme;
import cinemax.backend.filmes.Sessao;
import cinemax.frontend.PaginasGeranteeFuncionario.PaginaPrincipal;
import cinemax.frontend.controller.ControladorDeApp;

import javax.swing.JScrollPane;
import java.awt.Font;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaEscolhaFilme extends JFrame {
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JLabel lblUseOFiltro = new JLabel();
	private LocalDate diaSelecionado;
	private List<JCheckBox> checkBoxesGeneros;
	JPanel painelListaFilmes;
	private IBancoDeDadosFilme bancoFilmes = ControladorDeApp.getInstancia().getBackend().getBancoFilmes();

	// Launch the application.
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaEscolhaFilme frame = new TelaEscolhaFilme();
					frame.setLocationRelativeTo(null);
					frame.setSize(800, 500);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Create the frame.
	public TelaEscolhaFilme() {				
		gerarTela();
	}
	
	private void geraCheckBoxesGeneros(JPanel panelGeneros) {
	    checkBoxesGeneros = new ArrayList<>();

	    for (GeneroFilme genero : GeneroFilme.values()) {
	        JCheckBox checkBox = new JCheckBox(genero.toString());
	        checkBox.setActionCommand(genero.name()); 
	        checkBoxesGeneros.add(checkBox);

	        checkBox.addItemListener(e -> {
	            
	        	Filme[] filmesHoje;
	    		GeneroFilme[] generosSelecionados = pegaOsGeneros(checkBoxesGeneros);
	    		if(checkBoxesGeneros==null) filmesHoje = bancoFilmes.obterTodosFilmesNoDia(diaSelecionado);
	    		else filmesHoje = bancoFilmes.obterTodosFilmesNoDia(diaSelecionado, generosSelecionados);

	    		atualizarListaFilmesPorFiltroDia(filmesHoje, painelListaFilmes, diaSelecionado);
	        	
	        });
	    }

	    for (JCheckBox checkBox : checkBoxesGeneros) {
	        panelGeneros.add(checkBox);
	    }
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

	// Lista todos os filmes
	private void atualizarListaFilmesParaOHoje(JPanel painelListaFilmes) {
		painelListaFilmes.removeAll();

		LocalDate hoje = LocalDate.now();
		diaSelecionado = hoje;
		Filme[] filmesHoje;
		GeneroFilme[] generosSelecionados = pegaOsGeneros(checkBoxesGeneros);
		if(checkBoxesGeneros==null) filmesHoje = bancoFilmes.obterTodosFilmesNoDia(hoje);
		else filmesHoje = bancoFilmes.obterTodosFilmesNoDia(hoje, generosSelecionados);

		atualizarListaFilmesPorFiltroDia(filmesHoje, painelListaFilmes, hoje);

		painelListaFilmes.revalidate();
		painelListaFilmes.repaint();
	}

	private void atualizarListaFilmesPorFiltroDia(
		Filme[] filmesNoDia,
		JPanel painelListaFilmes,
		LocalDate diaFiltro
	) {
		painelListaFilmes.removeAll();

		for (Filme filme : filmesNoDia) {
			JPanel card = gerarCardFilme();
			
			JPanel panelNomeEClassificacao = gerarPanelNomeEClassificacao(filme);
			card.add(panelNomeEClassificacao);

			JLabel lblDuracao = new JLabel("Duração: " + filme.getDuracaoEmMinutos() + " min");
			lblDuracao.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblDuracao.setBounds(20, 35, 200, 20);
			card.add(lblDuracao);

			JLabel lblTituloSessoes = new JLabel("Sessões:");
			lblTituloSessoes.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblTituloSessoes.setBounds(20, 72, 100, 20);
			card.add(lblTituloSessoes);
			
			JPanel painelSessoes = gerarPainelSessoes();
			card.add(painelSessoes);

			for (Sessao sessao : bancoFilmes.obterTodasSessoesDoFilmeNoDia(filme.getId(), diaFiltro)) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
				String sessaoFormatada = sessao.getInicio().format(formatter);

				JButton btnSessao = new JButton(sessaoFormatada);

				btnSessao.addActionListener(e -> {
					aoSelecionarSessao(sessao);
				});
				painelSessoes.add(btnSessao);

				String salaFormatada = Integer.toString(sessao.getSala().getIdSala());
				JLabel sala = new JLabel("Sala " + salaFormatada, SwingConstants.CENTER);

				// Cria um painel vertical para colocar o label em cima do botão
				JPanel painelJuntaSessaoESala = new JPanel();
				painelJuntaSessaoESala.setLayout(new BoxLayout(painelJuntaSessaoESala, BoxLayout.Y_AXIS));
				painelJuntaSessaoESala.add(sala);
				painelJuntaSessaoESala.add(btnSessao);

				sala.setAlignmentX(Component.CENTER_ALIGNMENT);
				btnSessao.setAlignmentX(Component.CENTER_ALIGNMENT);

				painelSessoes.add(painelJuntaSessaoESala);
			}

			card.add(painelSessoes);

			painelListaFilmes.add(Box.createRigidArea(new Dimension(0, 10))); // espaço entre os cards
			painelListaFilmes.add(card);
		}
		painelListaFilmes.revalidate();
		painelListaFilmes.repaint();
	}
	private JPanel gerarCardFilme() {
		JPanel card = new JPanel();
		card.setLayout(null);
		card.setPreferredSize(new Dimension(1355, 120)); // Altura aumentada
		card.setMaximumSize(new Dimension(1355, 120));
		card.setBackground(new Color(230, 230, 250));
		card.setBorder(new EmptyBorder(100, 10, 10, 10));
		return card;
	}

	
	private JPanel gerarPanelNomeEClassificacao(Filme filme) {
		JPanel panelNomeEClassificacao = new JPanel();
		panelNomeEClassificacao.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelNomeEClassificacao.setBounds(15, 10, 800, 25);
		panelNomeEClassificacao.setBackground(new Color(230, 230, 250));
		panelNomeEClassificacao.setBorder(new EmptyBorder(0, 0, 0, 0));
		
		JLabel lblNome = new JLabel(filme.getNome());
		lblNome.setBounds(20, 10, 490, 25);
		panelNomeEClassificacao.add(lblNome);
		
		JLabel lblClassificacao = new JLabel("     " + filme.getClassificacaoIndicativa().toString());
		lblClassificacao.setBounds(20, 10, 400, 25);
		panelNomeEClassificacao.add(lblClassificacao);
		
		return panelNomeEClassificacao;
	}
	
	private JPanel gerarPainelSessoes() {
		JPanel painelSessoes = new JPanel();
		painelSessoes.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
		painelSessoes.setBounds(100, 60, 400, 50);
		painelSessoes.setOpaque(false);
		return painelSessoes;
	}
	
	private void aoSelecionarSessao(Sessao sessao) {
		TelaEscolhaPoltrona telaEscolhaPoltrona = new TelaEscolhaPoltrona(sessao);
		telaEscolhaPoltrona.setLocationRelativeTo(null);
		telaEscolhaPoltrona.setVisible(true);

		dispose();
	}

	private void geraPanelFiltroDeData(JPanel panelDias, JPanel painelListaFilmes) {
		for (int i = 0; i < 7; i++) {
			LocalDate hoje = LocalDate.now().plusDays(i);

			String diaFormatado = hoje.getDayOfWeek().getDisplayName(TextStyle.SHORT, new Locale("pt", "BR"))
					.toUpperCase();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM");
			String diaAtual = hoje.format(formatter);

			JLabel diaDaSemana = new JLabel(diaFormatado, SwingConstants.CENTER);// Deixar o texto no centro do label
			JButton botaoDia = new JButton(diaAtual);

			// Cria um painel vertical para colocar o label em cima do botão
			JPanel painelJuntaDataEDia = new JPanel();
			painelJuntaDataEDia.setLayout(new BoxLayout(painelJuntaDataEDia, BoxLayout.Y_AXIS));
			painelJuntaDataEDia.add(diaDaSemana);
			painelJuntaDataEDia.add(botaoDia);

			// Centraliza os elementos no painel
			diaDaSemana.setAlignmentX(Component.CENTER_ALIGNMENT);
			botaoDia.setAlignmentX(Component.CENTER_ALIGNMENT);

			final LocalDate dataSelecionada = hoje;

			botaoDia.addActionListener(e -> {
				diaSelecionado = dataSelecionada;
				lblUseOFiltro.setVisible(false);
				Filme[] filmesNoDia;
				GeneroFilme[] generosSelecionados = pegaOsGeneros(checkBoxesGeneros);
				if(checkBoxesGeneros==null) filmesNoDia = bancoFilmes.obterTodosFilmesNoDia(dataSelecionada);
				else filmesNoDia = bancoFilmes.obterTodosFilmesNoDia(dataSelecionada, generosSelecionados);

				atualizarListaFilmesPorFiltroDia(filmesNoDia, painelListaFilmes, dataSelecionada);
			});

			panelDias.add(painelJuntaDataEDia);
		}

	}

	private void gerarTela() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBackground(new Color(0, 64, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFiltroGenero = new JLabel("Filtro por gênero:");
		lblFiltroGenero.setForeground(new Color(255, 255, 255));
		lblFiltroGenero.setFont(new Font("Sitka Heading", Font.BOLD, 15));
		lblFiltroGenero.setBounds(630, 57, 144, 23);
		contentPane.add(lblFiltroGenero);
		
		JScrollPane scrollPaneGeneros = new JScrollPane();
		scrollPaneGeneros.setBounds(595, 91, 179, 222);
		scrollPaneGeneros.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		contentPane.add(scrollPaneGeneros);
		
		JPanel panelGeneros = new JPanel();
		scrollPaneGeneros.setViewportView(panelGeneros);
		panelGeneros.setLayout(new BoxLayout(panelGeneros, BoxLayout.Y_AXIS));
				
		geraCheckBoxesGeneros(panelGeneros);

		painelListaFilmes = new JPanel();
		painelListaFilmes.setLayout(new BoxLayout(painelListaFilmes, BoxLayout.Y_AXIS)); // lista vertical
		painelListaFilmes.setBackground(Color.WHITE);
		painelListaFilmes.setBorder(new EmptyBorder(10, 10, 10, 10)); // margem geral

		// Criar os cards dos filmes
		atualizarListaFilmesParaOHoje(painelListaFilmes);

		JPanel panelDatas = new JPanel();
		panelDatas.setBounds(27, 11, 558, 69);
		contentPane.add(panelDatas);
		panelDatas.setLayout(null);

		// Criando um JScrollPane para rolar horizontalmente o painel de dias da semana
		JScrollPane scrollPaneDatas = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_NEVER,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPaneDatas.setBounds(200, 0, 358, 70);
		panelDatas.add(scrollPaneDatas);
		
				// Criando o painel geral do filtro dos dias da semana
				JPanel panelDias = new JPanel();
				scrollPaneDatas.setViewportView(panelDias);
				panelDias.setLayout(new FlowLayout(FlowLayout.LEFT)); // Usando FlowLayout para disposição horizontal
				
						// Criando os botões para cada dia da semana
						geraPanelFiltroDeData(panelDias, painelListaFilmes);

		JLabel lblSelecionarDia = new JLabel("Selecione o dia: ");
		lblSelecionarDia.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSelecionarDia.setBounds(24, 19, 126, 30);
		panelDatas.add(lblSelecionarDia);

		// Agora colocar isso num JScrollPane
		JScrollPane scrollPaneFilmes = new JScrollPane(painelListaFilmes);
		scrollPaneFilmes.setBounds(27, 91, 558, 330);
		scrollPaneFilmes.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneFilmes.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		contentPane.add(scrollPaneFilmes);
		
		

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PaginaPrincipal.abrirPaginaPrincipal();
				dispose();
			}
		});
		btnVoltar.setBounds(10, 427, 89, 23);
		contentPane.add(btnVoltar);

		lblUseOFiltro.setText("AVISO: Atualize a lista de filme através dos filtros");
		lblUseOFiltro.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblUseOFiltro.setForeground(new Color(255, 255, 255));
		lblUseOFiltro.setBounds(150, 432, 449, 18);
		contentPane.add(lblUseOFiltro);
		
		
		
		
	}
}
