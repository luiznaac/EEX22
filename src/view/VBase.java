package view;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 * VArm
 * 
 * @author Luiz
 */
public class VBase extends Group {
  
  private int ARM_LENGTH = 194;
  private int LENGTH_WITH_STROKE;
  private int CORE;
  private int STROKE = 36;
  private int angle;
  private Line line;
  
  public VBase(int CORE, int posX) {
    super();
    this.CORE = CORE;
    angle = 90;
    LENGTH_WITH_STROKE = ARM_LENGTH - STROKE;
    line = new Line();
    line.setStroke(Color.DARKKHAKI);
    line.setStrokeWidth(STROKE);
    line.setStartX(posX - STROKE/2); // Stroke aumenta o tamanho da linha
    line.setStartY(CORE);
    line.setEndX(posX - LENGTH_WITH_STROKE);
    line.setEndY(CORE);
    getChildren().addAll(line);
  }
  
  public int getAngle() {
    return angle;
  }

  public void setAngle(int angle) {
    this.angle = angle;
    line.setEndX(line.getStartX() - LENGTH_WITH_STROKE*Math.sin(Math.toRadians(angle)));
    line.setEndY(line.getStartY() + LENGTH_WITH_STROKE*Math.cos(Math.toRadians(angle)));
  }
  
  public void setPosY(int posY) {
    line.setStartY(posY + CORE);
    line.setEndY(line.getStartY() + LENGTH_WITH_STROKE*Math.cos(Math.toRadians(angle)));
  }
  
}
