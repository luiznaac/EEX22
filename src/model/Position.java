package model;

import java.awt.Rectangle;

public class Position {

  private int x, y, count;
  
  public Position(int x, int y) {
    this.x = x;
    this.y = y;
    count = 1;
  }
  
  public boolean isTheSame(Position pos) {
    //System.out.print("(" + x + ", " + y + ")" + "(" + pos.getX() + ", " + pos.getY() + ") ");
    Rectangle rect1 = new Rectangle(100, 100, x, y);
    Rectangle rect2 = new Rectangle(100, 100, pos.getX(), pos.getY());
    if(rect1.intersects(rect2)) {
      count++;
      x = (x + pos.getX())/2;
      y = (y + pos.getY())/2;
      return true;
    }
    else return false;
  }
  
  public int getX() {
    return x;
  }
  
  public int getY() {
    return y;
  }
  
  public int getCount() {
    return count;
  }
  
}
