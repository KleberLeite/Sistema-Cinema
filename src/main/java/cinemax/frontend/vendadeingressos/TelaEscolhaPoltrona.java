package cinemax.frontend.vendadeingressos;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import cinemax.backend.filmes.Sessao;
import cinemax.backend.relatorios.filmes.Ingresso;
import cinemax.backend.salas.Poltrona;
import cinemax.backend.salas.Sala;
import cinemax.frontend.controller.ControladorDeApp;
import cinemax.frontend.estilizacao.Estilizador;
import cinemax.frontend.estilizacao.EstiloBotao;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.awt.event.ActionEvent;
import java.awt.Font;

@SuppressWarnings("serial")
public class TelaEscolhaPoltrona extends JFrame {
	private JPanel panelPrincipal;
	ControladorDeApp app = ControladorDeApp.getInstancia();
	private List<Poltrona> poltronas = new ArrayList<>();
	private int poltronasRestantes;// Contabiliza a quantidade de poltronas que ainda podem ser escolhidas
	private TelaDetalhesFilme telaDetalhesFilme;
	private JLabel lblTotalItens;
	private int totalItens;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaEscolhaPoltrona frame = new TelaEscolhaPoltrona(null);
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					frame.setTitle("Cinemax");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
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
		    	if(telaDetalhesFilme != null) {
		    		return;
		    	}
		    	
		        telaDetalhesFilme = new TelaDetalhesFilme(sessao.getFilme());
		        telaDetalhesFilme.setVisible(true);
		        telaDetalhesFilme.setLocationRelativeTo(null);
		        
		        telaDetalhesFilme.addWindowListener(new WindowAdapter() {
		            @Override
		            public void windowClosed(WindowEvent e) {
		            	telaDetalhesFilme = null;
		            }
		        });
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

	/**
	 * Create the application.
	 */
	public TelaEscolhaPoltrona(Sessao sessao1) {
		if(sessao1==null) sessao1 = app.getBackend().getBancoFilmes().obterFilmePorId(0).obterSessao(0);
		final Sessao sessao = sessao1;
		
		
		
		
		
		
		
		
		
		
		poltronasRestantes = 8;
		
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(-14, -42, 800, 700);
		panelPrincipal = new JPanel();
		panelPrincipal.setBackground(new Color(2, 18, 27));
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);

		JPanel panelPoltronas = new JPanel();
		panelPoltronas.setBackground(new Color(255, 255, 255));
		panelPoltronas.setBounds(10, 11, 425, 590);
		panelPrincipal.add(panelPoltronas);

		// --------------------------------------------------------------------------------------------------------------------------------------------------------------
		// Criando e preparando os icones já redimensionando-os
		// ------------------------------------------------------------------------------------------------------------------

		ImageIcon iconePoltronaLivreParcial = new ImageIcon(getClass().getResource("/img/Poltrona.png"));
		Image imgIconePoltronaLivreParcial = iconePoltronaLivreParcial.getImage().getScaledInstance(15, 15,
				Image.SCALE_SMOOTH);
		ImageIcon iconePoltrona = new ImageIcon(imgIconePoltronaLivreParcial);

		ImageIcon iconePoltronaSelecionadaParcial = new ImageIcon(
				getClass().getResource("/img/PoltronaSelecionada.png"));
		Image imgIconePoltronaSelecionadaParcial = iconePoltronaSelecionadaParcial.getImage().getScaledInstance(15, 15,
				Image.SCALE_SMOOTH);
		ImageIcon iconePoltronaSelecionada = new ImageIcon(imgIconePoltronaSelecionadaParcial);

		ImageIcon iconePoltronaOcupadaParcial = new ImageIcon(getClass().getResource("/img/PoltronaOcupada.png"));
		Image imgIconePoltronaOcupadaParcial = iconePoltronaOcupadaParcial.getImage().getScaledInstance(15, 15,
				Image.SCALE_SMOOTH);
		ImageIcon iconePoltronaOcupada = new ImageIcon(imgIconePoltronaOcupadaParcial);

