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

import cinemax.backend.core.Backend;
import cinemax.backend.filmes.BancoDeDadosFilme;
import cinemax.backend.filmes.ClassificacaoIndicativa;
import cinemax.backend.filmes.Filme;
import cinemax.backend.filmes.Sessao;
import cinemax.backend.salas.BancoDeDadosSala;
import cinemax.frontend.controller.ControladorDeApp;
import cinemax.frontend.model.DadosFilme;
import cinemax.frontend.model.ModeloTabela;

import javax.swing.JScrollPane;
import java.awt.Font;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TelaEscolhaFilme extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
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
        
        for (int i = 0 ; i < 7 ; i++) {
        	LocalDate hoje = LocalDate.now().plusDays(i);
        	
        	
    		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM");

    		String diaAtual = hoje.format(formatter);
    		
            JButton botaoDia = new JButton(diaAtual);
            
            final LocalDate dataSelecionada = hoje;

            // Evento de clique no botão
            botaoDia.addActionListener(e -> {
                System.out.println("Dia selecionado: " + dataSelecionada);

                // Aqui você pode, por exemplo, filtrar os filmes com sessões só nesse dia
                // Ou atualizar a interface com os dados da data escolhida
            });
            
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
		for (Filme filme : bancos.getBancoFilmes().obterTodosFilmes()) {
			JPanel card = new JPanel();
			card.setLayout(null); 
			card.setPreferredSize(new Dimension(1355, 100));
			card.setMaximumSize(new Dimension(1355, 100));
			card.setBackground(new Color(230, 230, 250));

			// ADICIONA BORDA PRA DESTACAR CADA RETÂNGULO
			card.setBorder(new EmptyBorder(10, 10, 10, 10)); // espaçamento interno

			JLabel lblNome = new JLabel(filme.getNome());
			lblNome.setBounds(20, 10, 400, 25); // mais largura pro nome
			card.add(lblNome);

			JLabel lblDuracao = new JLabel("Duração: " + filme.getDuracaoEmMinutos() + " min");
			lblDuracao.setBounds(20, 35, 200, 20);
			card.add(lblDuracao);

			JLabel lblTituloSessoes = new JLabel("Sessões:");
			lblTituloSessoes.setBounds(20, 60, 100, 20);
			card.add(lblTituloSessoes);

			JPanel painelSessoes = new JPanel();
			painelSessoes.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
			painelSessoes.setBounds(100, 60, 400, 25); // mais à esquerda pra sobrar espaço pro botão
			painelSessoes.setOpaque(false);
			card.add(painelSessoes);


		    for (Sessao sessao : filme.obterTodasSessoes()) {
		    	
		    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		    	String sessaoFormatada = sessao.getInicio().format(formatter);
		    	
		        //String sessao1 = s.getInicio().toLocalTime().toString(); // exibe só hora
		        JButton btnSessao1 = new JButton(sessaoFormatada);
				btnSessao1.setBounds(550, 65, 100, 25); // canto inferior direito do card
				card.add(btnSessao1);
				
				btnSessao1.addActionListener(e -> {
		                
					TelaEscolhaPoltrona telaEscolhaPoltrona = new TelaEscolhaPoltrona(sessao);
					telaEscolhaPoltrona.setLocationRelativeTo(null); // centraliza a tela
					telaEscolhaPoltrona.setVisible(true);

				    dispose();
		               
		        });
		        
		        painelSessoes.add(btnSessao1);
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
