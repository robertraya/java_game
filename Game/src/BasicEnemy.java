import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class BasicEnemy extends GameObject {

    private Handler handler;


    ///creating basic enemy object and elements
    public BasicEnemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler; 

        velX = 5;
        velY = 5;
       
    }

    //part of hit collision logic
    public Rectangle getBounds(){
        return new Rectangle((int)x, (int)y, 16, 16);
    }
    

    //it's basic movements
    public void tick() {

        x += velX;
        y += velY;

        //algorithm to have enemy bouncing around screen
        if(y <= 0 || y >= Game.HEIGHT - 32) velY *= -1;
        if(x <= 0 || x >= Game.HEIGHT - 16) velX *= -1;


        //adding trail to it's movements
        handler.addObject(new Trail(x, y, ID.Trail, Color.RED, 16, 16, 0.02f, handler));
        

    }

 
    ///rendering enemy color and shape
    public void render(Graphics g) {
       g.setColor(Color.RED);
       g.fillRect((int)x, (int)y, 16, 16);

    }
    
}
