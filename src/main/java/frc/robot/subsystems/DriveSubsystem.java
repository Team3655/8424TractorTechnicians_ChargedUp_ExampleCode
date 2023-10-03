package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveSubsystem extends SubsystemBase {
    
    private final CANSparkMax leftMotor1 = new CANSparkMax(1,MotorType.kBrushless);
    private final CANSparkMax leftMotor2 = new CANSparkMax(2,MotorType.kBrushless);
    private final CANSparkMax leftMotor3 = new CANSparkMax(3,MotorType.kBrushless);
    private final CANSparkMax rightMotor1 = new CANSparkMax(4,MotorType.kBrushless);
    private final CANSparkMax rightMotor2 = new CANSparkMax(5,MotorType.kBrushless);
    private final CANSparkMax rightMotor3 = new CANSparkMax(6,MotorType.kBrushless);

    public final SparkMaxPIDController leftController;
    public final SparkMaxPIDController rightController;

    RelativeEncoder leftEncoder;
    RelativeEncoder rightEncoder;

    public final AHRS gyro;

    public DifferentialDrive difDrive;

    public DriveSubsystem(){
        
        leftMotor1.restoreFactoryDefaults();
        leftMotor2.restoreFactoryDefaults();
        leftMotor3.restoreFactoryDefaults();
        rightMotor1.restoreFactoryDefaults();
        rightMotor2.restoreFactoryDefaults();
        rightMotor3.restoreFactoryDefaults();

        leftMotor1.setSmartCurrentLimit(35);
        leftMotor2.setSmartCurrentLimit(35);
        leftMotor3.setSmartCurrentLimit(35);
        rightMotor1.setSmartCurrentLimit(35);
        rightMotor2.setSmartCurrentLimit(35);
        rightMotor3.setSmartCurrentLimit(35);

        IdleMode driveTrainIdleMode = IdleMode.kBrake;
        leftMotor1.setIdleMode(driveTrainIdleMode);
        leftMotor2.setIdleMode(driveTrainIdleMode);
        leftMotor3.setIdleMode(driveTrainIdleMode);
        rightMotor1.setIdleMode(driveTrainIdleMode);
        rightMotor2.setIdleMode(driveTrainIdleMode);
        rightMotor3.setIdleMode(driveTrainIdleMode);

        leftMotor2.follow(leftMotor1);
        leftMotor3.follow(leftMotor1);
        rightMotor2.follow(rightMotor1);
        rightMotor3.follow(rightMotor1);

        rightMotor1.setInverted(true);
        leftMotor1.setInverted(false);

        double p = 4.1;
        double i = 0.001;
        double d = 0.00001;

        leftController = leftMotor1.getPIDController();
		leftController.setP(p);
		leftController.setI(i);
		leftController.setD(d);
        
        rightController = rightMotor1.getPIDController();
		rightController.setP(p);
		rightController.setI(i);
		rightController.setD(d);

        rightEncoder = rightMotor1.getEncoder();
        rightEncoder.setVelocityConversionFactor(0.000575);
        //rightEncoder.

        leftEncoder = leftMotor1.getEncoder();
        leftEncoder.setVelocityConversionFactor(0.000575);

        gyro = new AHRS(SerialPort.Port.kMXP);

        gyro.reset();

        difDrive = new DifferentialDrive(leftMotor1, rightMotor1);
    }

    @Override
    public void periodic() {
    }

    public void drive(double speed, double turn){
        difDrive.arcadeDrive(speed, turn);
        
        //setTargetRPM(speed * 3);
    }

    // public void setTargetRPM(double velocity){
    //     leftController.setReference(velocity, CANSparkMax.ControlType.kSmartVelocity);
    //     rightController.setReference(velocity, CANSparkMax.ControlType.kSmartVelocity);
    //     SmartDashboard.putNumber("left speed m/s", leftEncoder.getVelocity());
    //     SmartDashboard.putNumber("right speed m/s", rightEncoder.getVelocity());
    // }

}
