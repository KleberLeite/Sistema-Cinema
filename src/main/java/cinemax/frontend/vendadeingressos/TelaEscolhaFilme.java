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

import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import cinemax.backend.core.Backend;
import cinemax.backend.filmes.BancoDeDadosFilme;
import cinemax.backend.filmes.ClassificacaoIndicativa;
import cinemax.backend.filmes.Filme;
import cinemax.backend.filmes.Sessao;
import cinemax.backend.salas.BancoDeDadosSala;
import cinemax.frontend.PaginasGeranteeFuncionario.Gerente;
import cinemax.frontend.PaginasGeranteeFuncionario.PaginaPrincipal;
import cinemax.frontend.controller.ControladorDeApp;
import cinemax.frontend.model.DadosFilme;
import cinemax.frontend.model.ModeloTabela;

import javax.swing.JScrollPane;
import java.awt.Font;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaEscolhaFilme extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JLabel lblUseOFiltro = new JLabel();
	private ControladorDeApp app = ControladorDeApp.getInstancia();
	Backend bancos = app.getBackend();

	/**
	 * Launch the application.
	 */
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
	
	//Lista absolutamente todos os filmes
	private void atualizarListaFilmesParaOHoje(JPanel painelListaFilmes) {
		painelListaFilmes.removeAll();
		
		LocalDate hoje = LocalDate.now();
        Filme[] filmesHoje = app.getBackend().getBancoFilmes().obterTodosFilmesNoDia(hoje);
    	
    	atualizarListaFilmesPorFiltro(filmesHoje,painelListaFilmes,hoje);
		
		painelListaFilmes.revalidate();
		painelListaFilmes.repaint();
	}
	
	private void atualizarListaFilmesPorFiltro(Filme[] filmesNoDia, JPanel painelListaFilmes,LocalDate diaFiltro) {
		painelListaFilmes.removeAll();
		
		for (Filme filme : filmesNoDia) {
			JPanel card = new JPanel();
			card.setLayout(null); 
			card.setPreferredSize(new Dimension(1355, 100));
			card.setMaximumSize(new Dimension(1355, 100));
			card.setBackground(new Color(230, 230, 250));

			// ADICIONA BORDA PRA DESTACAR CADA RETÂNGULO
			card.setBorder(new EmptyBorder(100, 10, 10, 10)); // espaçamento interno

			JPanel panelNomeEClassificacao = new JPanel();
			panelNomeEClassificacao.setLayout(new FlowLayout(FlowLayout.LEFT)); 
			panelNomeEClassificacao.setBounds(15, 10, 800, 25);
			panelNomeEClassificacao.setBackground(new Color(230, 230, 250));
			panelNomeEClassificacao.setBorder(new EmptyBorder(0, 0, 0, 0));
			
			JLabel lblNome = new JLabel(filme.getNome());
			lblNome.setBounds(20, 10, 490, 25); 
			panelNomeEClassificacao.add(lblNome);
			
			JLabel lblClassificacao = new JLabel("     "+filme.getClassificacaoIndicativa().toString());
			lblClassificacao.setBounds(20, 10, 400, 25); 
			panelNomeEClassificacao.add(lblClassificacao);
			
			card.add(panelNomeEClassificacao);

			JLabel lblDuracao = new JLabel("Duração: " + filme.getDuracaoEmMinutos() + " min");
			lblDuracao.setBounds(20, 35, 200, 20);
			card.add(lblDuracao);

			JLabel lblTituloSessoes = new JLabel("Sessões:");
			lblTituloSessoes.setBounds(20, 60, 100, 20);
			card.add(lblTituloSessoes);

			JPanel painelSessoes = new JPanel();
			painelSessoes.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
			painelSessoes.setBounds(100, 60, 400, 25);
			painelSessoes.setOpaque(false);
			card.add(painelSessoes);
			
		    for (Sessao sessao : app.getBackend().getBancoFilmes().obterTodasSessoesDoFilmeNoDia(filme.getId(), diaFiltro)) {
		    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		    	String sessaoFormatada = sessao.getInicio().format(formatter);
		    	
		    	JButton btnSessao = new JButton(sessaoFormatada);
				
				btnSessao.addActionListener(e -> {
					TelaEscolhaPoltrona telaEscolhaPoltrona = new TelaEscolhaPoltrona(sessao);
					telaEscolhaPoltrona.setLocationRelativeTo(null);
					telaEscolhaPoltrona.setVisible(true);

				    dispose();
		        });
		        painelSessoes.add(btnSessao);
		        
		    	
		    	String salaFormatada = Integer.toString(sessao.getSala().getIdSala());
		    	JLabel sala = new JLabel("Sala "+salaFormatada, SwingConstants.CENTER);
		    	
		        // Cria um painel vertical para colocar o label em cima do botão
		        JPanel painelJuntaSessaoESala = new JPanel();
		        painelJuntaSessaoESala.setLayout(new BoxLayout(painelJuntaSessaoESala, BoxLayout.Y_AXIS));
		        painelJuntaSessaoESala.add(sala);
		        painelJuntaSessaoESala.add(btnSessao);
		    	
		        sala.setAlignmentX(Component.CENTER_ALIGNMENT);
		        btnSessao.setAlignmentX(Component.CENTER_ALIGNMENT);
		        
		        String data = sessao.getInicio().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
		        System.out.println("============================================================");
				System.out.println("-----------------------------------------------------");
				System.out.println("Id Sessao: "+ sessao.getId());
				System.out.println("Id Filme: "+ sessao.getFilme().getId());
				System.out.println("Sala: "+ sessao.getSala().getIdSala());
				System.out.println("Inicio: "+ data);
				System.out.println("Filme: "+ sessao.getFilme().getNome());
				System.out.println("-----------------------------------------------------");
				System.out.println("============================================================");
		        
		       
		    	
		        painelSessoes.add(painelJuntaSessaoESala);
		        
		    }

		    card.add(painelSessoes);
		    

		    
		    painelListaFilmes.add(Box.createRigidArea(new Dimension(0, 10))); // espaço entre os cards
		    painelListaFilmes.add(card);
		}
		painelListaFilmes.revalidate();
		painelListaFilmes.repaint();
	}
	
	
	private void geraPanelFiltroDeData(JPanel panelDias,JPanel painelListaFilmes) {
	    for (int i = 0 ; i < 7 ; i++) {
	        LocalDate hoje = LocalDate.now().plusDays(i);
	        
	        String diaFormatado = hoje.getDayOfWeek().getDisplayName(TextStyle.SHORT, new Locale("pt", "BR")).toUpperCase();
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM");
	        String diaAtual = hoje.format(formatter);
	        
	        JLabel diaDaSemana = new JLabel(diaFormatado, SwingConstants.CENTER);//Deixar o texto no centro do label
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
	        	lblUseOFiltro.setVisible(false);
	        	Filme[] filmesNoDia = app.getBackend().getBancoFilmes().obterTodosFilmesNoDia(dataSelecionada);
	        	
	        	atualizarListaFilmesPorFiltro(filmesNoDia,painelListaFilmes,dataSelecionada);
	        });

	        panelDias.add(painelJuntaDataEDia);
	    }
	    
	}
	


	/**
	 * Create the frame.
	 */
	public TelaEscolhaFilme() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 64, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel painelListaFilmes = new JPanel();
		painelListaFilmes.setLayout(new BoxLayout(painelListaFilmes, BoxLayout.Y_AXIS)); // lista vertical
		painelListaFilmes.setBackground(Color.WHITE);
		painelListaFilmes.setBorder(new EmptyBorder(10, 10, 10, 10)); // margem geral


		// Criar os cards dos filmes
		atualizarListaFilmesParaOHoje(painelListaFilmes);

		JPanel panelDatas = new JPanel();
		panelDatas.setBounds(27, 11, 709, 69);
		contentPane.add(panelDatas);
		panelDatas.setLayout(null);
		
		// Criando o painel geral do filtro dos dias da semana
		JPanel panelDias = new JPanel();
		panelDias.setLayout(new FlowLayout(FlowLayout.LEFT)); // Usando FlowLayout para disposição horizontal
		
		// Criando os botões para cada dia da semana
        geraPanelFiltroDeData(panelDias,painelListaFilmes );

		// Criando um JScrollPane para rolar horizontalmente o painel de dias da semana
		JScrollPane scrollPane_1 = new JScrollPane(panelDias, JScrollPane.VERTICAL_SCROLLBAR_NEVER,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBounds(322, 0, 358, 69);
		panelDatas.add(scrollPane_1);

		JLabel lblSelecionarDia = new JLabel("Selecione o dia: ");
		lblSelecionarDia.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSelecionarDia.setBounds(24, 19, 126, 30);
		panelDatas.add(lblSelecionarDia);
		
		// Agora colocar isso num JScrollPane
		JScrollPane scrollPaneFilmes = new JScrollPane(painelListaFilmes);
		scrollPaneFilmes.setBounds(27, 91, 720, 330);
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
		lblUseOFiltro.setBounds(236, 432, 449, 18);
		contentPane.add(lblUseOFiltro);
		
		

	}
}
