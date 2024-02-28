package frc.robot.subsystems;

import static frc.robot.Constants.ClimberConstants.*;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkPIDController;
//import com.revrobotics.CANSparkBase.ControlType;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkBase.SoftLimitDirection;
import com.revrobotics.CANSparkLowLevel.MotorType;


public class LeftClimberArm extends SubsystemBase{
    CANSparkMax m_LeftClimberArm;
    SmartdashboardItem m_LeftClimberArmSpeed;
    SmartdashboardItem m_LeftClimberArmCommandSpeed;

    SparkPIDController LeftClimberArmPID;
    
    public LeftClimberArm() {

    m_LeftClimberArm = new CANSparkMax(kLeftClimberArmID, MotorType.kBrushless);
    m_LeftClimberArmCommandSpeed = new SmartdashboardItem("LeftClimberArmCommandSpeed"); 
    m_LeftClimberArm.setSmartCurrentLimit(kLeftClimberArmCurrentLimit);
    m_LeftClimberArmCommandSpeed.setNumber(kLeftClimberArmSpeed);  
    m_LeftClimberArm.setIdleMode(IdleMode.kBrake);
    m_LeftClimberArm.restoreFactoryDefaults();
    m_LeftClimberArm.setInverted(false);

    m_LeftClimberArm.enableSoftLimit(SoftLimitDirection.kForward, true);
    m_LeftClimberArm.enableSoftLimit(SoftLimitDirection.kReverse, true);
    m_LeftClimberArm.setSoftLimit(SoftLimitDirection.kForward, -20);
    m_LeftClimberArm.setSoftLimit(SoftLimitDirection.kReverse, -320);




    LeftClimberArmPID = m_LeftClimberArm.getPIDController();

        LeftClimberArmPID.setP(climbP);
        LeftClimberArmPID.setI(climbI);
        LeftClimberArmPID.setD(climbD);
        LeftClimberArmPID.setFF(climbFF);
        LeftClimberArmPID.setOutputRange(0.0, 1.0);
        m_LeftClimberArm.burnFlash();

    }

    // public Command getAutoLeftClimberArmUP() {
    //   return this.runEnd(
    //     () -> {
    //        LeftClimberArmPID.setReference(LeftClimberArmPosition, ControlType.kPosition);
    //     },

    //     () -> {
    //       setLeftClimberArm(0);
    //     });

    // } 
    

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