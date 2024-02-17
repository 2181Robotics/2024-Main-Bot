package frc.robot.subsystems;


import static frc.robot.Constants.LauncherConstants.*;
import static frc.robot.Constants.IntakeConstants.*;


import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;




public class Feeder extends SubsystemBase {
    CANSparkMax m_FeederWheel;  
    SmartdashboardItem m_FeederWheelLaunchSpeed;
    SmartdashboardItem m_FeederWheelLaunchCommandSpeed;
    SmartdashboardItem m_FeederWheelIntakeSpeed;
    SmartdashboardItem m_FeederWheelIntakeCommandSpeed;

    
 
    
    public Feeder(){
    m_FeederWheel = new CANSparkMax(kFeederWheelID, MotorType.kBrushless);

    m_FeederWheelLaunchSpeed = new SmartdashboardItem("FeederWheelSpeed");

    m_FeederWheelLaunchCommandSpeed = new SmartdashboardItem("FeederWheelCommandSpeed");  

    m_FeederWheel.setSmartCurrentLimit(kFeederWheelCurrentLimit);
    
    m_FeederWheelLaunchCommandSpeed.setNumber(kFeederWheelLaunchSpeed);

    m_FeederWheelIntakeSpeed = new SmartdashboardItem("FeederWheelIntakeSpeed");

    m_FeederWheelIntakeCommandSpeed = new SmartdashboardItem("FeederWheelIntakeCommandSpeed");  

    m_FeederWheelIntakeCommandSpeed.setNumber(kFeederWheelIntakeSpeed);

  

    }

      public Command getFeederWheelLaunchCommand(){

        return this.startEnd(
            // When the command is initialized, set the wheels to the feeder speed values
            () -> {
              setFeederWheel(m_FeederWheelLaunchCommandSpeed.getNumber());
              
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
              setFeederWheel(-m_FeederWheelLaunchCommandSpeed.getNumber());
              
            },
            // When the command stops, stop the wheels
            () -> {
              stop();
            });
        
    }

     public Command setFeederZero(){

      return this.startEnd(
            // When the command is initialized, set the wheels to the feeder speed values
            () -> {
              setFeederWheel(0);
              
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
      

 public Command getFeederWheelIntakeCommand(){

        return this.startEnd(
            // When the command is initialized, set the wheels to the feeder speed values
            () -> {
              setFeederWheel(m_FeederWheelIntakeCommandSpeed.getNumber());
              
            },
            // When the command stops, stop the wheels
            () -> {
              stop();
            });
        
    }

    public void setFeederWheel(double speed){
      m_FeederWheel.set(speed);
    }

    public void stop() {
      m_FeederWheel.set(0);
    }
    


    public void getEncoders() {
        m_FeederWheelLaunchSpeed.setNumber(m_FeederWheel.getEncoder().getVelocity());
}




}