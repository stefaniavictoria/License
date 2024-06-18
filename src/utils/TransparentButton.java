package utils;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;

public class TransparentButton extends JButton {

    private static final long serialVersionUID = 1L;
    private Color borderColor;
    private Color backgroundColor;
//
//    public TransparentButton(String text) {
//        super(text);
//        setContentAreaFilled(false);
//        setOpaque(false);
//        setBorderPainted(true);
//        setFocusPainted(false);
//    }
    
    public TransparentButton(String text, Color borderColor, Color backgroundColor) {
        super(text);
        this.borderColor = borderColor;
        this.backgroundColor = backgroundColor;
        setContentAreaFilled(false);
        setOpaque(false);
        setBorderPainted(true);
        setFocusPainted(false);
    }

//    @Override
//    protected void paintComponent(Graphics g) {
//        if (getModel().isPressed()) {
//            g.setColor(new Color(0, 0, 0, 50));
//        } else if (getModel().isRollover()) {
//            g.setColor(new Color(0, 0, 0, 30));
//        } else {
//            g.setColor(new Color(0, 0, 0, 0));
//        }
//        g.fillRect(0, 0, getWidth(), getHeight());
//        super.paintComponent(g);
//    }
    
    @Override
    protected void paintComponent(Graphics g) {
        // Set semi-transparent background color
        g.setColor(backgroundColor);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        super.paintComponent(g);
    }
//
//    @Override
//    protected void paintBorder(Graphics g) {
//        g.setColor(new Color(220, 220, 220, 200));
//        g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
//    }
    
    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(borderColor);
        g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
    }
}
