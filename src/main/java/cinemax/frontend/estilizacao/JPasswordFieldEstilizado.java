package cinemax.frontend.estilizacao;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class JPasswordFieldEstilizado extends JPasswordField {
    private static final long serialVersionUID = 1L;
    private final int arc = 10;

    public JPasswordFieldEstilizado() {
        setOpaque(false);
        setBackground(new Color(240, 240, 240));
        setFont(new Font("Tahoma", Font.PLAIN, 13));
        setBorder(new EmptyBorder(5, 10, 5, 10));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);
        super.paintComponent(g);
        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(Color.GRAY);
        g2.setStroke(new BasicStroke(1.2f));
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arc, arc);
        g2.dispose();
    }
}
