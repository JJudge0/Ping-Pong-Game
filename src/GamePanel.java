package src;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable{

	static final int GAME_WIDTH = 1000; //Note: Must be static because if there is more than one instance of the gamepanel class it will use the same variable instead of individual and final keyword allows us not to modify midway through.
	static final int GAME_HEIGHT=(int)(GAME_WIDTH * (0.6)); //changing to 6ft to 10ft  cannot put division must put int otherwise won't display panel.
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
	
	GamePanel(){
		newPaddles();
		newBall();
		score = new Score(GAME_WIDTH,GAME_HEIGHT);
		this.setFocusable(true);
		this.addKeyListener(new AL());
		this.setPreferredSize(SCREEN_SIZE);
		
		Game_Thread = new Thread(this);
		Game_Thread.start();
	}
	
	public void newBall() {
		random = new Random();
		ball = new Ball((GAME_WIDTH/2)-(BALL_DIAMETER/2),random.nextInt(GAME_HEIGHT-BALL_DIAMETER),BALL_DIAMETER,BALL_DIAMETER);
	}
	public void newPaddles() {
		paddle1 = new Paddle(0,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,1);
		paddle2 = new Paddle(GAME_WIDTH-PADDLE_WIDTH,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,2);
	}
	public void paint(Graphics g) {
		image = createImage(getWidth(),getHeight());
		graphics = image.getGraphics();
		draw(graphics);
		g.drawImage(image,0,0,this);
	}
	public void draw(Graphics g) {
		paddle1.draw(g);
		paddle2.draw(g);
		ball.draw(g);
		score.draw(g);
         Toolkit.getDefaultToolkit().sync(); // I forgot to add this line of code in the video, it helps with the animation

	}
	public void move() {
		paddle1.move();
		paddle2.move();
		ball.move();
	}
	public void checkCollision() {
		
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
				move();
				checkCollision();
				repaint();
				delta--;
				//System.out.println("test");
			}
		}
	}
	public class AL extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
			paddle1.keyPressed(e);
			paddle2.keyPressed(e);
		}
		public void keyReleased(KeyEvent e) {
			paddle1.keyReleased(e);
			paddle2.keyReleased(e);
		}
	}
}


