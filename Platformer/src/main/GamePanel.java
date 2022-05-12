
package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;


public class GamePanel extends JPanel {
    
    private MouseInputs mouseInputs;
    private float xDelta = 0, yDelta = 0;
    private float xDir = 1f, yDir = 1f;
    private int frames = 0;
    private long lastCheck = 0;
    private Color color = new Color(150,20,90);
    private Random random;
    
    public GamePanel() {
        random = new Random();
        mouseInputs = new MouseInputs(this);
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }
    
    public void changeXDelta(int value) {
        this.xDelta += value;
        repaint();
    }
    
    public void changeYDelta(int value) {
        this.yDelta += value;
        repaint();
    }
    
    public void setRectPos(int x, int y) {
        this.xDelta = x;
        this.yDelta = y;
        repaint();
    }
    
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        for (CustomRect rect : rects) {
            rect.updateRect();
            rect.draw(g);
        }
        
        
//        g.setColor(color);
//        g.fillRect((int)xDelta, (int)yDelta, 200, 50);
        
        updateRectangle();
        
    }
    
    public void updateRectangle() {
        xDelta += xDir;
        if (xDelta > 400 || xDelta < 0){
            xDir *= -1; 
            color = getRandomColor();
        }
        yDelta += yDir;
        if (yDelta > 400 || yDelta < 0) {
            yDir *= -1;
            color = getRandomColor();
        }
    }
    
    private Color getRandomColor() {
        int r = random.nextInt(255);
        int b = random.nextInt(255);
        int g = random.nextInt(255);
        
        return new Color(r,b,g);
    }
    
    private ArrayList <CustomRect> rects = new ArrayList<>();
    
    public void spawnRect(int x, int y) {
        rects.add(new CustomRect(x,y));
    }
    
    public class CustomRect {
        int x,y,w,h;
        int xDir = 1;
        int yDir = 1;
        Color color;
        
        public CustomRect(int x, int y){
            this.x = x;
            this.y = y;
            h = random.nextInt(50);
            w = h * random.nextInt(3);
            color = newColor();
        }
        
        public void updateRect() {
            this.x += xDir;
            this.y += yDir;
            
            if ((x + w) > 400 || x < 0){
                xDir *= -1;
                color = newColor();
            }
            
            if ((y + h) > 400 || y < 0){
                yDir *= -1;
                color = newColor();
            }
        }
        
        public void draw(Graphics g) {
            g.setColor(color);
            g.fillRect(x, y, w, h);
        }
        
        private Color newColor() {
            return new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255));
        }
    }
}
