import java.awt.*;
import java.util.Random;

public class ball extends Rectangle {
    int xSpeed = 0;
    Random random;
    int ySpeed = -1;
    ball(int x, int y , int ball_diameter){
    super(x, y, ball_diameter, ball_diameter);
        random = new Random();
        xSpeed = random.nextInt(2);
        if (xSpeed == 0){
            xSpeed--;
        }
    }

    public void draw(Graphics g){
        g.setColor(Color.orange);
        g.fillRect(x, y , width, height);
    }

    public void move(){
        y += ySpeed;
        x += xSpeed;
    }





}
