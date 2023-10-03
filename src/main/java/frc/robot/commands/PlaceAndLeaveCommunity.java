package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.DriveSubsystem;

public class PlaceAndLeaveCommunity extends SequentialCommandGroup{
    
    public PlaceAndLeaveCommunity(DriveSubsystem driveSubsystem,ArmSubsystem armSubsystem) {
        
        addCommands(
          

            new InstantCommand(() -> armSubsystem.zeroArmPosition()),
            new InstantCommand(() -> armSubsystem.setIntakeMotor(0.6)),//take in cube/cone
            new InstantCommand(() -> armSubsystem.setArmPosition(Constants.ArmPoses.ScoreMid)),

            new Wait(2),

            new InstantCommand(() -> armSubsystem.setIntakeMotor(-0.6)),

            new Wait(2),
            new InstantCommand(() -> armSubsystem.setIntakeMotor(0)),
            new InstantCommand(() -> armSubsystem.setArmPosition(Constants.ArmPoses.Tucked)),
            new Wait(2),
            // Drive forward the specified distance
            new TimedDrive(driveSubsystem, 4.5, -0.5)
            
            // Release the hatch
            //new Balance(driveSubsystem)
        );

      }

}
