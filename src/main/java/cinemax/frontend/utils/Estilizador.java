package cinemax.frontend.utils;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.plaf.basic.BasicScrollBarUI;

import cinemax.backend.filmes.ClassificacaoIndicativa;

public class Estilizador {
	
	public static JScrollPane estilizandoScrollBarVertEHori(JScrollPane scrollPane) {
		// Acesse as barras do JScrollPane
		JScrollBar verticalBar = scrollPane.getVerticalScrollBar();

		// Cor do fundo da barra (trilha)
		verticalBar.setBackground(new Color(2, 2, 2)); // substitua com sua cor

		// Cor do "puxador" (thumb)
		verticalBar.setUI(new BasicScrollBarUI() {

			// -----------------necessario para tirar os botões das
			// bordas--------------------------------
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

			// -------------------------------------------------------------------------------------------
			@Override
			protected void configureScrollBarColors() {
				this.thumbColor = new Color(170, 170, 170); // puxador
				this.trackColor = new Color(2, 17, 28); // fundo
			}

		});
		JScrollBar horizontalBar = scrollPane.getHorizontalScrollBar();

		verticalBar.setBackground(new Color(2, 2, 2));

		horizontalBar.setUI(new BasicScrollBarUI() {
			
			@Override
			protected JButton createDecreaseButton(int orientation) {
				JButton button = new JButton("◀"); // seta para cima
				button.setForeground(Color.WHITE); // cor do texto
				button.setBackground(new Color(30, 30, 30)); // fundo
				button.setBorder(BorderFactory.createEmptyBorder());
				return button;
			}

			@Override
			protected JButton createIncreaseButton(int orientation) {
				JButton button = new JButton("▶"); // seta para baixo
				button.setForeground(Color.WHITE);
				button.setBackground(new Color(30, 30, 30));
				button.setBorder(BorderFactory.createEmptyBorder());
				return button;
			}
			
			@Override
			protected void configureScrollBarColors() {
				this.thumbColor = new Color(170, 170, 170);
				this.trackColor = new Color(2, 17, 28);
			}
		});

		return scrollPane;
	}
	
	public static Color escolherCorDaClassificacao(ClassificacaoIndicativa classificacaoIndicativa) {
		Color cor;

		if (classificacaoIndicativa == ClassificacaoIndicativa.AL
				|| classificacaoIndicativa == ClassificacaoIndicativa.AL10
				|| classificacaoIndicativa == ClassificacaoIndicativa.AL12)
			cor = new Color(0, 200, 80);
		else
			cor = new Color(200, 20, 0);

		return cor;
	}
	
}
