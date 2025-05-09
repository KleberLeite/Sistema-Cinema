package cinemax.frontend.gerenciamentofilmes;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import cinemax.backend.relatorios.filmes.Ingresso;
import cinemax.backend.salas.Poltrona;
import cinemax.backend.salas.Sala;
import cinemax.frontend.controller.ControladorDeApp;
import cinemax.frontend.vendadeingressos.CarrinhoIngressos;
import cinemax.frontend.vendadeingressos.TelaVendaDeIngresso;
import cinemax.frontend.vendadeingressos.TelaEscolhaMeiaOuInteira;

public class TelaEscolhaPoltronaBloquear extends JFrame {

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
					TelaEscolhaPoltronaBloquear frame = new TelaEscolhaPoltronaBloquear(null);
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
	public TelaEscolhaPoltronaBloquear(Sala salaEntrada) {
		if (salaEntrada == null)
			salaEntrada = app.getBackend().getBancoFilmes().obterFilmePorId(0).obterSessao(0).getSala();

		Sala salaAtual = salaEntrada;

		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(-14, -42, 900, 700);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(2, 18, 27));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panelPoltronas = new JPanel();
		panelPoltronas.setBackground(new Color(255, 255, 255));
		panelPoltronas.setBounds(255, 11, 619, 637);
		contentPane.add(panelPoltronas);

		// --------------------------------------------------------------------------------------------------------------------------------------------------------------
		// Criando e preparando os icones já redimensionando-os
		// ------------------------------------------------------------------------------------------------------------------

		ImageIcon iconePoltronaLivreParcial = new ImageIcon(getClass().getResource("/img/Poltrona.png"));
		Image imgIconePoltronaLivreParcial = iconePoltronaLivreParcial.getImage().getScaledInstance(25, 25,
				Image.SCALE_SMOOTH);
		ImageIcon iconePoltrona = new ImageIcon(imgIconePoltronaLivreParcial);

		ImageIcon iconePoltronaOcupadaParcial = new ImageIcon(getClass().getResource("/img/PoltronaOcupada.png"));
		Image imgIconePoltronaOcupadaParcial = iconePoltronaOcupadaParcial.getImage().getScaledInstance(25, 25,
				Image.SCALE_SMOOTH);
		ImageIcon iconePoltronaOcupada = new ImageIcon(imgIconePoltronaOcupadaParcial);

		ImageIcon iconePoltronaObesosLivreParcial = new ImageIcon(getClass().getResource("/img/PoltronaObesos.png"));
		Image imgIconePoltronaObesosLivreParcial = iconePoltronaObesosLivreParcial.getImage().getScaledInstance(28, 25,
				Image.SCALE_SMOOTH);
		ImageIcon iconePoltronaObesos = new ImageIcon(imgIconePoltronaObesosLivreParcial);

		ImageIcon iconePoltronaObesosOcupadaParcial = new ImageIcon(
				getClass().getResource("/img/PoltronaObesosOcupada.png"));
		Image imgIconePoltronaObesosOcupadaParcial = iconePoltronaObesosOcupadaParcial.getImage().getScaledInstance(28,
				25, Image.SCALE_SMOOTH);
		ImageIcon iconePoltronaObesosOcupada = new ImageIcon(imgIconePoltronaObesosOcupadaParcial);

		ImageIcon iconeLocalCadeirantesLivreParcial = new ImageIcon(getClass().getResource("/img/LocalCadeirante.png"));
		Image imgIconeLocalCadeirantesLivreParcial = iconeLocalCadeirantesLivreParcial.getImage().getScaledInstance(25,
				25, Image.SCALE_SMOOTH);
		ImageIcon iconeLocalCadeirantes = new ImageIcon(imgIconeLocalCadeirantesLivreParcial);

		ImageIcon iconeLocalCadeirantesOcupadoParcial = new ImageIcon(
				getClass().getResource("/img/LocalCadeiranteOcupado.png"));
		Image imgIconeLocalCadeirantesOcupadoParcial = iconeLocalCadeirantesOcupadoParcial.getImage()
				.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		ImageIcon iconeLocalCadeirantesOcupado = new ImageIcon(imgIconeLocalCadeirantesOcupadoParcial);

		ImageIcon iconeBloqueadoParcial = new ImageIcon(getClass().getResource("/img/PoltronaBloqueada.png"));
		Image imgIconeBloqueadoParcial = iconeBloqueadoParcial.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		ImageIcon iconeBloqueada = new ImageIcon(imgIconeBloqueadoParcial);

		ImageIcon iconeTomDeOcupadoParcial = new ImageIcon(getClass().getResource("/img/TomOcupado.png"));
		Image imgIconeTomDeOcupadoParcial = iconeTomDeOcupadoParcial.getImage().getScaledInstance(25, 25,
				Image.SCALE_SMOOTH);
		ImageIcon IconeTomDeOcupado = new ImageIcon(imgIconeTomDeOcupadoParcial);

