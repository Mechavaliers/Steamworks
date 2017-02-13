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
	
	public Talon Flywheel = new Talon(Constants.FlywheelPWM);

	
	public Counter Flywheel_sensor = new Counter(Constants.FlywheelEncoder);
	
	public FlywheelController Flywheel_Controller = new FlywheelController(
			Flywheel_sensor,
			Gains.Flywheel.RPM_P, 
			Gains.Flywheel.RPM_I, 
			Gains.Flywheel.RPM_D, 
			Gains.Flywheel.RPM_F, 
			Gains.Flywheel.TicksPerRev, 
			Gains.Flywheel.RPM_Deadband);

	
	public static Shooter grabInstance() {
		return thisInstance;
	}
	
	public void setRPMTarget(){
		Flywheel_Controller.setRPM(Gains.Flywheel.RPM_TARGET_KEY);
	}
	
	public void setRPMTarget(double target){
		Flywheel_Controller.setRPM(target);
	}
	
	public void enableFlywheels() {
		Flywheel.set(Flywheel_Controller.controllerOutput());
	
	}
	
	public double getFlywheelVelocity(){
		return (Flywheel_sensor.getRate() / Gains.Flywheel.TicksPerRev) / 60;
	}
	
	public double getLeftFlywheelRevsPerMin(){
		return 12;
	}

	
	public double getRightFlywheelRevsPerMin(){
		return 12;
	}
	
	public void resetSensors() {
		Flywheel_sensor.reset();	
		
	}

	public void update() {
		SmartDashboard.putBoolean("Left FlyWheel Controller Status", Flywheel_Controller.isEnabled());
		SmartDashboard.putNumber("Left Flywheel RPM", getFlywheelVelocity());
		SmartDashboard.putBoolean("Is Left on Target?", Flywheel_Controller.inRange());
	}


	public void disableSubsystem() {
		Flywheel_Controller.disable();
	}

}
