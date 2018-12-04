package view;


import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

/**Representa graficamente o trilho
 * 
 * @author Luiz
 */
public class VRail extends Group {

  private int RAIL_LENGTH = 500;
  private int GAP = 80;
  private int BASEX = 100;
  private int BASEY = 80;
  private int CORE = 40;
  private Rectangle base;
  private int posX, posY, sizeX;
  private VBase arm;
  
  public VRail(int sizeX, int posY) {
    super();
    posX = 0;
    this.posY = posY;
    this.sizeX = sizeX;
    Line line = new Line();
    line.setStroke(Color.GRAY);
    line.setStrokeWidth(12);
    line.setStartX((sizeX - GAP)/2);
    line.setStartY(0);
    line.setEndX((sizeX - GAP)/2);
    line.setEndY(RAIL_LENGTH);
    Line line2 = new Line();
    line2.setStroke(Color.GRAY);
    line2.setStrokeWidth(12);
    line2.setStartX((sizeX + GAP)/2);
    line2.setStartY(0);
    line2.setEndX((sizeX + GAP)/2);
    line2.setEndY(RAIL_LENGTH);
    base = new Rectangle();
    base.setFill(Color.DARKGOLDENROD);
    base.setX((sizeX - GAP)/2 - 10);
    base.setY(0);
    base.setWidth(BASEX);
    base.setHeight(BASEY);
    getChildren().addAll(line, line2, base);
  }
  
  public void attachArm(VBase arm) {
    getChildren().add(arm);
    this.arm = arm;
  }
  
  public int getPosX() {
    return posX;
  }

  public int getPosY() {
    return posY;
  }

  public void setPosY(int posY) {
    this.posY = posY;
    base.setY((posY*RAIL_LENGTH-BASEY)/1100 + 1);
    arm.setPosY((posY*RAIL_LENGTH-BASEY)/1100 + 1);
  }
  
  public int getCORE() {
    return CORE;
  }
  
  public int getCorePositionX() {
    return (sizeX - GAP)/2 - 10 + CORE;
  }
  
}
