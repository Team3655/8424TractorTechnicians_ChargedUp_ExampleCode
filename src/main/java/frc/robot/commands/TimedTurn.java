package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class TimedTurn extends CommandBase{
    
    private final DriveSubsystem driveSubsystem;
    private double time;
    private edu.wpi.first.wpilibj.Timer timer;
    private double speed;

    public TimedTurn(DriveSubsystem subsystem, double time, double speed){
        this.driveSubsystem = subsystem;
        this.time = time;
        this.speed = speed;
        timer = new edu.wpi.first.wpilibj.Timer();
        addRequirements(subsystem);
    }

    public void initialize(){
        timer.restart();
        driveSubsystem.difDrive.arcadeDrive(0, 0);
    }

    public void execute(){
        driveSubsystem.difDrive.arcadeDrive(0, speed); 
    }

    public void end(boolean interupted){
        driveSubsystem.difDrive.arcadeDrive(0, 0); 
    }

    public boolean isFinished() {
        return timer.get() >= time;
    }

}