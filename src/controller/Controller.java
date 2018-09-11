package controller;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import application.GlobalLock;
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
    Random rand = new Random();
    GlobalLock.lock();
    for(int i = 0 ; i < 15 ; i++) {
      arm.move(rand.nextInt(90));
      rail.move(0, rand.nextInt(300));
    }
    GlobalLock.unlock();
  }
  
}