		ImageIcon iconePoltronaObesosLivreParcial = new ImageIcon(getClass().getResource("/img/PoltronaObesos.png"));
		Image imgIconePoltronaObesosLivreParcial = iconePoltronaObesosLivreParcial.getImage().getScaledInstance(18, 15,
				Image.SCALE_SMOOTH);
		ImageIcon iconePoltronaObesos = new ImageIcon(imgIconePoltronaObesosLivreParcial);

		ImageIcon iconePoltronaObesosSelecionadaParcial = new ImageIcon(
				getClass().getResource("/img/PoltronaObesosSelecionada.png"));
		Image imgIconePoltronaObesosSelecionadaParcial = iconePoltronaObesosSelecionadaParcial.getImage()
				.getScaledInstance(18, 15, Image.SCALE_SMOOTH);
		ImageIcon iconePoltronaObesosSelecionada = new ImageIcon(imgIconePoltronaObesosSelecionadaParcial);

		ImageIcon iconePoltronaObesosOcupadaParcial = new ImageIcon(
				getClass().getResource("/img/PoltronaObesosOcupada.png"));
		Image imgIconePoltronaObesosOcupadaParcial = iconePoltronaObesosOcupadaParcial.getImage().getScaledInstance(18,
				15, Image.SCALE_SMOOTH);
		ImageIcon iconePoltronaObesosOcupada = new ImageIcon(imgIconePoltronaObesosOcupadaParcial);

		ImageIcon iconeLocalCadeirantesLivreParcial = new ImageIcon(getClass().getResource("/img/LocalCadeirante.png"));
		Image imgIconeLocalCadeirantesLivreParcial = iconeLocalCadeirantesLivreParcial.getImage().getScaledInstance(15,
				15, Image.SCALE_SMOOTH);
		ImageIcon iconeLocalCadeirantes = new ImageIcon(imgIconeLocalCadeirantesLivreParcial);

		ImageIcon iconeLocalCadeiranteSelecionado = new ImageIcon(
				getClass().getResource("/img/LocalCadeiranteSelecionado.png"));
		Image imgIconeLocalCadeiranteSelecionado = iconeLocalCadeiranteSelecionado.getImage().getScaledInstance(15, 15,
				Image.SCALE_SMOOTH);
		ImageIcon iconeLocalCadeirantesSelecionado = new ImageIcon(imgIconeLocalCadeiranteSelecionado);

		ImageIcon iconeLocalCadeirantesOcupadoParcial = new ImageIcon(
				getClass().getResource("/img/LocalCadeiranteOcupado.png"));
		Image imgIconeLocalCadeirantesOcupadoParcial = iconeLocalCadeirantesOcupadoParcial.getImage()
				.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
		ImageIcon iconeLocalCadeirantesOcupado = new ImageIcon(imgIconeLocalCadeirantesOcupadoParcial);

		ImageIcon iconeBloqueadoParcial = new ImageIcon(getClass().getResource("/img/PoltronaBloqueada.png"));
		Image imgIconeBloqueadoParcial = iconeBloqueadoParcial.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
		ImageIcon iconeBloqueada = new ImageIcon(imgIconeBloqueadoParcial);

		ImageIcon iconeTomDeOcupadoParcial = new ImageIcon(getClass().getResource("/img/TomOcupado.png"));
		Image imgIconeTomDeOcupadoParcial = iconeTomDeOcupadoParcial.getImage().getScaledInstance(15, 15,
				Image.SCALE_SMOOTH);
		ImageIcon IconeTomDeOcupado = new ImageIcon(imgIconeTomDeOcupadoParcial);

		ImageIcon iconeTomDeSelecionadoParcial = new ImageIcon(getClass().getResource("/img/TomSelecionado.png"));
		Image imgIconeTomDeSelecionadoParcial = iconeTomDeSelecionadoParcial.getImage().getScaledInstance(15, 15,
				Image.SCALE_SMOOTH);
		ImageIcon IconeTomDeSelecionado = new ImageIcon(imgIconeTomDeSelecionadoParcial);

