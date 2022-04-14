import java.util.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.Timer;
public class GamePanel extends JPanel {
   
   //create variables for x and y, file name, player object, and 2d arraylist
   int xStartPosition;
   int yStartPosition;
   String fileName;
   Player p1;
   ArrayList<ArrayList<GameObject>> twoDArray;
   
   //create up,down,left,right boolean variables
   boolean up;
   boolean down;
   boolean left;
   boolean right;
   //create jump, n variable, tickCounter and the timer variable
   double jump = 0;
   int n = 1;
   int tickCounter = 1;
   Timer t = new Timer(10,new TimeListenerGravity());
   
   //create constructor with size of panel and ask user for file name
   public GamePanel()
   {
      super();
      setSize(800,600);
      //add key listener and set focus to true
      addKeyListener(new KeyBoard1());
      setFocusable(true);
      try
      {
         /*create variable to scan what user entered and create file variable
         from what the user entered*/
         //fileName = scan.nextLine();
         File level = new File("Maze.txt");
         /*create scanner to read from file variable that was created and
         scan for the x position and y position for the player in the file*/
         Scanner scanFile = new Scanner(level);
         xStartPosition = scanFile.nextInt();
         yStartPosition = scanFile.nextInt();
         /*create player object with x position and y position that are
         used in the text file*/
         p1 = new Player(xStartPosition*25+13,yStartPosition*25+13);
         /*create variables for the rows and columns from the file that
         is read in and create blockWidth variable equal to 25*/
         int rows = scanFile.nextInt();
         int columns = scanFile.nextInt();
         int blockWidth = 25;
         
         //create 2d array that contain game objects
         twoDArray = new ArrayList<ArrayList<GameObject>>();
         //add an arraylist of game objects to 2d array
         twoDArray.add(new ArrayList<GameObject>());
         //create currentRow variable as 0 and currentColumn as 0
         int currentRow = 0;
         int currentColumn = 0;
         
         //while loop to scan the file until there is no other ints
         while(scanFile.hasNextInt())
         {
            //num variable will scan for the 0s and 1s
            int num = scanFile.nextInt();
            //create color variable and blockObject variable
            Color c;
            GameObject blockObject;
            
            /*if the number in the file is 1 it will create a red blockObject 
            (the red block is the player)
            at the location of the currentColumn multiplied by blockWidth+13
            For the y position it will use the value of currentRow multiplied by
            blockWidth + 13*/
            if(num == 1)
            {
               c = Color.RED;
               blockObject = new Block(currentColumn*blockWidth+13,currentRow*blockWidth+13);
            }
            
            /*if the number in the file is 2 it will create a gray blockObject
            at the location of the currentColumn multiplied by blockWidth+13
            For the y position it will use the value of currentRow multiplied by
            blockWidth + 13*/
            else if(num == 2)
            {
               c = Color.GRAY;
               blockObject = new VictoryBlock(currentColumn*blockWidth+13,currentRow*blockWidth+13);
            }
            
            /*if the number in the file is not 1 or 2 it will create a black blockObject
            at the location of the currentColumn multiplied by blockWidth+13
            For the y position it will use the value of currentRow multiplied by
            blockWidth + 13*/
            else
            {
               c = Color.BLACK;
               blockObject = null;
            }
            
            //Block blockObject = new Block(currentRow*blockWidth,currentColumn*blockWidth);
            
            /*use 2d array.get for the current row and add blockObject to the 2d array
            and increase currentColumn by 1*/
            twoDArray.get(currentRow).add(blockObject);
            currentColumn++;
            
            /*if the currentColumn variable equals the columns variable
            then currentRow will increase by 1 and currentColumn will be
            reset to 0 to start the column on the left side again. It will
            also add to the 2d array an array list of gameObjects*/
            if(currentColumn==columns)
            {
               currentRow++;
               currentColumn = 0;
               twoDArray.add(new ArrayList<GameObject>());
            }
         
         }
         //starting the timer here
         t.start();
         /*for(ArrayList<GameObject> list: twoDArray)
         {
            for(GameObject go:list)
            {
               System.out.println("test"+go);
            }
         }*/
      
      }
      catch(FileNotFoundException f)
      {
         System.out.println("File not found");
      }
   }
   
