// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.Forward;
import frc.robot.subsystems.RomiDrivetrain;
import edu.wpi.first.wpilibj2.command.Command;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  public final static RomiDrivetrain m_romiDrivetrain = new RomiDrivetrain();

  public final ExampleCommand m_autoCommand = new ExampleCommand(m_romiDrivetrain);

  private final Joystick m_controller  = new Joystick(0);

  private final SendableChooser<Command> m_chooser = new SendableChooser<>();
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
   

  }
  private void configureButtonBindings() {
    m_romiDrivetrain.setDefaultCommand(getArcadeDriveCommand());

    m_chooser.setDefaultOption("DriveForward", new Forward(10));
  }
  
  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
 

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_chooser.getSelected();
  }
  public Command getArcadeDriveCommand(){
    return new ArcadeDrive(m_romiDrivetrain, () -> -m_controller.getRawAxis(1), () -> m_controller.getRawAxis(4),
    () -> m_controller.getRawAxis(2), () -> m_controller.getRawAxis(3));
  }
}
