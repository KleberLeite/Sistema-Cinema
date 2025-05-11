package cinemax.frontend.estilizacao;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

import cinemax.backend.filmes.ClassificacaoIndicativa;

public class Estilizador {
	
	/**
     * Estiliza uma JTable com cabeçalho e células personalizadas.
     *
     * @param tabela             A JTable a ser estilizada.
     * @param corFundoCabecalho  Cor de fundo do cabeçalho.
     * @param corTextoCabecalho  Cor do texto do cabeçalho.
     * @param fonteCabecalho     Fonte do cabeçalho.
     * @param alturaCabecalho    Altura do cabeçalho.
     * @param corLinhaPar        Cor de fundo das linhas pares.
     * @param corLinhaImpar      Cor de fundo das linhas ímpares.
     * @param corTextoCelula     Cor do texto das células.
     * @param corBordaCelula     Cor da borda de cada célula.
     */
    public static void estilizarTabela(JTable tabela, Color corFundoCabecalho) {
    	Color corTextoCabecalho = Color.WHITE;
    	Font fonteCabecalho = new Font("Tahoma", Font.BOLD, 14);
    	int alturaCabecalho= 32;
        Color corLinhaPar = new Color(240, 240, 240);
        Color corLinhaImpar =  new Color(220, 220, 220);
        Color corTextoCelula = Color.BLACK;
        Color corBordaCelula = new Color(200, 200, 200);
        		
      
        // === Estilo do cabeçalho ===
        JTableHeader header = tabela.getTableHeader();
        header.setPreferredSize(new Dimension(header.getWidth(), alturaCabecalho));
        header.setBackground(corFundoCabecalho);
        header.setForeground(corTextoCabecalho);
        header.setFont(fonteCabecalho);

        header.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                label.setBackground(corFundoCabecalho);
                label.setForeground(corTextoCabecalho);
                label.setFont(fonteCabecalho);
                label.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
                return label;
            }
        });

        // === Estilo das células ===
        tabela.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {

                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                // Cores alternadas
                if (isSelected) {
                    c.setBackground(table.getSelectionBackground());
                    c.setForeground(table.getSelectionForeground());
                } else {
                    c.setBackground((row % 2 == 0) ? corLinhaPar : corLinhaImpar);
                    c.setForeground(corTextoCelula);
                }

                // Borda personalizada
                ((JComponent) c).setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, corBordaCelula));

                return c;
            }
        });

        // Outras configurações úteis
        tabela.setRowHeight(24);
        tabela.setGridColor(corBordaCelula);
        tabela.setShowGrid(true);
    }
	
	public static JScrollPane estilizarScrollPane(JScrollPane scrollPane) {
		
		
		scrollPane.setOpaque(false);
	    scrollPane.getViewport().setOpaque(false);
	    scrollPane.setBorder(BorderFactory.createEmptyBorder());

	    scrollPane.setViewportBorder(null);
	    scrollPane.setBackground(new Color(240, 240, 240));

	    scrollPane.setUI(new javax.swing.plaf.basic.BasicScrollPaneUI() {
	        private final int arc = 8;

	        @Override
	        public void paint(Graphics g, JComponent c) {
	            Graphics2D g2 = (Graphics2D) g.create();
	            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	            g2.setColor(new Color(240, 240, 240));
	            g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), arc, arc);
	            g2.setColor(Color.GRAY);
	            g2.setStroke(new BasicStroke(1.2f));
	            g2.drawRoundRect(0, 0, c.getWidth() - 1, c.getHeight() - 1, arc, arc);
	            g2.dispose();
	        }
	    });

	    return estilizandoScrollBarVertEHori( scrollPane);
	}
	
public static JScrollPane estilizandoScrollBarVertEHori(JScrollPane scrollPane) {
		
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
	

	    // Aplica borda arredondada ao viewport
	    scrollPane.setViewportBorder(BorderFactory.createEmptyBorder());
	    scrollPane.setOpaque(false);
	    scrollPane.getViewport().setOpaque(false);
		
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
	
	public static void estilizarTextArea(JTextArea textArea) {
	    textArea.setLineWrap(true);
	    textArea.setWrapStyleWord(true);
	    textArea.setFont(new Font("Tahoma", Font.PLAIN, 13));
	    textArea.setOpaque(false); // importante para transparência funcionar
	    textArea.setBorder(new EmptyBorder(10, 10, 10, 10));
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
	
	public static JPanel criarPainelArredondadoComBorda(Color corFundo, Color corBorda, int raioBorda, int espessuraBorda) {
	    return new JPanel() {
	        @Override
	        protected void paintComponent(Graphics g) {
	            super.paintComponent(g); // Desenha os filhos primeiro

	            Graphics2D g2 = (Graphics2D) g.create();
	            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

	            int largura = getWidth();
	            int altura = getHeight();

	            // Fundo
	            g2.setColor(corFundo);
	            g2.fillRoundRect(0, 0, largura, altura, raioBorda, raioBorda);

	            // Borda
	            g2.setStroke(new BasicStroke(espessuraBorda));
	            g2.setColor(corBorda);
	            g2.drawRoundRect(espessuraBorda / 2, espessuraBorda / 2,
	                    largura - espessuraBorda, altura - espessuraBorda,
	                    raioBorda, raioBorda);

	            g2.dispose();
	        }

	        {
	            setOpaque(false); // permite o fundo arredondado
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
	    final Color corFundoDesativado = ajustarBrilho(corFundo, 0.2f); // escurece 80%

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
	                corAtual[0] = ajustarBrilho(corFundo, 4.15f);
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
	
	
	public static void estilizarComboBoxClassificacaoIndicativa(JComboBox<?> comboBox) {
        comboBox.setFont(new Font("Tahoma", Font.BOLD, 13));
        comboBox.setBackground(new Color(2, 18, 27));
        comboBox.setForeground(Color.WHITE);
        comboBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        comboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                if (isSelected) {
                    label.setBackground(Color.GRAY);         // fundo do item selecionado
                    label.setForeground(Color.WHITE);        // texto do item selecionado
                } else {
                    label.setBackground(new Color(2, 18, 27)); // fundo dos itens normais
                    label.setForeground(Color.WHITE);         // texto dos itens normais
                }

                label.setFont(new Font("Tahoma", Font.PLAIN, 13));
                label.setOpaque(true); // necessário para o fundo ser visível
                return label;
            }
        });
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
