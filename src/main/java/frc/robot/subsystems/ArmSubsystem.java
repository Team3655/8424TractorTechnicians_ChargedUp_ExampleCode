package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ArmSubsystem extends SubsystemBase {
    
    private final CANSparkMax armMotor = new CANSparkMax(7,MotorType.kBrushless);
    private final CANSparkMax intakeMotor = new CANSparkMax(8,MotorType.kBrushless);
    public final RelativeEncoder armEncoder;
    public final SparkMaxPIDController armController;

    public ArmSubsystem() {

        armMotor.restoreFactoryDefaults();
        armMotor.setSmartCurrentLimit(12);
        armEncoder = armMotor.getEncoder();
        armController = armMotor.getPIDController();
        

        intakeMotor.restoreFactoryDefaults();
        intakeMotor.setSmartCurrentLimit(20);
        intakeMotor.setSecondaryCurrentLimit(12);

        double p = 0.02;
        double i = 0.0;
        double d = 0.0;

		armController.setP(p);
		armController.setI(i);
		armController.setD(d);
    }

    @Override
    public void periodic() {
        super.periodic();
    }

    public void setArmSpeed(double speed){
        armMotor.set(speed);
    }

    public void setArmPosition(double position){
        armController.setReference(position, ControlType.kPosition);
    }

    public void zeroArmPosition(){
        armEncoder.setPosition(0);
    }
    

    public void setIntakeMotor(double num){
        intakeMotor.set(num);
    }

}
