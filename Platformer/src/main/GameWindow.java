
package main;

import java.awt.Dimension;
import javax.swing.JFrame;


public class GameWindow {
    
    private JFrame jframe;
    
    public GameWindow (GamePanel gamePanel) {
        jframe = new JFrame();
        
        jframe.setSize(400, 400);
        
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setLocationRelativeTo(null);
        jframe.add(gamePanel);
        jframe.setVisible(true);
    }
}
