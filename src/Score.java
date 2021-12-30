package src;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
public class Score extends Rectangle  {
    

    static int GAME_WIDTH;
    static int GAME_HEIGHT;
    int Score_Player1; //Holds score of player1
    int Score_Player2; //Holds score of player2

    public  Score(int GAME_WIDTH, int GAME_HEIGHT) //score
    {

    Score.GAME_WIDTH = GAME_WIDTH;
    Score.GAME_HEIGHT = GAME_HEIGHT;
    }
    public void draw(Graphics g)   // Displaying on the game frame.
    {
    //==============================For the Scores==============================
    g.setColor(Color.WHITE);
    g.setFont(new Font("Consolas",Font.BOLD,60));
    //==============================For the Scores==============================

    g.drawLine(GAME_WIDTH/2, 0, GAME_WIDTH/2, GAME_HEIGHT); // Draws the line in the middle of the game
   
    g.drawString(String.valueOf(Score_Player1/10)+String.valueOf(Score_Player1%10), (GAME_WIDTH/2)-100, 300); // Draws scores on the screen for Player 1 [Where '300' is put '50' this should display at the top of the screen]
    g.drawString(String.valueOf(Score_Player2/10)+String.valueOf(Score_Player2%10), (GAME_WIDTH/2)+30, 300); // Draws scores on the screen for Player 2 [Where '300' is put '50' this should display at the top of the screen]
    // PUT "%10" so it displays double digits    [300] is middle of the screen, [50] is top of the screen, [590] is for bottom of the screen
}

}


