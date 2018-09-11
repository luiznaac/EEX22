package controller;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import application.MoveController;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import view.Arm;
import view.Rail;

public class Controller {
  
  @FXML Pane pnGraphic;
  private Rail rail;
  private Arm arm;
  
  @FXML
  public void initialize() {
    ArrayList<Thread> moveThreads = new ArrayList<>();
    rail = new Rail(500, moveThreads);
    arm = new Arm(rail.getCORE(), rail.getCorePositionX(), moveThreads);
    rail.attachArm(arm);
    pnGraphic.getChildren().addAll(rail);
    Runnable threadController = new MoveController(moveThreads);
    Thread t = new Thread(threadController);
    t.start();
    move();
  }
  
  public void move() {
    Lock lock = new ReentrantLock();  
    arm.move(0, lock);
    rail.move(0, 200, lock);
    arm.move(45, lock);
    rail.move(0, 100, lock);
    arm.move(90, lock);
    rail.move(0, 450, lock);
    arm.move(180, lock);
    rail.move(0, 100, lock);
  }
  
}