		// --------------------------------------------------------------------------------------------------------------------------------------------------------------
		// --------------------------------------------------------------------------------------------------------------------------------------------------------------

		panelPoltronas.setLayout(null);

		JLabel imgPoltrona = new JLabel(iconePoltrona);
		imgPoltrona.setBounds(41, 499, 19, 19);
		panelPoltronas.add(imgPoltrona);

		JLabel imgTomSelecionado = new JLabel(IconeTomDeSelecionado);
		imgTomSelecionado.setBounds(41, 529, 19, 19);
		panelPoltronas.add(imgTomSelecionado);

		JLabel imgPoltronaObesos = new JLabel(iconePoltronaObesos);
		imgPoltronaObesos.setBounds(201, 499, 19, 19);
		panelPoltronas.add(imgPoltronaObesos);

		JLabel imgLocalCadeirantes = new JLabel(iconeLocalCadeirantes);
		imgLocalCadeirantes.setBounds(201, 529, 19, 19);
		panelPoltronas.add(imgLocalCadeirantes);

		JLabel imgTomOcupado = new JLabel(IconeTomDeOcupado);
		imgTomOcupado.setBounds(41, 559, 19, 19);
		panelPoltronas.add(imgTomOcupado);

		JLabel imgBloqueado = new JLabel(iconeBloqueada);
		imgBloqueado.setBounds(201, 560, 19, 19);
		panelPoltronas.add(imgBloqueado);

		JLabel lblLinha = new JLabel("__________________________________________________________");
		lblLinha.setBounds(10, 461, 411, 14);
		panelPoltronas.add(lblLinha);

		JLabel lblDisponivel = new JLabel("Disponível");
		lblDisponivel.setBounds(70, 499, 84, 14);
		panelPoltronas.add(lblDisponivel);

		JLabel lblPoltronaSelecionada = new JLabel("Tom de Selecionado");
		lblPoltronaSelecionada.setBounds(70, 529, 126, 14);
		panelPoltronas.add(lblPoltronaSelecionada);

		JLabel lblPoltronaPObesos = new JLabel("Poltrona p/ Obesos");
		lblPoltronaPObesos.setBounds(230, 499, 121, 14);
		panelPoltronas.add(lblPoltronaPObesos);

		JLabel lblLocalPCadeirantes = new JLabel("Local p/ Cadeirantes");
		lblLocalPCadeirantes.setBounds(230, 529, 131, 14);
		panelPoltronas.add(lblLocalPCadeirantes);

		JLabel lblTomOcupado = new JLabel("Tom de Ocupado");
		lblTomOcupado.setBounds(70, 565, 105, 14);
		panelPoltronas.add(lblTomOcupado);

		JLabel lblBloqueado = new JLabel("Bloqueado");
		lblBloqueado.setBounds(230, 565, 61, 14);
		panelPoltronas.add(lblBloqueado);

		JLabel lblLegenda = new JLabel("Legenda");
		lblLegenda.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblLegenda.setBounds(10, 478, 91, 14);
		panelPoltronas.add(lblLegenda);
		
		JLabel lblNewLabel = new JLabel("P");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(10, 9, 15, 14);
		panelPoltronas.add(lblNewLabel);
		
		JPanel panelLetras = new JPanel();
		panelLetras.setBounds(5, 55, 19, 340);
		panelLetras.setBackground(new Color(255, 255, 255));
		panelLetras.setLayout(new BoxLayout(panelLetras, BoxLayout.Y_AXIS)); // Layout vertical

		// Gera letras de B até O
		for (char letra = 'O'; letra >= 'B'; letra--) {
		    JLabel label = new JLabel(String.valueOf(letra));
		    label.setFont(new Font("Tahoma", Font.BOLD, 13));
		    label.setAlignmentX(Component.CENTER_ALIGNMENT); // Centraliza horizontalmente
		    panelLetras.add(label);
		    
		    
		        panelLetras.add(Box.createRigidArea(new Dimension(0, 9))); // Espaço vertical
		    
		    
		}
		panelPoltronas.add(panelLetras);
		
