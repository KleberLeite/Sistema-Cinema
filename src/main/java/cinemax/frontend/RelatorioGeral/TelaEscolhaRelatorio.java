package cinemax.frontend.RelatorioGeral;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import cinemax.backend.alimentos.Alimento;
import cinemax.backend.filmes.Filme;
import cinemax.backend.filmes.Sessao;
import cinemax.backend.relatorios.Ingresso;
import cinemax.backend.relatorios.Relatorio;
import cinemax.backend.relatorios.RelatorioAlimentos;
import cinemax.backend.relatorios.RelatorioFilmes;
import cinemax.backend.relatorios.TipoDeIngresso;
import cinemax.backend.salas.Sala;
import cinemax.frontend.PaginasGeranteeFuncionario.Gerente;
import cinemax.frontend.controller.ControladorDeApp;

import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.awt.event.ActionEvent;

public class TelaEscolhaRelatorio extends JFrame {

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
					TelaEscolhaRelatorio frame = new TelaEscolhaRelatorio();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	  private Map<Alimento, Integer> gerarCompraRandomAlimento(Random random, Alimento[] alimentos, int n) {
			Map<Alimento, Integer> compra = new HashMap<>();
			for (int i = 0; i < n; i++) {
				
				int index = random.nextInt(alimentos.length);
				Alimento a = alimentos[index];
				compra.put(a, compra.getOrDefault(a, 0) + 1);
			}
			return compra;
		}


	    public List<Ingresso> simularVendasFilmes() {
	        List<Ingresso> ingressos = new ArrayList<>();

	        for(Filme filme : app.getBackend().getBancoFilmes().obterTodosFilmes()) {
		        for (Sessao sessao : filme.obterTodasSessoes()) {
		            Sala sala = sessao.getSala();
		
		            // Vamos simular venda de 5 ingressos por sessão
		            for (int i = 0; i < 5; i++) {
		                int linha = i / sala.getColunas(); // Simplesmente para variar
		                int coluna = i % sala.getColunas();
		                
		                Ingresso ingresso = new Ingresso(sessao);
		                ingresso.setTipo(i % 2 == 0 ? TipoDeIngresso.Inteira : TipoDeIngresso.Meia);
		                ingresso.setRG("12345678" + i);
		
		                ingressos.add(ingresso);
		            }
		        }
	        }
	        return ingressos;
	        
	    }

	/**
	 * Create the frame.
	 */
	public TelaEscolhaRelatorio() {
		
		
		
		/*testes relatórios
		
		
		app.getBackend().tentarAbrirDia();
    	
    	Relatorio relatorio = app.getBackend().getGerenciadorRelatorios().obterRelatorioDoDia();
        RelatorioAlimentos relatorioAlimento = relatorio.getRelatorioAlimentos();
        RelatorioFilmes relatorioFilmes = relatorio.getRelatorioFilmes();
    	

         	//GErando vendas alimentos ---------------------------------------------------------------------
    		Alimento[] alimentos = app.getBackend().getBancoAlimentos().obterTodosAlimentos();
    		Random random = new Random(42);

    		for (int i = 1; i < 10; i++) {
    			int n = random.nextInt(10)+1;
    			relatorioAlimento.adicionarVendas(gerarCompraRandomAlimento(random, alimentos, n));
    		}
    		//GErando vendas alimentos ---------------------------------------------------------------------
    		
    		relatorioFilmes.adicionarVendas(simularVendasFilmes());
    	
    		app.getBackend().tentarFecharDia();
    		
    		
    	
		*/
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 64, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setBounds(10, 11, 564, 508);
		contentPane.add(panelPrincipal);
		panelPrincipal.setLayout(null);
		
		JLabel lblEscolhaORelatorio = new JLabel("Escolha o Relatório:");
		lblEscolhaORelatorio.setVerticalAlignment(SwingConstants.CENTER);
		lblEscolhaORelatorio.setHorizontalAlignment(SwingConstants.CENTER);
		lblEscolhaORelatorio.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblEscolhaORelatorio.setBounds(102, 91, 356, 52);
		panelPrincipal.add(lblEscolhaORelatorio);
		
		
		
		JPanel panelMostrarRelatorios = new JPanel();
		panelMostrarRelatorios.setLayout(new BoxLayout(panelMostrarRelatorios, BoxLayout.Y_AXIS));
		
		JScrollPane scrollPaneRelatorios = new JScrollPane(panelMostrarRelatorios);
		scrollPaneRelatorios.setBounds(149, 154, 272, 317);
		scrollPaneRelatorios.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneRelatorios.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		contentPane.add(scrollPaneRelatorios);
		
		for (int i = 0; i < app.getBackend().getGerenciadorRelatorios().obterTodos().size(); i++) {
		    Relatorio relatoriaAtual = app.getBackend().getGerenciadorRelatorios().obterTodos().get(i);

		    DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm");
		    DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/MM");

		    String diaFormatadoInicio = relatoriaAtual.getInicio().format(formatterData);
		    String horaFormatadoInicio = relatoriaAtual.getInicio().format(formatterHora);
		    String diaFormatadoFim = relatoriaAtual.getFim().format(formatterData);
		    String horaFormatadoFim = relatoriaAtual.getFim().format(formatterHora);

		    String textoBotao = "<html><div style='text-align: center;'>"
		        + diaFormatadoInicio + " - " + diaFormatadoFim + "<br>"
		        + horaFormatadoInicio + " - " + horaFormatadoFim
		        + "</div></html>";

		    JButton botaoRelatorio = new JButton(textoBotao);
		    botaoRelatorio.setAlignmentX(Component.CENTER_ALIGNMENT);
		    botaoRelatorio.setFont(new Font("Tahoma", Font.PLAIN, 13));
		    botaoRelatorio.setMaximumSize(new java.awt.Dimension(150, 60));

		    final int indexRelatorio = i;
		    botaoRelatorio.addActionListener(e -> {
		        Relatorio relatorioDoDia = app.getBackend().getGerenciadorRelatorios().obterTodos().get(indexRelatorio);
		        TelaRelatorioFinal telaRelatorioFinal = new TelaRelatorioFinal(relatorioDoDia);
		        telaRelatorioFinal.setVisible(true);
		        telaRelatorioFinal.setLocationRelativeTo(null);
		        dispose();
		    });

		    panelMostrarRelatorios.add(botaoRelatorio);
		    panelMostrarRelatorios.add(javax.swing.Box.createVerticalStrut(10)); // espaço entre botões
		}

		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Gerente telaGerente = new Gerente();
                telaGerente.setVisible(true);
                telaGerente.setLocationRelativeTo(null);
                dispose();
			}
		});
		btnVoltar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnVoltar.setBounds(10, 527, 89, 23);
		contentPane.add(btnVoltar);
	}
}
