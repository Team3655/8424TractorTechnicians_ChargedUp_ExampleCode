package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class DriveUntilOnChargeStation extends CommandBase {

    private final DriveSubsystem driveSubsystem;
    private Timer timer;
    private double time;
    private double speed;

    public DriveUntilOnChargeStation(DriveSubsystem subsystem, double time, double speed) {
        this.driveSubsystem = subsystem;
        this.timer = new Timer();
        this.time = time;
        this.speed = speed;
        addRequirements(subsystem);
    }

    public void initialize() {
        timer.restart();
        driveSubsystem.drive(0, 0);

    }

    public void execute() {
        driveSubsystem.drive(speed, 0);

    }

    public void end(boolean interupted) {
        driveSubsystem.drive(0, 0);

    }

    public boolean isFinished() {
        return timer.get() > time || Math.abs(driveSubsystem.gyro.getPitch()) > 10;
    }

}
