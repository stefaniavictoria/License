package utils;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class RoundedCornerLabel extends JLabel {

    private int arcWidth;
    private int arcHeight;
    private Color backgroundColor;

    public RoundedCornerLabel(String text, int arcWidth, int arcHeight) {
        super(text);
        this.arcWidth = arcWidth;
        this.arcHeight = arcHeight;
        this.backgroundColor = getBackground(); // Keep track of the original background color
        setOpaque(false); // Make the label transparent so that the rounded rectangle shows
    }

    @Override
    protected void paintComponent(Graphics g) {
        // Paint the rounded rectangle background
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(backgroundColor);
        g2d.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arcWidth, arcHeight);

        // Call super to paint the text
        super.paintComponent(g);

        g2d.dispose();
    }

    // Optional: Allow changing the background color
    @Override
    public void setBackground(Color bg) {
        super.setBackground(bg);
        this.backgroundColor = bg;
    }

    // Optional: Allow changing the corner radius
    public void setCornerRadius(int arcWidth, int arcHeight) {
        this.arcWidth = arcWidth;
        this.arcHeight = arcHeight;
        repaint(); // Redraw the label with the new corner radius
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Rounded Corner Label Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new FlowLayout());

            // Create a rounded corner label with text
            RoundedCornerLabel label = new RoundedCornerLabel("Rounded Label", 15, 15);
            label.setPreferredSize(new Dimension(200, 50)); // Set preferred size for visibility
            label.setHorizontalAlignment(SwingConstants.CENTER); // Center align the text
            label.setBackground(Color.BLUE); // Set background color

            frame.add(label);
            frame.pack();
            frame.setVisible(true);
        });
    }
}
