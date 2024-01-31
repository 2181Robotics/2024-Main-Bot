package frc.robot.subsystems;

import static frc.robot.Constants.LauncherConstants.*;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;


public class Launcher extends SubsystemBase {
  CANSparkMax m_BottomLaunchWheel;
  CANSparkMax m_TopLaunchWheel;

  SmartdashboardItem m_BottomLauchWheelSpeed;
  SmartdashboardItem m_TopLaunchWheelSpeed;

  SmartdashboardItem m_TopLaunchWheelCommandSpeed;
  SmartdashboardItem m_BottomLaunchWheelCommandSpeed;
  

  /** Creates a new Launcher. */
  public Launcher() {
    m_BottomLaunchWheel = new CANSparkMax(kBottomLaunchWheelID,MotorType.kBrushless);
    m_TopLaunchWheel = new CANSparkMax(kTopLaunchWheelID, MotorType.kBrushless);

    m_BottomLauchWheelSpeed = new SmartdashboardItem("BottomLaunchWheelSpeed");
    m_TopLaunchWheelSpeed = new SmartdashboardItem("TopLaunchWheelSpeed");

    m_TopLaunchWheelCommandSpeed = new SmartdashboardItem("TopLaunchWheelCommandSpeed");
    m_BottomLaunchWheelCommandSpeed = new SmartdashboardItem("BottomLaunchCommandSpeed");

    m_BottomLaunchWheel.setSmartCurrentLimit(kBottomLaunchWheelCurrentLimit);
    m_TopLaunchWheel.setSmartCurrentLimit(kTopLaunchWheelCurrentLimit);

    m_BottomLaunchWheelCommandSpeed.setNumber(kBottomLaunchWheelSpeed);
    m_TopLaunchWheelCommandSpeed.setNumber(kTopLaunchWheelSpeed);
  }

  /**
   * This method is an example of the 'subsystem factory' style of command creation. A method inside
   * the subsytem is created to return an instance of a command. This works for commands that
   * operate on only that subsystem, a similar approach can be done in RobotContainer for commands
   * that need to span subsystems. The Subsystem class has helper methods, such as the startEnd
   * method used here, to create these commands.
   */
  public Command getLaunchCommand() {
    // The startEnd helper method takes a method to call when the command is initialized and one to
    // call when it ends
    return this.startEnd(
        // When the command is initialized, set the wheels to the intake speed values
        () -> {
          setTopLaunchWheel(m_TopLaunchWheelCommandSpeed.getNumber());
          setBottomLaunchWheel(m_BottomLaunchWheelCommandSpeed.getNumber());
        },
        // When the command stops, stop the wheels
        () -> {
          setTopLaunchWheel(0);
          setBottomLaunchWheel(0);
        });
  }

  // An accessor method to set the speed (technically the output percentage) of the launch wheel
  public void setBottomLaunchWheel(double speed) {
    m_BottomLaunchWheel.set(speed);
  }

  // An accessor method to set the speed (technically the output percentage) of the feed wheel
  public void setTopLaunchWheel(double speed) {
    m_TopLaunchWheel.set(speed);
  }

  // A helper method to stop both wheels. You could skip having a method like this and call the
  // individual accessors with speed = 0 instead
  public void stop() {
    m_BottomLaunchWheel.set(0);
    m_TopLaunchWheel.set(0);
  }
  public void getEncoders() {
    m_BottomLauchWheelSpeed.setNumber(m_BottomLaunchWheel.getEncoder().getVelocity());
    m_TopLaunchWheelSpeed.setNumber(m_TopLaunchWheel.getEncoder().getVelocity());
  }
}

