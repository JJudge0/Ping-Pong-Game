package src;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
// Note: Remember Ball moves on X axis aswell Y axis
public class Ball extends Rectangle  {
    
    Random random;
    int xVelocity;
	int yVelocity;
	int initialSpeed = 4; // Speed of the ball
	
	Ball(int x, int y, int width, int height)  
	{
		super(x,y,width,height); // Width and height is ball diameter 
		random = new Random();
		int randomXDirection = random.nextInt(2); 
		if(randomXDirection == 0) // Uses 0 to go left
			randomXDirection --; 
        Set_X_Direction(randomXDirection*initialSpeed);
		
		int randomYDirection = random.nextInt(2);
		if(randomYDirection == 0)
			randomYDirection--;
        Set_y_Direction(randomYDirection*initialSpeed);
		
	}
	
	public void Set_X_Direction(int randomXDirection) {
		xVelocity = randomXDirection;
	}
	public void Set_y_Direction(int randomYDirection) {
		yVelocity = randomYDirection;
	}
	public void move() {
		x += xVelocity;
		y += yVelocity;
	}
	public void draw(Graphics g) {
	    g.setColor( new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255))); Note: // Multi colored ball DISABLED FOR NOW!!
		// g.setColor(Color.GREEN); 
		g.fillOval(x, y, height, width); //height and width is the ball diameter from the Game Panel class which is set to default 20
	}

}
