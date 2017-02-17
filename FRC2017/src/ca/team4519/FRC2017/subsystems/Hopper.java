package ca.team4519.FRC2017.subsystems;

import ca.team4519.FRC2017.Constants;
import ca.team4519.lib.Subsystem;
import ca.team4519.lib.Toggle;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Hopper extends Subsystem{

	private static Hopper thisInstance = new Hopper();
	
	public static Hopper grabInstance() {
		return thisInstance;
	}

	Talon agitatorMotor = new Talon(Constants.hopperMotor);
	
	Toggle runMotor;
	
	boolean isEnabled = false;
	

	public void hopperControl(boolean button){
		runMotor = new Toggle(button);
	
		agitatorMotor.set(runMotor.getState()? 1.0 : 0.0);
		
	}
	
	
	public void resetSensors() {
		isEnabled = false;		
	}
	
	public void update() {
		SmartDashboard.putBoolean("Hopper Running", isEnabled);
	}
	
	public void disableSubsystem() {
		isEnabled = false;
	}

}
