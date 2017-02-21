package ca.team4519.FRC2017.subsystems;

import ca.team4519.FRC2017.Constants;
import ca.team4519.lib.Subsystem;
import ca.team4519.lib.Thread;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GearBox extends Subsystem implements Thread{

	private static GearBox thisInstance = new GearBox();
	
	public static GearBox grabInstance() {
		return thisInstance;
	}
	
	Servo left;
	Servo right;

	boolean t1 = false;
	boolean t2 = false;
	boolean t3 = false;
	boolean t4 = false;

	double leftAngle = 15;
	double rightAngle = 163;
	
	public GearBox(){
		thisInstance = this;
		
		left = new Servo(Constants.leftServo);
		right = new Servo(Constants.rightServo);
		
	}
	
	public void setDeg(boolean closed, boolean open){
		
		if(!closed){
			t1 = true;
		}else if(t1){
			leftAngle = 15;
			rightAngle = 165;
			t1 = false;
		}
		
		if(!open){
			t2 = true;
		}else if(t2){
			leftAngle =35;
			rightAngle = 140;
			t2 = false;
		}
	
		
		//closed
		//left 15
		//right 165
		//open
		//left 35
		//right 140
		left.setAngle(leftAngle);
		right.setAngle(rightAngle);
		
		
		}
	
	
	@Override
	public void resetSensors() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		SmartDashboard.putNumber("Left Servo", left.getAngle());
		SmartDashboard.putNumber("Right Number", right.getAngle());
		
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
