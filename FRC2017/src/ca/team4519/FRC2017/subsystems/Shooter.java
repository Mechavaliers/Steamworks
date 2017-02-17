package ca.team4519.FRC2017.subsystems;

import ca.team4519.lib.Subsystem;
import ca.team4519.FRC2017.Constants;
import ca.team4519.FRC2017.Gains;
import ca.team4519.lib.pid.FlywheelController;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Shooter extends Subsystem{

	private static Shooter thisInstance = new Shooter();
	
	public Talon Flywheel = new Talon(Constants.FlywheelPWM);

	
	public Counter Flywheel_sensor = new Counter(Constants.FlywheelEncoder);
	
	public enum Flywheel_State{
		OFF, KEY, WALL
	}
	
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
	
	public void setRPMTarget(double target){
		Flywheel_Controller.setRPM(target);
	}
	
	public void enableFlywheel() {
		Flywheel.set(Flywheel_Controller.controllerOutput());
	
	}
		
	public void Flywheel_State_Machine(Flywheel_State state){
		
		if(state == Flywheel_State.KEY){
			setRPMTarget(Gains.Flywheel.RPM_TARGET_KEY);
			enableFlywheel();
		}else if (state == Flywheel_State.WALL){
			setRPMTarget(Gains.Flywheel.RPM_TARGET_WALL);
			enableFlywheel();
		}else if (state == Flywheel_State.OFF){
			Flywheel_Controller.disable();
		}
		
	}
	
	
	public double getFlywheelVelocity(){
		return (Flywheel_sensor.getRate() / Gains.Flywheel.TicksPerRev) / 60;
	}
	
	public double getFlywheelRevsPerMin(){
		return getFlywheelVelocity();
	}

	public void resetSensors() {
		Flywheel_sensor.reset();	
		
	}

	public void update() {
		SmartDashboard.putBoolean("FlyWheel Controller Status", Flywheel_Controller.isEnabled());
		SmartDashboard.putNumber("Flywheel RPM", getFlywheelVelocity());
		SmartDashboard.putBoolean("Flywheel on Target?", Flywheel_Controller.inRange());
	}


	public void disableSubsystem() {
		Flywheel_Controller.disable();
	}

}
