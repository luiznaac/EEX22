package gpio;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinPwmOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.util.CommandArgumentParser;

public class Arm {
  
  private static GpioPinPwmOutput pwm;
  private static int pos;
  
  static {
    System.out.println("Inicializando motor da garra...");
    
    Pin pin = CommandArgumentParser.getPin(
        RaspiPin.class,    // pin provider class to obtain pin instance from
        RaspiPin.GPIO_01,  // default pin if no pin argument found
        "");             // argument array to search in

    GpioController gpio = GpioFactory.getInstance();
    pwm = gpio.provisionPwmOutputPin(pin);

    // you can optionally use these wiringPi methods to further customize the PWM generator
    // see: http://wiringpi.com/reference/raspberry-pi-specifics/
    com.pi4j.wiringpi.Gpio.pwmSetMode(com.pi4j.wiringpi.Gpio.PWM_MODE_MS);
    com.pi4j.wiringpi.Gpio.pwmSetRange(4096);
    com.pi4j.wiringpi.Gpio.pwmSetClock(93);
    
    pos = 180;
  }
  
  public static void initialize() {
    try {
      pwm.setPwm(180);
      Thread.sleep(1000);
    } catch(Exception E) {
      E.printStackTrace();
    }
  }

  public static void up() {
    pwm.setPwm(250);
  }
  
  public static void down() {
    pwm.setPwm(160);
  }
  
  public static void setPwm(int newPos) {
    pwm.setPwm(newPos);
    pos = newPos;
  }
  
  public static void pwmUp() {
    pos += 5;
    pwm.setPwm(pos);
  }
  
  public static void pwmDown() {
    pos -= 5;
    pwm.setPwm(pos);
  }
  
}