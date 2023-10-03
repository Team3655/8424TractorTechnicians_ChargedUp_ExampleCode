package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.DriveSubsystem;

public class PlaceAndBalance extends SequentialCommandGroup{
    
    public PlaceAndBalance(DriveSubsystem driveSubsystem,ArmSubsystem armSubsystem) {
        
        addCommands(
            
            //take in cube and set arm to mid
            new InstantCommand(() -> armSubsystem.zeroArmPosition()),
            new InstantCommand(() -> armSubsystem.setIntakeMotor(0.6)),//take in cube/cone
            new InstantCommand(() -> armSubsystem.setArmPosition(Constants.ArmPoses.ScoreMid)),

            new Wait(1),
            
            //drop cube
            new InstantCommand(() -> armSubsystem.setIntakeMotor(-0.6)),

            new Wait(1),
            //reset motors and tuck arm
            new InstantCommand(() -> armSubsystem.setIntakeMotor(0)),
            new InstantCommand(() -> armSubsystem.setArmPosition(Constants.ArmPoses.Tucked)),
            
            new Wait(0.5),
            new TimedDrive(driveSubsystem, 0.5, -0.35),
            // Drive forward the specified distance
            new DriveUntilOnChargeStation(driveSubsystem, 4, -0.5),
            new TimedDrive(driveSubsystem, 0.5, -0.5),
            // Release the hatch
            new Balance(driveSubsystem)
        );

      }

}
