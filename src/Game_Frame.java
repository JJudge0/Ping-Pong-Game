package src;
import java.awt.*;
import javax.swing.*;
public class Game_Frame extends JFrame {
    
Game_Panel Panel;

public Game_Frame()
{
    Panel = new Game_Panel();
    this.add(Panel);
    this.setTitle("Ping Pong Game by JJudge0");
    this.setResizable(false); // Window cannot be resized by user because contents within the game won't be resized to fit the frame.
    this.setBackground(Color.black); // Background Color for the Game Frame.
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.pack();// NOTE: Allows the GameFrame to be adjusted to the Game Panel  so doesn't need to be resized.
    this.setVisible(true); // When the game  is running you can put it too visible 
    this.setLocationRelativeTo(null); //Appears on the middle of the screen comment out later.
}



}
