package src;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
public class GamePanel extends JPanel implements Runnable {
    
     static final int GAME_WIDTH=1000; //Note: Must be static because if there is more than one instance of the gamepanel class it will use the same variable instead of individual and final keyword allows us not to modify midway through.
     static final int GAME_HEIGHT=(int)(GAME_WIDTH * (5/9)); //chaning to 6ft to 10ft 
     static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH,GAME_HEIGHT);
     static final int BALL_DIAMETER=20;
     static final int PADDLE_WIDTH=25;
     static final int PADDLE_HEIGHT=100;
     Thread game_thread;
     Image image;
     Graphics graphics; //Using a thread to run the game
     Random randmom;
     Paddle paddle1; //Player1
     Paddle paddle2; //Player2
     Score score;
     Ball ball;


    public GamePanel()
    {
    
    
        
    }

    public  void New_Ball() //Creates new ball on the screen
    {


    }

    public  void New_Paddles() //Creates new paddle on the screen resets when it's a new game
    {

    }

    public  void Paint(Graphics g) 
    {

    }

    public  void Draw(Graphics g)
    {

    }

    public  void Move(Graphics g) // Moves after each iteration of the game loop
    {

    }

    public void Check_Collisions()
    {

    }

    public  void Run()
    {

    }

    public class AL extends KeyAdapter // Action Listener for the components IMPORTANT!
    {
        public void Key_Pressed(KeyEvent e)
        {

        }

        public void Key_Released(KeyEvent e)
        {
            
        }




    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        
    }


}

