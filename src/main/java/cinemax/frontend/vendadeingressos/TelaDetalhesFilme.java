package cinemax.frontend.vendadeingressos;

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

import cinemax.backend.filmes.ClassificacaoIndicativa;
import cinemax.backend.filmes.Filme;
import cinemax.backend.filmes.GeneroFilme;
import cinemax.frontend.controller.ControladorDeApp;
import cinemax.frontend.utils.Estilizador;

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

	private Color escolherCorDaClassificacao(ClassificacaoIndicativa classificacaoIndicativa) {
		Color cor;

		if (classificacaoIndicativa == ClassificacaoIndicativa.AL
				|| classificacaoIndicativa == ClassificacaoIndicativa.AL10
				|| classificacaoIndicativa == ClassificacaoIndicativa.AL12)
			cor = new Color(0, 200, 80);
		else
			cor = new Color(200, 20, 0);

		return cor;
	}

	private String escolherFraseDaClassificao(ClassificacaoIndicativa classificacaoIndicativa) {
		String frase;

		if (classificacaoIndicativa == ClassificacaoIndicativa.AL)
			frase = "  Permitido para todas as idades";
		else
			frase = "Não recomendado para menores de " + classificacaoIndicativa.name();

		return frase;
	}

	/**
	 * Create the frame.
	 */
	public TelaDetalhesFilme(Filme filme) {
		if (filme == null)
			filme = app.getBackend().getBancoFilmes().obterFilmePorId(0);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(2, 18, 27));
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPaneSinopse = new JScrollPane();
		scrollPaneSinopse = Estilizador.estilizandoScrollBarVertEHori(scrollPaneSinopse);
		scrollPaneSinopse.setBounds(10, 121, 414, 129);
		contentPane.add(scrollPaneSinopse);

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		scrollPaneSinopse.setOpaque(false);
		scrollPaneSinopse.getViewport().setOpaque(false);
		scrollPaneSinopse.setBorder(null); // se quiser um visual "limpo"
		scrollPaneSinopse.setBorder(BorderFactory.createEmptyBorder());

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
		textAreaSinopse.setOpaque(false); // deixa o fundo transparente (opcional)
		textAreaSinopse.setBorder(BorderFactory.createEmptyBorder());
		textAreaSinopse.setBorder(new EmptyBorder(5, 5, 5, 5));
		textAreaSinopse.setText(filme.getSinopse());

		JLabel lblNome = new JLabel(filme.getNome());
		lblNome.setBounds(10, 11, 490, 25);
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNome.setForeground(new Color(255, 255, 255));
		contentPane.add(lblNome);

		Color corCerta = escolherCorDaClassificacao(filme.getClassificacaoIndicativa());
		String fraseCerta = escolherFraseDaClassificao(filme.getClassificacaoIndicativa());

		JPanel panelClassificacaoFraseESimbolo = new JPanel();
		panelClassificacaoFraseESimbolo.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelClassificacaoFraseESimbolo.setBackground(new Color(2, 17, 28));
		panelClassificacaoFraseESimbolo.setBounds(0, 73, 414, 32);
		panelClassificacaoFraseESimbolo.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.add(panelClassificacaoFraseESimbolo);

		JLabel lblSimboloClassificacao = new JLabel(fraseCerta);
		lblSimboloClassificacao.setBounds(10, 69, 400, 25);
		lblSimboloClassificacao.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSimboloClassificacao.setForeground(new Color(255, 255, 255));
		panelClassificacaoFraseESimbolo.add(lblSimboloClassificacao);

		JLabel lblFraseClassificacao = new JLabel(" " + filme.getClassificacaoIndicativa().name()+" ");
		lblFraseClassificacao.setBounds(10, 69, 400, 25);
		lblFraseClassificacao.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblFraseClassificacao.setForeground(new Color(255, 255, 255));
		lblFraseClassificacao.setOpaque(true);
		lblFraseClassificacao.setBackground(corCerta);
		panelClassificacaoFraseESimbolo.add(lblFraseClassificacao);

		JPanel panelDuracaoEGeneros = new JPanel();
		panelDuracaoEGeneros.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelDuracaoEGeneros.setBackground(new Color(2, 17, 28));
		panelDuracaoEGeneros.setBounds(10, 40, 414, 32);
		panelDuracaoEGeneros.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.add(panelDuracaoEGeneros);

		JLabel lblDuracao = new JLabel("Duração:" + Integer.toString(filme.getDuracaoEmMinutos()) + "min  ");
		lblDuracao.setBounds(20, 10, 490, 25);
		lblDuracao.setForeground(new Color(255, 255, 255));
		lblDuracao.setFont(new Font("Tahoma", Font.BOLD, 13));
		panelDuracaoEGeneros.add(lblDuracao);

		for (GeneroFilme genero : filme.getGeneros()) {
			JLabel lblGenero = new JLabel("     " + genero.name());
			lblGenero.setBounds(20, 10, 40, 25);
			lblGenero.setForeground(new Color(255, 255, 255));
			lblGenero.setFont(new Font("Tahoma", Font.BOLD, 13));
			panelDuracaoEGeneros.add(lblGenero);
		}

	}
}
