package ca.team4519.FRC2017.subsystems;

import ca.team4519.lib.Subsystem;
import ca.team4519.FRC2017.Constants;
import ca.team4519.FRC2017.Gains;
import ca.team4519.lib.pid.FlywheelController;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Shooter extends Subsystem{

	public static Shooter thisInstance = new Shooter();
	
	public Talon leftFlywheel = new Talon(Constants.leftFlywheelPWM);
	public Talon rightFlywheel = new Talon(Constants.rightFlywheelPWM);
	
	public Counter leftFlywheel_sensor = new Counter(Constants.leftFlywheelEncoder);
	public Counter rightFlywheel_sensor = new Counter(Constants.rightFlywheelEncoder);
	
	public FlywheelController leftFlywheel_Controller = new FlywheelController(
			leftFlywheel_sensor,
			Gains.Flywheel.LeftRPM_P, 
			Gains.Flywheel.LeftRPM_I, 
			Gains.Flywheel.LeftRPM_D, 
			Gains.Flywheel.LeftRPM_F, 
			Gains.Flywheel.LeftTicksPerRev, 
			Gains.Flywheel.RPM_Deadband,
			Gains.Flywheel.Left_Inverted);
	
	public FlywheelController rightFlywheel_Controller = new FlywheelController(
			rightFlywheel_sensor,
			Gains.Flywheel.RightRPM_P, 
			Gains.Flywheel.RightRPM_I, 
			Gains.Flywheel.RightRPM_D, 
			Gains.Flywheel.RightRPM_F, 
			Gains.Flywheel.RightTicksPerRev, 
			Gains.Flywheel.RPM_Deadband,
			Gains.Flywheel.Right_Inverted);
	
	public static Shooter grabInstance() {
		return thisInstance;
	}
	
	public void setRPMTarget(double target){
		leftFlywheel_Controller.setRPM(target);
		rightFlywheel_Controller.setRPM(target);
	}
	
	public void enableFlywheels() {
		leftFlywheel.set(leftFlywheel_Controller.controllerOutput());
		rightFlywheel.set(rightFlywheel_Controller.controllerOutput());
	}
	
	public double getLeftFlywheelVelocity(){
		return (leftFlywheel_sensor.getRate() / Gains.Flywheel.LeftTicksPerRev) / 60;
	}
	
	public double getLeftFlywheelRevsPerMin(){
		return 12;
	}

	public double getRightFlywheelVelocity(){
		return (rightFlywheel_sensor.getRate() / Gains.Flywheel.RightTicksPerRev) / 60;
	}
	
	public double getRightFlywheelRevsPerMin(){
		return 12;
	}
	
	public void resetSensors() {
		leftFlywheel_sensor.reset();
		rightFlywheel_sensor.reset();
		
		
	}

	public void update() {
		SmartDashboard.putBoolean("Left FlyWheel Controller Status", leftFlywheel_Controller.isEnabled());
		SmartDashboard.putNumber("Left Flywheel RPM", getLeftFlywheelVelocity());
		SmartDashboard.putBoolean("Is Left on Target?", leftFlywheel_Controller.inRange());
		SmartDashboard.putBoolean("Right FlyWheel Controller Staus", rightFlywheel_Controller.isEnabled());
		SmartDashboard.putNumber("Rgiht Flywheel RPM", getRightFlywheelVelocity());
		SmartDashboard.putBoolean("Is Right on Target?", rightFlywheel_Controller.inRange());
	}


	public void disableSubsystem() {
	
		leftFlywheel_Controller.disable();
		rightFlywheel_Controller.disable();
		
	}

}
