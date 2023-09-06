import javax.swing.*;
import java.awt.*;

public class frame extends JFrame {

    frame(){
        this.setTitle("Brick Breaker!");
        this.add(new gamePanel());
        this.pack();
        this.setBackground(Color.BLACK);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

}
