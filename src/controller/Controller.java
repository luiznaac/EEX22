package controller;

import java.util.ArrayList;

import application.ThreadController;
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
    Runnable threadController = new ThreadController(moveThreads);
    Thread t = new Thread(threadController);
    t.start();
    move();
  }
  
  public void move() {
    rail.move(0, 100);
    arm.move(45);
    rail.move(0, 150);
    arm.move(90);
  }
  
}
