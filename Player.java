import java.util.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class Player extends GameObject {

   //create constructor for player with parameters of x and y
   Player(int x_in,int y_in)
   {
      /*use super to call constructor from GameObject with
      x, y, and a red color in the parameters*/
      super(x_in,y_in,Color.RED);
   }

   /*method for is on ground will take a 2d array for the parameters
   and set a boolean variable result to false and create a y variable
   and assign to the getY method. Then it calls the setY method with
   y+1 in the parameters.*/
   public boolean isOnGround(ArrayList<ArrayList<GameObject>> data)
   {
      boolean result = false;
      int y = getY();
      setY(y+1);
      
      /*if the player collides with the 2d array then the boolean variable
      result will be assigned to the value "true"*/
      if(collides(data))
      {
         result = true;
      }
      
      /*here the setY method is called with y in the parameters
      and at the end of the method it returns the value of result*/
      setY(y);
      
      return result;
   }
   
   /*method for hitting the ceiling will take a 2d array for the parameters
   and set a boolean variable ifHitCeling to false and create a y variable
   and assign to the getY method. Then it calls the setY method with
   y+1 in the parameters.*/
   public boolean hitCeiling(ArrayList<ArrayList<GameObject>> data)
   {
      boolean ifHitCeiling = false;
      int y = getY();
      setY(y-1);
      
      /*if the player collides with the 2d array then the boolean variable
      ifHitCeling will be assigned to the value "true"*/
      if(collides(data))
      {
         ifHitCeiling = true;
      }
      /*here the setY method is called with y in the parameters
      and at the end of the method it returns the value of ifHitCeiling*/
      setY(y);
      
      return ifHitCeiling;
   }
   
   /*method for checking if the player
   collides with the victory block. The
   method will take a 2d array for the parameters
   and set a boolean variable ifCollideWithVictoryBlock 
   to false and create a y1 variable
   and assign to the getY method. Then it calls the setY method with
   y1-1 in the parameters.*/
   public boolean checkCollideWithVictoryBlock(ArrayList<ArrayList<GameObject>> data)
   {
      boolean ifCollideWithVictoryBlock = false;
      int y1 = getY();
      setY(y1-1);
      
      /*if the player touches the victory block with the 2d array then the boolean variable
      ifCollideWithVictoryBlock will be assigned to the value "true" and
      it will use the setY method and have y1 in the parameters*/
      if(touchVictoryBlock(data))
      {
         ifCollideWithVictoryBlock = true;
      }
      setY(y1);
      
      /*create a y2 variable
      and assign to the getY method. Then it calls the setY method with
      y2+1 in the parameters.*/
      int y2 = getY();
      setY(y2+1);
      
      /*if the player touches the victory block with the 2d array then the boolean variable
      ifCollideWithVictoryBlock will be assigned to the value "true" and
      it will use the setY method and have y2 in the parameters*/
      if(touchVictoryBlock(data))
      {
         ifCollideWithVictoryBlock = true;
      }
      setY(y2);
      
      /*create a x1 variable
      and assign to the getX method. Then it calls the setX method with
      x1-1 in the parameters.*/
      int x1 = getX();
      setX(x1-1);
      
      /*if the player touches the victory block with the 2d array then the boolean variable
      ifCollideWithVictoryBlock will be assigned to the value "true" and
      it will use the setX method and have x1 in the parameters*/
      if(touchVictoryBlock(data))
      {
         ifCollideWithVictoryBlock = true;
      }
      setX(x1);
      
      
      /*create a x2 variable
      and assign to the getX method. Then it calls the setX method with
      x2+1 in the parameters.*/
      int x2 = getX();
      setX(x2+1);
      
      /*if the player touches the victory block with the 2d array then the boolean variable
      ifCollideWithVictoryBlock will be assigned to the value "true" and
      it will use the setX method and have x2 in the parameters*/
      if(touchVictoryBlock(data))
      {
         ifCollideWithVictoryBlock = true;
      }
      setX(x2);
      
      //returns ifCollideWithVictoryBlock here
      return ifCollideWithVictoryBlock;
   }
   
   /*method to check if the player collides with the victory block.
   The method will take in a 2d array of Game Objects and set the 
   playerXPosition to getX()-13/25 and set playerYPosition to 
   (getY()-13)/25. In the first nested for loop it will loop until j
   is less than or equal to the playerXPosition+1. For the second for
   loop it will loop until i is less than or equal to the playerYPosition+1.*/
   public boolean touchVictoryBlock(ArrayList<ArrayList<GameObject>> data)
   {
      int playerXPosition = (getX()-13)/25;
      int playerYPosition = (getY()-13)/25;
         for(int i = playerXPosition-1;i<=playerXPosition+1;i++)
         {
            for(int j = playerYPosition-1;j<=playerYPosition+1;j++)
            {
               /*if j >= 0 and j < the size of the "data" 2d array then
               the if statement will excecute*/
               if(j>=0&&j<data.size())
               {
                  /*if i >= 0 and i < the size of the "data" 2d array with the
                  .get(j) method then
                  the if statement will excecute*/
                  if(i>=0&&i<data.get(j).size())
                  {
                     /*since super is used here, it is using the Game Object
                     class and using .collides of data.get(j) and .get(i)
                     to check if the staement is true*/
                     if(super.collides(data.get(j).get(i)))
                     {
                         /*if the value of the 2d array "data" of data.get(j)
                         and .get(i) is an isntance of the Game Object
                         "VictoryBlock" then the if statement will return true*/
                         if(data.get(j).get(i) instanceof VictoryBlock)
                         {
                            return true;
                         }
                         //System.out.println("x"+playerXPosition);
                         //System.out.println("y"+playerYPosition);
                         //System.out.println(data.get(j).get(i));
                     }
                  }
               }
            }
         }
         /*if one or none of the if statements are true then the method
         will return false*/
         return false;
       }
   
   //method for moving the player that takes in move1, move2, and 2d array
   public boolean move(int move1, int move2,ArrayList<ArrayList<GameObject>> data)
   {
      /*create xPosition to equal getX() and yPosition to equal getY() and create the
      tryXPosition which equals getX()+move1 and tryYPosition equals getY()+move2
      so then the player can move left and right*/
      int xPosition = getX();
      int yPosition = getY();
      int tryXPosition = getX()+move1;
      int tryYPosition = getY()+move2;
      /*here is using the setX method and inserting the tryXPosition variable
      as the parameters and using the setY method and inserting the tryYPosition variable
      as the parameters*/
      setX(tryXPosition);
      setY(tryYPosition);
      
      /*if the player collides with any Game Object in the 2d array then it will
      use setX(xPosition) and same with setY(yPosition) so that when the player runs
      into a wall it will reset the players position so the player won't move through the wall*/
      if(collides(data))
      {
         setX(xPosition);
         setY(yPosition);
         return false;
      }
      
      //if the player doesn't collide with a wall then the move method will return true
      else
      {
         return true;
      }
   }
   
   /*method to check if the player collides with any game object that is in the 2d array
   in the parameters the 2d array is passed in*/ 
   /* it sets the playerXPosition to getX()-13/25 and set playerYPosition to 
   (getY()-13)/25. In the first nested for loop it will loop until j
   is less than or equal to the playerXPosition+1. For the second for
   loop it will loop until i is less than or equal to the playerYPosition+1.*/
   public boolean collides(ArrayList<ArrayList<GameObject>> data)
   {
      int playerXPosition = (getX()-13)/25;
      int playerYPosition = (getY()-13)/25;
         for(int i = playerXPosition-1;i<=playerXPosition+1;i++)
         {
            for(int j = playerYPosition-1;j<=playerYPosition+1;j++)
            {
               /*if j >= 0 and j < the size of the "data" 2d array then
               the if statement will excecute*/
               if(j>=0&&j<data.size())
               {
                  /*if i >= 0 and i < the size of the "data" 2d array with the
                  .get(j) method then
                  the if statement will excecute*/
                  if(i>=0&&i<data.get(j).size())
                  {
                     /*since super is used here, it is using the Game Object
                     class and using .collides of data.get(j) and .get(i)
                     to check if the staement is true*/
                     if(super.collides(data.get(j).get(i)))
                     {
                         //System.out.println("x"+playerXPosition);
                         //System.out.println("y"+playerYPosition);
                         //System.out.println(data.get(j).get(i));
                         
                         /*returns true if the game object collides with the value
                         the 2d array.get(j).get(i)*/
                         return true; 
                     }
                  }
               }
            }
         }
         /*if one or none of the if statements are true then the method
         will return false*/
         return false;
       }
    }