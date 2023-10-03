package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class Balance extends CommandBase{
    
    private final DriveSubsystem driveSubsystem;
    
    public Balance(DriveSubsystem subsystem){
        this.driveSubsystem = subsystem;
        
        
        addRequirements(subsystem);
    }

    public void initialize(){
        
        driveSubsystem.drive(0, 0);
        newController.reset();
        
    }

    //good
    PIDController newController = new PIDController(0.02, 0.001, 0);

    //PIDController newController = new PIDController(0.023, 0.0015, 0.0001);

    public void execute(){
        
        

        double offset = driveSubsystem.gyro.getPitch();

        SmartDashboard.putNumber("offset", offset);


        if (Math.abs(offset) > 20) offset = offset * 0.2;
        
        if (Math.abs(offset) < 0.5) newController.reset();

        double output = newController.calculate(offset,0);
        
        
        SmartDashboard.putNumber("power", output);
        
        output = (output > 0.8) ? 0.8 : output;;

        driveSubsystem.drive(output,0);

    }

    public void end(boolean interupted){
        driveSubsystem.drive(0, 0); 
        
    }

    public boolean isFinished() {
        return false;
    }

}
