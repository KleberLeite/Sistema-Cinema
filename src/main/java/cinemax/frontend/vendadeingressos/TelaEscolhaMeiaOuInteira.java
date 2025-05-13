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
import cinemax.frontend.estilizacao.Estilizador;
import cinemax.frontend.estilizacao.EstiloBotao;
import cinemax.frontend.estilizacao.JTextFieldEstilizado;

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
import javax.swing.BorderFactory;
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
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
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
	private List<Component> rgSpacers = new ArrayList<>(); 

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
	    Component spacer = Box.createRigidArea(new Dimension(0, 5));
	    JPanel card = Estilizador.criarPainelArredondado(new Color(2, 18, 27), 10);
	    card.setLayout(null);
	    card.setPreferredSize(new Dimension(350, 40));
	    card.setMaximumSize(new Dimension(350, 40));
	    card.setBorder(new EmptyBorder(5, 5, 5, 5));

	    JLabel lblRG = new JLabel("RG:");
	    lblRG.setForeground(new Color(255,255,255));
	    lblRG.setFont(new Font("Tahoma", Font.BOLD, 14));
	    lblRG.setBounds(10, 8, 40, 25);
	    card.add(lblRG);

	    JTextField textField = new JTextFieldEstilizado(null);
	    textField.setBounds(60, 8, 100, 25);
	    card.add(textField);

	    rgCards.add(card);
	    rgSpacers.add(spacer); // salva o espaçador
	    listaDeTextFieldsRGs.add(textField);

	    panelRGs.add(spacer);
	    panelRGs.add(card);

	    ativaOuDesativaListaRGs();
	}

	public void removeUltimoRGLista(JPanel panelRGs) {
	    if (!rgCards.isEmpty()) {
	        JPanel lastCard = rgCards.remove(rgCards.size() - 1);
	        Component lastSpacer = rgSpacers.remove(rgSpacers.size() - 1);

	        listaDeTextFieldsRGs.remove(listaDeTextFieldsRGs.size() - 1);

	        panelRGs.remove(lastCard);
	        panelRGs.remove(lastSpacer); // remove o espaçador correspondente

	        panelRGs.revalidate();
	        panelRGs.repaint();

	        ativaOuDesativaListaRGs();
	    }
	}
	
	private JLabel geraLabelVerDetalhes(Sessao sessao) {
		JLabel lblVerDetalhes = new JLabel("Ver detalhes...");
		lblVerDetalhes.setForeground(new Color(0, 12, 159));
		lblVerDetalhes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblVerDetalhes.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblVerDetalhes.setBounds(134, 114, 78, 20);

		Color corOriginal = new Color(0, 12, 159);
		Color corHover = new Color(80, 80, 200); // um azul mais claro

		lblVerDetalhes.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        telaDetalhesFilme = new TelaDetalhesFilme(sessao.getFilme());
		        telaDetalhesFilme.setVisible(true);
		        telaDetalhesFilme.setLocationRelativeTo(null);
		    }

		    @Override
		    public void mouseEntered(MouseEvent e) {
		        lblVerDetalhes.setForeground(corHover);
		    }

		    @Override
		    public void mouseExited(MouseEvent e) {
		        lblVerDetalhes.setForeground(corOriginal);
		    }
		});

		return lblVerDetalhes;

	}



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
		Estilizador.aplicarEstiloBotao(btnVoltar, EstiloBotao.CLARO_UNIFICADO);
		btnVoltar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				TelaEscolhaPoltrona telaEscolhaPoltrona = new TelaEscolhaPoltrona(sessao);
				telaEscolhaPoltrona.setLocationRelativeTo(null);
				telaEscolhaPoltrona.setVisible(true);

				dispose();
				
				telaDetalhesFilme.dispose();
				}
		});
		btnVoltar.setBounds(10, 608, 89, 23);
		contentPane_1.add(btnVoltar);

		JButton btnFinalizarCompra = new JButton("Concluir Compra");
		Estilizador.aplicarEstiloBotao(btnFinalizarCompra, EstiloBotao.CLARO_UNIFICADO);
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
		lblPrecoTotal.setBounds(256, 429, 91, 14);
		panelResumo.add(lblPrecoTotal);

		JLabel lblTotalItens = new JLabel("0");
		lblTotalItens.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTotalItens.setBounds(280, 397, 91, 14);
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
		lblSubPrecoInteira.setBounds(256, 322, 76, 14);
		panelResumo.add(lblSubPrecoInteira);

		JLabel lblSubPrecoMeia = new JLabel("R$ 0.00");
		lblSubPrecoMeia.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSubPrecoMeia.setBounds(256, 347, 76, 14);
		panelResumo.add(lblSubPrecoMeia);

		JLabel lblNewLabel_1 = new JLabel("Resumo do Pedido:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(10, 0, 233, 31);
		panelResumo.add(lblNewLabel_1);

		JPanel panelResumoFIlme = new JPanel();
		panelResumoFIlme.setBorder(BorderFactory.createLineBorder(Color.BLACK));
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

		JLabel lblEspaço = new JLabel("     ");
		panelNomeEClassificacao.add(lblEspaço);

		Color corCerta = Estilizador.escolherCorDaClassificacao(sessao.getFilme().getClassificacaoIndicativa());
		
		
		JLabel lblFraseClassificacao = new JLabel(" " + sessao.getFilme().getClassificacaoIndicativa().name()+" ");
		lblFraseClassificacao.setBounds(10, 69, 400, 25);
		lblFraseClassificacao.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblFraseClassificacao.setForeground(new Color(255, 255, 255));
		lblFraseClassificacao.setOpaque(true);
		lblFraseClassificacao.setBackground(corCerta);
		panelNomeEClassificacao.add(lblFraseClassificacao);

		JLabel lblCinemax = new JLabel("Cinemax");
		lblCinemax.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCinemax.setBounds(134, 78, 185, 20);
		panelResumoFIlme.add(lblCinemax);

		JLabel lblVerDetalhes = geraLabelVerDetalhes(sessao);
		panelResumoFIlme.add(lblVerDetalhes);
		
		String diaFormatado = sessao.getInicio().getDayOfWeek().getDisplayName(TextStyle.SHORT, new Locale("pt", "BR")).toUpperCase();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		String horaDaSessao = sessao.getInicio().format(formatter);

		JLabel lblSalaEData = new JLabel("SALA "+sessao.getId()+" | "+diaFormatado+" "+horaDaSessao);
		lblSalaEData.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSalaEData.setBounds(134, 96, 185, 20);
		panelResumoFIlme.add(lblSalaEData);

		JLabel lblPoltronas = new JLabel("Poltronas");
		lblPoltronas.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPoltronas.setBounds(10, 176, 124, 24);
		panelResumo.add(lblPoltronas);

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
				label.setFont(new Font("Tahoma", Font.BOLD, 14));

				return label;
			}
		};

		JList<String> listPoltronasSelecionadas = new JList<String>(modeloLista);
		scrollPanePoltronas.setViewportView(listPoltronasSelecionadas);
		listPoltronasSelecionadas.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		listPoltronasSelecionadas.setVisibleRowCount(-1); // -1 significa que ele vai quebrar sozinho
		listPoltronasSelecionadas.setFixedCellWidth(40); // Largura de cada "item" (ajuste como quiser)
		listPoltronasSelecionadas.setCellRenderer(defaultListCellRenderer);

		scrollPaneRGs = new JScrollPane();
		scrollPaneRGs = Estilizador.estilizarScrollPane(scrollPaneRGs);
		scrollPaneRGs.setEnabled(false);
		scrollPaneRGs.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneRGs.setBounds(90, 80, 195, 138);
		panelMeias.add(scrollPaneRGs);

		panelRGs = new JPanel();
		panelRGs.setLayout(new BoxLayout(panelRGs, BoxLayout.Y_AXIS)); // lista vertical
		panelRGs.setBorder(new EmptyBorder(5, 5, 5, 5)); // margem geral
		panelRGs.setEnabled(false);
		scrollPaneRGs.setViewportView(panelRGs);

		ativaOuDesativaListaRGs();

		// Declaração dos botões de + e -
		JButton btnMaisUmaInteira = new JButton("+");
		Estilizador.aplicarEstiloBotao(btnMaisUmaInteira, EstiloBotao.PADRAO_ESCURECIDO);
		JButton btnMenosUmaInteira = new JButton("-");
		Estilizador.aplicarEstiloBotao(btnMenosUmaInteira, EstiloBotao.PADRAO_ESCURECIDO);
		JButton btnMaisUmaMeia = new JButton("+");
		Estilizador.aplicarEstiloBotao(btnMaisUmaMeia, EstiloBotao.PADRAO_ESCURECIDO);
		JButton btnMenosUmaMeia = new JButton("-");
		Estilizador.aplicarEstiloBotao(btnMenosUmaMeia, EstiloBotao.PADRAO_ESCURECIDO);

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
