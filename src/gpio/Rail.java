package gpio;

import com.pi4j.component.motor.EasyDriver;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;

public class Rail {

  private static EasyDriver ed;
  
  static {
    System.out.println("Inicializando motor do trilho...");
    
    int drivingMode = EasyDriver.FULL_STEP;
    Pin step = RaspiPin.GPIO_05;
    Pin dir = RaspiPin.GPIO_06;
    Pin sleep = RaspiPin.GPIO_07;
    Pin enable = RaspiPin.GPIO_08;
    Pin ms1 = RaspiPin.GPIO_09;
    Pin ms2 = RaspiPin.GPIO_10;
    Pin reset = RaspiPin.GPIO_11;
    ed = new EasyDriver(drivingMode, step, dir, sleep, enable, ms1, ms2, reset);
  }
  
  public static void initialize() {
    try {
      ed.move(1200, 1);
      ed.move(-1200, 1);
    }
    catch(Exception E) {
      E.printStackTrace();
    }
  }
  
  public static void stepCW() {
    try {
      ed.move(2, 1);
    }
    catch(Exception E) {
      E.printStackTrace();
    }
  }
  
  public static void stepCCW() {
    try {
      ed.move(-2, 1);
    }
    catch(Exception E) {
      E.printStackTrace();
    }
  }
  
}
