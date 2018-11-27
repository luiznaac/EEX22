package gpio;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class Magnet {

  private static GpioPinDigitalOutput pin;
  
  static {
    System.out.println("Inicializando eletroima...");
    
    final GpioController gpio = GpioFactory.getInstance();
    pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_12, "MyLED", PinState.HIGH);

    // set shutdown state for this pin
    pin.setShutdownOptions(true, PinState.LOW);

    pin.low();
  }
  
  public static void toggle() {
    pin.toggle();
    System.out.println(pin.isHigh() ? "Ima ligado." : "Ima desligado.");
  }
  
  public static void initialize() {
    
  }
  
}