		ImageIcon iconeTomDeSelecionadoParcial = new ImageIcon(getClass().getResource("/img/TomSelecionado.png"));
		Image imgIconeTomDeSelecionadoParcial = iconeTomDeSelecionadoParcial.getImage().getScaledInstance(25, 25,
				Image.SCALE_SMOOTH);
		ImageIcon IconeTomDeSelecionado = new ImageIcon(imgIconeTomDeSelecionadoParcial);

		// --------------------------------------------------------------------------------------------------------------------------------------------------------------
		// --------------------------------------------------------------------------------------------------------------------------------------------------------------

		panelPoltronas.setLayout(null);

		JPanel panelLetras = new JPanel();
		panelLetras.setBounds(0, 74, 34, 483);
		panelPoltronas.add(panelLetras);
		panelLetras.setBackground(new Color(255, 255, 255));
		panelLetras.setLayout(new BoxLayout(panelLetras, BoxLayout.Y_AXIS)); // Layout vertical

		// Gera letras de B até O
		for (char letra = 'O'; letra >= 'B'; letra--) {
			JLabel label = new JLabel(String.valueOf(letra));
			label.setFont(new Font("Tahoma", Font.BOLD, 16));
			label.setAlignmentX(Component.CENTER_ALIGNMENT); // Centraliza horizontalmente
			panelLetras.add(label);

			panelLetras.add(Box.createRigidArea(new Dimension(0, 13))); // Espaço vertical

		}

		JPanel panelNumeros = new JPanel();
		panelNumeros.setBounds(32, 592, 566, 34);
		panelNumeros.setBackground(new Color(255, 255, 255));
		panelNumeros.setLayout(new BoxLayout(panelNumeros, BoxLayout.X_AXIS)); // Layout horizontal

		for (int i = 1; i <= 16; i++) {
			JLabel label = new JLabel("  " + String.valueOf(i));
			label.setFont(new Font("Tahoma", Font.BOLD, 16));
			label.setAlignmentY(Component.CENTER_ALIGNMENT); // Alinha verticalmente
			panelNumeros.add(label);

			// Adiciona espaço à direita, exceto no último
			if (i != 16) {
				if(i>=1 && i<=9)panelNumeros.add(Box.createRigidArea(new Dimension(11, 0))); // Espaço horizontal
				else panelNumeros.add(Box.createRigidArea(new Dimension(10, 0)));
			}
		}

		panelPoltronas.add(panelNumeros);

		JLabel lblP = new JLabel("P");
		lblP.setBounds(10, 0, 34, 34);
		panelPoltronas.add(lblP);
		lblP.setFont(new Font("Tahoma", Font.BOLD, 16));

		JLabel lblA = new JLabel("A");
		lblA.setBounds(10, 573, 15, 14);
		lblA.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelPoltronas.add(lblA);
																						
																								JPanel panel = new JPanel();
																								panel.setBounds(10, 11, 235, 372);
																								contentPane.add(panel);
																								panel.setBackground(new Color(255, 255, 255));
																								panel.setLayout(null);
																								
																										JLabel lblLegenda = new JLabel("Legenda");
																										lblLegenda.setBounds(66, 11, 91, 21);
																										panel.add(lblLegenda);
																										lblLegenda.setFont(new Font("Tahoma", Font.BOLD, 17));
																										
																												JLabel imgPoltrona = new JLabel(iconePoltrona);
																												imgPoltrona.setBounds(28, 45, 29, 29);
																												panel.add(imgPoltrona);
																												
																														JLabel imgBloqueado = new JLabel(iconeBloqueada);
																														imgBloqueado.setBounds(28, 165, 29, 29);
																														panel.add(imgBloqueado);
																														
																																JLabel lblBloqueado = new JLabel("Bloqueado");
																																lblBloqueado.setFont(new Font("Tahoma", Font.PLAIN, 14));
																																lblBloqueado.setBounds(66, 165, 91, 29);
																																panel.add(lblBloqueado);
																																
																																		JLabel imgLocalCadeirantes = new JLabel(iconeLocalCadeirantes);
																																		imgLocalCadeirantes.setBounds(28, 125, 29, 29);
																																		panel.add(imgLocalCadeirantes);
																																		
																																				JLabel lblLocalPCadeirantes = new JLabel("Local p/ Cadeirantes");
																																				lblLocalPCadeirantes.setFont(new Font("Tahoma", Font.PLAIN, 14));
																																				lblLocalPCadeirantes.setBounds(66, 125, 131, 29);
																																				panel.add(lblLocalPCadeirantes);
																																				
																																						JLabel lblPoltronaPObesos = new JLabel("Poltrona p/ Obesos");
																																						lblPoltronaPObesos.setFont(new Font("Tahoma", Font.PLAIN, 14));
																																						lblPoltronaPObesos.setBounds(66, 85, 121, 29);
																																						panel.add(lblPoltronaPObesos);
																																						
