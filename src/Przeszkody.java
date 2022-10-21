import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

public class Przeszkody {
    private class Przeszkoda {
        BufferedImage image;
        int x;
        int y;

        Rectangle getObstacle() {
            Rectangle obstacle = new Rectangle();
            obstacle.x = x;
            obstacle.y = y;
            obstacle.width = image.getWidth();
            obstacle.height = image.getHeight();

            return obstacle;
        }
    }

    private int firstX;
    private int obstacleInterval;
    private int movementSpeed;

    private ArrayList<BufferedImage> imageList;
    private ArrayList<Przeszkoda> obList;

    private Przeszkoda blockedAt;

    public Przeszkody(int firstPos) {
        obList = new ArrayList<Przeszkoda>();
        imageList = new ArrayList<BufferedImage>();

        firstX = firstPos;
        obstacleInterval = 200;
        movementSpeed = 11;

        imageList.add(new Resource().getResourceImage("../Projekt02_s21390/ITN-1.png"));
        imageList.add(new Resource().getResourceImage("../Projekt02_s21390/ITN-2.png"));
        imageList.add(new Resource().getResourceImage("../Projekt02_s21390/ITN-2.png"));
        imageList.add(new Resource().getResourceImage("../Projekt02_s21390/ITN-1.png"));
        // imageList.add(new Resource().getResourceImage("../images/Cactus-3.png"));
        // imageList.add(new Resource().getResourceImage("../images/Cactus-4.png"));
        imageList.add(new Resource().getResourceImage("../Projekt02_s21390/ITN-5.png"));

        int x = firstX;

        for(BufferedImage bi : imageList) {

            Przeszkoda ob = new Przeszkoda();

            ob.image = bi;
            ob.x = x;
            ob.y = Ziemia.GROUND_Y - bi.getHeight() + 5;
            x += obstacleInterval;

            obList.add(ob);
        }
    }

    public void update() {
        Iterator<Przeszkoda> looper = obList.iterator();

        Przeszkoda firstOb = looper.next();
        firstOb.x -= movementSpeed;

        while(looper.hasNext()) {
            Przeszkoda ob = looper.next();
            ob.x -= movementSpeed;
        }

        Przeszkoda lastOb = obList.get(obList.size() - 1);

        if(firstOb.x < -firstOb.image.getWidth()) {
            obList.remove(firstOb);
            firstOb.x = obList.get(obList.size() - 1).x + obstacleInterval;
            obList.add(firstOb);
        }
    }

    public void create(Graphics g) {
        for(Przeszkoda ob : obList) {
            g.setColor(Color.black);

            g.drawImage(ob.image, ob.x, ob.y, null);
        }
    }

    public boolean hasCollided() {
        for(Przeszkoda ob : obList) {
            if(Dinus.getDino().intersects(ob.getObstacle())) {
                System.out.println("Dino = " + Dinus.getDino() + "\nObstacle = " + ob.getObstacle() + "\n\n");
                blockedAt = ob;
                return true;
            }
        }
        return false;
    }

    public void resume() {

        int x = firstX/2;
        obList = new ArrayList<Przeszkoda>();

        for(BufferedImage bi : imageList) {

            Przeszkoda ob = new Przeszkoda();

            ob.image = bi;
            ob.x = x;
            ob.y = Ziemia.GROUND_Y - bi.getHeight() + 5;
            x += obstacleInterval;

            obList.add(ob);
        }
    }

}
