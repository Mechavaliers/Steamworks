package ca.team4519.FRC2017.subsystems;

import ca.team4519.FRC2017.Constants;
import ca.team4519.FRC2017.Gains;
import ca.team4519.lib.Subsystem;
import ca.team4519.lib.Thread;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;

public class GearBox extends Subsystem{

	protected static GearBox thisInstance = new GearBox();
	
	public static GearBox grabInstance() {
		return thisInstance;
	}

	protected Servo left;
	protected Servo right;
	
	protected Solenoid ejector;
	protected Solenoid clamp;
	protected Solenoid release;
	
	protected boolean t1 = false;
	protected boolean t2 = false;
	
	protected boolean wantEject = false;
	protected boolean wantClosed = true;
	
	public GearBox(){
		thisInstance = this;
		
		left = new Servo(Constants.leftServo);
		right = new Servo(Constants.rightServo);
		
		clamp = new Solenoid(0);
		release = new Solenoid(1);
		ejector = new Solenoid(2);
		
		
	}

	public void setState(boolean closed, boolean open) {
		
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
	
	@Override
	public void resetSensors() {	
	}

	@Override
	public void update() {
	}

	@Override
	public void disableSubsystem() {
		closed();
	}
	
	public void open() {
		left.setAngle(Gains.GearBox.Left_Open);
		right.setAngle(Gains.GearBox.Right_Open);
		clamp.set(false);
		release.set(true);
	}
	
	public void closed() {
		right.setAngle(Gains.GearBox.Right_Closed);
		left.setAngle(Gains.GearBox.Left_Closed);
		clamp.set(true);
		release.set(false);
		ejector.set(false);
	}
	
	public void eject() {
		open();
		Timer.delay(0.1);
		ejector.set(true);
	}

}
