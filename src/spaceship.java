import java.awt.*;
import java.awt.event.KeyEvent;
import java.security.Key;

public class spaceship extends Rectangle {

    int speed;

    spaceship(int x, int y, int PADDLE_LENGTH, int PADDLE_HEIGHT){
        super(x, y, PADDLE_LENGTH, PADDLE_HEIGHT);
    }

    public void draw(Graphics g){
        g.setColor(Color.white);
        g.fillRect(x,y,width, height);
    }

    public void move(){
        x += speed;
    }

    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            speed = -2;
            move();
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            speed = +2;
            move();
        }
    }
    public void keyReleased(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT) {
            speed = 0;
            move();
        }
    }

}