   //create paint component method
   public void paintComponent(Graphics g)
   {
      //setting the collor to the gray and black background
      g.setColor(Color.BLACK);
      g.fillRect(0,0,800,600);
      g.setColor(Color.GRAY);
      g.fillRect(0,0,790,590);
      
      /*for(int i = 0;i<twoDArray.size();i++)
      {
         for(int j = 0;j<twoDArray.get(i).size();j++)
         {*/
         
         /*nested for loop to loop through the game object
         arraylist until it reaches the amount of elements that
         twoDArray contains. The second for loop is looping until
         the game object reaches the amount of elements in list*/
         for(ArrayList<GameObject> list: twoDArray)
         {
            for(GameObject go:list)
            {
               //if game object doesn't equal null it will keep drawing
               if(go != null)
               {
                  go.draw(g);
               }
            }
         }
         p1.draw(g);
                  
      }
      
      //key listener class
   public class KeyBoard1 implements KeyListener 
   {
      public void keyTyped(KeyEvent e) {}
      /*in keyreleased if the user stops pushing down
      w,a, or d then the boolean variables will be false*/
      public void keyReleased(KeyEvent e) 
      {
         if(e.getKeyCode() == KeyEvent.VK_W)
         {
            up=false;
         }    
         if(e.getKeyCode() == KeyEvent.VK_A)
         {
            left = false;
            
         }
         if(e.getKeyCode() == KeyEvent.VK_D)
         {
            right = false;
         }

      }
      /*in keyPressed if the user pushes down
      w,a, or d then the boolean variables will be true*/
      public void keyPressed(KeyEvent e) 
      {
         if(e.getKeyCode() == KeyEvent.VK_W)
         {
            up=true;
         }
         if(e.getKeyCode() == KeyEvent.VK_A)
         {
            left = true;
         }
         if(e.getKeyCode() == KeyEvent.VK_D)
         {
            right = true;
         }
      repaint();
    }
    }
    
    /*time listener implemented in the timer that I created at
    the top of the code and also deals with the gravity inside 
    this action listener*/
    public class TimeListenerGravity implements ActionListener
    {
      /*if the jump variable is greater than 0 it will move the player
      up by 1 until the "i" variable reaches the value jump is at, which is 7*/
      public void actionPerformed(ActionEvent e)
      {
         if(jump>0)
         {
            for(int i = 0;i<jump;i++)
            {
               p1.move(0,-1,twoDArray);
            }
            /*after the player reaches the maximum height of the jump it will
            decrease jump and make the player fall down*/
            jump-=0.1;
            
            /*if the player hits the ceiling it will reset the player's jump
            variable and make them stop all movement*/
            if(p1.hitCeiling(twoDArray))
            {
               jump = 0;
            }
         }
         
         /*else statement if the jump variable isn't greater than 0 and increase
         tick counter variable*/
         else
         {
         tickCounter++;
         
         /*if the 2d array doesn't equal null then the for loop will loop once
         since I created the n variable to equal 1*/
         if(twoDArray!=null)
         {
            for(int i=0;i<n;i++)
            {
               /*if the player isn't on the ground(which would be if the player
               is in the air) then it will move the player down*/
               if(!p1.isOnGround(twoDArray))
               {
                  p1.move(0,1,twoDArray);
                  //p1.setY(p1.getY()+1);
               }
               /*else statement if the 2d array does equal null, it will set n
               back to equal 1*/
               else
               {
                  n=1;
               }
            }
         
            /*if the tick counter equals 20 then n will increase by 1 and 
            tickCounter will reset to 1*/
            if(tickCounter==20)
            {
               n++;
               tickCounter = 1;
            }
         }
        }
        
        /*if the player collides with the victory block then the message will
        pop up saying that the level is done*/
        if(p1.checkCollideWithVictoryBlock(twoDArray))
        {
          JOptionPane.showMessageDialog(getParent(), "Finish");
          System.exit(1);
        }
         
         /*if the W key is pressed and the up variable is true and the player
         is on the ground then it will changet the jump value to 7*/
         if(up)
         {
            if(p1.isOnGround(twoDArray))
            {
               jump = 7;
            }
            //p1.setY(p1.getY()-1);
            //p1.move(0,1,twoDArray);
            //if(jump<0)
         }
         /*if(down)
         {
            p1.setY(p1.getY()+1);
         }*/
         
         /*if the A key is pressed and the left variable is true then it will
         move the player to the left as long as the button is held*/
         if(left)
         {
            //System.out.println(p1.collides(twoDArray));
            p1.move(-1,0,twoDArray);
            //p1.setX(p1.getX()-1);
         }
         
         /*if the D key is pressed and the right variable is true then it will
         move the player to the right as long as the button is held*/
         if(right)
         {
            //System.out.println(p1.collides(twoDArray));
            p1.move(1,0,twoDArray);
            //p1.setX(p1.getX()+1);
         }
         repaint();
         
       }
     } 
}