package frc.robot.subsystems;

import static frc.robot.Constants.ClimberConstants.*;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

//import com.ctre.phoenix6.signals.ReverseLimitValue;
import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkPIDController;
//import com.revrobotics.CANSparkBase.ControlType;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkBase.SoftLimitDirection;
import com.revrobotics.CANSparkLowLevel.MotorType;


public class RightClimberArm extends SubsystemBase{
    CANSparkMax m_RightClimberArm;
    SmartdashboardItem m_RightClimberArmSpeed;
    SmartdashboardItem m_RightClimberArmCommandSpeed;
     
    private SparkPIDController RightClimberArmPID;
    
    public RightClimberArm() {

    m_RightClimberArm = new CANSparkMax(kRightClimberArmID,MotorType.kBrushless);
        m_RightClimberArmCommandSpeed = new SmartdashboardItem("RightClimberArmCommandSpeed");
        m_RightClimberArm.setSmartCurrentLimit(kRightClimberArmCurrentLimit);
        m_RightClimberArmCommandSpeed.setNumber(kRightClimberArmSpeed);  
        m_RightClimberArm.restoreFactoryDefaults();
        m_RightClimberArm.setIdleMode(IdleMode.kBrake); 
        m_RightClimberArm.setInverted(false);


        m_RightClimberArm.enableSoftLimit(SoftLimitDirection.kForward, true);
        m_RightClimberArm.enableSoftLimit(SoftLimitDirection.kReverse, true);
        m_RightClimberArm.setSoftLimit(SoftLimitDirection.kForward, 300);
        m_RightClimberArm.setSoftLimit(SoftLimitDirection.kReverse, 20);




        RightClimberArmPID = m_RightClimberArm.getPIDController();

        RightClimberArmPID.setP(climbP);
        RightClimberArmPID.setI(climbI);
        RightClimberArmPID.setD(climbD);
        RightClimberArmPID.setFF(climbFF);
        RightClimberArmPID.setOutputRange(0.0, 1.0);
        m_RightClimberArm.burnFlash();

    }

    // public Command getAutoRightClimberArmUP() {
    //   return this.runEnd(
    //     () -> {
    //        RightClimberArmPID.setReference(RightClimberArmPosition, ControlType.kPosition);
    //     },

    //     () -> {
    //       setRightClimberArm(0);
    //     });


    public Command getRightClimberArmUpCommand() {
        // The startEnd helper method takes a method to call when the command is initialized and one to
        // call when it ends
        return this.runEnd(
            // When the command is initialized, set the wheels to the intake speed values
            () -> {
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
              setRightClimberArm(-kRightClimberArmSpeed);
             
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
