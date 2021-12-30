package src;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Game_Panel extends JPanel implements Runnable{

	static final int GAME_WIDTH = 1000; //Note: Must be static because if there is more than one instance of the gamepanel class it will use the same variable instead of individual and final keyword allows us not to modify midway through.
	static final int GAME_HEIGHT=(int)(GAME_WIDTH * (0.6)); //Ping Pong table measurements are 6ft to 10ft. NOTE: DO NOT PUT 6/10  cannot put division must put int otherwise won't display panel.
	static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH,GAME_HEIGHT);
	static final int BALL_DIAMETER = 20;
	static final int PADDLE_WIDTH = 25;
	static final int PADDLE_HEIGHT = 100;
	Thread Game_Thread;
	Image image;
	Graphics graphics;
	Random random;
	Paddle paddle1; //Player 1
	Paddle paddle2; //Player 2
	Ball ball;
	Score score;
	
	Game_Panel(){
		newPaddles();
		newBall();
		score = new Score(GAME_WIDTH,GAME_HEIGHT);
		this.setFocusable(true);
		this.addKeyListener(new AL()); 
		this.setPreferredSize(SCREEN_SIZE); // Uses the ping pong table measurements
		
		Game_Thread = new Thread(this);
		Game_Thread.start();
	}

	
	public void newBall() {
		random = new Random(); // Randomizes the ball position in this case
		ball = new Ball((GAME_WIDTH/2)-(BALL_DIAMETER/2),random.nextInt(GAME_HEIGHT-BALL_DIAMETER),BALL_DIAMETER,BALL_DIAMETER);  //[(GAME_WIDTH/2)-(BALL_DIAMETER/2) sets the ball in the middle of the x axis], [random.nextInt(GAME_HEIGHT-BALL_DIAMETER) sets the ball for the y axist]
	}
	public void newPaddles() {       // declares position of the 
		paddle1 = new Paddle(0,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,1); // Declares positions of the Paddles on the GameFrame, places on the paddle on the x position left hand side
		paddle2 = new Paddle(GAME_WIDTH-PADDLE_WIDTH,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,2); // Remember Id=1 is player 1 and Id=2 is player 2
	}
	public void paint(Graphics g) {
		image = createImage(getWidth(),getHeight());
		graphics = image.getGraphics();
		draw(graphics);
		g.drawImage(image,0,0,this);  // Want to pass image and the X coordinate which is '0' and the Y coordinate which is '0' and the Game panel class
	}
	public void draw(Graphics g) {
		paddle1.draw(g);
		paddle2.draw(g);
		ball.draw(g);
		score.draw(g);
        Toolkit.getDefaultToolkit().sync(); // Helps with the animation and the pixels depending on the screen size of the user running the game

	}
	public void move() { // Paddles lag when they move on the screen so we call Both of the paddles.
		paddle1.move();
	//	paddle2.move(); // TESTING: Paddle 2 will lag and paddle 1 won't
		paddle2.move();
		ball.move();
	}
	public void checkCollision()  // Ensures the Paddles and balls are kept within the frames border.
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
		//stops paddles from going off the screen.
		if(paddle1.y<=0)
			paddle1.y=0;
		if(paddle1.y >= (GAME_HEIGHT-PADDLE_HEIGHT)) // When paddle 1 is moving up and down
			paddle1.y = GAME_HEIGHT-PADDLE_HEIGHT; // Ensures the paddle is kept within this boundary borders
		if(paddle2.y<=0)
			paddle2.y=0;
		if(paddle2.y >= (GAME_HEIGHT-PADDLE_HEIGHT))
			paddle2.y = GAME_HEIGHT-PADDLE_HEIGHT;
		//give a player 1 point and creates new paddles & ball
              int s=1;
              for( int i=0; s<i;i++) 
			  {
               i++;
			  }

		if(ball.x <=0) {
			score.Score_Player2++;
			newPaddles();  // Everytime a player scores the paddle is repositioned to the middle
			newBall(); // ball is randomly
			System.out.println("Score of Player 2: "+score.Score_Player2); 
		}
		if(ball.x >= GAME_WIDTH-BALL_DIAMETER) {
			score.Score_Player1++;
			newPaddles();
			newBall();
			System.out.println("Score of Player 1: "+score.Score_Player1);
		}
	}

	public void run() {
		//game loop
		long lastTime = System.nanoTime();
		double amountOfTicks =60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		while(true) {
			long now = System.nanoTime();
			delta += (now -lastTime)/ns;
			lastTime = now;
			if(delta >=1) {
				move();   // This helps with the lags so after each iteration it refreshes allowing the movement of the paddles to be more smooth
				checkCollision();
				repaint();
				delta--;
				//System.out.println("test");
			}
		}
	}

	public class AL extends KeyAdapter{
		public void keyPressed(KeyEvent e) 
		{
			paddle1.keyPressed(e); // Allows Player one to move the paddle vertically on the GameFrame
			paddle2.keyPressed(e); // Allows Player two to move the paddle vertically on the GameFrame
		} 
		public void keyReleased(KeyEvent e) 
		{
			paddle1.keyReleased(e);
			paddle2.keyReleased(e);
		}
	}
}


