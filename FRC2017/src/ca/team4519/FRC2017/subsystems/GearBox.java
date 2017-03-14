package ca.team4519.FRC2017.subsystems;

import ca.team4519.FRC2017.Constants;
import ca.team4519.FRC2017.Gains;
import ca.team4519.lib.Subsystem;
import ca.team4519.lib.Thread;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Solenoid;

public class GearBox extends Subsystem implements Thread{

	protected static GearBox thisInstance = new GearBox();
	
	public static GearBox grabInstance() {
		return thisInstance;
	}
	
	public enum Gearage_State {
		OPEN,
		CLOSED,
		EJECT
	}
	
	public Gearage_State currentState;
	
	protected Servo left;
	protected Servo right;
	//protected Servo ejector;
	
	protected Solenoid ejector;
	protected Solenoid clamp;
	protected Solenoid release;
	
	protected boolean t1 = false;
	protected boolean t2 = false;
	
	protected boolean wantEject = false;
	protected boolean wantClosed = true;
	
	public GearBox(){
		thisInstance = this;
		
		currentState = Gearage_State.CLOSED;
		
		left = new Servo(Constants.leftServo);
		right = new Servo(Constants.rightServo);
		//ejector = new Servo(Constants.ejectorServo);
		
		clamp = new Solenoid(0);
		release = new Solenoid(1);
		ejector = new Solenoid(2);
		
		
	}

	public void setState(boolean closed, boolean open){
		
		if(!closed){
			t1 = true;
		}else if(t1){
			wantClosed = !wantClosed;
			closed();
			t1 = false;
		}
		
		if(!open){
			t2 = true;
		}else if(t2){
			wantEject = !wantEject;
			open();
			t2 = false;
		}
		
	

	}

	public void changeState(Gearage_State state){
		currentState = state;
	}
	
	@Override
	public void resetSensors() {	
	}

	@Override
	public void update() {
	}

	@Override
	public void disableSubsystem() {	
		currentState = Gearage_State.CLOSED;
	}
	
	public void open(){
		left.setAngle(Gains.GearBox.Left_Open);
		right.setAngle(Gains.GearBox.Right_Open);
	}
	
	public void closed(){
		right.setAngle(Gains.GearBox.Right_Closed);
		left.setAngle(Gains.GearBox.Left_Closed);
	}

	@Override
	public void controlLoops() {	
	/*	switch(currentState){
		case OPEN:
			left.setAngle(Gains.GearBox.Left_Open);
			right.setAngle(Gains.GearBox.Right_Open);
			break;
		case EJECT:
			left.setAngle(Gains.GearBox.Left_Open);
			right.setAngle(Gains.GearBox.Right_Open);
			ejector.setAngle(Gains.GearBox.Ejector_Open);
			break;
		case CLOSED:
			ejector.setAngle(Gains.GearBox.Ejector_Closed);
			left.setAngle(Gains.GearBox.Left_Closed);
			right.setAngle(Gains.GearBox.Right_Closed);
			break;
		default:
			ejector.setAngle(Gains.GearBox.Ejector_Closed);
			left.setAngle(Gains.GearBox.Left_Closed);
			right.setAngle(Gains.GearBox.Right_Closed);
			break;
		}*/	
	}
}

