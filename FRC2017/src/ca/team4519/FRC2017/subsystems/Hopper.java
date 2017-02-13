package ca.team4519.FRC2017.subsystems;

import ca.team4519.FRC2017.Constants;
import ca.team4519.lib.Subsystem;
import ca.team4519.lib.Toggle;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Hopper extends Subsystem{

	public static Hopper thisInstance = new Hopper();
	
	public static Hopper grabInstance() {
		return thisInstance;
	}

	Talon agitatorMotor;
	boolean isEnabled = false;

	public Toggle on;
	public Toggle off;
	
	public Hopper(){
		
		agitatorMotor = new Talon(Constants.hopperMotor);
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