		JLabel lblA = new JLabel("A");
		lblA.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblA.setBounds(10, 436, 15, 14);
		panelPoltronas.add(lblA);
		
		JPanel panelNumeros = new JPanel();
		panelNumeros.setBounds(32, 449, 389, 19);
		panelNumeros.setBackground(new Color(255, 255, 255));
		panelNumeros.setLayout(new BoxLayout(panelNumeros, BoxLayout.X_AXIS)); // Layout horizontal

		for (int i = 1; i <= 16; i++) {
		    JLabel label = new JLabel(String.valueOf(i));
		    label.setAlignmentY(Component.CENTER_ALIGNMENT); // Alinha verticalmente
		    panelNumeros.add(label);

		    // Adiciona espaço à direita, exceto no último
		    if (i != 16) {
		        if(i>=1 && i<=8)panelNumeros.add(Box.createRigidArea(new Dimension(18, 0))); // Espaço horizontal
		        else if(i<=13)panelNumeros.add(Box.createRigidArea(new Dimension(12, 0)));
		        else panelNumeros.add(Box.createRigidArea(new Dimension(10, 0)));
		    }
		}

		panelPoltronas.add(panelNumeros);

		JButton btnAvançar = new JButton("Avançar");
		Estilizador.aplicarEstiloBotao(btnAvançar, EstiloBotao.CLARO_UNIFICADO);
		btnAvançar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(poltronasRestantes >= 0 && poltronasRestantes!=8) {
					CarrinhoIngressos carrinho = new CarrinhoIngressos();
					for(Poltrona p : poltronas) {
						carrinho.adicionaIngresso(new Ingresso(sessao, p));
					}
					
					TelaEscolhaMeiaOuInteira telaFinalizarCompra = new TelaEscolhaMeiaOuInteira(sessao, carrinho);
					telaFinalizarCompra.setVisible(true);
					telaFinalizarCompra.setLocationRelativeTo(null);
	
					if(telaDetalhesFilme != null) {
						telaDetalhesFilme.dispose();
					}
					
					dispose();
				}else {
					JOptionPane.showMessageDialog(null, "Escolha ao menos uma poltrona para avançar!", "Aviso",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnAvançar.setBounds(674, 612, 89, 23);
		panelPrincipal.add(btnAvançar);

		JButton btnVoltar = new JButton("Voltar");
		Estilizador.aplicarEstiloBotao(btnVoltar, EstiloBotao.CLARO_UNIFICADO);
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				TelaVendaDeIngresso telaEscolhaFilme = new TelaVendaDeIngresso();
				telaEscolhaFilme.setLocationRelativeTo(null);
				telaEscolhaFilme.setVisible(true);
				
				if(telaDetalhesFilme != null) {
					telaDetalhesFilme.dispose();
				}

				dispose();
			}
		});
		btnVoltar.setBounds(10, 612, 89, 23);
		panelPrincipal.add(btnVoltar);

		JPanel panelResumo = new JPanel();
		panelResumo.setBackground(new Color(255, 255, 255));
		panelResumo.setBounds(445, 11, 329, 449);
		panelPrincipal.add(panelResumo);
		panelResumo.setLayout(null);

		Sala sala = sessao.getSala();
		int espaco = 5;
		int tamanho = 20;

		// parcial modelo da lista
		DefaultListModel<String> modeloLista = new DefaultListModel<>();

		for (int i = 0; i < sala.getLinhas(); i++) {

			for (int j = 0; j < sala.getColunas(); j++) {

				JButton botao = null;
				String poltronaSelecionada = sala.obterTipoDeEstrutura(i, j).getIdentificador();
				int deslocamentoEmX = 25;
				
				
				if(sala.obterTipoDeEstrutura(i, j).getBloqueado()) {
					botao = new JButton(iconeBloqueada);
					botao.setBounds(deslocamentoEmX + j * (tamanho + espaco), 5 + i * (tamanho + espaco), tamanho, tamanho);
					botao.setEnabled(false);
				} else {
					switch(sala.obterTipoDeEstrutura(i, j).getTipo()) {
					case Vazio:
						botao = new JButton();
						botao.setBounds(deslocamentoEmX + j * (tamanho + espaco), 5 + i * (tamanho + espaco), tamanho, tamanho);
						botao.setVisible(false);
						break;
					case Poltrona:
						if(sessao.estaReservado(i, j)) {
							botao = new JButton(iconePoltronaOcupada);
							botao.setEnabled(false);
						} else {						
							botao = new JButton(iconePoltrona);
						}

						botao.setBounds(deslocamentoEmX + j * (tamanho + espaco), 5 + i * (tamanho + espaco), tamanho, tamanho);
						break;
					case PoltronaObesos:
						if(sessao.estaReservado(i, j)) {
							botao = new JButton(iconePoltronaObesosOcupada);
							botao.setEnabled(false);
						} else {						
							botao = new JButton(iconePoltronaObesos);
						}
						botao.setBounds(deslocamentoEmX-2 + j * (tamanho + espaco), 3 + i * (tamanho + espaco), tamanho + 5, tamanho + 5);
						break;
					case LocalCadeirantes:
						if(sessao.estaReservado(i, j)) {
							botao = new JButton(iconeLocalCadeirantesOcupado);
							botao.setEnabled(false);
						} else {						
							botao = new JButton(iconeLocalCadeirantes);
						}
						botao.setBounds(deslocamentoEmX + j * (tamanho + espaco), 5 + i * (tamanho + espaco), tamanho, tamanho);
						break;
					}
				}
				botao.setBorderPainted(false);
				botao.setContentAreaFilled(false);
				botao.setFocusPainted(false);
				botao.setBackground(Color.WHITE);
				botao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

				// Ação do botão
				final int auxI = i;
				final int auxJ = j;
				final JButton auxBotao = botao;
				botao.addActionListener(e -> {
					Poltrona poltrona = (Poltrona) sala.obterTipoDeEstrutura(auxI, auxJ);

					boolean isSelecionada = auxBotao.getIcon().equals(iconePoltronaSelecionada)
							|| auxBotao.getIcon().equals(iconePoltronaObesosSelecionada)
							|| auxBotao.getIcon().equals(iconeLocalCadeirantesSelecionado);

					// Se já está selecionada, desmarcar
					if (isSelecionada) {
						if (auxBotao.getIcon().equals(iconePoltronaSelecionada)) {
							auxBotao.setIcon(iconePoltrona);
						} else if (auxBotao.getIcon().equals(iconePoltronaObesosSelecionada)) {
							auxBotao.setIcon(iconePoltronaObesos);
						} else {
							auxBotao.setIcon(iconeLocalCadeirantes);
						}
						modeloLista.removeElement(poltronaSelecionada);
						poltronas.remove(poltrona);
						poltronasRestantes++;
						totalItens--;
						lblTotalItens.setText(Integer.toString(totalItens));
					}
					// Se ainda pode selecionar, marcar
					else if (poltronasRestantes > 0) {
						if (auxBotao.getIcon().equals(iconePoltrona)) {
							auxBotao.setIcon(iconePoltronaSelecionada);
						} else if (auxBotao.getIcon().equals(iconePoltronaObesos)) {
							auxBotao.setIcon(iconePoltronaObesosSelecionada);
						} else if (auxBotao.getIcon().equals(iconeLocalCadeirantes)) {
							auxBotao.setIcon(iconeLocalCadeirantesSelecionado);
						}
						modeloLista.addElement(poltronaSelecionada);
						poltronas.add(poltrona);
						poltronasRestantes--;
						totalItens++;
						lblTotalItens.setText(Integer.toString(totalItens));
					} else {
						JOptionPane.showMessageDialog(null, "Limite de  8 poltronas atingido!", "Aviso",
								JOptionPane.WARNING_MESSAGE);
					}
				});

				panelPoltronas.add(botao);
			}
		}

		JScrollPane scrollPanePoltronas = new JScrollPane();
		scrollPanePoltronas.setBounds(0, 205, 329, 53);
		panelResumo.add(scrollPanePoltronas);
		
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
		

		
		

		

		JLabel lblLinha_1 = new JLabel("______________________________________________");
		lblLinha_1.setBounds(0, 372, 329, 14);
		panelResumo.add(lblLinha_1);
		
		JPanel panelResumoFIlme = new JPanel();
		panelResumoFIlme.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panelResumoFIlme.setBackground(new Color(255, 255, 255));
		panelResumoFIlme.setBounds(0, 32, 329, 145);
		panelResumo.add(panelResumoFIlme);
		panelResumoFIlme.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 114, 123);
		panelResumoFIlme.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Suposta Imagem");
		lblNewLabel_2.setBounds(10, 53, 80, 14);
		panel.add(lblNewLabel_2);
		
		JPanel panelNomeEClassificacao = new JPanel();
		panelNomeEClassificacao.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelNomeEClassificacao.setBackground(new Color(255, 255, 255));
		panelNomeEClassificacao.setBounds(134, 11, 185, 56);
		panelNomeEClassificacao.setBorder(new EmptyBorder(0, 0, 0, 0));
		panelResumoFIlme.add(panelNomeEClassificacao);
		
		JLabel lblNome = new JLabel(sessao.getFilme().getNome());
		lblNome.setBounds(20, 10, 490, 25);
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
		
		JLabel lblNewLabel_3 = new JLabel("Cinemax");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_3.setBounds(134, 74, 185, 24);
		panelResumoFIlme.add(lblNewLabel_3);
		
		JLabel lblVerDetalhes = geraLabelVerDetalhes(sessao);
		panelResumoFIlme.add(lblVerDetalhes);
		
		String diaFormatado = sessao.getInicio().getDayOfWeek().getDisplayName(TextStyle.SHORT, new Locale("pt", "BR")).toUpperCase();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		String horaDaSessao = sessao.getInicio().format(formatter);

		JLabel lblSalaEData = new JLabel("SALA "+sessao.getId()+" | "+diaFormatado+" "+horaDaSessao);
		lblSalaEData.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSalaEData.setBounds(134, 94, 185, 20);
		panelResumoFIlme.add(lblSalaEData);
		
		JLabel lblNewLabel_1 = new JLabel("Resumo do Pedido:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(4, 0, 233, 31);
		panelResumo.add(lblNewLabel_1);
				
				JLabel lblNewLabel_4 = new JLabel("Poltronas");
				lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 13));
				lblNewLabel_4.setBounds(4, 180, 124, 24);
				panelResumo.add(lblNewLabel_4);
				
				JLabel lblItens = new JLabel("Itens");
				lblItens.setFont(new Font("Tahoma", Font.BOLD, 13));
				lblItens.setBounds(10, 397, 83, 14);
				panelResumo.add(lblItens);
				
				JLabel lblTotal = new JLabel("Total:");
				lblTotal.setFont(new Font("Tahoma", Font.BOLD, 13));
				lblTotal.setBounds(10, 424, 83, 14);
				panelResumo.add(lblTotal);
				
				lblTotalItens = new JLabel("0");
				lblTotalItens.setFont(new Font("Tahoma", Font.BOLD, 13));
				lblTotalItens.setBounds(280, 397, 91, 14);
				panelResumo.add(lblTotalItens);
				
				JLabel lblPrecoTotal = new JLabel("R$ 0.00");
				lblPrecoTotal.setFont(new Font("Tahoma", Font.BOLD, 13));
				lblPrecoTotal.setBounds(256, 425, 91, 14);
				panelResumo.add(lblPrecoTotal);

		/*
		 * Checa se tá achando a imagem mesmo java.net.URL url =
		 * getClass().getResource("/img/poltronaPreta.png");
		 * System.out.println("URL da imagem: " + url);
		 */

	}
}
