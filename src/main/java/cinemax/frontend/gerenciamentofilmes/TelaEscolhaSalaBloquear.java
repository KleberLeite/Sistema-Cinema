package cinemax.frontend.gerenciamentofilmes;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import cinemax.backend.relatorios.Relatorio;
import cinemax.backend.salas.Sala;
import cinemax.frontend.PaginasGeranteeFuncionario.Gerente;
import cinemax.frontend.RelatorioGeral.TelaRelatorioFinal;
import cinemax.frontend.controller.ControladorDeApp;

public class TelaEscolhaSalaBloquear extends JFrame {

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
					TelaEscolhaSalaBloquear frame = new TelaEscolhaSalaBloquear();
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
	public TelaEscolhaSalaBloquear() {
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
		
		JLabel lblEscolhaORelatorio = new JLabel("Escolha a Sala:");
		lblEscolhaORelatorio.setVerticalAlignment(SwingConstants.CENTER);
		lblEscolhaORelatorio.setHorizontalAlignment(SwingConstants.CENTER);
		lblEscolhaORelatorio.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblEscolhaORelatorio.setBounds(0, 91, 564, 52);
		panelPrincipal.add(lblEscolhaORelatorio);
		
		JScrollPane scrollPaneRelatorios = new JScrollPane();
		scrollPaneRelatorios.setBounds(168, 154, 225, 315);
		scrollPaneRelatorios.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panelPrincipal.add(scrollPaneRelatorios);
		
		
		
		JPanel panelMostrarRelatorios = new JPanel();
		scrollPaneRelatorios.setViewportView(panelMostrarRelatorios);
		panelMostrarRelatorios.setLayout(new BoxLayout(panelMostrarRelatorios, BoxLayout.Y_AXIS));
		
		for (int i = 0; i < app.getBackend().getBancoSalas().obterTodasSalas().length; i++) {
		    


		    JButton botaoRelatorio = new JButton("Sala: "+i);
		    botaoRelatorio.setAlignmentX(Component.CENTER_ALIGNMENT);
		    botaoRelatorio.setFont(new Font("Tahoma", Font.PLAIN, 13));
		    botaoRelatorio.setMaximumSize(new java.awt.Dimension(100, 30));

		    final int indexSala = i;
		    botaoRelatorio.addActionListener(e -> {
		    	Sala salaAtual = app.getBackend().getBancoSalas().obterSalaPorId(indexSala);
		    	
		        TelaEscolhaPoltronaBloquear telaEscolhaPoltronaBloquear = new TelaEscolhaPoltronaBloquear(salaAtual);
		        telaEscolhaPoltronaBloquear.setVisible(true);
		        telaEscolhaPoltronaBloquear.setLocationRelativeTo(null);
		        dispose();
		    });

		    panelMostrarRelatorios.add(botaoRelatorio);
		    panelMostrarRelatorios.add(javax.swing.Box.createVerticalStrut(10)); // espaço entre botões
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
