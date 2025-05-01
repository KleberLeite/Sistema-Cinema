package cinemax.frontend.vendadeingressos;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cinemax.backend.core.Backend;
import cinemax.backend.filmes.Sessao;
import cinemax.frontend.compra.Carrinho;
import cinemax.frontend.compra.Ingresso;
import cinemax.frontend.controller.ControladorDeApp;

import java.awt.Color;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

public class TelaFinalizarCompra extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ControladorDeApp app = ControladorDeApp.getInstancia();
	Backend bancos = app.getBackend();
	private Sessao sessao = bancos.getBancoFilmes().obterFilmePorId(0).obterSessao(0);
	private Carrinho carrinho;

	private int totalDeIngressosSelecionadosMeias;
	private int totalDeIngressosSelecionadosInteiras;
	private int totalDeIngressosSelecionadosTotal;
	private int totalDeIngressosRestantes;
	private double somaInteira;
	private double somaMeia;
	private double somaTotal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaFinalizarCompra frame = new TelaFinalizarCompra(null, null);
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
	public TelaFinalizarCompra(Sessao sessaoAtual, Carrinho carrinho) {
		this.sessao = sessaoAtual;
		this.carrinho = carrinho;
		totalDeIngressosRestantes = carrinho.qtdeTotalIngressos();
		System.out.println("Total de Ingressos: " + totalDeIngressosRestantes);

		Sessao sessao = this.sessao;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel contentPane_1 = new JPanel();
		contentPane_1.setLayout(null);
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane_1.setBackground(new Color(0, 64, 128));
		contentPane_1.setBounds(0, 0, 784, 671);
		contentPane.add(contentPane_1);

		// --------------------------------------------------------------------------------------------------------------------------------------------------------------
		// Criando e preparando os icones já redimensionando-os
		// ------------------------------------------------------------------------------------------------------------------

		ImageIcon iconeBilheteInteiraParcial = new ImageIcon(getClass().getResource("/img/BilheteInteira.png"));
		Image imgIconeBilheteInteiraParcial = iconeBilheteInteiraParcial.getImage().getScaledInstance(46, 35,Image.SCALE_SMOOTH);
		ImageIcon iconeBilheteInteira = new ImageIcon(imgIconeBilheteInteiraParcial);

		ImageIcon iconeBilheteMeiaParcial = new ImageIcon(getClass().getResource("/img/BilheteMeia.png"));
		Image imgIconeBilheteMeiaaParcial = iconeBilheteMeiaParcial.getImage().getScaledInstance(46, 35,Image.SCALE_SMOOTH);
		ImageIcon iconeBilheteMeia = new ImageIcon(imgIconeBilheteMeiaaParcial);
		
		// ------------------------------------------------------------------------------------------------------------------

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaEscolhaPoltrona telaEscolhaPoltrona = new TelaEscolhaPoltrona(sessao);
				telaEscolhaPoltrona.setLocationRelativeTo(null);
				telaEscolhaPoltrona.setVisible(true);

				dispose();
			}
		});
		btnVoltar.setBounds(10, 608, 89, 23);
		contentPane_1.add(btnVoltar);

		JButton btnFinalizarCompra = new JButton("Finalizar Compra");
		btnFinalizarCompra.setBounds(630, 608, 144, 23);
		contentPane_1.add(btnFinalizarCompra);

		JPanel panelIngressos = new JPanel();
		panelIngressos.setLayout(null);
		panelIngressos.setBackground(Color.WHITE);
		panelIngressos.setBounds(10, 7, 425, 453);
		contentPane_1.add(panelIngressos);

		JPanel panelInteiras = new JPanel();
		panelInteiras.setBounds(10, 11, 405, 57);
		panelIngressos.add(panelInteiras);
		panelInteiras.setLayout(null);

		JLabel lblInteira = new JLabel("Inteira");
		lblInteira.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblInteira.setBounds(78, 10, 72, 14);
		panelInteiras.add(lblInteira);

		JLabel imgInteira = new JLabel(iconeBilheteInteira);
		imgInteira.setBounds(10, 11, 46, 35);
		panelInteiras.add(imgInteira);

		double ingressoInteria = Ingresso.precoIngresso();
		String ingressoInteiroPreco = String.format("R$ %.2f", ingressoInteria);
		JLabel lblPrecoInteira = new JLabel(ingressoInteiroPreco);
		lblPrecoInteira.setBounds(78, 32, 96, 14);
		panelInteiras.add(lblPrecoInteira);

		JPanel panelMeias = new JPanel();
		panelMeias.setBounds(10, 79, 405, 57);
		panelIngressos.add(panelMeias);
		panelMeias.setLayout(null);

		JLabel lblMeia = new JLabel("Meia");
		lblMeia.setBounds(78, 13, 72, 14);
		panelMeias.add(lblMeia);
		lblMeia.setFont(new Font("Tahoma", Font.BOLD, 14));

		double ingressoMeia = Ingresso.precoIngresso() / 2;
		String ingressoMeiaPreco = String.format("R$ %.2f", ingressoMeia);
		JLabel lblPrecoMeia = new JLabel(ingressoMeiaPreco);
		lblPrecoMeia.setBounds(78, 32, 96, 14);
		panelMeias.add(lblPrecoMeia);

		JLabel imgMeia= new JLabel(iconeBilheteMeia);
		imgMeia.setBounds(10, 13, 46, 35);
		panelMeias.add(imgMeia);

		String ingressosRestantes = String.format("Total de Ingressos Restantes: %d", totalDeIngressosRestantes);
		JLabel lblTotalDeIngressosRestantes = new JLabel(ingressosRestantes);
		lblTotalDeIngressosRestantes.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTotalDeIngressosRestantes.setBounds(10, 147, 234, 25);
		panelIngressos.add(lblTotalDeIngressosRestantes);

		JPanel panelResumo = new JPanel();
		panelResumo.setBackground(new Color(255, 255, 255));
		panelResumo.setLayout(null);
		panelResumo.setBounds(445, 7, 329, 453);
		contentPane_1.add(panelResumo);

		JLabel lblLinha = new JLabel("_____________________________________________________");
		lblLinha.setBounds(5, 372, 329, 14);
		panelResumo.add(lblLinha);

		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTotal.setBounds(10, 428, 83, 14);
		panelResumo.add(lblTotal);

		JLabel lblItens = new JLabel("Itens");
		lblItens.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblItens.setBounds(10, 397, 83, 14);
		panelResumo.add(lblItens);

		JLabel lblPrecoTotal = new JLabel("R$ 0.00");
		lblPrecoTotal.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPrecoTotal.setBounds(263, 429, 91, 14);
		panelResumo.add(lblPrecoTotal);

		JLabel lblTotalItens = new JLabel("0");
		lblTotalItens.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTotalItens.setBounds(283, 397, 91, 14);
		panelResumo.add(lblTotalItens);

		JLabel lblInteiraResumo = new JLabel("Inteira");
		lblInteiraResumo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblInteiraResumo.setBounds(10, 247, 83, 14);
		panelResumo.add(lblInteiraResumo);

		JLabel lblMeiaResumo = new JLabel("Meia");
		lblMeiaResumo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMeiaResumo.setBounds(10, 272, 83, 14);
		panelResumo.add(lblMeiaResumo);

		JLabel lblSubPrecoInteira = new JLabel("R$ 0.00");
		lblSubPrecoInteira.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSubPrecoInteira.setBounds(263, 247, 76, 14);
		panelResumo.add(lblSubPrecoInteira);

		JLabel lblSubPrecoMeia = new JLabel("R$ 0.00");
		lblSubPrecoMeia.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSubPrecoMeia.setBounds(263, 272, 76, 14);
		panelResumo.add(lblSubPrecoMeia);

		// Declaração dos botões de + e -
		JButton btnMaisUmaInteira = new JButton("+");
		JButton btnMenosUmaInteira = new JButton("-");
		JButton btnMaisUmaMeia = new JButton("+");
		JButton btnMenosUmaMeia = new JButton("-");

		// Estilização e funcções dos botões
		btnMaisUmaInteira.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (totalDeIngressosRestantes == 0)
					btnMaisUmaInteira.setEnabled(false);
				if (totalDeIngressosRestantes > 0) {
					if (!btnMenosUmaInteira.isEnabled())
						btnMenosUmaInteira.setEnabled(true);
					double ingressoAtual = Ingresso.precoIngresso();
					somaInteira += ingressoAtual;
					totalDeIngressosSelecionadosInteiras++;
					somaTotal += ingressoAtual;
					totalDeIngressosSelecionadosTotal++;

					String subPrecoInteira = String.format("R$ %.2f", somaInteira);
					String subPrecoTotal = String.format("R$ %.2f", somaTotal);
					lblSubPrecoInteira.setText(subPrecoInteira);
					lblPrecoTotal.setText(subPrecoTotal);

					String qtdeInteiras = String.format("%dx Inteira", totalDeIngressosSelecionadosInteiras);
					String qtdeIngressos = String.format("%d", totalDeIngressosSelecionadosTotal);
					lblInteiraResumo.setText(qtdeInteiras);
					lblTotalItens.setText(qtdeIngressos);

					totalDeIngressosRestantes--;
					String qtdeIngressosRestantes = String.format("Total de Ingressos Restantes: %d",
							totalDeIngressosRestantes);
					lblTotalDeIngressosRestantes.setText(qtdeIngressosRestantes);
				}

			}
		});
		btnMaisUmaInteira.setBounds(354, 17, 41, 23);
		panelInteiras.add(btnMaisUmaInteira);

		btnMenosUmaInteira.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (somaInteira == 0)
					btnMenosUmaInteira.setEnabled(false);
				if (somaInteira > 0) {
					if (!btnMaisUmaInteira.isEnabled())
						btnMaisUmaInteira.setEnabled(true);
					double ingressoAtual = Ingresso.precoIngresso();
					somaInteira -= ingressoAtual;
					totalDeIngressosSelecionadosInteiras--;
					somaTotal -= ingressoAtual;
					totalDeIngressosSelecionadosTotal--;

					String subPrecoInteira = String.format("R$ %.2f", somaInteira);
					String subPrecoTotal = String.format("R$ %.2f", somaTotal);
					lblSubPrecoInteira.setText(subPrecoInteira);
					lblPrecoTotal.setText(subPrecoTotal);

					String qtdeInteiras = String.format("%dx Inteira", totalDeIngressosSelecionadosInteiras);
					String qtdeIngressos = String.format("%d", totalDeIngressosSelecionadosTotal);
					lblInteiraResumo.setText(qtdeInteiras);
					lblTotalItens.setText(qtdeIngressos);

					totalDeIngressosRestantes++;
					String qtdeIngressosRestantes = String.format("Total de Ingressos Restantes: %d",
							totalDeIngressosRestantes);
					lblTotalDeIngressosRestantes.setText(qtdeIngressosRestantes);
				}

			}
		});
		btnMenosUmaInteira.setBounds(303, 17, 41, 23);
		panelInteiras.add(btnMenosUmaInteira);

		btnMaisUmaMeia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (totalDeIngressosRestantes == 0)
					btnMaisUmaMeia.setEnabled(false);
				if (totalDeIngressosRestantes > 0) {
					if (!btnMenosUmaMeia.isEnabled())
						btnMenosUmaMeia.setEnabled(true);
					double ingressoAtual = Ingresso.precoIngresso() / 2;
					somaMeia += ingressoAtual;
					totalDeIngressosSelecionadosMeias++;
					somaTotal += ingressoAtual;
					totalDeIngressosSelecionadosTotal++;

					String subPrecoMeia = String.format("R$ %.2f", somaMeia);
					String precoTotal = String.format("R$ %.2f", somaTotal);
					lblSubPrecoMeia.setText(subPrecoMeia);
					lblPrecoTotal.setText(precoTotal);

					String qtdeMeias = String.format("%dx Meia", totalDeIngressosSelecionadosMeias);
					String qtdeIngressos = String.format("%d", totalDeIngressosSelecionadosTotal);
					lblMeiaResumo.setText(qtdeMeias);
					lblTotalItens.setText(qtdeIngressos);

					totalDeIngressosRestantes--;
					String qtdeIngressosRestantes = String.format("Total de Ingressos Restantes: %d",
							totalDeIngressosRestantes);
					lblTotalDeIngressosRestantes.setText(qtdeIngressosRestantes);
				}

			}
		});

		btnMaisUmaMeia.setBounds(354, 17, 41, 23);
		panelMeias.add(btnMaisUmaMeia);

		btnMenosUmaMeia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (somaMeia == 0)
					btnMenosUmaMeia.setEnabled(false);
				if (somaMeia > 0) {
					if (!btnMaisUmaMeia.isEnabled())
						btnMaisUmaMeia.setEnabled(true);
					double ingressoAtual = Ingresso.precoIngresso() / 2;
					somaMeia -= ingressoAtual;
					totalDeIngressosSelecionadosMeias--;
					somaTotal -= ingressoAtual;
					totalDeIngressosSelecionadosTotal--;

					String subPrecoMeia = String.format("R$ %.2f", somaMeia);
					String precoTotal = String.format("R$ %.2f", somaTotal);
					lblSubPrecoMeia.setText(subPrecoMeia);
					lblPrecoTotal.setText(precoTotal);

					String qtdeMeias = String.format("%dx Meia", totalDeIngressosSelecionadosMeias);
					String qtdeIngressos = String.format("%d", totalDeIngressosSelecionadosTotal);
					lblMeiaResumo.setText(qtdeMeias);
					lblTotalItens.setText(qtdeIngressos);

					totalDeIngressosRestantes++;
					String qtdeIngressosRestantes = String.format("Total de Ingressos Restantes: %d",
							totalDeIngressosRestantes);
					lblTotalDeIngressosRestantes.setText(qtdeIngressosRestantes);
				}

			}
		});
		btnMenosUmaMeia.setBounds(303, 17, 41, 23);
		panelMeias.add(btnMenosUmaMeia);

	}
}
