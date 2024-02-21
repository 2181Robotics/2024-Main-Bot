package frc.robot.subsystems;

import static frc.robot.Constants.ClimberConstants.*;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

public class LeftClimberArm extends SubsystemBase{
    CANSparkMax m_LeftClimberArm;
    SmartdashboardItem m_LeftClimberArmSpeed;
    SmartdashboardItem m_LeftClimberArmCommandSpeed;
    
    public LeftClimberArm() {

    m_LeftClimberArm = new CANSparkMax(kLeftClimberArmID, MotorType.kBrushless);
    m_LeftClimberArmCommandSpeed = new SmartdashboardItem("LeftClimberArmCommandSpeed"); 
    m_LeftClimberArm.setSmartCurrentLimit(kLeftClimberArmCurrentLimit);
    m_LeftClimberArmCommandSpeed.setNumber(kLeftClimberArmSpeed);  
    m_LeftClimberArm.setIdleMode(IdleMode.kBrake); 
    }

      public Command getLeftClimberArmUpCommand() {
        // The startEnd helper method takes a method to call when the command is initialized and one to
        // call when it ends
        return this.runEnd(
            // When the command is initialized, set the wheels to the intake speed values
            () -> {
              setLeftClimberArm(-kLeftClimberArmSpeed);
             
            },
            // When the command stops, stop the wheels
            () -> {
              stop();
            });
      }
    public Command getLeftClimberArmDownCommand() {
        // The startEnd helper method takes a method to call when the command is initialized and one to
        // call when it ends
        return this.runEnd(
            // When the command is initialized, set the wheels to the intake speed values
            () -> {
             //if(!m_FeederStop.get()){
              setLeftClimberArm(kLeftClimberArmSpeed);
             
            },
            // When the command stops, stop the wheels
            () -> {
              stop();
            });
      }
      //Sets Climber Arms' to proper speeds

      public void setLeftClimberArm(double speed){
        m_LeftClimberArm.set(speed);
      }    
    
      //Sets Climber Arms' speed to 0
      public void stop() {
        m_LeftClimberArm.set(0);
      }

      //gets Climber Arm Encoders
      public void getEncoders() {
        m_LeftClimberArmSpeed.setNumber(m_LeftClimberArm.getEncoder().getVelocity());
      }

}