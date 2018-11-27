package model;

import java.io.Serializable;

public class Coin implements Serializable {

  private int originTrail, originBase, destTrail, destBase;

  public Coin(int originTrail, int originBase, int destTrail, int destBase) {
    this.originTrail = originTrail;
    this.originBase = originBase;
    this.destTrail = destTrail;
    this.destBase = destBase;
  }

  public int getOriginTrail() {
    return originTrail;
  }

  public void setOriginTrail(int originTrail) {
    this.originTrail = originTrail;
  }

  public int getOriginBase() {
    return originBase;
  }

  public void setOriginBase(int originBase) {
    this.originBase = originBase;
  }

  public int getDestTrail() {
    return destTrail;
  }

  public void setDestTrail(int destTrail) {
    this.destTrail = destTrail;
  }

  public int getDestBase() {
    return destBase;
  }

  public void setDestBase(int destBase) {
    this.destBase = destBase;
  }
  
}
