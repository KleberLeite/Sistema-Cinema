package cinemax.frontend.vendadeingressos;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cinemax.backend.relatorios.Ingresso;
import cinemax.frontend.controller.ControladorDeApp;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.Font;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaConclusaoDeCompra extends JFrame {

	private static final long serialVersionUID = 1L;
	private ControladorDeApp app = ControladorDeApp.getInstancia();
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaConclusaoDeCompra frame = new TelaConclusaoDeCompra(null);
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
	public TelaConclusaoDeCompra(CarrinhoIngressos carrinho) {		
		
		app.getBackend().tentarAbrirDia();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 64, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPanePrincipal = new JScrollPane();
		scrollPanePrincipal.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPanePrincipal.setBounds(10, 11, 764, 388);
		contentPane.add(scrollPanePrincipal);
		
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
		scrollPanePrincipal.setViewportView(panelPrincipal);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaFinalizarCompra telaFinalizarCompra = new TelaFinalizarCompra(carrinho.getIngressos().get(0).getSessao(),carrinho);
				telaFinalizarCompra.setVisible(true);
				telaFinalizarCompra.setLocationRelativeTo(null);
				
				dispose();
			}
		});
		btnVoltar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnVoltar.setBounds(10, 416, 89, 23);
		contentPane.add(btnVoltar);
		
		JButton btnImprimir = new JButton("Imprimir");
		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				app.getBackend().getGerenciadorRelatorios().obterRelatorioDoDia().getRelatorioFilmes().adicionarVendas(carrinho.getIngressos());
				
				TelaEscolhaFilme telaEscolhaFilme = new TelaEscolhaFilme();
				telaEscolhaFilme.setLocationRelativeTo(null);
				telaEscolhaFilme.setVisible(true);

			    dispose();
			}
		});
		btnImprimir.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnImprimir.setBounds(685, 416, 89, 23);
		contentPane.add(btnImprimir);
		
		for (Ingresso ingresso : carrinho.getIngressos()) {
			JPanel card = new JPanel();
			card.setLayout(null); 
			card.setPreferredSize(new Dimension(1355, 140));
			card.setMaximumSize(new Dimension(1355, 140));
			card.setBackground(new Color(230, 230, 250));
			
			if(ingresso.getRG()!=null) {
				card.setPreferredSize(new Dimension(1355, 190));
				card.setMaximumSize(new Dimension(1355, 190));
				
				JLabel lblRG = new JLabel("RG: "+ingresso.getRG());
				lblRG.setBounds(20, 135, 100, 20); // mais largura pro nome
				card.add(lblRG);
				JLabel lblAviso = new JLabel("AVISO: Entrada permitida apenas com o porte do RG");
				lblAviso.setForeground(Color.RED);
				lblAviso.setBounds(20, 160, 300, 20); // mais largura pro nome
				card.add(lblAviso);
			}

			// ADICIONA BORDA PRA DESTACAR CADA RETÂNGULO
			card.setBorder(new EmptyBorder(10, 10, 10, 10)); // espaçamento interno

			JLabel lblIdSala = new JLabel("Sala: "+Integer.toString(ingresso.getSessao().getSala().getIdSala()));
			lblIdSala.setBounds(20, 10, 100, 20); // mais largura pro nome
			card.add(lblIdSala);
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM//yyyy HH:mm");

			JLabel lblIdSessao = new JLabel("Sessão: "+ ingresso.getSessao().getInicio().format(formatter));
			lblIdSessao.setBounds(20, 35, 200, 20);
			card.add(lblIdSessao);
			
			JLabel lblFilme = new JLabel("Filme: "+ingresso.getSessao().getFilme().getNome());
			lblFilme.setBounds(20, 60, 400, 20); // mais largura pro nome
			card.add(lblFilme);
			
			JLabel lblTipo = new JLabel("Tipo: "+ingresso.getTipo().name());
			lblTipo.setBounds(20, 85, 100, 20); // mais largura pro nome
			card.add(lblTipo);
			
			JLabel lblPoltrona = new JLabel("Poltrona: " + ingresso.getPoltrona());
			lblPoltrona.setBounds(20, 110, 100, 20);
			card.add(lblPoltrona);
			
			if(ingresso.getRG()!=null) {
				JLabel lblRG = new JLabel("RG: "+ingresso.getRG());
				lblRG.setBounds(20, 135, 100, 20); // mais largura pro nome
				card.add(lblRG);
				JLabel lblAviso = new JLabel("AVISO: Entrada permitida apenas com o porte do RG");
				lblAviso.setForeground(Color.RED);
				lblAviso.setBounds(20, 160, 300, 20); // mais largura pro nome
				card.add(lblAviso);
			}
		    
		    panelPrincipal.add(Box.createRigidArea(new Dimension(0, 10))); // espaço entre os cards
		    panelPrincipal.add(card);
		}
	}
}
