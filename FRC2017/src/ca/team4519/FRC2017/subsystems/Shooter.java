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
			leftFlywheel_sensor, leftFlywheel, 
			Gains.Flywheel.LeftRPM_P, 
			Gains.Flywheel.LeftRPM_I, 
			Gains.Flywheel.LeftRPM_D, 
			Gains.Flywheel.LeftRPM_F, 
			Gains.Flywheel.LeftTicksPerRev, 
			Gains.Flywheel.RPM_Deadband);
	
	public FlywheelController rightFlywheel_Controller = new FlywheelController(
			rightFlywheel_sensor, rightFlywheel, 
			Gains.Flywheel.RightRPM_P, 
			Gains.Flywheel.RightRPM_I, 
			Gains.Flywheel.RightRPM_D, 
			Gains.Flywheel.RightRPM_F, 
			Gains.Flywheel.RightTicksPerRev, 
			Gains.Flywheel.RPM_Deadband);
	
	public static Shooter grabInstance() {
		return thisInstance;
	}
	

	public void resetSensors() {
		leftFlywheel_sensor.reset();
		rightFlywheel_sensor.reset();
		
		
	}

	public void update() {
		SmartDashboard.putBoolean("Left FlyWheel Controller Status", leftFlywheel_Controller.isEnabled());
		SmartDashboard.putBoolean("Right FlyWheel Controller Staus", rightFlywheel_Controller.isEnabled());
	}


	public void disableSubsystem() {
	
		leftFlywheel_Controller.disable();
		rightFlywheel_Controller.disable();
		
	}

}
