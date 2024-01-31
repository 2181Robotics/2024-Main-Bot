package frc.robot.subsystems;


    import static frc.robot.Constants.IntakeConstants.*;

    import edu.wpi.first.wpilibj2.command.Command;
    import edu.wpi.first.wpilibj2.command.SubsystemBase;
    

    import com.revrobotics.CANSparkMax;
    import com.revrobotics.CANSparkLowLevel.MotorType;

public class Intake extends SubsystemBase {
    CANSparkMax m_BottomIntakeWheel;
    CANSparkMax m_TopIntakeWheel;

    SmartdashboardItem m_BottomIntakeWheelSpeed;
    SmartdashboardItem m_TopIntakeWheelSpeed;

    SmartdashboardItem m_TopIntakeWheelCommandSpeed;
    SmartdashboardItem m_BottomIntakeWheelCommandSpeed;


    public Intake(){
    m_BottomIntakeWheel = new CANSparkMax(kBottomIntakeWheelID,MotorType.kBrushless);
    m_TopIntakeWheel = new CANSparkMax(kTopIntakeWheelID, MotorType.kBrushless);

    m_BottomIntakeWheelSpeed = new SmartdashboardItem("BottomIntakeWheelSpeed");
    m_TopIntakeWheelSpeed = new SmartdashboardItem("TopIntakeWheelSpeed");

    m_BottomIntakeWheelCommandSpeed = new SmartdashboardItem("BottomIntakeCommandSpeed");
    m_TopIntakeWheelCommandSpeed = new SmartdashboardItem("TopIntakeWheelCommandSpeed");

    m_BottomIntakeWheel.setSmartCurrentLimit(kBottomIntakeWheelCurrentLimit);
    m_TopIntakeWheel.setSmartCurrentLimit(kTopIntakeWheelCurrentLimit);

    m_BottomIntakeWheelCommandSpeed.setNumber(kBottomIntakeWheelSpeed);
    m_TopIntakeWheelCommandSpeed.setNumber(kTopIntakeWheelSpeed);
    }

    public Command getIntakeCommand() {
        // The startEnd helper method takes a method to call when the command is initialized and one to
        // call when it ends
        return this.startEnd(
            // When the command is initialized, set the wheels to the intake speed values
            () -> {
              setTopIntakeWheel(m_TopIntakeWheelCommandSpeed.getNumber());
              setBottomIntakeWheel(m_BottomIntakeWheelCommandSpeed.getNumber());
            },
            // When the command stops, stop the wheels
            () -> {
              stop();
            });
      }
    
      //Sets intake wheels to proper speeds
      public void setTopIntakeWheel(double speed){
        m_TopIntakeWheel.set(speed);
      }

      public void setBottomIntakeWheel(double speed){
        m_BottomIntakeWheel.set(speed);
      }    
    
      //Sets Intake wheels' speed to 0
      public void stop() {
        m_BottomIntakeWheel.set(0);
        m_TopIntakeWheel.set(0);
      }

      //gets Intake motor Encoders
      public void getEncoders() {
        m_BottomIntakeWheelSpeed.setNumber(m_BottomIntakeWheel.getEncoder().getVelocity());
        m_TopIntakeWheelSpeed.setNumber(m_TopIntakeWheel.getEncoder().getVelocity());
      }

    }