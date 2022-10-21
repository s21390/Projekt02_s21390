import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Dinus {
        private static int dinoBaseY, dinoTopY, dinoStartX, dinoEndX;
        private static int dinoTop, dinoBottom, topPoint;

        private static boolean topPointReached;
        private static int jumpFactor = 20;

        public static final int STAND_STILL = 1,
                RUNNING = 2,
                JUMPING = 3,
                DIE = 4;
        private final int LEFT_FOOT = 1,
                RIGHT_FOOT = 2,
                NO_FOOT = 3;

        private static int state;

        private int foot;

        static BufferedImage image;
        BufferedImage leftFootDino;
        BufferedImage rightFootDino;
        BufferedImage deadDino;

        public Dinus()   {
            int x=5;

            image = new Resource().getResourceImage("../Projekt02_s21390/Dino-stand.png");
            leftFootDino = new Resource().getResourceImage("../Projekt02_s21390/Dino-left-up.png");
            rightFootDino = new Resource().getResourceImage("../Projekt02_s21390/Dino-right-up.png");
            deadDino = new Resource().getResourceImage("../Projekt02_s21390/Dino-big-eyes.png");


            dinoBaseY = Ziemia.GROUND_Y + x;
            dinoTopY = Ziemia.GROUND_Y - image.getHeight() + x;
            dinoStartX = 100;
            dinoEndX = dinoStartX + image.getWidth();
            topPoint = dinoTopY - 120;

            state = 1;
            foot = NO_FOOT;


        }

        public void create(Graphics g) {
            dinoBottom = dinoTop + image.getHeight();



            switch(state) {

                case STAND_STILL:
                    System.out.println("stand");
                    g.drawImage(image, dinoStartX, dinoTopY, null);
                    break;

                case RUNNING:
                    if(foot == NO_FOOT) {
                        foot = LEFT_FOOT;
                        g.drawImage(leftFootDino, dinoStartX, dinoTopY, null);
                    } else if(foot == LEFT_FOOT) {
                        foot = RIGHT_FOOT;
                        g.drawImage(rightFootDino, dinoStartX, dinoTopY, null);
                    } else {
                        foot = LEFT_FOOT;
                        g.drawImage(leftFootDino, dinoStartX, dinoTopY, null);
                    }
                    break;

                case JUMPING:
                    if(dinoTop > topPoint && !topPointReached) {
                        g.drawImage(image, dinoStartX, dinoTop -= jumpFactor, null);
                        break;
                    }
                    if(dinoTop >= topPoint && !topPointReached) {
                        topPointReached = true;
                        g.drawImage(image, dinoStartX, dinoTop += jumpFactor, null);
                        break;
                    }
                    if(dinoTop > topPoint && topPointReached) {
                        if(dinoTopY == dinoTop && topPointReached) {
                            state = RUNNING;
                            topPointReached = false;
                            break;
                        }
                        g.drawImage(image, dinoStartX, dinoTop += jumpFactor, null);
                        break;
                    }
                case DIE:
                    g.drawImage(deadDino, dinoStartX, dinoTop, null);
                    break;
            }
        }

        public void die() {
            state = DIE;
        }

        public static Rectangle getDino() {
            Rectangle dino = new Rectangle();
            dino.x = dinoStartX;

            if(state == JUMPING && !topPointReached) dino.y = dinoTop - jumpFactor;
            else if(state == JUMPING && topPointReached) dino.y = dinoTop + jumpFactor;
            else if(state != JUMPING) dino.y = dinoTop;

            dino.width = image.getWidth();
            dino.height = image.getHeight();

            return dino;
        }

        public void startRunning() {
            dinoTop = dinoTopY;
            state = RUNNING;
        }

        public void jump() {
            dinoTop = dinoTopY;
            topPointReached = false;
            state = JUMPING;
        }

        private class DinoImages {

        }

    }

