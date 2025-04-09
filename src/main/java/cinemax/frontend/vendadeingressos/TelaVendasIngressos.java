package cinemax.frontend.vendadeingressos;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import cinemax.frontend.model.DadosFilme;
import cinemax.frontend.model.ModeloTabela;

import javax.swing.JScrollPane;

public class TelaVendasIngressos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private ArrayList<DadosFilme> filmes;

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
		filmes = new ArrayList<>();
		filmes.add(new DadosFilme(1, "Rei leão", "Um leão", "4", 190, "AL"));
		filmes.add(new DadosFilme(2, "Rei leão 2", "Filha do leão", "4", 170, "AL"));
		filmes.add(new DadosFilme(3, "Rei leão 3", "Amigos do Leão", "4", 180, "AL"));
		filmes.add(new DadosFilme(4, "Shrek", "Um leão", "4", 200, "AL"));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 64, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Criando a tabela com filmes
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 86, 709, 361);
		contentPane.add(scrollPane);

		ModeloTabela modeloTabela = new ModeloTabela(filmes);
		table = new JTable();
		table.setModel(modeloTabela);
		scrollPane.setViewportView(table);

		JPanel panel = new JPanel();
		panel.setBounds(27, 11, 709, 52);
		contentPane.add(panel);
		panel.setLayout(null);

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
		panel.add(scrollPane_1);

		JLabel lblNewLabel = new JLabel("Selecione o dia: ");
		lblNewLabel.setBounds(24, 11, 126, 30);
		panel.add(lblNewLabel);

	}
}
