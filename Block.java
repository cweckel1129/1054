import java.util.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class Block extends GameObject {

   //create constructor for block with parameters of x and y
   Block(int x_in,int y_in)
   {
      /*use super to call constructor from GameObject with
      x, y, and a blue color in the parameters*/
      super(x_in,y_in,Color.BLUE);
   }
}