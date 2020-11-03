import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class SmartEnemy extends GameObject {

    private Handler handler;
    private GameObject player;


    ///creating basic enemy object and elements
    public SmartEnemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler; 

        for(int i = 0; i < handler.object.size(); i++){
            if(handler.object.get(i).getID() == ID.Player)
                player = handler.object.get(i);
        }

       
    }

    //part of hit collision logic
    public Rectangle getBounds(){
        return new Rectangle((int)x, (int)y, 16, 16);
    }
    

    //smart movements
    public void tick() {

        x += velX;
        y += velY;

        float diffX = x - player.getX() - 8;
        float diffY = y - player.getY() - 8;
        float distance = (float) Math.sqrt((x - player.getX()) * (x-player.getX()) + (y-player.getY()) * (y-player.getY()));

        velX = (int) ((-1.0/distance) * diffX);
        velY = (int) ((-1.0/distance) * diffY);

        //algorithm to have enemy bouncing around screen
        if(y <= 0 || y >= Game.HEIGHT - 32) velY *= -1;
        if(x <= 0 || x >= Game.HEIGHT - 16) velX *= -1;


        //adding trail to it's movements
        handler.addObject(new Trail(x, y, ID.Trail, Color.MAGENTA, 16, 16, 0.02f, handler));
        

    }

 
    ///rendering enemy color and shape
    public void render(Graphics g) {
       g.setColor(Color.MAGENTA);
       g.fillRect((int)x, (int)y, 16, 16);

    }
    
}
