package cinemax.frontend.RelatorioGeral;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cinemax.frontend.PaginasGeranteeFuncionario.Gerente;
import cinemax.frontend.controller.ControladorDeApp;
import cinemax.backend.alimentos.Alimento;
import cinemax.backend.relatorios.Relatorio;
import cinemax.backend.relatorios.RelatorioAlimentos;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Map;
import java.awt.event.ActionEvent;

public class TelaRelatorioFinal extends JFrame {

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
					TelaRelatorioFinal frame = new TelaRelatorioFinal();
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
	public TelaRelatorioFinal() {
		Relatorio relatorio = app.getBackend().getGerenciadorRelatorios().obterRelatorioDoDia();
		
		RelatorioAlimentos relatorioAlimento = relatorio.getRelatorioAlimentos();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 64, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		for (Map.Entry<Alimento, Integer> entry : relatorioAlimento.obterVendas()) {
		    Alimento alimento = entry.getKey(); // Chave (Alimento)
		    Integer quantidade = entry.getValue(); // Valor (Quantidade)
		    System.out.println("Alimento: " + alimento.getNome() +"     Preço R$"+alimento.getPreco()+ "     Quantidade: " + quantidade);
		}

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 864, 499);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblLinha = new JLabel("__________________________________________________________________________________________________________________________");
		lblLinha.setBounds(5, 412, 864, 14);
		panel.add(lblLinha);
		
		JPanel panelPrincipalRelatorioFilme = new JPanel();
		panelPrincipalRelatorioFilme.setBounds(0, 0, 432, 423);
		panel.add(panelPrincipalRelatorioFilme);
		
		JPanel panelPrincipalRelatórioAlimentos = new JPanel();
		panelPrincipalRelatórioAlimentos.setBounds(431, 0, 432, 423);
		panel.add(panelPrincipalRelatórioAlimentos);
		
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
		
		JButton btnFecharSistema = new JButton("Fechar Sistema");
		btnFecharSistema.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnFecharSistema.setBounds(720, 528, 154, 23);
		contentPane.add(btnFecharSistema);
	}
}
