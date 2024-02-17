package frc.robot.subsystems;

import static frc.robot.Constants.ClimberConstants.*;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

public class Climber extends SubsystemBase{
    CANSparkMax m_RightClimberArm;
    CANSparkMax m_LeftClimberArm;

    SmartdashboardItem m_RightClimberArmSpeed;
    SmartdashboardItem m_LeftClimberArmSpeed;

    SmartdashboardItem m_RightClimberArmCommandSpeed;
    SmartdashboardItem m_LeftClimberArmCommandSpeed;
    
    public Climber() {

    m_RightClimberArm = new CANSparkMax(kRightClimberArmID,MotorType.kBrushless);
    m_LeftClimberArm = new CANSparkMax(kLeftClimberArmID, MotorType.kBrushless);
    
        m_RightClimberArmCommandSpeed = new SmartdashboardItem("RightClimberArmCommandSpeed");
        m_LeftClimberArmCommandSpeed = new SmartdashboardItem("LeftClimberArmCommandSpeed"); 
    
        m_RightClimberArm.setSmartCurrentLimit(kRightClimberArmCurrentLimit);
        m_LeftClimberArm.setSmartCurrentLimit(kLeftClimberArmCurrentLimit);
    
        m_RightClimberArmCommandSpeed.setNumber(kRightClimberArmSpeed);
        m_LeftClimberArmCommandSpeed.setNumber(kLeftClimberArmSpeed);   
    }

    public Command getRightClimberArmUpCommand() {
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
    public Command getRightClimberArmDownCommand() {
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

      public Command getLeftClimberArmUpCommand() {
        // The startEnd helper method takes a method to call when the command is initialized and one to
        // call when it ends
        return this.runEnd(
            // When the command is initialized, set the wheels to the intake speed values
            () -> {
              setLeftClimberArm(kLeftClimberArmSpeed);
             
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
              setLeftClimberArm(-kLeftClimberArmSpeed);
             
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

      public void setLeftClimberArm(double speed){
        m_LeftClimberArm.set(speed);
      }    
    
      //Sets Climber Arms' speed to 0
      public void stop() {
        m_RightClimberArm.set(0);
        m_LeftClimberArm.set(0);
      }

      //gets Climber Arm Encoders
      public void getEncoders() {
        m_RightClimberArmSpeed.setNumber(m_RightClimberArm.getEncoder().getVelocity());
        m_LeftClimberArmSpeed.setNumber(m_LeftClimberArm.getEncoder().getVelocity());
      }

}
