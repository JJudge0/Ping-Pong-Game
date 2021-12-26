package src;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
public class GamePanel extends JPanel implements Runnable {
    
     static final int GAME_WIDTH=1000; //Note: Must be static because if there is more than one instance of the gamepanel class it will use the same variable instead of individual and final keyword allows us not to modify midway through.
     static final int GAME_HEIGHT=(int)(GAME_WIDTH * (0.5555)); //chaning to 6ft to 10ft  cannot put division must put int otherwise won't display panel.
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
     New_Paddles();
     New_Ball();
     score= new Score(GAME_WIDTH,GAME_HEIGHT);
     this.setFocusable(true); //Focuses on the keystrokes pressed by the users
     this.addKeyListener(new ActionListener());
     this.setPreferredSize(SCREEN_SIZE);
     Thread gameThread = new Thread(this);
     gameThread.start();
        
    }

    public  void New_Ball() //Creates new ball on the screen
    {
        Random random = new Random();
		ball = new Ball((GAME_WIDTH/2)-(BALL_DIAMETER/2),random.nextInt(GAME_HEIGHT-BALL_DIAMETER),BALL_DIAMETER,BALL_DIAMETER);

    }

    public  void New_Paddles() //Creates new paddle on the screen resets when it's a new game
    {
        paddle1 = new Paddle(0,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,1);
		paddle2 = new Paddle(GAME_WIDTH-PADDLE_WIDTH,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,2);
    }

    public  void Paint(Graphics g) 
    {
        image = createImage(getWidth(),getHeight());
        graphics = image.getGraphics();
        Draw(graphics);
        g.drawImage(image,0,0,this); // so draws image and sets it to 0 so starts from the corners and uses this class with "this" object 

    }

    public  void Draw(Graphics g)
    {
        paddle1.draw(g);
		paddle2.draw(g);
		ball.draw(g);
		score.draw(g);
         Toolkit.getDefaultToolkit().sync(); // I forgot to add this line of code in the video, it helps with the animation
    }

    public  void Move() // Moves after each iteration of the game loop
    {
        paddle1.move();
		paddle2.move();
		ball.move();
    }

    public void Check_Collisions()
    {
//bounce ball off top & bottom window edges
if(ball.y <=0) {
    ball.Set_y_Direction(-ball.yVelocity);
}
if(ball.y >= GAME_HEIGHT-BALL_DIAMETER) {
    ball.Set_y_Direction(-ball.yVelocity);
}
//bounce ball off paddles
if(ball.intersects(paddle1)) {
    ball.xVelocity = Math.abs(ball.xVelocity);
    ball.xVelocity++; //optional for more difficulty
    if(ball.yVelocity>0)
        ball.yVelocity++; //optional for more difficulty
    else
        ball.yVelocity--;
    ball.Set_X_Direction(ball.xVelocity);
    ball.Set_y_Direction(ball.yVelocity);
}
if(ball.intersects(paddle2)) {
    ball.xVelocity = Math.abs(ball.xVelocity);
    ball.xVelocity++; //optional for more difficulty
    if(ball.yVelocity>0)
        ball.yVelocity++; //optional for more difficulty
    else
        ball.yVelocity--;
    ball.Set_X_Direction(-ball.xVelocity);
    ball.Set_y_Direction(ball.yVelocity);
}
//stops paddles at window edges
if(paddle1.y<=0)
    paddle1.y=0;
if(paddle1.y >= (GAME_HEIGHT-PADDLE_HEIGHT))
    paddle1.y = GAME_HEIGHT-PADDLE_HEIGHT;
if(paddle2.y<=0)
    paddle2.y=0;
if(paddle2.y >= (GAME_HEIGHT-PADDLE_HEIGHT))
    paddle2.y = GAME_HEIGHT-PADDLE_HEIGHT;
//give a player 1 point and creates new paddles & ball
if(ball.x <=0) {
    score.Score_Player2++;
    New_Paddles();
    New_Ball();
    System.out.println("Player 2: "+score.Score_Player2);
}
if(ball.x >= GAME_WIDTH-BALL_DIAMETER) {
    score.Score_Player1++;
    New_Paddles();
    New_Ball();
    System.out.println("Player 1: "+score.Score_Player1);
}
    }

    public  void run()
    {
          //Game loop
        long Last_Time = System.nanoTime();
        double Amount_Of_Ticks=60.0;
        double Nano_Seconds=1000000000/Amount_Of_Ticks;
        double Delta =0;
        while(true){
            long Now = System.nanoTime();
            Delta += (Now-Last_Time)/Nano_Seconds;
            Last_Time =Now;
            if(Delta >=1)
            {
                Move();
                Check_Collisions();
                repaint();
                Delta--;
               //System.out.println("TEST");
            }

            
        }


    }

    public class ActionListener extends KeyAdapter // Action Listener for the components IMPORTANT!
    {
        public void Key_Pressed(KeyEvent e)
        {
            paddle1.keyPressed(e);
			paddle2.keyPressed(e);
        }

        public void Key_Released(KeyEvent e)
        {
            paddle1.keyReleased(e);
			paddle2.keyReleased(e);
        }




    }
}

