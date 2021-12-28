package src;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
public class Paddle extends Rectangle {
    

    //  int id; // id1 for player1 and  id2 for player2
    //  int yVelocity; // How fast the paddle moves on the frame


    // public Paddle()
    // {

    // }

   
    // public void Key_Pressed(KeyEvent e)
    // {

    // }

    // public void Key_Released(KeyEvent e)
    // {
            
    // }

    // public void Set_Y_Direction(int YDirection ) //Only goes up and down
    // {
            
    // }

    // public void Move()
    // {

    // }

    // public void Draw(Graphics g)
    // {


    // }

    int id; // id1 for player1 and  id2 for player2
	int yVelocity;  // How fast the paddle moves on the frame
	int speed = 10; // Speed for the Paddles
	
	Paddle(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id){
		super(x,y,PADDLE_WIDTH,PADDLE_HEIGHT);
		this.id=id;
	}
	
	A
	public void keyPressed(KeyEvent e) {
		switch(id) {
		case 1:               //Player 1                              
			if(e.getKeyCode()==KeyEvent.VK_W) {   //Uses W key to go up asdadadasda
				setYDirection(-speed);
			}
			if(e.getKeyCode()==KeyEvent.VK_S) {   //Uses S key to go down ssss asdsadsadsada
				setYDirection(speed); 
			}
			break;
		case 2:               // Player 2
			if(e.getKeyCode()==KeyEvent.VK_UP) {   //Uses Arrow up key to go up
				setYDirection(-speed);
			}
			if(e.getKeyCode()==KeyEvent.VK_DOWN) {  //Uses Arrow down key to go down
				setYDirection(speed);
			}
			break;
		}
	}
	public void keyReleased(KeyEvent e) {
		switch(id) {
		case 1:
			if(e.getKeyCode()==KeyEvent.VK_W) {
				setYDirection(0);
			}
			if(e.getKeyCode()==KeyEvent.VK_S) {
				setYDirection(0);
			}
			break;
		case 2:
			if(e.getKeyCode()==KeyEvent.VK_UP) {
				setYDirection(0);
			}
			if(e.getKeyCode()==KeyEvent.VK_DOWN) {
				setYDirection(0);
			}
			break;
		}
	}
	public void setYDirection(int yDirection) {
		yVelocity = yDirection;
	}
	public void move() {
		y= y + yVelocity;
	}
	public void draw(Graphics g) {
		if(id==1)
			g.setColor(Color.blue);
		else
			g.setColor(Color.red);
		g.fillRect(x, y, width, height);
	}
}


    
   

