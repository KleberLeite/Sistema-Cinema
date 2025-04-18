package cinemax.frontend.vendadeingressos;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import cinemax.backend.filmes.BancoDeDadosFilme;
import cinemax.backend.filmes.ClassificacaoIndicativa;
import cinemax.backend.filmes.Filme;
import cinemax.backend.filmes.Sessao;
import cinemax.backend.salas.BancoDeDadosSala;
import cinemax.frontend.model.DadosFilme;
import cinemax.frontend.model.ModeloTabela;

import javax.swing.JScrollPane;
import java.awt.Font;
import java.time.LocalDateTime;

public class TelaVendasIngressos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private BancoDeDadosFilme filmes = new BancoDeDadosFilme(new BancoDeDadosSala());
	//private ArrayList<DadosFilme> filmes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaVendasIngressos frame = new TelaVendasIngressos();
					frame.setLocationRelativeTo(null);
					frame.setSize(800, 500);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaVendasIngressos() {
		filmes.tentarAdicionarFilme("Rei leão", "Um leão",  190, ClassificacaoIndicativa.AL);
		LocalDateTime sessao = LocalDateTime.of(2025,04,16,13,00);
		filmes.tentarAdicionarSessao(0, 0, sessao);
		filmes.tentarAdicionarSessao(0, 0, sessao.plusHours(2));
		filmes.tentarAdicionarFilme("Rei leão 2", "Filha do leão", 170, ClassificacaoIndicativa.AL);
		filmes.tentarAdicionarSessao(1, 1, sessao.plusHours(4));
		filmes.tentarAdicionarSessao(1, 1, sessao.plusHours(6));
		filmes.tentarAdicionarFilme("Rei leão 3", "Amigos do Leão", 180, ClassificacaoIndicativa.AL);
		filmes.tentarAdicionarSessao(2, 2, sessao.plusHours(8));
		filmes.tentarAdicionarSessao(2, 2, sessao.plusHours(10));
		filmes.tentarAdicionarFilme("Shrek", "Um leão", 200, ClassificacaoIndicativa.AL);
		filmes.tentarAdicionarSessao(3, 3, sessao.plusHours(12));
		filmes.tentarAdicionarSessao(3, 3, sessao.plusHours(14));
		

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 64, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		/*
		ModeloTabela modeloTabela = new ModeloTabela(filmes);
		table = new JTable();
		table.setModel(modeloTabela);
		scrollPane.setViewportView(table);
		*/

		JPanel panelDatas = new JPanel();
		panelDatas.setBounds(27, 11, 709, 52);
		contentPane.add(panelDatas);
		panelDatas.setLayout(null);
		
		// Criando o painel com os dias da semana
		JPanel panelDias = new JPanel();
		panelDias.setLayout(new FlowLayout(FlowLayout.LEFT)); // Usando FlowLayout para disposição horizontal
		
		// Criando os botões para cada dia da semana
        String[] diasSemana = {"Domingo", "Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado"};

        for (String dia : diasSemana) {
            JButton botaoDia = new JButton(dia);
            panelDias.add(botaoDia); // Adiciona o botão ao painel
        }

		// Criando um JScrollPane para rolar horizontalmente
		JScrollPane scrollPane_1 = new JScrollPane(panelDias, JScrollPane.VERTICAL_SCROLLBAR_NEVER,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBounds(323, 0, 358, 52);
		panelDatas.add(scrollPane_1);

		JLabel lblSelecionarDia = new JLabel("Selecione o dia: ");
		lblSelecionarDia.setBounds(24, 11, 126, 30);
		panelDatas.add(lblSelecionarDia);
		
		JPanel painelListaFilmes = new JPanel();
		painelListaFilmes.setLayout(new BoxLayout(painelListaFilmes, BoxLayout.Y_AXIS)); // lista vertical
		painelListaFilmes.setBackground(Color.WHITE);
		painelListaFilmes.setBorder(new EmptyBorder(10, 10, 10, 10)); // margem geral


		// Criar os cards dos filmes
		for (Filme filme : filmes.obterTodosFilmes()) {
			JPanel card = new JPanel();
			card.setLayout(null); 
			card.setPreferredSize(new Dimension(700, 100));
			card.setMaximumSize(new Dimension(700, 100));
			card.setBackground(new Color(230, 230, 250));

			// ADICIONA BORDA PRA DESTACAR CADA RETÂNGULO
			card.setBorder(new EmptyBorder(10, 10, 10, 10)); // espaçamento interno

		    
		    JLabel lblNome = new JLabel(filme.getNome());
		    lblNome.setBounds(20, 10, 300, 25);
		    card.add(lblNome);

		    JLabel lblDuracao = new JLabel("Duração: " + filme.getDuracaoEmMinutos() + " min");
		    lblDuracao.setBounds(20, 40, 200, 20);
		    card.add(lblDuracao);

		 // Adicionar um label título fixo "Sessões:"
		    JLabel lblTituloSessoes = new JLabel("Sessões:");
		    lblTituloSessoes.setBounds(20, 65, 100, 20);
		    card.add(lblTituloSessoes);

		    // Painel para exibir as sessões uma ao lado da outra
		    JPanel painelSessoes = new JPanel();
		    painelSessoes.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
		    painelSessoes.setBounds(100, 65, 580, 25); // Ajuste conforme necessário
		    painelSessoes.setOpaque(false); // deixa transparente, se quiser manter fundo do card

		    for (Sessao s : filme.obterTodasSessoes()) {
		        JLabel lblSessao = new JLabel(s.getInicio().toLocalTime().toString()); // exibe só hora
		        painelSessoes.add(lblSessao);
		    }

		    card.add(painelSessoes);
		    

		    
		    painelListaFilmes.add(Box.createRigidArea(new Dimension(0, 10))); // espaço entre os cards
		    painelListaFilmes.add(card);
		}

		// Agora colocar isso num JScrollPane
		JScrollPane scrollPaneFilmes = new JScrollPane(painelListaFilmes);
		scrollPaneFilmes.setBounds(27, 91, 720, 330);
		scrollPaneFilmes.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		contentPane.add(scrollPaneFilmes);
		
		

	}
}
