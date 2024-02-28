package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.*;
import frc.utils.LEDGroup;

//In robotContainer - LED.init() right after button bindings

//Any LED Group = make sure to add them to the long and short increments in this periodic loop

public class LEDs extends SubsystemBase {

    AddressableLEDBuffer ledBuffer = new AddressableLEDBuffer(40);
    AddressableLED m_led = new AddressableLED(0);
    
    //use this to cycle patterns
    private int loopCounter = 0;
    //shorter cycles for faster animations
    private int shortInc = 1;
    //longer cycles for slower animations
    private int longInc = 10;

    //Create groups here
    private LEDGroup electricalPanel = new LEDGroup(0, 10, ledBuffer);
    private LEDGroup arm1 = new LEDGroup(10,10 ,ledBuffer);
    private LEDGroup arm2 = new LEDGroup(20,10 ,ledBuffer);
    private LEDGroup underGlow = new LEDGroup(30,10 ,ledBuffer);


    //only call this once
    public void init(){
        m_led.setLength(ledBuffer.getLength());
    }


    //turns leds on
    public void LEDShow(){
        m_led.setData(ledBuffer);
        m_led.start();
    }


    //everything until nect comment was rearranged for testing. Look for inspiration, but don't copy

    public void electricalPanelOrange(){

        electricalPanel.setOrange();
        ledBuffer = electricalPanel.setSolid(ledBuffer);

    }

    public void arm1Blue(){

        arm1.setBlue();
        ledBuffer = arm1.setSolid(ledBuffer);

    }
    public void arm1Alliance(){

        arm1.setAllianceColor();
        ledBuffer = arm1.setSolid(ledBuffer);

    
    }


     public void electricalPanelBlueRoll(int incValue){

        electricalPanel.setRed();
        electricalPanel.setRoll(incValue);
        ledBuffer = electricalPanel.longInc(ledBuffer);
        LEDShow();

    }

    public void underGlowBreathe(){

        underGlow.setOrange();
        underGlow.setRoll(-1);
        ledBuffer = underGlow.longInc(ledBuffer);
        LEDShow();

    }

    public void arm1Rainbow(){
        arm1.setRainbow();
        //arm1.setRoll(incValue);
        ledBuffer = arm1.shortInc(ledBuffer);
        LEDShow();
    }

    public void arm2solid(){
        arm2.setOrange();
        ledBuffer = arm2.setSolid(ledBuffer);
        //arm1.setRoll(incValue);
        //ledBuffer = arm1.shortInc(ledBuffer);
        LEDShow();
    }

    public void arm1Orange(){

        arm1.setOrange();
        ledBuffer = arm1.setSolid(ledBuffer);

    }

    //end of experimentation

    @Override
    public void periodic(){

        //add all led groups to both if statements
        if(loopCounter%shortInc==0){
            electricalPanel.shortInc(ledBuffer);
            arm1.shortInc(ledBuffer);
            arm2.shortInc(ledBuffer);
            underGlow.shortInc(ledBuffer);
            LEDShow();
        }

        if(loopCounter%longInc==0){
            electricalPanel.longInc(ledBuffer);
            arm1.longInc(ledBuffer);
            arm2.longInc(ledBuffer);
            underGlow.longInc(ledBuffer);
            LEDShow();
        }

        if((loopCounter%shortInc==0)&&(loopCounter%longInc==0)){
            loopCounter = 0;
            
        }
        loopCounter++;

    }


}
