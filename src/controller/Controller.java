package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import model.Coin;
import remote.CommunicationInterface;
import view.VBase;
import view.VMoveBase;
import view.VMoveRail;
import view.VRail;

/**
 * Controller responsável por inicializar a visualização gráfica
 * do trilho e da garra e fazer a ponte entre o estado físico
 * e a interface gráfica
 * 
 * @author Luiz
 */
public class Controller {
  
  @FXML Pane pnGraphic;
  @FXML TextField tfTrail;
  @FXML TextField tfBase;
  @FXML TextField tfTrailDes;
  @FXML TextField tfBaseDes;
  @FXML TextField tfArm;
  @FXML Button btCompute;
  @FXML Button btComputePwm;
  @FXML Button btMagnet;
  private VRail rail;
  private VBase arm;
  private CommunicationInterface com;
  
  @FXML
  public void initialize() {
    
  }
  
  public void link(CommunicationInterface com) {
    this.com = com;
    try {
      rail = new VRail(500, com.getState().getPosY());
      arm = new VBase(rail.getCORE(), rail.getCorePositionX());
      rail.attachArm(arm);
      pnGraphic.getChildren().addAll(rail);
      Runnable moveRail = new VMoveRail(com, rail);
      Thread tRail = new Thread(moveRail);
      tRail.start();
      Runnable moveArm = new VMoveBase(com, arm);
      Thread tArm = new Thread(moveArm);
      tArm.start();
    } catch(Exception E) {
      E.printStackTrace();
    }
  }
  
  @FXML
  public void compute() {
    Coin newCoin = new Coin(Integer.parseInt(tfTrail.getText()), Integer.parseInt(tfBase.getText()), Integer.parseInt(tfTrailDes.getText()), Integer.parseInt(tfBaseDes.getText()));
    try {
      com.sendCoin(newCoin);
    } catch(Exception E) {
      E.printStackTrace();
    }
  }
  
  @FXML
  public void magnet() {
    try {
      com.toggleMagnet();
    } catch(Exception E) {
      E.printStackTrace();
    }
  }
  
  @FXML
  public void computePwm() {
    try {
      com.sendPwm(Integer.parseInt(tfArm.getText()));
    } catch(Exception E) {
      E.printStackTrace();
    }
  }
  
  @FXML
  public void enter(KeyEvent event) {
    if(event.getCode() == KeyCode.ENTER) {
      compute();
      event.consume();
    }
  }
  
}
