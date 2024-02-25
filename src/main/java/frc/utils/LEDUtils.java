/* package frc.utils;
import edu.wpi.first.wpilibj.*;
import frc.robot.Constants;


public class LEDUtils {
    AddressableLED m_led;
    AddressableLEDBuffer m_ledBuffer;


    public LEDGroup leftArm = new LEDGroup(0, 9, m_ledBuffer);
    public LEDGroup rightArm = new LEDGroup(10, 19, m_ledBuffer);


    public void LEDInit(){
        m_led = new AddressableLED(Constants.LEDConstants.LEDPort);

        // Reuse buffer
        // Default to a length of 60, start empty output
        // Length is expensive to set, so only set it once, then just update data
        m_ledBuffer = new AddressableLEDBuffer(Constants.LEDConstants.LEDLen);
        m_led.setLength(m_ledBuffer.getLength());

        // Set the data
        m_led.setData(m_ledBuffer);
        m_led.start();

        

        //leftArm.setRainbow();
        //rightArm.setRainbow();

        setLEDs();
    }


    public void setLEDs(){
        m_led.setData(m_ledBuffer);
    }

    


}
 */