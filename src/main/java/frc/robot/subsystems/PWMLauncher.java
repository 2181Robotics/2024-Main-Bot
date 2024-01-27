


package frc.robot.subsystems;

import static frc.robot.Constants.LauncherConstants.*;

import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class PWMLauncher extends SubsystemBase {
  PWMSparkMax m_BottomLaunchWheel;
  PWMSparkMax m_TopLaunchWheel;

  /** Creates a new Launcher. */
  public PWMLauncher() {
    m_BottomLaunchWheel = new PWMSparkMax(kBottomLaunchWheelID);
    m_TopLaunchWheel = new PWMSparkMax(kTopLaunchWheelID);
  }


  // An accessor method to set the speed (technically the output percentage) of the launch wheel
  public void setLaunchWheel(double speed) {
    m_BottomLaunchWheel.set(speed);
  }

  // An accessor method to set the speed (technically the output percentage) of the feed wheel
  public void setFeedWheel(double speed) {
    m_TopLaunchWheel.set(speed);
  }

  // A helper method to stop both wheels. You could skip having a method like this and call the
  // individual accessors with speed = 0 instead
  public void stop() {
    m_BottomLaunchWheel.set(0);
    m_TopLaunchWheel.set(0);
  }
}
