package cinemax.frontend.RelatorioGeral;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import cinemax.backend.filmes.Filme;
import cinemax.backend.relatorios.Relatorio;
import cinemax.frontend.PaginasGeranteeFuncionario.Gerente;
import cinemax.frontend.controller.ControladorDeApp;

import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;
import java.awt.event.ActionEvent;

public class TelaEscolhaRelatorio extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ControladorDeApp app = ControladorDeApp.getInstancia();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaEscolhaRelatorio frame = new TelaEscolhaRelatorio();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaEscolhaRelatorio() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 64, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setBounds(10, 11, 564, 508);
		contentPane.add(panelPrincipal);
		panelPrincipal.setLayout(null);
		
		JLabel lblEscolhaORelatorio = new JLabel("Escolha o Relatório:");
		lblEscolhaORelatorio.setVerticalAlignment(SwingConstants.CENTER);
		lblEscolhaORelatorio.setHorizontalAlignment(SwingConstants.CENTER);
		lblEscolhaORelatorio.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblEscolhaORelatorio.setBounds(102, 91, 356, 52);
		panelPrincipal.add(lblEscolhaORelatorio);
		
		JPanel panelMostrarRelatorios = new JPanel();
		JPanel panelPrincipalRelatorioFilmes = new JPanel();
        panelPrincipalRelatorioFilmes.setLayout(new BoxLayout(panelPrincipalRelatorioFilmes, BoxLayout.Y_AXIS));
		panelMostrarRelatorios.setBounds(149, 154, 272, 317);
		panelPrincipal.add(panelMostrarRelatorios);
		
		for(int i = 0; i < app.getBackend().getGerenciadorRelatorios().obterTodos().size(); i++) {
			 	LocalDate hoje = LocalDate.now().plusDays(i);
		        
		        String diaFormatado = hoje.getDayOfWeek().getDisplayName(TextStyle.SHORT, new Locale("pt", "BR")).toUpperCase();
		        
		        JButton botaoDia = new JButton("Relatório de "+diaFormatado);
		        
		        botaoDia.setAlignmentX(Component.CENTER_ALIGNMENT);
		        
		        final int indexRelatorio = i;
		        
		        botaoDia.addActionListener(e -> {
		        	
		        	Relatorio relatorioDoDia = app.getBackend().getGerenciadorRelatorios().obterTodos().get(indexRelatorio);
		        	
		        	TelaRelatorioFinal telaRelatorioFinal = new TelaRelatorioFinal(relatorioDoDia);
		        	telaRelatorioFinal.setVisible(true);
		        	telaRelatorioFinal.setLocationRelativeTo(null);
		        	
		        	dispose();
		        	
		        });

		}
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Gerente telaGerente = new Gerente();
                telaGerente.setVisible(true);
                telaGerente.setLocationRelativeTo(null);
                dispose();
			}
		});
		btnVoltar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnVoltar.setBounds(10, 527, 89, 23);
		contentPane.add(btnVoltar);
	}
}
