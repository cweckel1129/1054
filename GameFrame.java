import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.awt.Color;
public class GameFrame extends JFrame {
    
    //constructor to call super and create container
    public GameFrame()
    {
      super();
      Container containerVariable = getContentPane();
      
      //add panel to frame
      GamePanel panel = new GamePanel();
      containerVariable.add(new GamePanel());
      setSize(800,625);
      setVisible(true);
      panel.requestFocus();
    }
    public static void main(String[] args) 
     {
      GameFrame frame = new GameFrame();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}