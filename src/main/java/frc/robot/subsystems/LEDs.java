package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.util.Color;
import frc.utils.LEDGroup;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class LEDs extends SubsystemBase {


      private AddressableLED light_strip;
    private AddressableLEDBuffer light_stripBuffer;
    private Color currentColor;

    private boolean isRed;
    private boolean e = false;

    private DigitalInput sensor;

    private List<Integer> pattern;

    public LEDs() {
        light_strip = new AddressableLED(9);
        light_stripBuffer = new AddressableLEDBuffer(50);
        light_strip.setLength(light_stripBuffer.getLength());
        light_strip.setData(light_stripBuffer);
        light_strip.start();

        // choose default color
        Optional<Alliance> ally = DriverStation.getAlliance();
        if (ally.isPresent() && (ally.get() == Alliance.Blue)) {
            isRed = false;
        } else {
            isRed = true;
        }

        setDefault();

        sensor = new DigitalInput(5);



        pattern = new ArrayList<>();
        pattern.add(1); //twinkle
        pattern.add(2); //pulse
        pattern.add(3); //laser
        Collections.shuffle(pattern);
    }

    private void setColor() {
        for (var i = 0; i < light_stripBuffer.getLength(); i++) {
            light_stripBuffer.setLED(i, currentColor);
            ;
        }
        light_strip.setData(light_stripBuffer);
    }

    public void setDefault() {
        if (isRed) {
            currentColor = Color.kRed;
            setColor();
        } else {
            currentColor = Color.kBlue;
            setColor();
        }
    }

    private void runPattern(int current){
        if(current == 1){
            twinklePattern();
        }else if(current == 2){
            pulsePattern();
        }else if(current == 3){
            laserPattern();
        }
    }

    private boolean twinkle = false;
    private void twinklePattern() {
        for (var i = 0; i < light_stripBuffer.getLength(); i++) {
            if (i%2==0 && twinkle) {
                light_stripBuffer.setLED(i, currentColor);
            }else if (i%2==1 && twinkle){
                light_stripBuffer.setLED(i, Color.kBlack);
            } else if (i%2==0 && !twinkle){
                light_stripBuffer.setLED(i, Color.kBlack);
            }else{
                light_stripBuffer.setLED(i, currentColor);
            }
        }
        light_strip.setData(light_stripBuffer);
    }

    private int pulse = 0;
    private boolean pulsee = false;
    private void pulsePattern(){
        if(!pulsee){
            light_stripBuffer.setLED(pulse, Color.kBlack);
        }else{
            light_stripBuffer.setLED(pulse, currentColor);
        }
        if(pulse>=light_stripBuffer.getLength()){
            pulse=0;
            pulsee = !pulsee;
        }
        light_strip.setData(light_stripBuffer);
    }

    private void laserPattern(){

    }

    /*
     * private int a=5;
     * private boolean
     * private void dimPattern(){
     * List<Color> dim = new ArrayList<Color>();
     * for(var i = 0; i < light_stripBuffer.getLength(); i++){
     * final var brightness = a+(i*5);
     * light_stripBuffer.setRGB(i, brightness, 0, 0);
     * }
     * a+=5;
     * light_strip.setData(light_stripBuffer);
     * }
     */

    @Override
    public void periodic() {
        if (sensor.get()) {
        //     if (!e) {
        //         currentColor = Color.kOrange;
        //         setDefault();
        //         e = true;
        //     }
        // } else if (e) {
        //     setDefault();
        //     e = false;
         }

        runPattern(pattern.get(2));
        setDefault();


    }

    // AddressableLEDBuffer ledBuffer = new AddressableLEDBuffer(40);
    // AddressableLED m_led = new AddressableLED(0);
    
    // private int loopCounter = 0;
    // private int shortInc = 1;
    // private int longInc = 10;
    // private LEDGroup electricalPanel = new LEDGroup(0, 10, ledBuffer);
    // private LEDGroup arm1 = new LEDGroup(10,10 ,ledBuffer);
    // private LEDGroup arm2 = new LEDGroup(20,10 ,ledBuffer);
    // private LEDGroup underGlow = new LEDGroup(30,10 ,ledBuffer);

    // public void init(){
    //     m_led.setLength(ledBuffer.getLength());
    // }

    // public void LEDShow(){
    //     m_led.setData(ledBuffer);
    //     m_led.start();
    // }

    // public void electricalPanelOrange(){

    //     electricalPanel.setOrange();
    //     ledBuffer = electricalPanel.setSolid(ledBuffer);

    // }

    // public void arm1Blue(){

    //     arm1.setBlue();
    //     ledBuffer = arm1.setSolid(ledBuffer);

    // }
    // public void arm1Alliance(){

    //     arm1.setAllianceColor();
    //     ledBuffer = arm1.setSolid(ledBuffer);

    
    // }


    //  public void electricalPanelBlueRoll(int incValue){

    //     electricalPanel.setRed();
    //     electricalPanel.setRoll(incValue);
    //     ledBuffer = electricalPanel.longInc(ledBuffer);
    //     LEDShow();

    // }

    // public void underGlowBreathe(){

    //     underGlow.setOrange();
    //     underGlow.setRoll(-1);
    //     ledBuffer = underGlow.longInc(ledBuffer);
    //     LEDShow();

    // }

    // public void arm1Rainbow(){
    //     arm1.setRainbow();
    //     //arm1.setRoll(incValue);
    //     ledBuffer = arm1.shortInc(ledBuffer);
    //     LEDShow();
    // }

    // public void arm2solid(){
    //     arm2.setOrange();
    //     ledBuffer = arm2.setSolid(ledBuffer);
    //     //arm1.setRoll(incValue);
    //     //ledBuffer = arm1.shortInc(ledBuffer);
    //     LEDShow();
    // }

    // public void arm1Orange(){

    //     arm1.setOrange();
    //     ledBuffer = arm1.setSolid(ledBuffer);

    // }

    

    // @Override
    // public void periodic(){

    //     if(loopCounter%shortInc==0){
    //         electricalPanel.shortInc(ledBuffer);
    //         arm1.shortInc(ledBuffer);
    //         arm2.shortInc(ledBuffer);
    //         underGlow.shortInc(ledBuffer);
    //         LEDShow();
    //     }

    //     if(loopCounter%longInc==0){
    //         electricalPanel.longInc(ledBuffer);
    //         arm1.longInc(ledBuffer);
    //         arm2.longInc(ledBuffer);
    //         underGlow.longInc(ledBuffer);
    //         LEDShow();
    //     }

    //     if((loopCounter%shortInc==0)&&(loopCounter%longInc==0)){
    //         loopCounter = 0;
            
    //     }
    //     loopCounter++;

    // }


}
