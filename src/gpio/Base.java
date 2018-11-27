package gpio;

import com.pi4j.component.motor.impl.GpioStepperMotorComponent;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class Base {

  private static final GpioController gpio;
  private static GpioPinDigitalOutput[] pins;
  private static GpioStepperMotorComponent motor;
  private static byte[] single_step_sequence;
  
  static {
    System.out.println("Inicializando motor da base...");
    
    // create gpio controller
    gpio = GpioFactory.getInstance();

    // provision gpio pins #00 to #03 as output pins and ensure in LOW state
    pins = new GpioPinDigitalOutput[4];
    pins[0] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00, PinState.LOW);
    pins[1] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, PinState.LOW);
    pins[2] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, PinState.LOW);
    pins[3] = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03, PinState.LOW);

    // this will ensure that the motor is stopped when the program terminates
    gpio.setShutdownOptions(true, PinState.LOW, pins);

    // create motor component
    motor = new GpioStepperMotorComponent(pins);

    // create byte array to demonstrate a single-step sequencing
    // (This is the most basic method, turning on a single electromagnet every time.
    //  This sequence requires the least amount of energy and generates the smoothest movement.)
    single_step_sequence = new byte[4];
    single_step_sequence[0] = (byte) 0b0001;
    single_step_sequence[1] = (byte) 0b0010;
    single_step_sequence[2] = (byte) 0b0100;
    single_step_sequence[3] = (byte) 0b1000;

    // define stepper parameters before attempting to control motor
    motor.setStepInterval(2);
    motor.setStepSequence(single_step_sequence);
    motor.setStepsPerRevolution(2038);
  }
  
  public static void initialize() {
    motor.step(1019);
    motor.step(-1019);
  }
  
  public static void stepCW() {
    motor.step(2);
  }
  
  public static void stepCCW() {
    motor.step(-2);
  }
  
}
