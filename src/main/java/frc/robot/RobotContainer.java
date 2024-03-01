// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


//Advantage Kit Logging

//import org.littletonrobotics.junction.networktables.LoggedDashboardChooser;


//PathPlanner 

//import edu.wpi.first.math.util.Units;
import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;
//import com.pathplanner.lib.path.PathConstraints;
//import com.pathplanner.lib.path.PathPlannerPath;

//Imports for Controllers

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//Default Needed for Swerve

import edu.wpi.first.math.MathUtil;
//import edu.wpi.first.math.geometry.Pose2d;
//import edu.wpi.first.math.geometry.Rotation2d;
import frc.robot.Constants.OIConstants;
import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;


// Import all subsystems for the robot

import frc.robot.subsystems.Launcher;
import frc.robot.subsystems.LeftClimberArm;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.RightClimberArm;

import frc.robot.Commands.TurnToAngle;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;

/*
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */

public class RobotContainer {
  
  // The robot's subsystems
  private final DriveSubsystem m_robotDrive = new DriveSubsystem();

  private final Launcher m_Launcher = new Launcher();
  private final Intake m_Intake = new Intake();
  private final Feeder m_Feeder = new Feeder();
  private final RightClimberArm m_RightClimberArm = new RightClimberArm();
  private final LeftClimberArm m_LeftClimberArm = new LeftClimberArm();
  


  // The driver's controller
  XboxController m_driverController = new XboxController(OIConstants.kDriverControllerPort);
  CommandXboxController m_driveCommandController = new CommandXboxController(OIConstants.kDriverControllerPort);
  CommandXboxController m_operatorController = new CommandXboxController(OIConstants.kOperatorControllerPort);
  

  // Establishing the Auto Chooser that will appear on the SmartDashboard
  private final SendableChooser<Command> autoChooser;
  
  
  DigitalInput m_FeederStop;
  
  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    
    m_FeederStop = new DigitalInput(9);
   

    // Add all actions to PathPlanner
    NamedCommands.registerCommand("Amp Shoot", m_Launcher.getLaunchSpeakerCommand().withTimeout(1.5));
    NamedCommands.registerCommand("Speaker Shoot", m_Launcher.getLaunchSpeakerCommand().withTimeout(2.5));
    NamedCommands.registerCommand("Intake", 
    m_Intake.getIntakeCommand().alongWith(m_Feeder.getFeederWheelIntakeCommand().until(m_FeederStop::get)).withTimeout(1.5));
    
    NamedCommands.registerCommand("Feeder", m_Feeder.getFeederWheelLaunchCommand().withTimeout(.75));
    
    NamedCommands.registerCommand("Intake Bottom", m_Intake.getIntakeCommand().until(m_FeederStop::get).withTimeout(0.2));
    
    NamedCommands.registerCommand("Launch Stop", m_Launcher.setLaunchZero().withTimeout(.1));
    NamedCommands.registerCommand("Intake Stop", m_Intake.setIntakeZero().withTimeout(.1));
    NamedCommands.registerCommand("Feeder Stop", m_Feeder.setFeederZero().withTimeout(.1));

   
   
      autoChooser = AutoBuilder.buildAutoChooser("Center 4-Note");

      SmartDashboard.putData("Auto Chooser", autoChooser); 




    // Configure the button bindings
    configureButtonBindings();

