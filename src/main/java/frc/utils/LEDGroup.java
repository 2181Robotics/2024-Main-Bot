package frc.utils;



import edu.wpi.first.wpilibj.*;


public class LEDGroup {
    
    private AddressableLEDBuffer m_ledBuffer;

    private int m_startLED;

    private int loopStart;

    private int m_ledLength;
    //all colors are HSV
    private int[] m_baseColor = {120,255,255};//blue

    

    private int rainbowHue = 0;

    private int breatheValue = 0;
    private int breatheInc = 1;

    private int rollInc = 1;

    private Boolean isSolid = true;
    private Boolean isBreathing = false;
    private Boolean isRainbow = false;
    private Boolean isRolling = false;


    public LEDGroup(int startLED, int ledLength, AddressableLEDBuffer ledBuffer){

        m_startLED = startLED;
        m_ledLength = ledLength;
        if(m_ledLength < 1)
            m_ledLength = 1;

        m_ledBuffer = ledBuffer;
        
        loopStart = 0;

    }

    public void setColor(int h, int s, int v){

        m_baseColor[0] = h;
        m_baseColor[1] = s;
        m_baseColor[2] = v;

    }

    public void setOrange(){
        setColor (10,255,255);
    }

    public void setBlue(){
        setColor(120,255,255);
    }

    public void setRed(){
        setColor(0,255,255);
    }

    public void setAllianceColor(){
        setBlue();
        var alliance = DriverStation.getAlliance();
        if (alliance.isPresent()) {
            if(alliance.get() == DriverStation.Alliance.Red){
                setRed();
            }
        }
        

    }


    
    public AddressableLEDBuffer setSolid(AddressableLEDBuffer ledBuffer){
        for (var i = 0; i < m_ledLength; i++) {
            // Sets the specified LED to the RGB values for red
            ledBuffer.setHSV(i + m_startLED, m_baseColor[0], m_baseColor[1], m_baseColor[2]);
            
        }
        isSolid = true;
        isBreathing = isRainbow = isRolling = false;
        return m_ledBuffer;
    }

    public void setRoll(int incValue){
        rollInc = incValue;
        isRolling = true;
        isBreathing = isRainbow = isSolid = false;
    }

    public void setRainbow(){
        isRainbow = true;
        isBreathing = isRolling = isSolid = false;
    }

    public void setBreathing(){
        isBreathing = true;
        isRolling = isRainbow = isSolid = false;
    }

    public AddressableLEDBuffer incRoll(AddressableLEDBuffer ledBuffer){
        for (var i = 0; i < m_ledLength; i++) {
            if(i<=m_ledLength/2){
                ledBuffer.setHSV( m_startLED + (i + loopStart)%m_ledLength, m_baseColor[0], m_baseColor[1], m_baseColor[2]);
            }else{
                ledBuffer.setHSV( m_startLED + (i + loopStart)%m_ledLength, 0, 0, 255);
            }
            
        }
        loopStart += rollInc;
        loopStart = (loopStart+m_ledLength)%m_ledLength;
        

        return m_ledBuffer;
    }



//not fully tested, don't use
    public AddressableLEDBuffer breathe(AddressableLEDBuffer ledBuffer){

        for (var i = 0; i < m_ledLength; i++) {
            // Sets the specified LED to the RGB values for red
            ledBuffer.setHSV(i + m_startLED, m_baseColor[0], m_baseColor[1], breatheValue);
            
        }
        breatheValue = breatheValue + (breatheInc*25);
        if(breatheValue >= 255){
            breatheValue = 255;
            breatheInc = -1;
        }

        if(breatheValue <= 0){
            breatheValue = 0;
            breatheInc = 1;
        }
        return m_ledBuffer;
    }


    //cycle through rainbow colors
    public AddressableLEDBuffer rainbow(AddressableLEDBuffer ledBuffer){
        for (var i = 0; i < m_ledLength; i++) {
            // Sets the specified LED to the RGB values for red
            ledBuffer.setHSV(i + m_startLED, rainbowHue, 255, 255);
            
        }
        rainbowHue = (rainbowHue+1)%180;
        return m_ledBuffer;
    }

    public AddressableLEDBuffer shortInc(AddressableLEDBuffer ledBuffer){
        
        if(isBreathing)
            ledBuffer =  breathe(ledBuffer);
        if(isRainbow)
            ledBuffer =  rainbow(ledBuffer);

        return ledBuffer;
    }

    public AddressableLEDBuffer longInc(AddressableLEDBuffer ledBuffer){
        if(isRolling)
            ledBuffer =  incRoll(ledBuffer);

        return ledBuffer;
        
    }
}
