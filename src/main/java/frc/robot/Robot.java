// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


//Packages for advantage kit

import org.littletonrobotics.junction.LogFileUtil;
import org.littletonrobotics.junction.LoggedRobot;
import org.littletonrobotics.junction.Logger;
import org.littletonrobotics.junction.networktables.NT4Publisher;
import org.littletonrobotics.junction.wpilog.WPILOGReader;
import org.littletonrobotics.junction.wpilog.WPILOGWriter;
import org.littletonrobotics.urcl.URCL;

import com.pathplanner.lib.pathfinding.Pathfinding;

//Base packages

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;


/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the LoggedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */

// We are using the LoggedRobot instead of the TimedRobot so that we can take advantage
// of AdvantageKit for logging, debugging and robot simulation in AdvantageScope

public class Robot extends LoggedRobot {
    private Command m_autonomousCommand;
    private RobotContainer m_robotContainer;



  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */

  @Override
  public void robotInit() {
    // Initiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    initializeLogging();
    Pathfinding.setPathfinder(new LocalADStarAK());
    m_robotContainer = new RobotContainer();
  }

/**
   * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.    
    CommandScheduler.getInstance().run();
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  @Override
  public void disabledExit() {
  }

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {
      m_autonomousCommand = m_robotContainer.getAutonomousCommand();

      if (m_autonomousCommand != null) {
          m_autonomousCommand.schedule();
      }
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void autonomousExit() {
  }

  @Override
  public void teleopInit() {
     // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.     
    if (m_autonomousCommand != null) {
          m_autonomousCommand.cancel();
      }
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
  }

  @Override
  public void teleopExit() {
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.  
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {
  }

  @Override
  public void testExit() {
  }



//Advantage Kit logging

// This will be used in conjuction with a file storing device connected to the rio (USB) in order
// to collect data about what the robot was told to do during a match and compare it to what we physically
// saw it do out on the field, we do need to have GitHub properly synced to the code for the logging to function properly

  private void initializeLogging() {
    //Logger.recordMetadata("Project Name", BuildConstants.MAVEN_NAME);
    //Logger.recordMetadata("Branch Name", BuildConstants.GIT_BRANCH);
    //Logger.recordMetadata("Commit Hash (Short)", BuildConstants.GIT_SHA.substring(0, 8));
   // Logger.recordMetadata("Commit Hash (Full)", BuildConstants.GIT_SHA);
   // Logger.recordMetadata("Build Time", BuildConstants.BUILD_DATE);

    // if (isReal()) {
    //     // Log to USB & Network Tables
    //     Logger.addDataReceiver(new WPILOGWriter("/media/sda1/"));
    //     Logger.addDataReceiver(new NT4Publisher());
    // } else {
    //     // Replay from log and save to file
    //     setUseTiming(false);
    //     String logPath = LogFileUtil.findReplayLog();
    //     Logger.setReplaySource(new WPILOGReader(logPath));
    //     Logger.addDataReceiver(new WPILOGWriter(LogFileUtil.addPathSuffix(logPath, "_sim")));

    // }
    // Logger.registerURCL(URCL.startExternal());
    // Logger.start();
}

}
