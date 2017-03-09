package ca.team4519.FRC2017.subsystems;

import ca.team4519.FRC2017.Constants;
import ca.team4519.FRC2017.Gains;
import ca.team4519.FRC2017.subsystems.controllers.DriveLineController;
import ca.team4519.FRC2017.subsystems.controllers.RotationController;
import ca.team4519.lib.Thread;
import ca.team4519.lib.Subsystem;
import ca.team4519.lib.DrivetrainOutput;
import ca.team4519.lib.RobotPose;

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
	
	private final Talon leftDriveMotor;
	private final Talon rightDriveMotor;
	
	boolean lowMode = false, stayLow = false;
	private final MA3AnalogEncoder leftEncoder;
	private final MA3AnalogEncoder rightEncoder;
	
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
		rightEncoder =new MA3AnalogEncoder(2);

	}
	 
	public void setDistanceTarget(double distance, double velocity){
		double whatVelocity = Math.min(Gains.Drive.ROBOT_MAX_VELOCITY, Math.max(velocity, 0));
		controller = new DriveLineController(getRobotPose(), distance, whatVelocity);
	}
	
	public void setDistanceTarget(double distance){
		setDistanceTarget(distance,Gains.Drive.ROBOT_MAX_VELOCITY);
	}
	
	public void setTurnTarget(double angle, double velocity){
		double whatVelocity = Math.min(Gains.Drive.ROBOT_MAX_ROTATIONAL_VELOCITY, Math.max(velocity,  0));
		controller = new RotationController(getRobotPose(), angle, whatVelocity);
	}
	
	public void setTurnTarget(double angle){
		setTurnTarget(angle, Gains.Drive.ROBOT_MAX_ROTATIONAL_VELOCITY);
	}
	
	public void resetSensors() {
		leftEncoder.zero();
		rightEncoder.zero();
		gyro.reset();
		
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
	
	public void resetEncoders() {
		leftEncoder.zero();
		rightEncoder.zero();
	}
	
	public double currHeading() {
		return gyro.getAngle();
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
	
	public void setDrivePower(DrivetrainOutput output) {
		
		leftDriveMotor.set(output.leftOutput);
		rightDriveMotor.set(-output.rightOutput);
	}
	
	public void controlLoops(){
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
		SmartDashboard.putNumber("Left Encoder (Inches)", leftEncoderDist());
		SmartDashboard.putNumber("Rgiht Encoder (Inches)", rightEncoderDist());
		SmartDashboard.putNumber("Left velocity (Inches/Sec)", leftEncoderVel());
		SmartDashboard.putNumber("Left Output", leftDriveMotor.get());
		SmartDashboard.putNumber("Right Output", rightDriveMotor.get());
		SmartDashboard.putNumber("Right Velocity (Inches/Sec)", rightEncoderVel());
		if(controller == null){
			SmartDashboard.putNumber("Controller Status: left", 0);
			SmartDashboard.putNumber("Controller Status: right", 0);	
		}else{
		SmartDashboard.putNumber("Controller Status: left", controller.update(getRobotPose()).leftOutput);
		SmartDashboard.putNumber("Controller Status: right", controller.update(getRobotPose()).rightOutput);
		}
		
	}
	
}
