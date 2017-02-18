package ca.team4519.FRC2017.subsystems;

import ca.team4519.FRC2017.Constants;
import ca.team4519.lib.Subsystem;
import ca.team4519.lib.Thread;

import edu.wpi.first.wpilibj.Servo;

public class GearBox extends Subsystem implements Thread{

	private static GearBox thisInstance = new GearBox();
	
	public static GearBox grabInstance() {
		return thisInstance;
	}
	
	Servo left;
	Servo right;
	
	public GearBox(){
		thisInstance = this;
		
		left = new Servo(Constants.leftServo);
		right = new Servo(Constants.rightServo);
		
	}
	
	@Override
	public void resetSensors() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disableSubsystem() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controlLoops() {
		// TODO Auto-generated method stub
		
	}

}
