package frc.robot.subsystems;


import static frc.robot.Constants.LauncherConstants.*;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.subsystems.Intake;
    
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

public class Feeder extends SubsystemBase {
    CANSparkMax m_FeederWheel;  
    SmartdashboardItem m_FeederWheelSpeed;
    SmartdashboardItem m_FeederWheelCommandSpeed;

    DigitalInput m_FeederStop;
    SmartdashboardItem m_FeederLimitSwitch;

    //Intake m_Intake;    
    
    public Feeder(){
    m_FeederWheel = new CANSparkMax(kFeederWheelID, MotorType.kBrushless);

    m_FeederWheelSpeed = new SmartdashboardItem("FeederWheelSpeed");

    m_FeederWheelCommandSpeed = new SmartdashboardItem("FeederWheelCommandSpeed");  

    m_FeederWheel.setSmartCurrentLimit(kFeederWheelCurrentLimit);
    
    m_FeederWheelCommandSpeed.setNumber(kFeederWheelSpeed);

    m_FeederStop = new DigitalInput(9);
    // new Trigger(m_FeederStop::get).onTrue(stopFeeder());

   m_FeederLimitSwitch = new SmartdashboardItem("FeederLimitSwitch");
    
   //m_Intake = new Intake();
    }

      public Command getFeederWheelCommand(boolean isIntake){

        return this.runEnd(
            // When the command is initialized, set the wheels to the intake speed values
            () -> {
              
                if(isIntake && m_FeederStop.get()){
                  setFeederWheel(0);
                  //m_Intake.setBottomIntakeWheel(0);
                  //m_Intake.setTopIntakeWheel(0);
                }else{
                  setFeederWheel(kFeederWheelSpeed);
                }
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
        //m_leftRear.set(speed);
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

@Override
public void periodic(){
  //return m_FeederStop.get();
  m_FeederLimitSwitch.setBoolean(m_FeederStop.get());
}

 public Boolean FeederLimitSwitch() {
    return m_FeederStop.get();
  
}


}

