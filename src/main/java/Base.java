import components.Screen;

import javax.swing.JFrame;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Base extends JFrame {

    public Base() {
        initUI();
    }

    private void initUI() {
        Screen screen = new Screen();
        add(screen);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Timer timer = screen.getTimer();
                timer.stop();
            }
        });
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                String keyName = e.paramString().replaceFirst(".+keyText=", "").replaceFirst(",.+", "");
                System.out.println(e);
                if (keyName.equals("Escape")) {
                    System.exit(0);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        setTitle("Simple Java 2D example");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.ORANGE);
        setForeground(Color.ORANGE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(Base::run);
    }

    private static void run() {
        new Base().setVisible(true);
    }
}