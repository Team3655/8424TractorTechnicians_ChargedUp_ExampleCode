package frc.robot;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.PlaceAndBalance;
import frc.robot.commands.PlaceAndLeaveCommunity;
import frc.robot.commands.TeleopDrive;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.DriveSubsystem;

public class RobotContainer {
    
    public final DriveSubsystem driveSubsystem = new DriveSubsystem();
    public final ArmSubsystem armSubsystem = new ArmSubsystem();

    private SendableChooser<Command> autoChooser = new SendableChooser<>();

    private final CommandXboxController controller =
      new CommandXboxController(OperatorConstants.programmingControllerPort);
    
    private final CommandJoystick leftJoy =
      new CommandJoystick(OperatorConstants.leftJoystickPort);
    private final CommandJoystick rightJoy =
      new CommandJoystick(OperatorConstants.rightJoystickPort);
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public 
  RobotContainer() {
    // Configure the trigger bindings
    configureBindings();

    Shuffleboard.getTab("LiveWindow").add(autoChooser);

    autoChooser.addOption("Place and drive back", new PlaceAndLeaveCommunity(driveSubsystem,armSubsystem) );
    autoChooser.addOption("Place Balance", new PlaceAndBalance(driveSubsystem,armSubsystem) );

  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */

  private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    
    //in
    //controller.y().onTrue(new InstantCommand(() -> armSubsystem.setArmPosition(Constants.ArmPoses.Tucked)));
    //controller.x().onTrue(new InstantCommand(() -> armSubsystem.setArmPosition(Constants.ArmPoses.ScoreLow)));
    //controller.b().onTrue(new InstantCommand(() -> armSubsystem.setArmPosition(Constants.ArmPoses.ScoreMid)));
    //controller.a().onTrue(new InstantCommand(() -> armSubsystem.setArmPosition(Constants.ArmPoses.ScoreHigh)));
    //out
    controller.leftBumper().onTrue(new InstantCommand(() -> armSubsystem.zeroArmPosition()));
    controller.rightBumper().onTrue(new InstantCommand(() -> armSubsystem.setArmSpeed(0)));

    controller.start().onTrue(new InstantCommand(() -> armSubsystem.setArmSpeed(0.4)));
    controller.start().onFalse(new InstantCommand(() -> armSubsystem.setArmSpeed(0)));
    controller.back().onTrue((new InstantCommand(() -> armSubsystem.setArmSpeed(-0.4))));
    controller.back().onFalse(new InstantCommand(() -> armSubsystem.setArmSpeed(0)));

    controller.leftTrigger(0.8).onTrue((new InstantCommand(() -> armSubsystem.setIntakeMotor(-0.6))));
    controller.leftTrigger(0.8).onFalse((new InstantCommand(() -> armSubsystem.setIntakeMotor(0))));

    controller.rightTrigger(0.8).onTrue((new InstantCommand(() -> armSubsystem.setIntakeMotor(0.6))));
    controller.rightTrigger(0.8).onFalse((new InstantCommand(() -> armSubsystem.setIntakeMotor(0))));
    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
    
    //driveSubsystem.setDefaultCommand(new TeleopDrive(driveSubsystem, () -> controller.getLeftX(), () -> controller.getRightY()));
    driveSubsystem.setDefaultCommand(new TeleopDrive(driveSubsystem, () -> leftJoy.getX(), () -> rightJoy.getY()));

    SmartDashboard.putBoolean("this configuration runs", true);
    

  }

  public Command getAutonomousCommand() {
    return autoChooser.getSelected();
  }

}
