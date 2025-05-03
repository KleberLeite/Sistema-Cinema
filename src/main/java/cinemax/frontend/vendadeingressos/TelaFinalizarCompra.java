package cinemax.frontend.vendadeingressos;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cinemax.backend.core.Backend;
import cinemax.backend.filmes.Sessao;
import cinemax.backend.relatorios.Ingresso;
import cinemax.backend.relatorios.TipoDeIngresso;
import cinemax.frontend.controller.ControladorDeApp;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import java.awt.Dimension;

import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class TelaFinalizarCompra extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JPanel panelMeias;
	JPanel panelRGs;
	private java.util.List<JTextField> listaDeTextFieldsRGs = new ArrayList<>();
	JLabel lblTotalDeIngressosRestantes;
	JScrollPane scrollPaneRGs;
	JTextField textFieldRGs;
	private ControladorDeApp app = ControladorDeApp.getInstancia();
	Backend bancos = app.getBackend();
	private Sessao sessao = bancos.getBancoFilmes().obterFilmePorId(0).obterSessao(0);
	private int qtdeMeias;

	private int totalDeIngressosSelecionadosMeias;
	private int totalDeIngressosSelecionadosInteiras;
	private int totalDeIngressosSelecionadosTotal;
	private int totalDeIngressosRestantes;
	private int indexIngresso = 0;
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
	//MEthods utils ---------------------------------------------------------------------------------
	
	private void adicionaTipoIngresso(Ingresso ingresso, TipoDeIngresso tipo) {
		ingresso.setTipo(tipo);
	}
	
	public void atualizarListaDeRGs(JPanel panelRGs, int qtdeMeias) {
	    panelRGs.removeAll();
	    listaDeTextFieldsRGs.clear(); // Limpa os anteriores

	    for (int i = 0; i < qtdeMeias; i++) {
	        JPanel card = new JPanel();
	        card.setLayout(null);
	        card.setPreferredSize(new Dimension(400, 50));
	        card.setMaximumSize(new Dimension(400, 50));
	        card.setBackground(new Color(230, 210, 250));
	        card.setBorder(new EmptyBorder(5, 5, 5, 5));

	        JLabel lblRG = new JLabel("RG:");
	        lblRG.setFont(new Font("Tahoma", Font.BOLD, 14));
	        lblRG.setBounds(10, 10, 40, 25);
	        card.add(lblRG);

	        JTextField textField = new JTextField();
	        textField.setBounds(60, 10, 100, 25);
	        card.add(textField);

	        listaDeTextFieldsRGs.add(textField); // Adiciona à lista

	        panelRGs.add(Box.createRigidArea(new Dimension(0, 10)));
	        panelRGs.add(card);
	    }

	    panelRGs.revalidate();
	    panelRGs.repaint();
	}
	
	//-----------------------------------------------------------------------------------------------
	
	public void ativaOuDesativaListaRGs() {
		
		if(totalDeIngressosSelecionadosMeias > 0) {
			panelMeias.setBounds(10, 79, 405, 272);
			lblTotalDeIngressosRestantes.setBounds(10, 362, 234, 25);
			scrollPaneRGs.setEnabled(true);
			panelRGs.setEnabled(true);

		
		}else {
			panelMeias.setBounds(10, 79, 405, 57);
			lblTotalDeIngressosRestantes.setBounds(10, 147, 234, 25);
			scrollPaneRGs.setEnabled(false);
			panelRGs.setEnabled(false);	
			
		}
		
	}
	
	public void adicionaRGaMeia(List<Ingresso> ingressos) {
		
		for(int i = 0 ; i < qtdeMeias; i++) {
			ingressos.get(i).setRG(listaDeTextFieldsRGs.get(i).getText().trim());
		}
		
	}
	
	public boolean validarRGs() {
	    boolean todosValidos = true;
	    String regexRG = "\\d{7,9}"; // Exemplo: 7 a 9 dígitos numéricos

	    for (JTextField JTextFieldRG : listaDeTextFieldsRGs) {
	        String rg = JTextFieldRG.getText().trim();

	        if (!rg.matches(regexRG)) {
	            JTextFieldRG.setBackground(Color.PINK); // Destaque para inválido
	            todosValidos = false;
	        } else {
	            JTextFieldRG.setBackground(Color.WHITE); // Restaura cor para válido
	        }
	    }

	    return todosValidos;
	}

	/**
	 * Create the frame.
	 */
	public TelaFinalizarCompra(Sessao sessaoAtual, CarrinhoIngressos carrinho) {
		this.sessao = sessaoAtual;
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
		btnVoltar.setFont(new Font("Tahoma", Font.BOLD, 13));
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
		btnFinalizarCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(validarRGs()) {
					
					adicionaRGaMeia(carrinho.getIngressosMeias());
					
					TelaConclusaoDeCompra telaConclusaoDeCompra = new TelaConclusaoDeCompra(carrinho);
					telaConclusaoDeCompra.setVisible(true);
					telaConclusaoDeCompra.setLocationRelativeTo(null);
					
					dispose();
				}else {
					JOptionPane.showMessageDialog(null, "Aluns RGs estão incorretos, por favor, verifique-os!", "Erro",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnFinalizarCompra.setFont(new Font("Tahoma", Font.BOLD, 13));
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

		double ingressoInteria = Ingresso.PRECO_INGRESSO;
		String ingressoInteiroPreco = String.format("R$ %.2f", ingressoInteria);
		JLabel lblPrecoInteira = new JLabel(ingressoInteiroPreco);
		lblPrecoInteira.setBounds(78, 32, 96, 14);
		panelInteiras.add(lblPrecoInteira);

	    panelMeias = new JPanel();
		panelMeias.setBounds(10, 79, 405, 57);
		panelIngressos.add(panelMeias);
		panelMeias.setLayout(null);

		JLabel lblMeia = new JLabel("Meia");
		lblMeia.setBounds(78, 13, 72, 14);
		panelMeias.add(lblMeia);
		lblMeia.setFont(new Font("Tahoma", Font.BOLD, 14));

		double ingressoMeia = Ingresso.PRECO_INGRESSO / 2;
		String ingressoMeiaPreco = String.format("R$ %.2f", ingressoMeia);
		JLabel lblPrecoMeia = new JLabel(ingressoMeiaPreco);
		lblPrecoMeia.setBounds(78, 32, 96, 14);
		panelMeias.add(lblPrecoMeia);

		JLabel imgMeia= new JLabel(iconeBilheteMeia);
		imgMeia.setBounds(10, 13, 46, 35);
		panelMeias.add(imgMeia);

		String ingressosRestantes = String.format("Total de Ingressos Restantes: %d", totalDeIngressosRestantes);
		lblTotalDeIngressosRestantes = new JLabel(ingressosRestantes);
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
		
		scrollPaneRGs = new JScrollPane();
		scrollPaneRGs.setEnabled(false);
		scrollPaneRGs.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneRGs.setBounds(79, 109, 278, 138);
		panelMeias.add(scrollPaneRGs);
		
		panelRGs = new JPanel();
		panelRGs.setLayout(new BoxLayout(panelRGs, BoxLayout.Y_AXIS)); // lista vertical
		panelRGs.setBorder(new EmptyBorder(5, 5, 5, 5)); // margem geral
		panelRGs.setEnabled(false);
		scrollPaneRGs.setViewportView(panelRGs);
		
		ativaOuDesativaListaRGs();

		// Declaração dos botões de + e -
		JButton btnMaisUmaInteira = new JButton("+");
		JButton btnMenosUmaInteira = new JButton("-");
		JButton btnMaisUmaMeia = new JButton("+");
		JButton btnMenosUmaMeia = new JButton("-");

		// Estilização e funcções dos botões
		btnMaisUmaInteira.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (totalDeIngressosRestantes == 0) { 
					btnMaisUmaInteira.setEnabled(false);
					if(totalDeIngressosSelecionadosMeias == 0) {//desativa os bootões de meia se sua quantidade for nula
						btnMaisUmaMeia.setEnabled(false);
						btnMenosUmaMeia.setEnabled(false);
					}
					
				}
				if (totalDeIngressosRestantes > 0) {
					if (!btnMenosUmaInteira.isEnabled()) btnMenosUmaInteira.setEnabled(true);
					double ingressoAtual = Ingresso.PRECO_INGRESSO;
					somaInteira += ingressoAtual;
					totalDeIngressosSelecionadosInteiras++;
					somaTotal += ingressoAtual;
					totalDeIngressosSelecionadosTotal++;
					
					//Altera o tipo de ingresso na lista para meia
					adicionaTipoIngresso(carrinho.getIngressos().get(indexIngresso), TipoDeIngresso.Inteira);
					indexIngresso++;

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
				if (totalDeIngressosSelecionadosInteiras == 0) { 
					btnMenosUmaInteira.setEnabled(false);
					if(totalDeIngressosSelecionadosTotal != 0) {//reativa o botão de mais uma meia caso ainda haja ingressos
						btnMaisUmaMeia.setEnabled(true);
					}
				}
				if (totalDeIngressosSelecionadosInteiras > 0) {
					if (!btnMaisUmaInteira.isEnabled()) btnMaisUmaInteira.setEnabled(true);
					if(totalDeIngressosRestantes == 0) {// reativa o botão de mais uma meia, pois vai ser a última rodada das inteira e vai liberar um ingresso
						btnMaisUmaMeia.setEnabled(true);
					}
					double ingressoAtual = Ingresso.PRECO_INGRESSO;
					somaInteira -= ingressoAtual;
					totalDeIngressosSelecionadosInteiras--;
					somaTotal -= ingressoAtual;
					totalDeIngressosSelecionadosTotal--;
					
					//Apenas volta um no indice dos ingressos, não é necessário alterar de novo
					//adicionaTipoIngresso(carrinho.getIngressos().get(indexIngresso), TipoDeIngresso.Inteira);
					indexIngresso--;

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
				if (totalDeIngressosRestantes == 0 ) { 
					btnMaisUmaMeia.setEnabled(false);
					if(totalDeIngressosSelecionadosInteiras == 0) {//Desativa os botões das inteiras se o total deles for zero
						btnMaisUmaInteira.setEnabled(false);
						btnMenosUmaInteira.setEnabled(false);
					}
				}
				if (totalDeIngressosRestantes > 0) {
					if (!btnMenosUmaMeia.isEnabled()) btnMenosUmaMeia.setEnabled(true);
					double ingressoAtual = Ingresso.PRECO_INGRESSO / 2;
					somaMeia += ingressoAtual;
					totalDeIngressosSelecionadosMeias++;
					somaTotal += ingressoAtual;
					totalDeIngressosSelecionadosTotal++;
					
					//Altera o tipo de ingresso na lista para inteira
					adicionaTipoIngresso(carrinho.getIngressos().get(indexIngresso), TipoDeIngresso.Meia);
					indexIngresso++;
					qtdeMeias++;
					ativaOuDesativaListaRGs();
					atualizarListaDeRGs(panelRGs, qtdeMeias);
					

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
				if (totalDeIngressosSelecionadosMeias == 0) {
					btnMenosUmaMeia.setEnabled(false);
				}
				if (totalDeIngressosSelecionadosMeias > 0) {
					if (!btnMaisUmaMeia.isEnabled()) btnMaisUmaMeia.setEnabled(true);
					if(totalDeIngressosRestantes == 0) {// reativa o botão de mais uma inteira, pois vai ser a última rodada das meia e vai liberar um ingresso
						btnMaisUmaInteira.setEnabled(true);
					}
					double ingressoAtual = Ingresso.PRECO_INGRESSO / 2;
					somaMeia -= ingressoAtual;
					totalDeIngressosSelecionadosMeias--;
					somaTotal -= ingressoAtual;
					totalDeIngressosSelecionadosTotal--;
					
					//Apenas volta um no indice dos ingressos, não é necessário alterar de novo
					//adicionaTipoIngresso(carrinho.getIngressos().get(indexIngresso), TipoDeIngresso.Meia);
					indexIngresso--;
					qtdeMeias--;
					ativaOuDesativaListaRGs();
					atualizarListaDeRGs(panelRGs, qtdeMeias);

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
		
		
		/*JButton btnTeste = new JButton("Teste");
		btnTeste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int contMeia = 0;
				int contInteira = 0;
				for(Ingresso ingresso : carrinho.getIngressos()) {
					if(ingresso.getTipo() == TipoDeIngresso.Inteira) contInteira++;
					else contMeia++;
					System.out.println("-----------------------------------------");
					System.out.println(ingresso.toString());
					System.out.println("-----------------------------------------");
				}
				System.out.println("Quantidade de meia:"+contMeia);
				System.out.println("Quantidade de Inteira:"+contInteira);
			}
		});
		btnTeste.setBounds(400, 310, 89, 23);
		panelIngressos.add(btnTeste);*/

	}
}
