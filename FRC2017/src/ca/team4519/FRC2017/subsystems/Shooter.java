package ca.team4519.FRC2017.subsystems;

import ca.team4519.lib.Subsystem;
import ca.team4519.lib.Thread;
import ca.team4519.FRC2017.Constants;

import edu.wpi.first.wpilibj.Talon;

public class Shooter extends Subsystem implements Thread{


	private static Shooter thisInstance = new Shooter();
	
	public Talon Flywheel;

	boolean yay = false;
	boolean go = false;

	
	public enum Flywheel_State{
		OFF, ON, 
	}
	
	public Flywheel_State flywheelState = Flywheel_State.OFF;
	
	public Shooter(){
		thisInstance = this;
		
		Flywheel = new Talon(Constants.FlywheelPWM);		
	}
	
	public static Shooter grabInstance() {
		return thisInstance;
	}

	
	public void toggleState(boolean toggleState){
				
		if(!toggleState){
			go = true;
		}else if (go){
			if(flywheelState == Flywheel_State.OFF){
				flywheelState = Flywheel_State.ON;
			}else if(flywheelState == Flywheel_State.ON){
				flywheelState = Flywheel_State.OFF;
			}
			go = false;
		}
	}

		
	public void Flywheel_State_Machine(){
		
		switch(flywheelState){
			case ON:
				Flywheel.set(0.60);
			case OFF:
				Flywheel.set(0.0);				
			default:
				Flywheel.set(0.0);
		}	
	}
	
	public void resetSensors() {	
	}

	public void update() {
	}


	public void disableSubsystem() {
		flywheelState = Flywheel_State.OFF;
		Flywheel.set(0.0);
		
	}

	@Override
	public void controlLoops() {
		switch(flywheelState){
		case ON:
			Flywheel.set(0.60);
			break;
		case OFF:
			Flywheel.set(0.0);
			break;
		default:
			Flywheel.set(0.0);
			break;
	}
	}

}
