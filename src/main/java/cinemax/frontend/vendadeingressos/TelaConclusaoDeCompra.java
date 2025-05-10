package cinemax.frontend.vendadeingressos;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cinemax.backend.filmes.Sessao;
import cinemax.backend.relatorios.filmes.Ingresso;
import cinemax.backend.relatorios.filmes.TipoDeIngresso;
import cinemax.frontend.controller.ControladorDeApp;
import cinemax.frontend.estilizacao.Estilizador;
import cinemax.frontend.estilizacao.EstiloBotao;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.Font;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
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
		throw new UnsupportedOperationException("Não pode iniciar por aqui!");
	}
	

	/**
	 * Create the frame.
	 */
	public TelaConclusaoDeCompra(Sessao sessao, CarrinhoIngressos carrinho) {		
						
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Cinemax");
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(2, 18, 27));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPanePrincipal = new JScrollPane();
		scrollPanePrincipal = Estilizador.estilizandoScrollBarVertEHori(scrollPanePrincipal);
		scrollPanePrincipal.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPanePrincipal.setBounds(87, 11, 602, 388);
		contentPane.add(scrollPanePrincipal);
		
		JPanel panelPrincipal = new JPanel();
		scrollPanePrincipal.setViewportView(panelPrincipal);
		panelPrincipal.setBackground(new Color(255, 255, 255));
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
		
		JButton btnVoltar = new JButton("Voltar");
		Estilizador.aplicarEstiloBotao(btnVoltar, EstiloBotao.CLARO_UNIFICADO);
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaEscolhaMeiaOuInteira telaFinalizarCompra = new TelaEscolhaMeiaOuInteira(sessao, carrinho);
				telaFinalizarCompra.setVisible(true);
				telaFinalizarCompra.setLocationRelativeTo(null);
				
				dispose();
			}
		});
		btnVoltar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnVoltar.setBounds(10, 416, 89, 23);
		contentPane.add(btnVoltar);
		
		JButton btnImprimir = new JButton("Imprimir");
		Estilizador.aplicarEstiloBotao(btnImprimir, EstiloBotao.CLARO_UNIFICADO);
		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				boolean sucessoCompras= true;
				List<Ingresso> ingressosFinalizados = new ArrayList<>();
				
				for(Ingresso ingresso: carrinho.getIngressos()) {
					
					boolean sucesso = app.getBackend().getBancoFilmes().tentarReservar(
							ingresso.getSessao().getFilme().getId(),
							ingresso.getSessao().getId(), 
							ingresso.getPoltrona().getLinha(), 
							ingresso.getPoltrona().getColuna());
					System.out.println(sucesso);
					System.out.println(Integer.toString(ingresso.getSessao().getFilme().getId()));
					System.out.println(Integer.toString(ingresso.getSessao().getId()));
					System.out.println(Integer.toString(ingresso.getPoltrona().getLinha()));
					System.out.println(Integer.toString(ingresso.getPoltrona().getColuna()));
					sucessoCompras &= sucesso;
					if(sucesso) {
						ingressosFinalizados.add(ingresso);
					}else {
						JOptionPane.showMessageDialog(null, "Erro ao reservar poltronas! Por favor, refaça", "Erro",
								JOptionPane.ERROR_MESSAGE);
						TelaEscolhaMeiaOuInteira telaFinalizarCompra = new TelaEscolhaMeiaOuInteira(sessao, carrinho);
						telaFinalizarCompra.setVisible(true);
						telaFinalizarCompra.setLocationRelativeTo(null);
						
						dispose();
						break;
						
					}
				}
				
				if(sucessoCompras){
					app.getBackend().getGerenciadorRelatorios().obterRelatorioDoDia().getRelatorioFilmes().adicionarVendas(carrinho.getIngressos());
					
					TelaVendaDeIngresso telaEscolhaFilme = new TelaVendaDeIngresso();
					telaEscolhaFilme.setLocationRelativeTo(null);
					telaEscolhaFilme.setVisible(true);

				    dispose();
				}
			}
		});
		btnImprimir.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnImprimir.setBounds(685, 416, 89, 23);
		contentPane.add(btnImprimir);
		
		for (Ingresso ingresso : carrinho.getIngressos()) {
			JPanel card = Estilizador.criarPainelArredondado(new Color(192, 192, 192), 10);
			card.setLayout(null); 
			card.setPreferredSize(new Dimension(1150, 165));
			card.setMaximumSize(new Dimension(1150, 165));
			// ADICIONA BORDA PRA DESTACAR CADA RETÂNGULO
			card.setBorder(new EmptyBorder(10, 10, 10, 10)); // espaçamento interno
			
			if(ingresso.getTipo() == TipoDeIngresso.Meia) {
				card.setPreferredSize(new Dimension(1150, 215));
				card.setMaximumSize(new Dimension(1150, 215));
				JLabel lblRG = new JLabel("RG: "+ingresso.getRG());
				lblRG.setBounds(20, 160, 100, 20); // mais largura pro nome
				card.add(lblRG);
				JLabel lblAviso = new JLabel("AVISO: Entrada permitida apenas com o porte do RG");
				lblAviso.setForeground(Color.RED);
				lblAviso.setBounds(20, 185, 300, 20); // mais largura pro nome
				card.add(lblAviso);
			}

			JLabel lblCodigoIngresso = new JLabel("Código do Ingresso: "+ ingresso.getCodigoIngresso());
			lblCodigoIngresso.setBounds(20, 10, 200, 20);
			card.add(lblCodigoIngresso);
			

			JLabel lblIdSala = new JLabel("Sala: "+Integer.toString(ingresso.getSessao().getSala().getIdSala()));
			lblIdSala.setBounds(20, 35, 100, 20); // mais largura pro nome
			card.add(lblIdSala);
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM//yyyy HH:mm");

			JLabel lblIdSessao = new JLabel("Sessão: "+ ingresso.getSessao().getInicio().format(formatter));
			lblIdSessao.setBounds(20, 60, 200, 20);
			card.add(lblIdSessao);
			
			JLabel lblFilme = new JLabel("Filme: "+ingresso.getSessao().getFilme().getNome());
			lblFilme.setBounds(20, 85, 400, 20); // mais largura pro nome
			card.add(lblFilme);
			
			JLabel lblTipo = new JLabel("Tipo: "+ingresso.getTipo().name());
			lblTipo.setBounds(20, 110, 100, 20); // mais largura pro nome
			card.add(lblTipo);
			
			JLabel lblPoltrona = new JLabel("Poltrona: " + ingresso.getPoltrona().getIdentificador());
			lblPoltrona.setBounds(20, 135, 100, 20);
			card.add(lblPoltrona);
			
		    panelPrincipal.add(Box.createRigidArea(new Dimension(0, 10))); // espaço entre os cards
		    panelPrincipal.add(card);
		}
	}
}
