package ca.team4519.FRC2017.subsystems;

import ca.team4519.FRC2017.Constants;
import ca.team4519.FRC2017.Gains;
import ca.team4519.lib.Subsystem;
import ca.team4519.lib.DrivetrainOutput;
import ca.team4519.lib.RobotPose;
import ca.team4519.FRC2017.subsystems.controllers.DriveBackwardsController;
import ca.team4519.FRC2017.subsystems.controllers.DriveLineController;
import ca.team4519.FRC2017.subsystems.controllers.RotationController;
import ca.team4519.lib.Thread;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.AnalogGyro;

import com.team254.lib.util.MA3AnalogEncoder;

public class Drivebase extends Subsystem implements Thread{

	private static Drivebase thisInstance = new Drivebase();
	
	public static Drivebase grabInstance(){
		return thisInstance;
	}
	
	public interface Controllers {
		DrivetrainOutput update(RobotPose pose);
	}
	
	private final Talon leftDriveMotor;
	private final Talon rightDriveMotor;
	
	private AnalogInput leftDriveEncoder_Input;
	private AnalogInput rightDriveEncoder_Input;
	Encoder left;
	Encoder right;
	
	Counter leftDriveEncoder;
	Counter rightDriveEncoder;
	private AnalogGyro gyro;
	MA3AnalogEncoder leftEncoder;
	MA3AnalogEncoder rightEncoder;
	
	private Controllers controller = null;
	
	public double leftPower, rightPower;
	protected final double inchesPerTick = Gains.Drive.WheelSize_Inches * Math.PI / Gains.Drive.EncoderTicksPerRev;
	
	//private RobotPose storedPose = new RobotPose(0, 0, 0, 0, 0, 0);
	
	public Drivebase() {
		thisInstance = this;
		
		leftDriveMotor = new Talon(Constants.leftDrivePWM);
		rightDriveMotor = new Talon(Constants.rightDrivePWM);
		
		gyro = new AnalogGyro(0);
		
		leftEncoder = new MA3AnalogEncoder(1);
		rightEncoder =new MA3AnalogEncoder(2);

	}
	 /*
	public void setDistanceTarget(double distance, double velocity){
		double whatVelocity = Math.min(Gains.Drive.ROBOT_MAX_VELOCITY, Math.max(velocity, 0));
		controller = new DriveLineController(getRobotPose(), distance, whatVelocity);
	}
	
	public void setDistanceTarget(double distance){
		setDistanceTarget(distance,Gains.Drive.ROBOT_MAX_VELOCITY);
	}
	
	public void setReverseDistanceTarget(double distance, double velocity){
		double whatVelocity = Math.min(Gains.Drive.ROBOT_MAX_VELOCITY, Math.max(velocity, 0));
		controller = new DriveBackwardsController(getRobotPose(), distance, whatVelocity);
	}
	
	public void setReverseDistanceTarget(double distance){
		setReverseDistanceTarget(distance, Gains.Drive.ROBOT_MAX_VELOCITY);
	}
	
	
	public void setTurnTarget(double angle, double velocity){
		double whatVelocity = Math.min(Gains.Drive.ROBOT_MAX_ROTATIONAL_VELOCITY, Math.max(velocity,  0));
		controller = new RotationController(getRobotPose(), angle, whatVelocity);
	}
	
	public void setTurnTarget(double angle){
		setTurnTarget(angle, Gains.Drive.ROBOT_MAX_ROTATIONAL_VELOCITY);
	}
	*/
	public void resetSensors() {
		leftEncoder.zero();
		rightEncoder.zero();
		gyro.reset();
		
	}
	
	public double leftEncoderDist(){
		return leftEncoder.getContinuousAngleDegrees() * inchesPerTick;
	}
	
	public double rightEncoderDist(){
		return rightEncoder.getContinuousAngleDegrees() * inchesPerTick;
	}
	
	public double leftEncoderVel(){
		double last = 0;
		double vel = (last-leftEncoderDist())/0.02;
		last = leftEncoderDist();
		return vel;
	}
	
	public double rightEncoderVel(){
		double last = 0;
		double vel = (last-rightEncoderDist())/0.02;
		last = rightEncoderDist();
		return vel;
		
	}
	
	public void resetEncoders() {
		leftEncoder.zero();
		rightEncoder.zero();
	}
	
	public double currHeading() {
		return gyro.getAngle();
	}
		
	public DrivetrainOutput arcadeDriveMath(double forwardAxis, double turningAxis) {
 		
		forwardAxis = (Math.abs(forwardAxis) > 0.1)? forwardAxis : 0.0;
		turningAxis = (Math.abs(turningAxis) > 0.1)? turningAxis : 0.0;
		
		leftPower =(forwardAxis + turningAxis);
		rightPower =(-(forwardAxis - turningAxis));	
		
		return new DrivetrainOutput(leftPower, rightPower);
	}
	
	public void setDrivePower(DrivetrainOutput output) {
		
		leftDriveMotor.set(output.leftOutput);
		rightDriveMotor.set(output.rightOutput);
	}
	
	public void controlLoops(){
		if(controller == null){
			return;
		}
	//	setDrivePower(controller.update(getRobotPose()));
	}
	
/*	public RobotPose getRobotPose(){
		storedPose.reset(
				leftDriveEncoder.getDistance(),
				rightDriveEncoder.getDistance(),
				leftDriveEncoder.getRate(),
				rightDriveEncoder.getRate(),
				gyro.getAngle(),
				gyro.getRate());
		return storedPose;
		
	}
	*/
	public void disableSubsystem() {
		leftPower = 0;
		rightPower = 0;
	}
	
	public void update() {
		SmartDashboard.putNumber("Left Encoder (Inches)", leftEncoderDist());
		SmartDashboard.putNumber("Rgiht Encoder (Inches)", rightEncoderDist());
		SmartDashboard.putNumber("Left velocity (Inches/Sec)", leftEncoderVel());
		SmartDashboard.putNumber("Right Velocity (Inches/Sec)", rightEncoderVel());
		SmartDashboard.putNumber("Gyro Angle", currHeading());
		
	}
	
}
