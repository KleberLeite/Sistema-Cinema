package cinemax.frontend.vendadeingressos;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cinemax.backend.core.Backend;
import cinemax.backend.filmes.Sessao;
import cinemax.backend.relatorios.filmes.Ingresso;
import cinemax.backend.relatorios.filmes.TipoDeIngresso;
import cinemax.frontend.controller.ControladorDeApp;
import cinemax.frontend.gerenciamentofilmes.TelaDetalhesFilme;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;

import javax.swing.JScrollPane;
import javax.swing.JTextField;

import java.awt.Dimension;

import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import javax.swing.JList;
import javax.swing.ListModel;

public class TelaEscolhaMeiaOuInteira extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JPanel panelMeias;
	JPanel panelRGs;
	private List<JTextField> listaDeTextFieldsRGs = new ArrayList<>();
	JLabel lblTotalDeIngressosRestantes;
	JScrollPane scrollPaneRGs;
	JTextField textFieldRGs;
	private ControladorDeApp app = ControladorDeApp.getInstancia();
	Backend bancos = app.getBackend();
	private TelaDetalhesFilme telaDetalhesFilme;

	private int meiasCount;
	private int inteirasCount;
	private int totalCount;

	private List<JPanel> rgCards = new ArrayList<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		throw new UnsupportedOperationException("Não pode iniciar por aqui!");
	}
	// MEthods utils
	// ---------------------------------------------------------------------------------

	private int getRestanteCount() {
		return totalCount - inteirasCount - meiasCount;
	}

	private double getSubprecoInteiras() {
		return Ingresso.PRECO_INGRESSO * inteirasCount;
	}

	private int getTotalSelecionadoCount() {
		return meiasCount + inteirasCount;
	}

	private double getSubprecoMeias() {
		return Ingresso.PRECO_INGRESSO * meiasCount / 2;
	}

	private double getPrecoTotal() {
		return getSubprecoInteiras() + getSubprecoMeias();
	}

	public void adicionarRGLista(JPanel panelRGs) {
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

		rgCards.add(card);
		listaDeTextFieldsRGs.add(textField);

		panelRGs.add(Box.createRigidArea(new Dimension(0, 10)));
		panelRGs.add(card);

		ativaOuDesativaListaRGs();
	}

	public void removeUltimoRGLista(JPanel panelRGs) {
		JPanel lastCard = rgCards.get(rgCards.size() - 1);

		rgCards.remove(rgCards.size() - 1);
		listaDeTextFieldsRGs.remove(listaDeTextFieldsRGs.size() - 1);

		panelRGs.remove(lastCard);
		panelRGs.revalidate();
		panelRGs.repaint();

		ativaOuDesativaListaRGs();
	}

	/*
	 * public void atualizarListaDeRGs(JPanel panelRGs) { String[] valoresAntigos =
	 * new String[meiasCount]; System.out.println(listaDeTextFieldsRGs.size());
	 * for(int j = 0; j < meiasCount; j++) { valoresAntigos[j] =
	 * listaDeTextFieldsRGs.get(j).getText(); j++; }
	 * 
	 * ativaOuDesativaListaRGs();
	 * 
	 * panelRGs.removeAll(); listaDeTextFieldsRGs.clear();
	 * 
	 * 
	 * 
	 * for (int i = 0; i < meiasCount; i++) { JPanel card = new JPanel();
	 * card.setLayout(null); card.setPreferredSize(new Dimension(400, 50));
	 * card.setMaximumSize(new Dimension(400, 50)); card.setBackground(new
	 * Color(230, 210, 250)); card.setBorder(new EmptyBorder(5, 5, 5, 5));
	 * 
	 * JLabel lblRG = new JLabel("RG:"); lblRG.setFont(new Font("Tahoma", Font.BOLD,
	 * 14)); lblRG.setBounds(10, 10, 40, 25); card.add(lblRG);
	 * 
	 * JTextField textField = new JTextField(valoresAntigos[i]);
	 * textField.setBounds(60, 10, 100, 25); card.add(textField);
	 * 
	 * listaDeTextFieldsRGs.add(textField); // Adiciona à lista
	 * 
	 * panelRGs.add(Box.createRigidArea(new Dimension(0, 10))); panelRGs.add(card);
	 * }
	 * 
	 * panelRGs.revalidate(); panelRGs.repaint(); }
	 */

	// -----------------------------------------------------------------------------------------------

	public void ativaOuDesativaListaRGs() {

		if (meiasCount > 0) {
			panelMeias.setBounds(10, 79, 405, 272);
			lblTotalDeIngressosRestantes.setBounds(10, 362, 234, 25);
			scrollPaneRGs.setEnabled(true);
			panelRGs.setEnabled(true);

		} else {
			panelMeias.setBounds(10, 79, 405, 57);
			lblTotalDeIngressosRestantes.setBounds(10, 147, 234, 25);
			scrollPaneRGs.setEnabled(false);
			panelRGs.setEnabled(false);

		}

	}

	public void configuraRGsMeia(List<Ingresso> ingressos) {
		for (int i = 0; i < meiasCount; i++) {
			System.out.println(listaDeTextFieldsRGs.get(i).getText().trim());
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

	private void configuraTiposIngresso(List<Ingresso> ingressos) {
		for (int i = 0; i < meiasCount; i++) {
			ingressos.get(i).setTipo(TipoDeIngresso.Meia);
		}
		for (int i = meiasCount; i < totalCount; i++) {
			ingressos.get(i).setTipo(TipoDeIngresso.Inteira);
		}
	}

	/**
	 * Create the frame.
	 */
	public TelaEscolhaMeiaOuInteira(Sessao sessao, CarrinhoIngressos carrinho) {
		this.totalCount = carrinho.qtdeTotalIngressos();
		// System.out.println("Total de Ingressos: " + getRestanteCount());

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 700);
		setTitle("Cinemax");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel contentPane_1 = new JPanel();
		contentPane_1.setLayout(null);
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane_1.setBackground(new Color(2, 18, 27));
		contentPane_1.setBounds(0, 0, 784, 671);
		contentPane.add(contentPane_1);

		// --------------------------------------------------------------------------------------------------------------------------------------------------------------
		// Criando e preparando os icones já redimensionando-os
		// ------------------------------------------------------------------------------------------------------------------

		ImageIcon iconeBilheteInteiraParcial = new ImageIcon(getClass().getResource("/img/BilheteInteira.png"));
		Image imgIconeBilheteInteiraParcial = iconeBilheteInteiraParcial.getImage().getScaledInstance(46, 35,
				Image.SCALE_SMOOTH);
		ImageIcon iconeBilheteInteira = new ImageIcon(imgIconeBilheteInteiraParcial);

		ImageIcon iconeBilheteMeiaParcial = new ImageIcon(getClass().getResource("/img/BilheteMeia.png"));
		Image imgIconeBilheteMeiaaParcial = iconeBilheteMeiaParcial.getImage().getScaledInstance(46, 35,
				Image.SCALE_SMOOTH);
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
				if (getRestanteCount() > 0) {
					JOptionPane.showMessageDialog(null, "Adicione todos os ingressos antes de continuar!", "Erro",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				if (!validarRGs()) {
					JOptionPane.showMessageDialog(null, "Alguns RGs estão incorretos, por favor, verifique-os!", "Erro",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				configuraTiposIngresso(carrinho.getIngressos());
				configuraRGsMeia(carrinho.getIngressos());

				TelaConclusaoDeCompra telaConclusaoDeCompra = new TelaConclusaoDeCompra(sessao, carrinho);
				telaConclusaoDeCompra.setVisible(true);
				telaConclusaoDeCompra.setLocationRelativeTo(null);
				dispose();
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

		JLabel imgMeia = new JLabel(iconeBilheteMeia);
		imgMeia.setBounds(10, 13, 46, 35);
		panelMeias.add(imgMeia);

		String ingressosRestantes = String.format("Total de Ingressos Restantes: %d", getRestanteCount());
		lblTotalDeIngressosRestantes = new JLabel(ingressosRestantes);
		lblTotalDeIngressosRestantes.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTotalDeIngressosRestantes.setBounds(10, 147, 234, 25);
		panelIngressos.add(lblTotalDeIngressosRestantes);

		JPanel panelResumo = new JPanel();
		panelResumo.setBackground(new Color(255, 255, 255));
		panelResumo.setLayout(null);
		panelResumo.setBounds(445, 7, 329, 453);
		contentPane_1.add(panelResumo);

		JLabel lblLinha = new JLabel("_____________________________________________");
		lblLinha.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblLinha.setHorizontalAlignment(SwingConstants.CENTER);
		lblLinha.setBounds(0, 372, 329, 14);
		panelResumo.add(lblLinha);

		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTotal.setBounds(10, 428, 83, 14);
		panelResumo.add(lblTotal);

		JLabel lblItens = new JLabel("Itens");
		lblItens.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblItens.setBounds(10, 397, 83, 14);
		panelResumo.add(lblItens);

		JLabel lblPrecoTotal = new JLabel("R$ 0.00");
		lblPrecoTotal.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPrecoTotal.setBounds(263, 429, 91, 14);
		panelResumo.add(lblPrecoTotal);

		JLabel lblTotalItens = new JLabel("0");
		lblTotalItens.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTotalItens.setBounds(283, 397, 91, 14);
		panelResumo.add(lblTotalItens);

		JLabel lblInteiraResumo = new JLabel("0x Inteira");
		lblInteiraResumo.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblInteiraResumo.setBounds(10, 322, 83, 14);
		panelResumo.add(lblInteiraResumo);

		JLabel lblMeiaResumo = new JLabel("0x Meia");
		lblMeiaResumo.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMeiaResumo.setBounds(10, 347, 83, 14);
		panelResumo.add(lblMeiaResumo);

		JLabel lblSubPrecoInteira = new JLabel("R$ 0.00");
		lblSubPrecoInteira.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSubPrecoInteira.setBounds(263, 322, 76, 14);
		panelResumo.add(lblSubPrecoInteira);

		JLabel lblSubPrecoMeia = new JLabel("R$ 0.00");
		lblSubPrecoMeia.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSubPrecoMeia.setBounds(263, 347, 76, 14);
		panelResumo.add(lblSubPrecoMeia);

		JLabel lblNewLabel_1 = new JLabel("Resumo do Pedido:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(0, 0, 233, 31);
		panelResumo.add(lblNewLabel_1);

		JPanel panelResumoFIlme = new JPanel();
		panelResumoFIlme.setLayout(null);
		panelResumoFIlme.setBackground(Color.WHITE);
		panelResumoFIlme.setBounds(0, 32, 329, 145);
		panelResumo.add(panelResumoFIlme);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(10, 11, 114, 123);
		panelResumoFIlme.add(panel);

		JLabel lblNewLabel_2 = new JLabel("Suposta Imagem");
		lblNewLabel_2.setBounds(10, 53, 80, 14);
		panel.add(lblNewLabel_2);

		JPanel panelNomeEClassificacao = new JPanel();
		panelNomeEClassificacao.setBorder(new EmptyBorder(0, 0, 0, 0));
		panelNomeEClassificacao.setBackground(Color.WHITE);
		panelNomeEClassificacao.setBounds(134, 11, 185, 56);
		panelResumoFIlme.add(panelNomeEClassificacao);
		panelNomeEClassificacao.setLayout(new FlowLayout(FlowLayout.LEFT));

		JLabel lblNome = new JLabel(sessao.getFilme().getNome());
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 13));
		panelNomeEClassificacao.add(lblNome);

		JLabel lblClassificacao = new JLabel(sessao.getFilme().getClassificacaoIndicativa().name());
		lblClassificacao.setFont(new Font("Tahoma", Font.BOLD, 13));
		panelNomeEClassificacao.add(lblClassificacao);

		JLabel lblCinemax = new JLabel("Cinemax");
		lblCinemax.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCinemax.setBounds(134, 74, 185, 24);
		panelResumoFIlme.add(lblCinemax);

		JLabel lblVerDetalhes = new JLabel("Ver detalhes...");
		lblVerDetalhes.setForeground(new Color(0, 12, 159));
		lblVerDetalhes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblVerDetalhes.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		    	
		        telaDetalhesFilme = new TelaDetalhesFilme(sessao.getFilme());
		        telaDetalhesFilme.setVisible(true);
		        telaDetalhesFilme.setLocationRelativeTo(null);
		        
		    }
		});
		lblVerDetalhes.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblVerDetalhes.setBounds(134, 108, 78, 26);
		panelResumoFIlme.add(lblVerDetalhes);

		JLabel lblNewLabel_4 = new JLabel("Poltronas");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_4.setBounds(0, 176, 124, 24);
		panelResumo.add(lblNewLabel_4);

		JScrollPane scrollPanePoltronas = new JScrollPane();
		scrollPanePoltronas.setBounds(0, 205, 329, 53);
		panelResumo.add(scrollPanePoltronas);
		
		DefaultListModel<String> modeloLista = new DefaultListModel<>();

		for (Ingresso ingresso : carrinho.getIngressos()) {
			modeloLista.addElement(ingresso.getPoltrona().getIdentificador());
		}

		// Estilizando a lista:
		DefaultListCellRenderer defaultListCellRenderer = new DefaultListCellRenderer() {
			public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected,
						cellHasFocus);

				label.setHorizontalAlignment(SwingConstants.CENTER);
				label.setFont(new Font("Tahoma", Font.PLAIN, 14));

				return label;
			}
		};

		JList<String> listPoltronasSelecionadas = new JList<String>(modeloLista);
		scrollPanePoltronas.setViewportView(listPoltronasSelecionadas);
		listPoltronasSelecionadas.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		listPoltronasSelecionadas.setVisibleRowCount(-1); // -1 significa que ele vai quebrar sozinho
		listPoltronasSelecionadas.setFixedCellWidth(50); // Largura de cada "item" (ajuste como quiser)
		listPoltronasSelecionadas.setCellRenderer(defaultListCellRenderer);

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
				inteirasCount++;

				if (getRestanteCount() == 0) {
					btnMaisUmaInteira.setEnabled(false);
					btnMaisUmaMeia.setEnabled(false);
				}

				if (!btnMenosUmaInteira.isEnabled()) {
					btnMenosUmaInteira.setEnabled(true);
				}

				lblSubPrecoInteira.setText(String.format("R$ %.2f", getSubprecoInteiras()));
				lblPrecoTotal.setText(String.format("R$ %.2f", getPrecoTotal()));

				lblInteiraResumo.setText(String.format("%dx Inteira", inteirasCount));
				lblTotalItens.setText(String.format("%d", getTotalSelecionadoCount()));

				lblTotalDeIngressosRestantes
						.setText(String.format("Total de Ingressos Restantes: %d", getRestanteCount()));
			}
		});
		btnMaisUmaInteira.setBounds(354, 17, 41, 23);
		panelInteiras.add(btnMaisUmaInteira);

		btnMenosUmaInteira.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inteirasCount--;

				if (inteirasCount == 0) {
					btnMenosUmaInteira.setEnabled(false);
				}

				btnMaisUmaInteira.setEnabled(true);
				btnMaisUmaMeia.setEnabled(true);

				lblSubPrecoInteira.setText(String.format("R$ %.2f", getSubprecoInteiras()));
				lblPrecoTotal.setText(String.format("R$ %.2f", getPrecoTotal()));

				lblInteiraResumo.setText(String.format("%dx Inteira", inteirasCount));
				lblTotalItens.setText(String.format("%d", getTotalSelecionadoCount()));

				lblTotalDeIngressosRestantes
						.setText(String.format("Total de Ingressos Restantes: %d", getRestanteCount()));
			}
		});
		btnMenosUmaInteira.setBounds(303, 17, 41, 23);
		panelInteiras.add(btnMenosUmaInteira);
		btnMenosUmaInteira.setEnabled(false);

		btnMaisUmaMeia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				meiasCount++;

				if (getRestanteCount() == 0) {
					btnMaisUmaInteira.setEnabled(false);
					btnMaisUmaMeia.setEnabled(false);
				}

				if (!btnMenosUmaMeia.isEnabled()) {
					btnMenosUmaMeia.setEnabled(true);
				}

				lblSubPrecoMeia.setText(String.format("R$ %.2f", getSubprecoMeias()));
				lblPrecoTotal.setText(String.format("R$ %.2f", getPrecoTotal()));

				lblMeiaResumo.setText(String.format("%dx Meia", meiasCount));
				lblTotalItens.setText(String.format("%d", getTotalSelecionadoCount()));

				lblTotalDeIngressosRestantes
						.setText(String.format("Total de Ingressos Restantes: %d", getRestanteCount()));

				adicionarRGLista(panelRGs);
			}
		});
		btnMaisUmaMeia.setBounds(354, 17, 41, 23);
		panelMeias.add(btnMaisUmaMeia);

		btnMenosUmaMeia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				meiasCount--;

				if (meiasCount == 0) {
					btnMenosUmaMeia.setEnabled(false);
				}

				btnMaisUmaInteira.setEnabled(true);
				btnMaisUmaMeia.setEnabled(true);

				lblSubPrecoMeia.setText(String.format("R$ %.2f", getSubprecoMeias()));
				lblPrecoTotal.setText(String.format("R$ %.2f", getPrecoTotal()));

				lblMeiaResumo.setText(String.format("%dx Meia", meiasCount));
				lblTotalItens.setText(String.format("%d", getTotalSelecionadoCount()));

				lblTotalDeIngressosRestantes
						.setText(String.format("Total de Ingressos Restantes: %d", getRestanteCount()));

				removeUltimoRGLista(panelRGs);
			}
		});
		btnMenosUmaMeia.setBounds(303, 17, 41, 23);
		panelMeias.add(btnMenosUmaMeia);
		btnMenosUmaMeia.setEnabled(false);

	}
}
