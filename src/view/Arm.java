package view;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;

import application.MoveArm;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Arm extends Group {
  
  private int ARM_LENGTH = 194;
  private int LENGTH_WITH_STROKE;
  private int CORE;
  private int STROKE = 36;
  private int angle;
  private Line line;
  private ArrayList<Thread> moveThreads;
  
  public Arm(int CORE, int posX, ArrayList<Thread> moveThreads) {
    super();
    this.moveThreads = moveThreads;
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
  
  public void move(int angle, Lock lock) {
    Runnable moveArm = new MoveArm(this, angle, lock);
    Thread t = new Thread(moveArm);
    moveThreads.add(t);
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
