package ca.team4519.FRC2017.subsystems;

import ca.team4519.FRC2017.Constants;
import ca.team4519.FRC2017.Gains;
import ca.team4519.lib.Subsystem;
import ca.team4519.lib.DrivetrainOutput;
import ca.team4519.lib.RobotPose;
import ca.team4519.FRC2017.subsystems.controllers.DriveLineController;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;

public class Drivebase extends Subsystem{

	public static Drivebase thisInstance = new Drivebase();
	
	public static Drivebase grabInstance(){
		return thisInstance;
	}
	
	public interface Controllers {
		DrivetrainOutput update(RobotPose pose);
	}
	
	private final VictorSP leftDriveMotor, rightDriveMotor;
	Encoder leftDriveEncoder, rightDriveEncoder;
	ADXRS450_Gyro gyro;
	
	private Controllers controller = null;
	
	double leftPower, rightPower;
	protected final double inchesPerTick = Gains.Drive.WheelSize_Inches * Math.PI / Gains.Drive.EncoderTicksPerRev;
	
	private RobotPose storedPose = new RobotPose(0, 0, 0, 0, 0, 0);
	
	public Drivebase() {
		leftDriveMotor = new VictorSP(Constants.leftDrivePWM);
		rightDriveMotor = new VictorSP(Constants.rightDrivePWM);
		
		gyro = new ADXRS450_Gyro();
		
		leftDriveEncoder = new Encoder(Constants.leftDriveEncoderA, Constants.leftDriveEncoderB, false);
		rightDriveEncoder = new Encoder(Constants.rightDriveEncoderA, Constants.rightDriveEncoderB, false);
		leftDriveEncoder.setDistancePerPulse(inchesPerTick);
		rightDriveEncoder.setDistancePerPulse(inchesPerTick);
		
		gyro.calibrate();
	}
	
	public void setDistanceTarget(double distance, double velocity){
		double whatVelocity = Math.min(Gains.Drive.ROBOT_MAX_VELOCITY, Math.max(velocity, 0));
		controller = new DriveLineController(getRobotPose(), distance, whatVelocity);
	}
	
	public void setDistanceTarget(double distance){
		setDistanceTarget(distance,Gains.Drive.ROBOT_MAX_VELOCITY);
	}
	
	public void resetSensors() {
		leftDriveEncoder.reset();
		rightDriveEncoder.reset();
		gyro.reset();
		
	}
	
	public double leftVelocity() {
		return leftDriveEncoder.getRate();
	}
	
	public double rightVelocity() {
		return rightDriveEncoder.getRate();
	}

	public double avgVelocity() {
		return ((rightVelocity()+leftVelocity())/2);
	}
	
	public double currHeading() {
		return gyro.getAngle();
	}
		
	public void arcadeDriveMath(double forwardAxis, double turningAxis) {
 		
		leftPower =(-(forwardAxis - turningAxis));
		rightPower =(forwardAxis + turningAxis);		
	}
	
	public void setDrivePower(DrivetrainOutput output) {
		
		leftDriveMotor.set(output.leftOutput);
		rightDriveMotor.set(output.rightOutput);
	}
	
	public void controlLoops(){
		if(controller == null){
			return;
		}
		setDrivePower(controller.update(getRobotPose()));
	}
	
	public RobotPose getRobotPose(){
		storedPose.reset(
				leftDriveEncoder.getDistance(),
				leftDriveEncoder.getDistance(),
				leftDriveEncoder.getRate(),
				rightDriveEncoder.getRate(),
				gyro.getAngle(),
				gyro.getRate());
		return storedPose;
		
	}
	
	public void disableSubsystem() {
		
		leftPower = 0;
		rightPower = 0;
		
	}
	
	public void update() {
		SmartDashboard.putNumber("Left velocity", leftVelocity());
		SmartDashboard.putNumber("Left Distance", leftDriveEncoder.getDistance());
		SmartDashboard.putNumber("Right Velocity", rightVelocity());
		SmartDashboard.putNumber("Right Distance", leftDriveEncoder.getDistance());
		SmartDashboard.putNumber("Gyro Angle", currHeading());
		
	}
	
}
