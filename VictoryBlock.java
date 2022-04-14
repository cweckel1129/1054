import java.util.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class VictoryBlock extends GameObject {

   //create constructor for victory block with parameters of x and y
   VictoryBlock(int x_in,int y_in)
   {
      /*use super to call constructor from GameObject with
      x, y, and a green color in the parameters and also
      assigning the c here in this class to equal c*/
      super(x_in,y_in,Color.GREEN);
      this.c = c;
   }
}