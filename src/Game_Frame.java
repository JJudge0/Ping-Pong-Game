package src;
import java.awt.*;
import javax.swing.*;
public class Game_Frame extends JFrame {
    
GamePanel Panel;

public Game_Frame()
{
    Panel = new GamePanel();
    this.add(Panel);
    this.setTitle("Ping Pong Game by JJudge0");
    this.setResizable(false); // Window cannot be resized by user because contents within the game won't be resized to fit the frame.
    this.setBackground(Color.black); // Background Color
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.pack();// NOTE: Allows the Game Panel class to be adjusted on the game frame so it's not to big or small it adjusts accordingly.
    this.setVisible(true); // When the game  is running you can put it too visible 
   // this.setLocationRelativeTo(null); //Appears on the middle of the screen comment out later.
}



}
