package cars;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Timer;
import javax.swing.JPanel;



public class work extends JPanel implements ActionListener, KeyListener {


    private int space;
    private int width = 80;
    private int height = 70;
    private int speed;
    private int WIDTH = 500;
    private int Height = 700;
    private int move=20;
    private int count=1;
    private ArrayList<Rectangle> ocars;
    private Random random;
    BufferedImage user;
    BufferedImage op1;
    BufferedImage op2;
    BufferedImage road;
    Timer t;
    private Rectangle car;

    public work()

    {
        try {
            user= ImageIO.read(new File("C:\\Users\\jeet\\Downloads\\car7.png"));
            op1= ImageIO.read(new File("C:\\Users\\jeet\\Downloads\\car9.png"));
            op2= ImageIO.read(new File("C:\\Users\\jeet\\Downloads\\car10.png"));
            road = ImageIO.read(new File("C:\\Users\\jeet\\Downloads\\road.png"));

        }catch(IOException ex)
        {
            Logger.getLogger(work.class.getName()).log(Level.SEVERE,null,ex);
        }
        t=new Timer(20,this);

        random = new Random();
        ocars = new ArrayList<Rectangle>();
        car = new Rectangle(500 / 2 - 90, 700 - 100, 80, 70);
        space = 300;
        speed = 2;
        addKeyListener(this);
        setFocusable(true);
        addocars(true);
        addocars(true);
        addocars(true);

        addocars(true);



        t.start();
    }

    public void addocars(boolean first)
    {
        int positionx = random.nextInt() % 2;
        int x = 0;
        int y = 0;
        int width = 80;
        int height = 70;
        if (positionx == 0) {
            x = 500 / 2 - 90;
        } else {
            x = 500 / 2 + 10;
        }
        if (first)
        {
            ocars.add(new Rectangle(x, y - 100 - (ocars.size() * space), 80, 70));
        }
    else
        {

            ocars.add(new Rectangle(x, ocars.get(ocars.size()-1).y-300,80,70));
        }
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponents(g);
        g.setColor(Color.green);
        g.fillRect(0,0,500,700);
        g.drawImage(road,500/2-100, 0,null);
        g.drawImage(user,car.x,car.y,null);
        g.setColor(Color.blue);
        g.drawLine(500/2,0,500/2,700);
        g.setColor(Color.MAGENTA);
        for(Rectangle rect:ocars )
        {
            if(random.nextInt()%2==0) {
                g.drawImage(op1, rect.x, rect.y, null);
            }else{
                g.drawImage(op2,rect.x,rect.y,null);
            }




        }


    }
    public void actionPerformed(ActionEvent e)
    {
        Rectangle rect;
        count++;
        for(int i=0;i<ocars.size();i++)
        {
            rect = ocars.get(i);
            if(count%1000==0) {
                speed++;
                if (move < 50) {
                    move += 10;
                }
            }



            rect.y += speed;
        }
        //cars crashing
        for(Rectangle r:ocars){
            if(r.intersects(car)){
                car.y=r.y+height;

            }
        }
        for(int i=0;i<ocars.size();i++){
            rect=ocars.get(i);
            if(rect.y+rect.height>700){
                ocars.remove(rect);
                addocars(false);
            }
        }
        repaint();
    }

//movingup
    public void moveup() {
        if (car.y - move > 0) {
            System.out.println("\b");
        }
        else {
            car.y -= move;
        }
    }
    public void movedown() {
        if (car.y + move + car.height > 700 - 1) {
            System.out.println("\b");
        } else {
            car.y -= move;
        }
    }
    public void moveleft() {
        if (car.x - move < 500 / 2 - 90) {
            System.out.println("\b");
        } else {
            car.x -= move;
        }
    }
    public void moveright() {
        if (car.x + move > 500 / 2 + 10) {
            System.out.println("\b");
        } else {
            car.x+= move;
        }
    }



    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key=e.getKeyCode();
        switch(key){
            case KeyEvent.VK_UP:
                moveup();


            case KeyEvent.VK_DOWN:
                movedown();


            case KeyEvent.VK_LEFT:
                moveleft();
                break;

            case KeyEvent.VK_RIGHT:

                moveright();
                break;

            default:
                break;
        }
    }
}













