package ca.team4519.FRC2017.subsystems;

import ca.team4519.FRC2017.Constants;
import ca.team4519.lib.Subsystem;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;

public class Drivebase extends Subsystem{

	VictorSP leftDriveMotor;
	VictorSP rightDriveMotor;
	
	double leftPower;
	double rightPower;
	
	
	ADXRS450_Gyro gyro;
	
	Encoder leftDriveEncoder;
	Encoder rightDriveEncoder;
	
	public void drivebaseInit() {
		leftDriveMotor = new VictorSP(Constants.leftDrivePWM);
		rightDriveMotor = new VictorSP(Constants.rightDrivePWM);
		
		gyro = new ADXRS450_Gyro();
		
		leftDriveEncoder = new Encoder(Constants.leftDriveEncoderA, Constants.leftDriveEncoderB, false);
		rightDriveEncoder = new Encoder(Constants.rightDriveEncoderA, Constants.rightDriveEncoderB, false);
		
		gyro.calibrate();
	}
	

	public void resetSensors() {
		leftDriveEncoder.reset();
		rightDriveEncoder.reset();
		gyro.reset();
		
	}

	public void arcadeDriveMath(double forwardAxis, double turningAxis) {
 		
		leftPower =(-(forwardAxis - turningAxis));
		rightPower =(forwardAxis + turningAxis);		
	}
	
	public void setLeftRightPower(double leftOutput, double rightOutput) {
		
		leftDriveMotor.set(leftOutput);
		rightDriveMotor.set(rightOutput);
	}
	
	public void disableSubsystem() {
		
		leftPower = 0;
		rightPower = 0;
		
	}

	public void update() {
		
		SmartDashboard.putNumber("Gyro Angle", gyro.getAngle());
		
	}
	
	
}
