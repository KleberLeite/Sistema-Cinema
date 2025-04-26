package cinemax.frontend.vendadeingressos;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;

import javax.print.DocFlavor.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cinemax.backend.core.Backend;
import cinemax.backend.filmes.Sessao;
import cinemax.backend.salas.Sala;
import cinemax.backend.salas.TipoDeEstrutura;
import cinemax.frontend.controller.ControladorDeApp;

import javax.swing.Icon;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaEscolhaPoltrona extends JFrame{

	private ControladorDeApp app = ControladorDeApp.getInstancia();
	private JPanel contentPane;
	private double valorDoBilhete=20;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaEscolhaPoltrona frame = new TelaEscolhaPoltrona(null);
					frame.setVisible(true);
					frame.setSize(800, 700);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaEscolhaPoltrona(Sessao sessao) {
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(-14, -42, 800, 700);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 64, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelPoltronas = new JPanel();
		panelPoltronas.setBackground(new Color(255, 255, 255));
		panelPoltronas.setBounds(10, 11, 425, 504);
		contentPane.add(panelPoltronas);
		

		//--------------------------------------------------------------------------------------------------------------------------------------------------------------
		//Criando e preparando icone já redimensionado------------------------------------------------------------------------------------------------------------------
		
		ImageIcon iconePoltronaLivreParcial = new ImageIcon(getClass().getResource("/img/Poltrona.png"));
		Image  imgIconePoltronaLivreParcial = iconePoltronaLivreParcial.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
		ImageIcon iconePoltrona = new ImageIcon(imgIconePoltronaLivreParcial);
		
		ImageIcon iconePoltronaSelecionadaParcial = new ImageIcon(getClass().getResource("/img/PoltronaSelecionada.png"));
		Image  imgIconePoltronaSelecionadaParcial = iconePoltronaSelecionadaParcial.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
		ImageIcon iconePoltronaSelecionada = new ImageIcon(imgIconePoltronaSelecionadaParcial);
		
		ImageIcon iconePoltronaOcupadaParcial = new ImageIcon(getClass().getResource("/img/PoltronaOcupada.png"));
		Image  imgIconePoltronaOcupadaParcial = iconePoltronaOcupadaParcial.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
		ImageIcon iconePoltronaOcupada = new ImageIcon(imgIconePoltronaOcupadaParcial);
		
		
		ImageIcon iconePoltronaObesosLivreParcial = new ImageIcon(getClass().getResource("/img/PoltronaObesos.png"));
		Image  imgIconePoltronaObesosLivreParcial = iconePoltronaObesosLivreParcial.getImage().getScaledInstance(18, 15, Image.SCALE_SMOOTH);
		ImageIcon iconePoltronaObesos = new ImageIcon(imgIconePoltronaObesosLivreParcial);
		
		ImageIcon iconePoltronaObesosSelecionadaParcial = new ImageIcon(getClass().getResource("/img/PoltronaObesosSelecionada.png"));
		Image  imgIconePoltronaObesosSelecionadaParcial = iconePoltronaObesosSelecionadaParcial.getImage().getScaledInstance(18, 15, Image.SCALE_SMOOTH);
		ImageIcon iconePoltronaObesosSelecionada = new ImageIcon(imgIconePoltronaObesosSelecionadaParcial);
		
		ImageIcon iconePoltronaObesosOcupadaParcial = new ImageIcon(getClass().getResource("/img/PoltronaObesosOcupada.png"));
		Image  imgIconePoltronaObesosOcupadaParcial = iconePoltronaObesosOcupadaParcial.getImage().getScaledInstance(18, 15, Image.SCALE_SMOOTH);
		ImageIcon iconePoltronaObesosOcupada = new ImageIcon(imgIconePoltronaObesosOcupadaParcial);
		
		
		ImageIcon iconeLocalCadeirantesLivreParcial = new ImageIcon(getClass().getResource("/img/LocalCadeirante.png"));
		Image  imgIconeLocalCadeirantesLivreParcial = iconeLocalCadeirantesLivreParcial.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
		ImageIcon iconeLocalCadeirantes = new ImageIcon(imgIconeLocalCadeirantesLivreParcial);
		
		ImageIcon iconeLocalCadeirantesParcial = new ImageIcon(getClass().getResource("/img/LocalCadeiranteSelecionado.png"));
		Image  imgIconeLocalCadeirantesParcial = iconeLocalCadeirantesParcial.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
		ImageIcon iconeLocalCadeirantesSelecionado = new ImageIcon(imgIconeLocalCadeirantesParcial);
		
		ImageIcon iconeLocalCadeirantesOcupadoParcial = new ImageIcon(getClass().getResource("/img/LocalCadeiranteOcupado.png"));
		Image  imgIconeLocalCadeirantesOcupadoParcial = iconeLocalCadeirantesOcupadoParcial.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
		ImageIcon iconeLocalCadeirantesOcupado = new ImageIcon(imgIconeLocalCadeirantesOcupadoParcial);
		
		
		ImageIcon iconeEspacoVazioParcial = new ImageIcon(getClass().getResource("/img/EspacoVazio.png"));
		Image  imgIconeEspacoVazioParcial = iconeEspacoVazioParcial.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
		ImageIcon iconeEspacoVazio = new ImageIcon(imgIconeEspacoVazioParcial);
		
		ImageIcon iconeBloqueadoParcial = new ImageIcon(getClass().getResource("/img/PoltronaBloqueada.png"));
		Image  imgIconeBloqueadoParcial = iconeBloqueadoParcial.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
		ImageIcon iconeBloqueada = new ImageIcon(imgIconeBloqueadoParcial);
		
		ImageIcon iconeTomDeOcupadoParcial = new ImageIcon(getClass().getResource("/img/TomOcupado.png"));
		Image  imgIconeTomDeOcupadoParcial = iconeTomDeOcupadoParcial.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
		ImageIcon IconeTomDeOcupado = new ImageIcon(imgIconeTomDeOcupadoParcial);
		
		ImageIcon iconeTomDeSelecionadoParcial = new ImageIcon(getClass().getResource("/img/TomSelecionado.png"));
		Image  imgIconeTomDeSelecionadoParcial = iconeTomDeSelecionadoParcial.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
		ImageIcon IconeTomDeSelecionado = new ImageIcon(imgIconeTomDeSelecionadoParcial);
		
		ImageIcon[] iconesLivres = {iconePoltrona, iconePoltronaObesos, iconeLocalCadeirantes};
		ImageIcon[] iconesOcupados = {iconePoltronaOcupada, iconePoltronaObesosOcupada, iconeLocalCadeirantesOcupado};
		ImageIcon[] iconesSelecionados = {iconePoltronaSelecionada, iconePoltronaObesosSelecionada, iconeLocalCadeirantesSelecionado};
		ImageIcon[] iconesUtilitarios = {iconeEspacoVazio, iconeBloqueada};
		
		//--------------------------------------------------------------------------------------------------------------------------------------------------------------
		//--------------------------------------------------------------------------------------------------------------------------------------------------------------
		
		panelPoltronas.setLayout(null);
		
		JLabel imgPoltrona = new JLabel(iconePoltrona);
		imgPoltrona.setBounds(43, 413, 19, 19);
		panelPoltronas.add(imgPoltrona);
		
		JLabel imgTomSelecionado = new JLabel(IconeTomDeSelecionado);
		imgTomSelecionado.setBounds(43, 443, 19, 19);
		panelPoltronas.add(imgTomSelecionado);
		
		JLabel imgPoltronaObesos = new JLabel(iconePoltronaObesos);
		imgPoltronaObesos.setBounds(203, 413, 19, 19);
		panelPoltronas.add(imgPoltronaObesos);
		
		JLabel imgLocalCadeirantes = new JLabel(iconeLocalCadeirantes);
		imgLocalCadeirantes.setBounds(203, 443, 19, 19);
		panelPoltronas.add(imgLocalCadeirantes);
		
		
		JLabel imgTomOcupado = new JLabel(IconeTomDeOcupado);
		imgTomOcupado.setBounds(43, 473, 19, 19);
		panelPoltronas.add(imgTomOcupado);
		
		JLabel imgBloqueado = new JLabel(iconeBloqueada);
		imgBloqueado.setBounds(203, 474, 19, 19);
		panelPoltronas.add(imgBloqueado);
		
		JLabel lblLinha = new JLabel("__________________________________________________________");
		lblLinha.setBounds(2, 397, 411, 14);
		panelPoltronas.add(lblLinha);
		
		JLabel lblNewLabel = new JLabel("Disponível");
		lblNewLabel.setBounds(72, 413, 84, 14);
		panelPoltronas.add(lblNewLabel);
		
		JLabel lblPoltronaSelecionada = new JLabel("Tom de Selecionado");
		lblPoltronaSelecionada.setBounds(72, 443, 126, 14);
		panelPoltronas.add(lblPoltronaSelecionada);
		
		JList list = new JList();
		list.setBounds(76, 461, 1, 1);
		panelPoltronas.add(list);
		
		JLabel lblPoltronaPObesos = new JLabel("Poltrona p/ Obesos");
		lblPoltronaPObesos.setBounds(232, 413, 121, 14);
		panelPoltronas.add(lblPoltronaPObesos);
		
		JLabel lblLocalPCadeirantes = new JLabel("Local p/ Cadeirantes");
		lblLocalPCadeirantes.setBounds(232, 443, 131, 14);
		panelPoltronas.add(lblLocalPCadeirantes);
		
		JLabel lblTomOcupado = new JLabel("Tom de Ocupado");
		lblTomOcupado.setBounds(72, 479, 105, 14);
		panelPoltronas.add(lblTomOcupado);
		
		JLabel lblNewLabel_2 = new JLabel("Bloqueado");
		lblNewLabel_2.setBounds(232, 479, 61, 14);
		panelPoltronas.add(lblNewLabel_2);
		
		
		
		JPanel panel = new JPanel();
		panel.setBounds(445, 11, 329, 449);
		contentPane.add(panel);
		
		JButton btnAvançar = new JButton("Avançar");
		btnAvançar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TelaEscolhaFilme telaEscolhaFilme = new TelaEscolhaFilme();
				telaEscolhaFilme.setLocationRelativeTo(null); // centraliza a tela
				telaEscolhaFilme.setVisible(true);

			    // Fecha a tela atual (opcional)
			    dispose();
				
			}
		});
		btnAvançar.setBounds(674, 612, 89, 23);
		contentPane.add(btnAvançar);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TelaEscolhaFilme telaEscolhaTipoBilhete = new TelaEscolhaFilme();
				telaEscolhaTipoBilhete.setLocationRelativeTo(null); // centraliza a tela
				telaEscolhaTipoBilhete.setVisible(true);


			    // Fecha a tela atual (opcional)
			    dispose();
				
			}
		});
		btnVoltar.setBounds(10, 612, 89, 23);
		contentPane.add(btnVoltar);
		
		
		Sala sala = sessao.getSala();
		int linhas = sala.getLinhas();
		int colunas = sala.getLinhas();
		int espaco = 5;
		int tamanho = 20;
		
		double preçoTotal;
		int qtdeDeCaideiras = 0;

		for (int i = 0; i < linhas; i++) {
	        for (int j = 0; j < colunas; j++) {
	            
	        	JButton botao;
	            // Definir o ícone diretamente dentro do laço
	            final ImageIcon iconeQueAparecera;  // Usar uma variável local dentro do loop para garantir que será final
	            if (TipoDeEstrutura.Vazio == sala.obterTipoDeEstrutura(i, j)) {
		            botao = new JButton(iconeEspacoVazio);
		            botao.setBounds(5+j * (tamanho + espaco), 5+i * (tamanho + espaco), tamanho, tamanho);
		            botao.setEnabled(false);
	            } else if (TipoDeEstrutura.Poltrona == sala.obterTipoDeEstrutura(i, j)) {
		            botao = new JButton(iconePoltrona);
		            botao.setBounds(5+j * (tamanho + espaco), 5+i * (tamanho + espaco), tamanho, tamanho);
	            } else if (TipoDeEstrutura.PoltronaObesos == sala.obterTipoDeEstrutura(i, j)) {
		            botao = new JButton(iconePoltronaObesos);
		            botao.setBounds(3+j * (tamanho + espaco), 3+i * (tamanho + espaco), tamanho+5, tamanho+5);
	            } else {
		            botao = new JButton(iconeLocalCadeirantes);
		            botao.setBounds(5+j * (tamanho + espaco), 5+i * (tamanho + espaco), tamanho, tamanho);
	            }

	            botao.setBackground(Color.WHITE);
	            botao.setBorderPainted(false);

	            // Ação do botão
	            botao.addActionListener(e -> {
	            	
	                if (botao.getIcon().equals(iconePoltrona) || botao.getIcon().equals(iconePoltronaSelecionada)) {
	                	if (botao.getIcon().equals(iconePoltrona)) {
	                		botao.setIcon(iconePoltronaSelecionada);
		                    
		                } else {
		                    botao.setIcon(iconePoltrona);
		                }
	                }else if(botao.getIcon().equals(iconePoltronaObesos) || botao.getIcon().equals(iconePoltronaObesosSelecionada)) {
	                	if (botao.getIcon().equals(iconePoltronaObesos)) {
	                		botao.setIcon(iconePoltronaObesosSelecionada);
		                    
		                } else {
		                    botao.setIcon(iconePoltronaObesos);
		                }
	                }else {
	                	if (botao.getIcon().equals(iconeLocalCadeirantes)) {
	                		botao.setIcon(iconeLocalCadeirantesSelecionado);
		                    
		                } else {
		                    botao.setIcon(iconeLocalCadeirantes);
		                }
	                }
	            });

	            panelPoltronas.add(botao);
	        }
	    }
		
		/*Checa se tá achando a imagem mesmo
		java.net.URL url = getClass().getResource("/img/poltronaPreta.png");
		System.out.println("URL da imagem: " + url);*/



	}
}
