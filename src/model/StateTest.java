package model;

public class StateTest {

  private int posY, angle, arm;
  private boolean magnet;
  
  public StateTest(int posY, int angle, int arm, boolean magnet) {
    this.posY = posY;
    this.angle = angle;
    this.arm = arm;
    this.magnet = magnet;
  }

  public int getPosY() {
    return posY;
  }

  public void setPosY(int posY) {
    this.posY = posY;
  }

  public int getAngle() {
    return angle;
  }

  public void setAngle(int angle) {
    this.angle = angle;
  }

  public int getArm() {
    return arm;
  }

  public void setArm(int arm) {
    this.arm = arm;
  }

  public boolean isMagnet() {
    return magnet;
  }

  public void setMagnet(boolean magnet) {
    this.magnet = magnet;
  }
  
}
