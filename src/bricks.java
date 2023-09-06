import java.awt.*;

public class bricks extends Rectangle {
    int hp = 3;
    Color color;
    bricks(int x, int y, int width, int height){
    super(x, y, width, height);
    }

    public void draw(Graphics g){
        switch (hp){
            case 3 : color = Color.green;
            break;
            case 2 : color = Color.orange;
            break;
            case 1 : color = Color.red;
        }
        g.setColor(color);
        g.fillRect(x, y, width, height);

    }
}
