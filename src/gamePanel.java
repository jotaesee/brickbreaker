import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class gamePanel extends JPanel implements Runnable  {

    bricks[][] brick ;
    static final int GAME_WIDTH = 500;
    static final int GAME_HEIGHT = 600;
    static final int UNIT_SIZE = 25;
    static final int GAME_UNITS = (GAME_WIDTH * GAME_HEIGHT) / UNIT_SIZE;
    public Graphics graphics;
    Image image;
    spaceship navecita;
    ball pelotita;
    Thread gameThread;
    int player_hp;
    boolean youLost;
    int bricks_hits = 0;

    gamePanel(){
    this.setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
    this.setLayout(null);
    this.setFocusable(true);
    this.addKeyListener(new AL());
    gameStart();
    gameThread = new Thread(this);
    gameThread.start();
    }

    public void gameStart(){
        newShip();
        newBall();
        newBricks();
        player_hp = 2;
        youLost = false;
    }

    public void newShip() {
        navecita = new spaceship(100,550, UNIT_SIZE*3, UNIT_SIZE);
    }

    public  void newBricks() {
        brick = new bricks[4][5];
        for (int i = 0; i <4 ; i++) {
            for (int j = 0 ; j < 5; j++) {
                brick[i][j] = new bricks(108*j, i*50, UNIT_SIZE*3, UNIT_SIZE);
            }
        }
    }

    public  void newBall() {
        pelotita = new ball(navecita.x+25, navecita.y -30 , UNIT_SIZE);
    }


    public void checkCollision(){
        if (navecita.x < 0) {
            navecita.x =0;
        }
        if (navecita.x > 425) {
            navecita.x = 425;
        }
        if (pelotita.y < 0 || pelotita.intersects(navecita) ) {
            pelotita.ySpeed = -(pelotita.ySpeed);
        }
        if (pelotita.y > GAME_HEIGHT && player_hp != 0) {
            newBall();
            player_hp--;
        }
        if (pelotita.y > GAME_HEIGHT && player_hp == 0){
            youLost = true;
        }
        if(pelotita.x < 0 || pelotita.x > GAME_WIDTH-25) {
            pelotita.xSpeed = -pelotita.xSpeed;
        }
        for (int i = 0; i <4 ; i++) {
            for (int j = 0 ; j < 5; j++) {
                if (pelotita.intersects(brick[i][j]) && brick[i][j].hp > 0) {
                    brick[i][j].hp --;
                    pelotita.ySpeed = -pelotita.ySpeed;
                    bricks_hits++;
                }
            }
        }


    }

    public void move(){
        navecita.move();
        pelotita.move();

    }

    public void draw(Graphics g){
        navecita.draw(g);
        pelotita.draw(g);
        for (int i = 0; i <4 ; i++) {
            for (int j = 0 ; j < 5; j++) {
                if (brick[i][j].hp > 0) {
                    brick[i][j].draw(g);
                }
            }
        }
        if (youLost) {
            g.setColor(Color.red);
            g.setFont(new Font("Comic Sans", Font.PLAIN, 20));
            g.drawString("You lost!", GAME_WIDTH/2, GAME_HEIGHT/2);
            g.drawString("Press R to restart",(GAME_WIDTH/2)-20, (GAME_HEIGHT/2)+20);
        }

        if (bricks_hits == 4*5*3){
            g.setColor(Color.green);
            g.setFont(new Font("Comic Sans", Font.PLAIN, 20));
            g.drawString("You Won!", GAME_WIDTH/2, GAME_HEIGHT/2);
            g.drawString("Press R to restart",(GAME_WIDTH/2)-20, (GAME_HEIGHT/2)+20);
        }
    }

    public void paint(Graphics g){
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image, 0 , 0, this);
    }
    public void run () {

        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 100000000 / amountOfTicks;
        double delta = 0;
        while (true) {
            long now = System.nanoTime();
            delta += (now - lastTime)/ns;
            lastTime = now;
            if (delta >= 1){
                move();
                checkCollision();
                repaint();
                delta--;
            }
        }

    }

    public class AL extends KeyAdapter{
        public void keyPressed(KeyEvent e){
            if (e.getKeyCode() == KeyEvent.VK_R) {
                gameStart();
            }
            navecita.keyPressed(e);
            repaint();
        }
        public void keyReleased(KeyEvent e){
            navecita.keyReleased(e);
            repaint();
        }
    }

}

