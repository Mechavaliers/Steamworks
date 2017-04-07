package ca.team4519.FRC2017.subsystems;

import ca.team4519.FRC2017.Constants;
import ca.team4519.FRC2017.Gains;
import ca.team4519.FRC2017.subsystems.controllers.DriveLineController;
import ca.team4519.FRC2017.subsystems.controllers.RotationController;
import ca.team4519.lib.Thread;
import ca.team4519.lib.Subsystem;
import ca.team4519.lib.DrivetrainOutput;
import ca.team4519.lib.RobotPose;
import ca.team4519.lib.motion.Coords;
import ca.team4519.lib.motion.Path;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
	
	//Turning right is -degrees and left is +degrees
	
	private final double driveDeadzone = 0.03;
	private double QSA;
	private final double turnSensitivity = Gains.Drive.HANDLING_MODIFIER;
	
	private final Talon leftDriveMotor;
	private final Talon rightDriveMotor;
	
	boolean lowMode = false, stayLow = false;
	private final MA3AnalogEncoder leftEncoder;
	private final MA3AnalogEncoder rightEncoder;
	
	private Coords robotCoords = new Coords(0,0);
	
	private AnalogGyro gyro;
	
	private Controllers controller = null;
	
	public double leftPower, rightPower;
	protected final double inchesPerTick = Gains.Drive.WheelSize_Inches * Math.PI / Gains.Drive.EncoderDegsPerRev;
	
	private RobotPose storedPose = new RobotPose(0, 0, 0, 0, 0, 0);
	
	public Drivebase() {
		thisInstance = this;
		
		leftDriveMotor = new Talon(Constants.leftDrivePWM);
		rightDriveMotor = new Talon(Constants.rightDrivePWM);
		
		gyro = new AnalogGyro(0);
		
		leftEncoder = new MA3AnalogEncoder(1);
		rightEncoder = new MA3AnalogEncoder(2);

	}
	
	public void followPath(Path path, double velocity) {	
		double whatVelocity = Math.min(Gains.Drive.ROBOT_MAX_VELOCITY, Math.max(velocity, 0));
		setTurnTarget(path.update(robotCoords).getAngle());
		setDistanceTarget(path.update(robotCoords).getDistance(), whatVelocity);	
	}
	
	public void followPath(Path path) {
		setTurnTarget(path.update(robotCoords).getAngle());
		setDistanceTarget(path.update(robotCoords).getDistance());	
	}
	
	 
	public void setDistanceTarget(double distance, double velocity) {
		double whatVelocity = Math.min(Gains.Drive.ROBOT_MAX_VELOCITY, Math.max(velocity, 0));
		controller = new DriveLineController(getRobotPose(), distance, whatVelocity);
	}
	
	public void setDistanceTarget(double distance) {
		setDistanceTarget(distance,Gains.Drive.ROBOT_MAX_VELOCITY);
	}
	
	public void setTurnTarget(double angle, double velocity) {
		double whatVelocity = Math.min(Gains.Drive.ROBOT_MAX_ROTATIONAL_VELOCITY, Math.max(velocity,  0));
		controller = new RotationController(getRobotPose(), angle, whatVelocity);
	}
	
	public void setTurnTarget(double angle) {
		setTurnTarget(angle, Gains.Drive.ROBOT_MAX_ROTATIONAL_VELOCITY);
	}
	
	public void resetSensors() {
		leftEncoder.zero();
		rightEncoder.zero();
		gyro.reset();
				
	}
	
	public void killController() {
		controller = null;
	}
	
	public double leftEncoderDist(){
		return leftEncoder.getContinuousAngleDegrees() * inchesPerTick;
	}
	
	public double rightEncoderDist(){
		return -rightEncoder.getContinuousAngleDegrees() * inchesPerTick;
	}
	
	public double leftEncoderVel(){
		return leftEncoder.getRate() * inchesPerTick;
	}
	
	public double rightEncoderVel(){
		return rightEncoder.getRate() * inchesPerTick;
	}
	
	public double averageDistance() {
		return (leftEncoderDist() + rightEncoderDist()) / 2;
	}
	
	public void resetEncoders() {
		leftEncoder.zero();
		rightEncoder.zero();
	}
	
	public double currHeading() {
		return gyro.getAngle();
	}
	
	public double rotRate() {
		return gyro.getRate();
	}
	
	public Coords getCoords(){
		getRobotPose();
		
		double x = averageDistance() * Math.sin(gyro.getAngle());
		double y = averageDistance() * Math.cos(gyro.getAngle());
		
		return new Coords(x,y);
		
	}
		
	public DrivetrainOutput arcadeDriveMath(double forwardAxis, double turningAxis, boolean switchDPI) {
 		
		if(!switchDPI){
			lowMode = true;
		}else if (lowMode){
			stayLow = !stayLow;
			lowMode = false;
		}
		
		forwardAxis = (Math.abs(forwardAxis) > 0.1)? forwardAxis : 0.0;
		turningAxis = (Math.abs(turningAxis) > 0.1)? turningAxis : 0.0;
		
		if(Math.abs(turningAxis) > 90){
				forwardAxis = forwardAxis/2;
		}
		
		if(stayLow){
			leftPower = (forwardAxis + turningAxis)/2;
			rightPower = (forwardAxis - turningAxis)/2;
			
		}else{
			leftPower = forwardAxis + turningAxis;
			rightPower = forwardAxis - turningAxis;	
		}
		
		return new DrivetrainOutput(leftPower, rightPower);
	}
	
	public DrivetrainOutput cheesyArcade(double throttle, double turn, boolean tip){
		
		throttle = (Math.abs(throttle) > Math.abs(driveDeadzone))? throttle : 0.0;
		turn = (Math.abs(turn) > Math.abs(driveDeadzone))? turn : 0.0;
		
		double morePower;
		double turnPower;
		
		if(tip){
			if(Math.abs(throttle) < 0.2){
				double alpha = 0.1;
						QSA = (1-alpha) * QSA + alpha * ((Math.abs(turn) > Math.abs(1.0))? turn: 0.0) * 2;
			}
			morePower = 1.0;
			turnPower = turn;
		}else{
		
			morePower = 0.0;
			turnPower = Math.abs(throttle) * turn *turnSensitivity - QSA;
			if(QSA > 1) {
				QSA-=1;
			}else if(QSA < -1){
				QSA += 1;
			}else{
				QSA = 0.0;
			}
		}
		
		double right = throttle + turnPower;
		double left = throttle - turnPower;
		
		if(left > 1.0){
			right += morePower * (left-1.0);
			left = 1.0;
		}else if(right > 1.0){
			left += morePower * (right-1.0);
			right = 1.0;
		}else if(left < -1.0){
			right -= morePower * (-1.0 - left);
			left = -1.0;
		}else if(right < -1.0){
			left -= morePower * (-1.0 - right);
			right=1.0;
			
		}
		
		return new DrivetrainOutput(left, right);
	}
	
	public void setDrivePower(DrivetrainOutput output) {
		
		leftDriveMotor.set(output.leftOutput);
		rightDriveMotor.set(-output.rightOutput);
	}
	
	public void controlLoops(){
		robotCoords = getCoords();
		if(controller == null){
			return;
		}
		setDrivePower(controller.update(getRobotPose()));
	}
	
	public RobotPose getRobotPose(){
		storedPose.reset(
				leftEncoderDist(),
				rightEncoderDist(),
				leftEncoderVel(),
				rightEncoderVel(),
				gyro.getAngle(),
				gyro.getRate());
		return storedPose;
		
	}
	
	public void disableSubsystem() {
		leftPower = 0;
		rightPower = 0;
		controller = null;
	}
	
	public void update() {
		SmartDashboard.putNumber("Robot Angle", currHeading());
		SmartDashboard.putNumber("Robot Rotational Rate", rotRate());
		SmartDashboard.putNumber("Left Encoder Distance", leftEncoderDist());
		SmartDashboard.putNumber("Rgiht Encoder Distance", rightEncoderDist());
		SmartDashboard.putNumber("Left Encoder Velocity", leftEncoderVel());
		SmartDashboard.putNumber("Right Encoder Velocity", rightEncoderVel());
		SmartDashboard.putNumber("Left Output", leftDriveMotor.get());
		SmartDashboard.putNumber("Right Output", rightDriveMotor.get());
		SmartDashboard.putString("Robot Coords", robotCoords.toString());
		if(controller == null){
			SmartDashboard.putNumber("Controller Status: left", 0);
			SmartDashboard.putNumber("Controller Status: right", 0);	
		}else{
			SmartDashboard.putNumber("Controller Status: left", controller.update(getRobotPose()).leftOutput);
			SmartDashboard.putNumber("Controller Status: right", controller.update(getRobotPose()).rightOutput);
		}
		
	}
	
}
