// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

/**
 * This is a demo program showing the use of the DifferentialDrive class, specifically it contains
 * the code necessary to operate a robot with tank drive.
 */
public class Robot extends TimedRobot {
  

  RobotContainer robotContainer;

  @Override
  public void robotInit() {
    // We need to invert one side of the drivetrain so that positive voltages
    // result in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side instead.
    robotContainer = new RobotContainer();
  }

  public void robotPeriodic(){
    CommandScheduler.getInstance().run();

    SmartDashboard.putNumber("Arm position", robotContainer.armSubsystem.armEncoder.getPosition());
    
  }

  @Override
  public void teleopPeriodic() {
    
  }

  private Command autonomousCommand;

  public void autonomousInit(){

    //if (true) return;

    autonomousCommand = robotContainer.getAutonomousCommand();

		// schedule the autonomous command (example)
		if (autonomousCommand != null) {
			autonomousCommand.schedule();
		}

  }

  

  public void autonomousPeriodic(){
    
  }

}
