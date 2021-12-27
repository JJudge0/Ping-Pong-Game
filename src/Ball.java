package src;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
public class Ball extends Rectangle  {
    

    // Random random;
    // int xVelocity; //How fast the ball is moving on the x-axis
    // int yVelocity; //How fast the ball is moving on the y-axis
    // public  Ball()
    // {
    
    
        
    // }

    // public void Set_X_Direction(int Random_X_Direction ) 
    // {
            
    // }

    // public void Set_Y_Direction(int Random_Y_Direction) 
    // {
            
    // }

    // public void Move() 
    // {
            
    // }

    // public void Draw(Graphics g)
    // {


    // }

    Random random;
    int xVelocity;
	int yVelocity;
	int initialSpeed = 2;
	
	Ball(int x, int y, int width, int height){
		super(x,y,width,height);
		random = new Random();
		int randomXDirection = random.nextInt(2);
		if(randomXDirection == 0)
			randomXDirection--;
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
		g.setColor( new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
		g.fillOval(x, y, height, width);
	}

}
