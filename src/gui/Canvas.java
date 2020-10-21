package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class Canvas extends JPanel{
	private int width;
    private int height;

    private final BufferedImage buffer;

    public Canvas(int width, int height) {

        this.width = width;
        this.height = height;
        this.buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        this.setSize(width, height);
        this.setBackground(Color.RED);

    }

    public BufferedImage getBuffer() {
        return buffer;
    }

    @Override
    public void paintComponent (Graphics graphics) {
        graphics.clearRect(0, 0, width, height);
        graphics.drawImage(buffer, 0, 0, null);
    }
}