    // Configure default commands
    bindDrive();
  }


  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by
   * instantiating a {@link edu.wpi.first.wpilibj.GenericHID} or one of its
   * subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then calling
   * passing it to a
   * {@link JoystickButton}.
   */
  private void configureButtonBindings() {
    // new JoystickButton(m_driverController, Button.squa)
    //     .whileTrue(new RunCommand(
    //         () -> m_robotDrive.setX(),
    //         m_robotDrive));

            m_driveCommandController.a().whileTrue(new RunCommand(() -> m_robotDrive.setX(),m_robotDrive));
            m_driveCommandController.rightTrigger().whileTrue(m_RightClimberArm.getRightClimberArmDownCommand());
            m_driveCommandController.leftTrigger().whileTrue(m_LeftClimberArm.getLeftClimberArmDownCommand());
            m_driveCommandController.b().whileTrue(new TurnToAngle(m_robotDrive, -60, false));
            m_driveCommandController.x().whileTrue(new TurnToAngle(m_robotDrive, 60, false));
            m_driveCommandController.y().toggleOnTrue(
              m_RightClimberArm.getRightClimberArmUpCommand().withTimeout(8).alongWith
              (m_LeftClimberArm.getLeftClimberArmUpCommand().withTimeout(8)));

            m_driveCommandController.rightBumper().whileTrue(m_RightClimberArm.getRightClimberArmUpCommand());
            m_driveCommandController.leftBumper().whileTrue(m_LeftClimberArm.getLeftClimberArmUpCommand());


            m_operatorController.y().whileTrue(m_Launcher.getLaunchSpeakerCommand());
            m_operatorController.a().whileTrue(m_Launcher.getLaunchAmpCommand().alongWith());
            m_operatorController.rightTrigger().whileTrue(m_Feeder.getFeederWheelLaunchCommand());
            m_operatorController.leftTrigger().whileTrue(m_Feeder.getReverseFeederCommand());

            
            m_operatorController.b().whileTrue(m_Intake.getIntakeCommand().alongWith(m_Feeder.getFeederWheelIntakeCommand()).until(m_FeederStop::get).andThen(new ParallelCommandGroup(new RunCommand(() -> m_operatorController.getHID().setRumble(RumbleType.kBothRumble, 1)),new RunCommand(() -> m_driveCommandController.getHID().setRumble(RumbleType.kBothRumble, 1))))); 
            m_operatorController.b().onFalse(new ParallelCommandGroup(new RunCommand(() -> m_operatorController.getHID().setRumble(RumbleType.kBothRumble, 0)), new RunCommand(() -> m_driveCommandController.getHID().setRumble(RumbleType.kBothRumble, 0)))); 
            m_operatorController.leftBumper().whileTrue(m_Intake.getIntakeCommand().alongWith(m_Feeder.getFeederWheelIntakeCommand()));
  
                    // *******************************
                        // Path Plan to pose, then follow path
                        // *******************************
                        // On the fly path following
 //                       PathPlannerPath path = PathPlannerPath.fromPathFile("Get Cone");

                        // Create the constraints to use while pathfinding. The constraints defined in
                        // the path will only be used for the path.
 //                       PathConstraints constraints = new PathConstraints(
 //                           3.0, 4.0,
 //                           Units.degreesToRadians(540), Units.degreesToRadians(720));

                        // Since AutoBuilder is configured, we can use it to build pathfinding commands
 //                       Command pathfindingCommand = AutoBuilder.pathfindThenFollowPath(
 //                           path,
 //                           constraints,
 //                           3.0 // Rotation delay distance in meters. This is how far the robot should travel
                                // before attempting to rotate.
 //                       );
 //                       new JoystickButton(m_driverController, 3).onTrue(pathfindingCommand);

                        // *******************************
                        // Path Plan to pose
                        // *******************************
                        // Since we are using a holonomic drivetrain, the rotation component of this
                        // pose
                        // represents the goal holonomic rotation
//                        Pose2d targetPose = new Pose2d(2, 2.25, Rotation2d.fromDegrees(180));

                        // Create the constraints to use while pathfinding
//                        PathConstraints scoring_constraints = new PathConstraints(
//                            3.0, 4.0,
//                            Units.degreesToRadians(540), Units.degreesToRadians(720));

                        // Since AutoBuilder is configured, we can use it to build pathfinding commands
//                        Command scoring_pathfindingCommand = AutoBuilder.pathfindToPose(
//                            targetPose,
//                            scoring_constraints,
//                            0.0, // Goal end velocity in meters/sec
//                            0.0 // Rotation delay distance in meters. This is how far the robot should travel
//                                // before attempting to rotate.
//                        );
                    
                    
//                        new JoystickButton(m_driverController, 4).onTrue(scoring_pathfindingCommand);
   
   
   
              // new JoystickButton(driverXbox, 3).whileTrue(new RepeatCommand(new
                // InstantCommand(drivebase::lock, drivebase)));
             }

  
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */

   // The way we currentlly have this configured, out Autonomous is automatically
   // passed from the stored files based on the SmartDashboard selection we make
   
  public Command getAutonomousCommand() {
    
    return autoChooser.getSelected();
    }

    private int HeaidngCorrection() {

        var alliance = DriverStation.getAlliance();
                            if (alliance.isPresent()) {
                                if(alliance.get() == DriverStation.Alliance.Red){
                                  return 1;
                                }else{
                                  return -1;
                                }
                            }
                            return -1;

        }


        public void bindDrive(){

          m_robotDrive.setDefaultCommand(
        // The left stick controls translation of the robot.
        // Turning is controlled by the X axis of the right stick.
        new RunCommand(
            () -> m_robotDrive.drive(
                HeaidngCorrection()*MathUtil.applyDeadband(m_driverController.getLeftY(), OIConstants.kDriveDeadband),
                HeaidngCorrection()*MathUtil.applyDeadband(m_driverController.getLeftX(), OIConstants.kDriveDeadband),
                -MathUtil.applyDeadband(m_driverController.getRightX(), OIConstants.kDriveDeadband),
                true, true),
            m_robotDrive));
        }


}

