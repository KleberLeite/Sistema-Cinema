package cinemax.frontend.estilizacao;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonModel;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;

import cinemax.backend.filmes.ClassificacaoIndicativa;

public class Estilizador {
	
	public static JScrollPane estilizandoScrollBarVertEHori(JScrollPane scrollPane) {
		
		scrollPane.setBorder(BorderFactory.createEmptyBorder());

		
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
	
	public static JPanel criarPainelArredondado(Color corFundo, int raioBorda) {
	    return new JPanel() {
	        @Override
	        protected void paintComponent(Graphics g) {
	            Graphics2D g2 = (Graphics2D) g.create();
	            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

	            // Preenche fundo com bordas arredondadas
	            g2.setColor(corFundo);
	            g2.fillRoundRect(0, 0, getWidth(), getHeight(), raioBorda, raioBorda);
	            g2.dispose();
	            super.paintComponent(g); // importante para manter os filhos desenhados
	        }

	        {
	            setOpaque(false); // importante para o fundo arredondado funcionar
	        }
	    };
	}
	
	public static void aplicarEstiloBotao(JButton botao, EstiloBotao estilo) {
	    switch (estilo) {
	        case PADRAO_ESCURECIDO:
	            estilizarBotao(botao, new Color(13, 27, 42), Color.WHITE, 15);
	            break;
	        case MODERNO_DESTACADO:
	            estilizarBotao(botao, new Color(46, 46, 46), Color.WHITE, 15);
	            break;
	        case CLARO_UNIFICADO:
	            estilizarBotao(botao, new Color(230, 230, 230), Color.BLACK, 10);
	            break;
	    }
	}
	
	private static void estilizarBotao(JButton botao, Color corFundo, Color corTexto, int raioBorda) {
	    botao.setContentAreaFilled(false);
	    botao.setFocusPainted(false);
	    botao.setForeground(corTexto);
	    botao.setFont(new Font("Tahoma", Font.BOLD, 14));
	    botao.setBorder(new EmptyBorder(4, 10, 4, 10));

	    final Color[] corAtual = { corFundo };
	    final Color corFundoDesativado = ajustarBrilho(corFundo, 0.6f); // escurece 40%

	    botao.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
	        @Override
	        public void paint(Graphics g, JComponent c) {
	            Graphics2D g2 = (Graphics2D) g.create();
	            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

	            g2.setColor(corAtual[0]);
	            g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), raioBorda, raioBorda);

	            super.paint(g2, c);
	            g2.dispose();
	        }

	        @Override
	        public void installUI(JComponent c) {
	            super.installUI(c);
	            c.setOpaque(false);
	        }
	    });

	    botao.addMouseListener(new java.awt.event.MouseAdapter() {
	        @Override
	        public void mouseEntered(java.awt.event.MouseEvent e) {
	            if (botao.isEnabled()) {
	                corAtual[0] = ajustarBrilho(corFundo, 2.15f);
	                botao.repaint();
	            }
	        }

	        @Override
	        public void mouseExited(java.awt.event.MouseEvent e) {
	            if (botao.isEnabled()) {
	                corAtual[0] = corFundo;
	                botao.repaint();
	            }
	        }
	    });

	    // Listener para atualizar a cor ao ativar/desativar
	    botao.addPropertyChangeListener("enabled", evt -> {
	        boolean habilitado = botao.isEnabled();
	        corAtual[0] = habilitado ? corFundo : corFundoDesativado;
	        botao.repaint();
	    });

	    // Aplica a cor correta inicial caso já venha desabilitado
	    if (!botao.isEnabled()) {
	        corAtual[0] = corFundoDesativado;
	    }
	}

	// Clareia ou escurece a cor original com base no fator
	private static Color ajustarBrilho(Color cor, float fator) {
	    int media = (cor.getRed() + cor.getGreen() + cor.getBlue()) / 3;
	    if (media < 100) {
	        int r = Math.min(cor.getRed() + 30, 255);
	        int g = Math.min(cor.getGreen() + 30, 255);
	        int b = Math.min(cor.getBlue() + 30, 255);
	        return new Color(r, g, b);
	    } else {
	        int r = Math.min((int)(cor.getRed() * fator), 255);
	        int g = Math.min((int)(cor.getGreen() * fator), 255);
	        int b = Math.min((int)(cor.getBlue() * fator), 255);
	        return new Color(r, g, b);
	    }
	}

	
	
	
	
	
	
	public static void estilizarCheckBox(JCheckBox checkBox, Color corFundo, Color corTexto, Font fonte) {
	    checkBox.setBackground(corFundo);
	    checkBox.setForeground(corTexto);
	    checkBox.setFont(fonte);
	    checkBox.setFocusPainted(false); // remove a borda de foco azul
	    checkBox.setOpaque(true); // garante que a cor de fundo apareça
	    checkBox.setBorder(new EmptyBorder(5, 10, 5, 10)); // padding interno
	}
	
    /**
     * Aplica estilo customizado em CheckBoxes com uma caixa cinza.
     * Deve ser chamado no início da aplicação.
     */
    public static void estilizarCheckBoxCinza() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            UIManager.put("CheckBox.icon", new CustomCheckBoxIcon());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Ícone customizado de CheckBox com caixa cinza e check preto.
     */
    private static class CustomCheckBoxIcon implements Icon {
        private static final int SIZE = 16;

        @Override
        public int getIconWidth() {
            return SIZE;
        }

        @Override
        public int getIconHeight() {
            return SIZE;
        }

        @Override
        public void paintIcon(Component c, Graphics g, int x, int y) {
            Graphics2D g2 = (Graphics2D) g.create();
            AbstractButton button = (AbstractButton) c;
            ButtonModel model = button.getModel();

            // Anti-aliasing para suavizar bordas
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Caixa de fundo arredondada
            g2.setColor(Color.LIGHT_GRAY);
            g2.fillRoundRect(x, y, SIZE, SIZE, 6, 6); // 6 = raio de borda

            // Borda arredondada
            g2.setColor(Color.DARK_GRAY);
            g2.drawRoundRect(x, y, SIZE - 1, SIZE - 1, 6, 6);

            // Check customizado (✓)
            if (model.isSelected()) {
                g2.setStroke(new BasicStroke(2.5f));
                g2.setColor(new Color(2, 17, 28)); // Azul
                g2.drawLine(x + 3, y + 8, x + 7, y + 12);
                g2.drawLine(x + 7, y + 12, x + 13, y + 3);
            }

            g2.dispose();
        }
    }

	
}
