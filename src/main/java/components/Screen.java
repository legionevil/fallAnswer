package components;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Screen extends JPanel implements ActionListener {
    private static int drawX = 0;
    private static int drawY = 0;
    private int fontSize = 10;
    private Timer timer;
    private String str = "Test";
    private double fallSpeed;
    private static final int PIXELS_PER_METER = 100;
    private static final int GROUND_Y = 1000;
    private static final int MS_PER_SECOND = 1000;

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public int getSizeFont() {
        return fontSize;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public Screen() {
        timer = new Timer(16, this);
        timer.start();
    }

    public Timer getTimer() {
        return timer;
    }

    private void doDrawing(Graphics graphics) {
        Graphics2D g2d = (Graphics2D) graphics.create();
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHints(rh);
        setBackground(Color.BLACK);
        g2d.drawLine(0, GROUND_Y, getWidth(), GROUND_Y);
        g2d.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, fontSize));
        g2d.setColor(Color.GREEN);
        if (drawY < GROUND_Y) {
            System.out.println("дистанция: " + drawY + " ускорение: " + fallSpeed);
            fallSpeed = fallSpeed + (9.8d * PIXELS_PER_METER / MS_PER_SECOND);
            drawY = drawY + (int) fallSpeed;
            if (drawY > GROUND_Y) {
                drawY = GROUND_Y;
            }
            str = String.valueOf(fallSpeed);
        }
        g2d.drawString(str, drawX + fontSize * 2, drawY);
        g2d.fillRect(drawX, drawY - fontSize, fontSize, fontSize);
        g2d.dispose();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}