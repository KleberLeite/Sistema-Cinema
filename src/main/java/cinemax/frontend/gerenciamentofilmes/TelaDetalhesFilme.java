package cinemax.frontend.gerenciamentofilmes;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;

import cinemax.backend.filmes.Filme;
import cinemax.backend.filmes.GeneroFilme;
import cinemax.frontend.controller.ControladorDeApp;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class TelaDetalhesFilme extends JFrame {

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
					TelaDetalhesFilme frame = new TelaDetalhesFilme(null);
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					frame.setTitle("Cinemax");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaDetalhesFilme(Filme filme) {
		if(filme==null) filme = app.getBackend().getBancoFilmes().obterFilmePorId(0);
		
		
		
		
		
		
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(2, 18, 27));
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPaneSinopse = new JScrollPane();
		scrollPaneSinopse.setBounds(10, 121, 414, 129);
		contentPane.add(scrollPaneSinopse);
		
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/*
		// Acesse as barras do JScrollPane
		JScrollBar verticalBar = scrollPaneSinopse.getVerticalScrollBar();
		

		// Cor do fundo da barra (trilha)
		verticalBar.setBackground(new Color(0, 0, 0)); // substitua com sua cor

		// Cor do "puxador" (thumb)
		verticalBar.setUI(new BasicScrollBarUI() {
			
			//-----------------necessario para tirar os botões das bordas--------------------------------
			@Override
		    protected JButton createDecreaseButton(int orientation) {
		    	JButton button = new JButton("▲"); // seta para cima
		        button.setForeground(Color.WHITE); // cor do texto
		        button.setBackground(new Color(30, 30, 30)); // fundo
		        button.setBorder(BorderFactory.createEmptyBorder());
		        return button;
		    }

		    @Override
		    protected JButton createIncreaseButton(int orientation) {
		        JButton button = new JButton("▼"); // seta para baixo
		        button.setForeground(Color.WHITE);
		        button.setBackground(new Color(30, 30, 30));
		        button.setBorder(BorderFactory.createEmptyBorder());
		        return button;
		    }
		    
			//-------------------------------------------------------------------------------------------
		    @Override
		    protected void configureScrollBarColors() {
		        this.thumbColor = new Color(192, 192, 192); // puxador
		        this.trackColor = new Color(0, 0, 0);   // fundo
		    }
		   
		    
		    
		});*/
/*JScrollBar horizontalBar = scrollPaneSinopse.getHorizontalScrollBar();
 * 
 * //horizontalBar.setBackground(new Color(30, 30, 30));
 * 
		horizontalBar.setUI(new BasicScrollBarUI() {
		    @Override
		    protected void configureScrollBarColors() {
		        this.thumbColor = new Color(70, 130, 180);
		        this.trackColor = new Color(30, 30, 30);
		    }
		});*/
		scrollPaneSinopse.setOpaque(false);
		scrollPaneSinopse.getViewport().setOpaque(false);
		scrollPaneSinopse.setBorder(null); // se quiser um visual "limpo"
		
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		JTextArea textAreaSinopse = new JTextArea();
		scrollPaneSinopse.setViewportView(textAreaSinopse);
		textAreaSinopse.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textAreaSinopse.setForeground(Color.WHITE);
		
				textAreaSinopse.setBackground(null); // ou remova essa linha
				
				textAreaSinopse.setLineWrap(true);
				textAreaSinopse.setWrapStyleWord(true);
				textAreaSinopse.setEditable(false);
				textAreaSinopse.setFocusable(false); // evita que ele receba foco
				textAreaSinopse.setOpaque(false);    // deixa o fundo transparente (opcional)
				textAreaSinopse.setBorder(null);     // remove a borda (opcional)
				textAreaSinopse.setBorder(new EmptyBorder(5, 5, 5, 5));
				textAreaSinopse.setText(filme.getSinopse());
				
				JPanel panelNomeEClassificacao = new JPanel();
				panelNomeEClassificacao.setLayout(new FlowLayout(FlowLayout.LEFT));
				panelNomeEClassificacao.setBackground(new Color(2, 17, 28));
				panelNomeEClassificacao.setBounds(10, 11, 414, 32);
				panelNomeEClassificacao.setBorder(new EmptyBorder(0, 0, 0, 0));
				contentPane.add(panelNomeEClassificacao);
				
				JLabel lblNome = new JLabel(filme.getNome());
				lblNome.setBounds(20, 10, 490, 25);
				lblNome.setFont(new Font("Tahoma", Font.BOLD, 13));
				lblNome.setBackground(new Color(255,255,255));
				panelNomeEClassificacao.add(lblNome);
				
				JLabel lblClassificacao = new JLabel("     " + filme.getClassificacaoIndicativa().toString());
				lblClassificacao.setBounds(20, 10, 400, 25);
				lblClassificacao.setFont(new Font("Tahoma", Font.BOLD, 13));
				lblClassificacao.setBackground(new Color(255,255,255));
				panelNomeEClassificacao.add(lblClassificacao);
				
				
				JPanel panelDuracaoEGeneros = new JPanel();
				panelDuracaoEGeneros.setLayout(new FlowLayout(FlowLayout.LEFT));
				panelDuracaoEGeneros.setBackground(new Color(2, 17, 28));
				panelDuracaoEGeneros.setBounds(10, 41, 414, 32);
				panelDuracaoEGeneros.setBorder(new EmptyBorder(0, 0, 0, 0));
				contentPane.add(panelDuracaoEGeneros);
				
				JLabel lblDuracao = new JLabel("Duração:"+Integer.toString(filme.getDuracaoEmMinutos())+"min  ");
				lblDuracao.setBounds(20, 10, 490, 25);
				lblDuracao.setBackground(new Color(255, 255, 255));
				lblDuracao.setFont(new Font("Tahoma", Font.BOLD, 13));
				panelDuracaoEGeneros.add(lblDuracao);
				
				for(GeneroFilme genero : filme.getGeneros()) {
					JLabel lblGenero = new JLabel("     " + genero.name());
					lblGenero.setBounds(20, 10, 40, 25);
					lblGenero.setBackground(new Color(255,255,255));
					lblGenero.setFont(new Font("Tahoma", Font.BOLD, 13));
					panelDuracaoEGeneros.add(lblGenero);
				}
				

	}
}
