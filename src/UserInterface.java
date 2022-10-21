import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

class UserInterface {
    JFrame mainWindow = new JFrame("Dinus Gejming");
    ImageIcon img = new ImageIcon("D:\\Szkoła\\Projekt02_s21390\\ikona.png");


    public static int WIDTH = 800;
    public static int HEIGHT = 500;

    public void createAndShowGUI() {
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setIconImage(img.getImage());
        Container container = mainWindow.getContentPane();

        GamePanel gamePanel = new GamePanel();
        gamePanel.addKeyListener(gamePanel);
        gamePanel.setFocusable(true);

        container.setLayout(new BorderLayout());

        container.add(gamePanel, BorderLayout.CENTER);

        mainWindow.setSize(WIDTH, HEIGHT);
        mainWindow.setResizable(false);
        mainWindow.setVisible(true);
        mainWindow.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new UserInterface().createAndShowGUI();
            }
        });
    }
}