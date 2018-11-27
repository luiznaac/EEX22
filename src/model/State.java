package model;

import java.io.Serializable;

import application.MotorArm;
import application.MotorBase;
import application.MotorRail;
import gpio.Magnet;

public class State implements Serializable {

  private int posY, angle, arm;
  private boolean magnet;
  
  public State(int posY, int angle, int arm, boolean magnet) {
    this.posY = posY;
    this.angle = angle;
    this.arm = arm;
    this.magnet = magnet;
  }
  
  public State(int posY, int angle) {
    this.posY = posY;
    this.angle = angle;
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
  
  public void setState(State state) {
    this.posY = state.getPosY();
    this.angle = state.getAngle();
    this.arm = state.getArm();
    this.magnet = state.isMagnet();
  }
  
  public void changeArm(int pwm) {
    Runnable motorArm = new MotorArm(this, pwm);
    Thread tArm = new Thread(motorArm);
    tArm.start();
  }
  
  public void changeMagnet() {
    Magnet.toggle();
  }
  
  public boolean changeState(State state) {
    try {
      Runnable motorRail = new MotorRail(this, state.getPosY());
      Thread tRail = new Thread(motorRail);
      tRail.start();
      Runnable motorBase = new MotorBase(this, state.getAngle());
      Thread tBase = new Thread(motorBase);
      tBase.start();
      tRail.join();
      tBase.join();
      Runnable motorArm;
      if(state.isMagnet())
        motorArm = new MotorArm(this, 140);
      else
        motorArm = new MotorArm(this, 160);
      Thread tArm = new Thread(motorArm);
      tArm.start();
      tArm.join();
      Thread.sleep(100);
      Magnet.toggle();
      Thread.sleep(900);
      this.setMagnet(!this.isMagnet());
      Runnable motorArm2 = new MotorArm(this, 260);
      Thread tArm2 = new Thread(motorArm2);
      tArm2.start();
      tArm2.join();
    } catch(Exception E) {
      E.printStackTrace();
    }
    System.out.println("Indo para posição default.");
    return defaultState();
  }
  
  public boolean defaultState() {
    try {
      Runnable motorArm = new MotorArm(this, 260);
      Thread tArm = new Thread(motorArm);
      tArm.start();
      tArm.join();
      Runnable motorRail = new MotorRail(this, 0);
      Thread tRail = new Thread(motorRail);
      tRail.start();
      Runnable motorBase = new MotorBase(this, 258);
      Thread tBase = new Thread(motorBase);
      tBase.start();
      tRail.join();
      tBase.join();
      System.out.println("Na posição default.");
      Thread.sleep(1000);
      return true;
    } catch(Exception E) {
      E.printStackTrace();
      return false;
    }
  }
  
}
