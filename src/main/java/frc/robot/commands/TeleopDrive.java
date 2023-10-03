package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class TeleopDrive extends CommandBase{
    
  private final DriveSubsystem driveSubsystem;
  private final DoubleSupplier forward;
  private final DoubleSupplier turn;

  private SlewRateLimiter limiter = new SlewRateLimiter(1.5);

  /**
   * Creates a new DefaultDrive.
   *
   * @param subsystem The drive subsystem this command wil run on.
   * @param forward The control input for driving forwards/backwards
   * @param rotation The control input for turning
   */
  public TeleopDrive(
    DriveSubsystem subsystem, 
    DoubleSupplier turn, 
    DoubleSupplier forward 
    ) {

    driveSubsystem = subsystem;
    this.forward = forward;
    this.turn = turn;
    
    addRequirements(driveSubsystem);

    SmartDashboard.putBoolean("this constructor runs", true);
  }

  @Override
  public void execute() {
    SmartDashboard.putBoolean("this runs", true);
    SmartDashboard.putNumber("speed:", forward.getAsDouble());
    driveSubsystem.drive( limiter.calculate(forward.getAsDouble()/-1), turn.getAsDouble()/-2);
  }


}
