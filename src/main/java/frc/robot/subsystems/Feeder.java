package frc.robot.subsystems;


import static frc.robot.Constants.LauncherConstants.*;



import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;




public class Feeder extends SubsystemBase {
    CANSparkMax m_FeederWheel;  
    SmartdashboardItem m_FeederWheelSpeed;
    SmartdashboardItem m_FeederWheelCommandSpeed;

    
 
    
    public Feeder(){
    m_FeederWheel = new CANSparkMax(kFeederWheelID, MotorType.kBrushless);

    m_FeederWheelSpeed = new SmartdashboardItem("FeederWheelSpeed");

    m_FeederWheelCommandSpeed = new SmartdashboardItem("FeederWheelCommandSpeed");  

    m_FeederWheel.setSmartCurrentLimit(kFeederWheelCurrentLimit);
    
    m_FeederWheelCommandSpeed.setNumber(kFeederWheelSpeed);

  

    }

      public Command getFeederWheelCommand(){

        return this.startEnd(
            // When the command is initialized, set the wheels to the feeder speed values
            () -> {
              setFeederWheel(m_FeederWheelCommandSpeed.getNumber());
              
            },
            // When the command stops, stop the wheels
            () -> {
              stop();
            });
        
    }


    public Command getReverseFeederCommand(){

      return this.startEnd(
            // When the command is initialized, set the wheels to the feeder speed values
            () -> {
              setFeederWheel(-m_FeederWheelCommandSpeed.getNumber());
              
            },
            // When the command stops, stop the wheels
            () -> {
              stop();
            });
        
    }

    // public Command stopFeeder(){
    //   return runOnce(() -> {
    //       setFeederWheel(0);
    //   });
    //   }
      

    public void setFeederWheel(double speed){
      m_FeederWheel.set(speed);
    }

    public void stop() {
      m_FeederWheel.set(0);
    }


    public void getEncoders() {
        m_FeederWheelSpeed.setNumber(m_FeederWheel.getEncoder().getVelocity());
}




}