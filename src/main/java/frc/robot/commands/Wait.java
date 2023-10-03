package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class Wait extends CommandBase{
    
    private Timer timer;
    private double time;

    public Wait(double time){
        this.timer = new Timer();
        this.time = time;
    }

    public void initialize(){
        timer.restart();
        
    }


    public boolean isFinished() {
        return timer.get() > time;
    }

}
