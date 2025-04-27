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
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import cinemax.backend.core.Backend;
import cinemax.backend.filmes.Sessao;
import cinemax.backend.salas.Sala;
import cinemax.backend.salas.TipoDeEstrutura;
import cinemax.frontend.controller.ControladorDeApp;

import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class TelaEscolhaPoltrona extends JFrame{

	private ControladorDeApp app = ControladorDeApp.getInstancia();
	private List<String> PoltronasSelecionadas = new ArrayList<>();
	private JPanel panelPrincipal;
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
		panelPrincipal = new JPanel();
		panelPrincipal.setBackground(new Color(0, 64, 128));
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);
		
		JPanel panelPoltronas = new JPanel();
		panelPoltronas.setBackground(new Color(255, 255, 255));
		panelPoltronas.setBounds(10, 11, 425, 590);
		panelPrincipal.add(panelPoltronas);
		

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
		panelPrincipal.add(btnAvançar);
		
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
		panelPrincipal.add(btnVoltar);
		
		JPanel panelResumo = new JPanel();
		panelResumo.setBounds(445, 11, 329, 449);
		panelPrincipal.add(panelResumo);
		panelResumo.setLayout(null);
		
		
		Sala sala = sessao.getSala();
		int espaco = 5;
		int tamanho = 20;
		
		//parcial modelo da lista
		DefaultListModel<String> modeloLista = new DefaultListModel<>();
		
		char letra = (char)((int)'A' + sala.getLinhas() - 2);
		int numero = (sala.getColunas() - 2);
		
		for (int i = 0; i < sala.getLinhas(); i++) {
				if(i!=2 && i!=14) letra--;

	        for (int j = 0; j < sala.getColunas(); j++) {
	            
	        	JButton botao;
	        	final int coluna = j;
	        	String poltronaSelecionada = letra + String.valueOf(coluna + 1);
	        	
	            if (TipoDeEstrutura.Vazio == sala.obterTipoDeEstrutura(i, j).getTipo()) {
		            botao = new JButton(iconeEspacoVazio);
		            botao.setBounds(5+j * (tamanho + espaco), 5+i * (tamanho + espaco), tamanho, tamanho);
		            botao.setEnabled(false);
	            } else if (TipoDeEstrutura.Poltrona == sala.obterTipoDeEstrutura(i, j).getTipo()) {
		            botao = new JButton(iconePoltrona);
		            botao.setBounds(5+j * (tamanho + espaco), 5+i * (tamanho + espaco), tamanho, tamanho);
	            } else if (TipoDeEstrutura.PoltronaObesos == sala.obterTipoDeEstrutura(i, j).getTipo()) {
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
	                		PoltronasSelecionadas.add(poltronaSelecionada);
	                		modeloLista.addElement(poltronaSelecionada);
		                    
		                } else {
		                    botao.setIcon(iconePoltrona);
		                    PoltronasSelecionadas.remove(poltronaSelecionada);
	                		modeloLista.removeElement(poltronaSelecionada);
		                }
	                }else if(botao.getIcon().equals(iconePoltronaObesos) || botao.getIcon().equals(iconePoltronaObesosSelecionada)) {
	                	if (botao.getIcon().equals(iconePoltronaObesos)) {
	                		botao.setIcon(iconePoltronaObesosSelecionada);
	                		PoltronasSelecionadas.add(poltronaSelecionada);
	                		modeloLista.addElement(poltronaSelecionada);
		                    
		                } else {
		                    botao.setIcon(iconePoltronaObesos);
		                    PoltronasSelecionadas.remove(poltronaSelecionada);
	                		modeloLista.removeElement(poltronaSelecionada);
		                }
	                }else {
	                	if (botao.getIcon().equals(iconeLocalCadeirantes)) {
	                		botao.setIcon(iconeLocalCadeirantesSelecionado);
	                		PoltronasSelecionadas.add(poltronaSelecionada);
	                		modeloLista.addElement(poltronaSelecionada);
		                } else {
		                    botao.setIcon(iconeLocalCadeirantes);
		                    PoltronasSelecionadas.remove(poltronaSelecionada);
	                		modeloLista.removeElement(poltronaSelecionada);
		                }
	                }
	                
	                
	            });

	            panelPoltronas.add(botao);
	        }
	    }
		
		JScrollPane scrollPanePoltronas = new JScrollPane();
		scrollPanePoltronas.setBounds(37, 267, 250, 100);
		panelResumo.add(scrollPanePoltronas);
		
		
		JList<String> listPoltronasSelecionadas = new JList(modeloLista);
		scrollPanePoltronas.setViewportView(listPoltronasSelecionadas);
		listPoltronasSelecionadas.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		listPoltronasSelecionadas.setVisibleRowCount(-1); // -1 significa que ele vai quebrar sozinho
		listPoltronasSelecionadas.setFixedCellWidth(50);  // Largura de cada "item" (ajuste como quiser)
		
		
		
		/*Checa se tá achando a imagem mesmo
		java.net.URL url = getClass().getResource("/img/poltronaPreta.png");
		System.out.println("URL da imagem: " + url);*/

	}
}
