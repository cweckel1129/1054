import java.util.*;
import java.io.*;
import java.awt.Graphics;
import javax.swing.*;
import java.awt.Color;
public class GameObject {

   //create protected xPosition, yPosition, and Color c variables
   protected int xPosition;
   protected int yPosition;
   protected Color c;
   //create constructor with x_in, y_in, and Color c_in
   public GameObject(int x_in,int y_in,Color c_in)
   {
      //set the xPosition, yPosition, and Color c to equal x_in, y_in, and Color c_in
      xPosition = x_in;
      yPosition = y_in;
      c = c_in;
   }

   //method to get x
   public int getX()
   {
      return xPosition;
   }
   
   //method to get y
   public int getY()
   {
      return yPosition;
   }
   
   //set y method mutator to set yPosition equal to y_in
   public void setY(int y_in)
   {
      yPosition = y_in;
   }
   
   //set x method mutator to set xPosition equal to x_in
   public void setX(int x_in)
   {
      xPosition = x_in;
   }
   
   //collides method that takes in a game object as the parameters
   public boolean collides(GameObject otherGameObject)
   {
      //if the game object passed into parameter equals null then return false
      if(otherGameObject==null)
      {
         return false;
      }
      
      /*if the object that is referred to with "this" equals the game object passed
      into the parameter then return false*/
      if(this.equals(otherGameObject))
         {
            return false;
         }
         
         /*if neither of the above if statements are true then this else statement
         will execute*/
         else
         {
            //create all the variables for topthis etc., topother
            int topthis = yPosition-12;
            int bottomthis = yPosition+12;
            int leftthis = xPosition-12;
            int rightthis = xPosition+12;
            int topother = otherGameObject.yPosition-12;
            int bottomother = otherGameObject.yPosition+12;
            int leftother = otherGameObject.xPosition-12;
            int rightother = otherGameObject.xPosition+12;
            
            //return true or false if not the values of bottomthis less than topother etc.
            return !((bottomthis<topother)||(topthis>bottomother)||(leftthis>rightother)||(rightthis<leftother));
         }
       }
       
       //draw method to set the color and fill rect with xPosition and yPosition
       public void draw(Graphics g)
       {
         g.setColor(c);
         g.fillRect(xPosition-12,yPosition-12,25,25); 
       }
     
   }