																																								JLabel imgPoltronaObesos = new JLabel(iconePoltronaObesos);
																																								imgPoltronaObesos.setBounds(28, 85, 29, 29);
																																								panel.add(imgPoltronaObesos);
																																								
																																										JLabel lblDisponivel = new JLabel("Disponível");
																																										lblDisponivel.setFont(new Font("Tahoma", Font.PLAIN, 14));
																																										lblDisponivel.setBounds(66, 43, 84, 31);
																																										panel.add(lblDisponivel);
																																										
																																												JButton btnVoltar = new JButton("Voltar");
																																												btnVoltar.setBounds(24, 614, 89, 23);
																																												contentPane.add(btnVoltar);
																																												btnVoltar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				TelaEscolhaSalaBloquear telaEscolhaSalaBloquear = new TelaEscolhaSalaBloquear();
				telaEscolhaSalaBloquear.setLocationRelativeTo(null);
				telaEscolhaSalaBloquear.setVisible(true);

				dispose();

			}
		});

		Sala sala = salaAtual;
		int espaco = 10;
		int tamanho = 23;

		for (int i = 0; i < sala.getLinhas(); i++) {

			for (int j = 0; j < sala.getColunas(); j++) {

				JButton botao = null;
				String poltronaSelecionada = sala.obterTipoDeEstrutura(i, j).getIdentificador();
				int deslocamentoX = 35;

				switch (sala.obterTipoDeEstrutura(i, j).getTipo()) {
				case Vazio:
					botao = new JButton();
					botao.setBounds(deslocamentoX + j * (tamanho + espaco+2), 8 + i * (tamanho + espaco), tamanho,
							tamanho);
					botao.setVisible(false);
					break;
				case Poltrona:
					if (sala.estaBloqueado(i, j)) {
						botao = new JButton(iconeBloqueada);
					} else {
						botao = new JButton(iconePoltrona);
					}

					botao.setBounds(deslocamentoX + j * (tamanho + espaco+2), 8 + i * (tamanho + espaco), tamanho,
							tamanho);
					break;
				case PoltronaObesos:
					if (sala.estaBloqueado(i, j)) {
						botao = new JButton(iconeBloqueada);
					} else {
						botao = new JButton(iconePoltronaObesos);
					}
					botao.setBounds(deslocamentoX - 2 + j * (tamanho + espaco+2), 5 + i * (tamanho + espaco), tamanho + 5,
							tamanho + 5);
					break;
				case LocalCadeirantes:
					if (sala.estaBloqueado(i, j)) {
						botao = new JButton(iconeBloqueada);
					} else {
						botao = new JButton(iconeLocalCadeirantes);
					}
					botao.setBounds(deslocamentoX + j * (tamanho + espaco+2), 8 + i * (tamanho + espaco), tamanho,
							tamanho);
					break;
				}

				botao.setBackground(Color.WHITE);
				botao.setBorderPainted(false);

				// Ação do botão
				final int auxI = i;
				final int auxJ = j;
				final JButton auxBotao = botao;
				botao.addActionListener(e -> {
					Poltrona poltrona = (Poltrona) sala.obterTipoDeEstrutura(auxI, auxJ);

					boolean isBloqueada = auxBotao.getIcon().equals(iconeBloqueada);

					// Se já está bloqueada, desmarcar
					if (isBloqueada) {
						if (auxBotao.getIcon().equals(iconeBloqueada)) {
							auxBotao.setIcon(iconePoltrona);
						} else if (auxBotao.getIcon().equals(iconeBloqueada)) {
							auxBotao.setIcon(iconePoltronaObesos);
						} else {
							auxBotao.setIcon(iconeLocalCadeirantes);
						}
						/*
						 * modeloLista.removeElement(poltronaSelecionada); poltronas.remove(poltrona);
						 * poltronasRestantes++;
						 */
						System.out.println(app.getBackend().getBancoSalas()
								.tentarDesbloquearLocal(salaAtual.getIdSala(), auxI, auxJ));
						;
					}
					// Se ainda pode selecionar, marcar
					else {
						if (auxBotao.getIcon().equals(iconePoltrona)) {
							auxBotao.setIcon(iconeBloqueada);
						} else if (auxBotao.getIcon().equals(iconePoltronaObesos)) {
							auxBotao.setIcon(iconeBloqueada);
						} else if (auxBotao.getIcon().equals(iconeLocalCadeirantes)) {
							auxBotao.setIcon(iconeBloqueada);
						}
						/*
						 * modeloLista.addElement(poltronaSelecionada); poltronas.add(poltrona);
						 * poltronasRestantes--;
						 */
						System.out.println(app.getBackend().getBancoSalas().tentarBloquearLocal(salaAtual.getIdSala(),
								auxI, auxJ));
					}
				});

				panelPoltronas.add(botao);
			}
		}

		/*
		 * Checa se tá achando a imagem mesmo java.net.URL url =
		 * getClass().getResource("/img/poltronaPreta.png");
		 * System.out.println("URL da imagem: " + url);
		 */

	}
}
