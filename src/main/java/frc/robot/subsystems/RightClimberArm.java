package frc.robot.subsystems;

import static frc.robot.Constants.ClimberConstants.*;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

public class RightClimberArm extends SubsystemBase{
    CANSparkMax m_RightClimberArm;
    SmartdashboardItem m_RightClimberArmSpeed;
    SmartdashboardItem m_RightClimberArmCommandSpeed;
    
    public RightClimberArm() {

    m_RightClimberArm = new CANSparkMax(kRightClimberArmID,MotorType.kBrushless);
        m_RightClimberArmCommandSpeed = new SmartdashboardItem("RightClimberArmCommandSpeed");
        m_RightClimberArm.setSmartCurrentLimit(kRightClimberArmCurrentLimit);
        m_RightClimberArmCommandSpeed.setNumber(kRightClimberArmSpeed);  
        m_RightClimberArm.setIdleMode(IdleMode.kBrake); 
    }

    public Command getRightClimberArmUpCommand() {
        // The startEnd helper method takes a method to call when the command is initialized and one to
        // call when it ends
        return this.runEnd(
            // When the command is initialized, set the wheels to the intake speed values
            () -> {
             //if(!m_FeederStop.get()){
              setRightClimberArm(-kRightClimberArmSpeed);
             
            },
            // When the command stops, stop the wheels
            () -> {
              stop();
            });
      }
    public Command getRightClimberArmDownCommand() {
        // The startEnd helper method takes a method to call when the command is initialized and one to
        // call when it ends
        return this.runEnd(
            // When the command is initialized, set the wheels to the intake speed values
            () -> {
             //if(!m_FeederStop.get()){
              setRightClimberArm(kRightClimberArmSpeed);
             
            },
            // When the command stops, stop the wheels
            () -> {
              stop();
            });
      }
      //Sets Climber Arms' to proper speeds
      public void setRightClimberArm(double speed){
        m_RightClimberArm.set(speed);
      }
    
      //Sets Climber Arms' speed to 0
      public void stop() {
        m_RightClimberArm.set(0);
      }

      //gets Climber Arm Encoders
      public void getEncoders() {
        m_RightClimberArmSpeed.setNumber(m_RightClimberArm.getEncoder().getVelocity());
      }

}
