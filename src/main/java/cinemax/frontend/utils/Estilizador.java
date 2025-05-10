package cinemax.frontend.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
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
	
	public static void estilizarBotao(JButton botao, Color corFundo, Color corTexto, int raioBorda) {
        botao.setContentAreaFilled(false); // remove preenchimento padrão
        botao.setFocusPainted(false);      // remove foco padrão
        botao.setForeground(corTexto);     // cor do texto
        botao.setFont(new Font("Tahoma", Font.BOLD, 14));
        botao.setBorder(new EmptyBorder(10, 20, 10, 20)); // padding interno

        // Cria um botão customizado com aparência arredondada
        botao.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Desenha fundo arredondado
                g2.setColor(corFundo);
                g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), raioBorda, raioBorda);

                // Desenha texto normalmente
                super.paint(g2, c);
                g2.dispose();
            }

            @Override
            public void installUI(JComponent c) {
                super.installUI(c);
                c.setOpaque(false); // fundo transparente para ver nossa pintura
            }
        });
    }

	
}
