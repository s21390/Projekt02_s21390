import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


class GamePanel extends JPanel implements KeyListener, Runnable {

    public static int WIDTH;
    public static int HEIGHT;
    private Thread animator;

    private boolean running = false;
    private boolean gameOver = false;

    Image img;

    Ziemia ziemia;
    Dinus dinus;
    Przeszkody przeszkody;

    private int score;

    public GamePanel()  {
        WIDTH = UserInterface.WIDTH;
        HEIGHT = UserInterface.HEIGHT;

        ziemia = new Ziemia(HEIGHT);
        dinus = new Dinus();
        przeszkody = new Przeszkody((int)(WIDTH * 1.5));

        score = 0;
img = Toolkit.getDefaultToolkit().createImage("../Projekt02_s21390/pjatk.png");
        setSize(WIDTH, HEIGHT);
        setVisible(true);
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(img,0,0,null);
        g.setFont(new Font("Courier New", Font.BOLD, 25));
        g.drawString(Integer.toString(score), getWidth()/2 - 5, 100);
        ziemia.create(g);
        dinus.create(g);
        przeszkody.create(g);

    }

    public void run() {
        running = true;

        while(running) {
            updateGame();
            repaint();
            try {
                Thread.sleep(50);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateGame() {
        score += 1;
Image img;
        ziemia.update();

        przeszkody.update();
img = Toolkit.getDefaultToolkit().createImage("../Projekt02_s21390/koniec.png");
        if(przeszkody.hasCollided()) {
            dinus.die();
            getGraphics().drawImage(img,300,300,null);
            repaint();
            running = false;
            gameOver = true;
            System.out.println("Zderzenie czołowe z ITN");
        }

    }

    public void reset() {
        score = 0;
        System.out.println("Jeszcze raz!!!");
        przeszkody.resume();
        gameOver = false;
    }

    public void keyTyped(KeyEvent e) {

        if(e.getKeyChar() == ' ') {
            if(gameOver) reset();
            if (animator == null || !running) {
                System.out.println("Lecimy!!!");
                animator = new Thread(this);
                animator.start();
                dinus.startRunning();
            } else {
                dinus.jump();
            }
        }
    }

    public void keyPressed(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {

    }
}