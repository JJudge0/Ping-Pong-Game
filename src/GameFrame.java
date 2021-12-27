package src;
import java.awt.*;
import javax.swing.*;
public class GameFrame extends JFrame {
    
GamePanel Panel;

public GameFrame()
{
    Panel = new GamePanel();
    this.add(Panel);
    this.setTitle("Ping Pong Game by JJudge0");
    this.setResizable(true); // Window can be resized by user
    this.setBackground(Color.black); // Background Color
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.pack();// NOTE: Allows the Game Panel class to be adjusted on the game frame so it's not to big or small it adjusts accordingly.
    this.setVisible(true); // When the game  is running you can put it too visible 
   // this.setLocationRelativeTo(null); //Appears on the middle of the screen comment out later.
}



}
