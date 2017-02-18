package ca.team4519.FRC2017.subsystems;

import ca.team4519.FRC2017.Constants;
import ca.team4519.lib.Subsystem;
import ca.team4519.lib.Thread;
import ca.team4519.lib.Toggle;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Hopper extends Subsystem implements Thread{

	private static Hopper thisInstance = new Hopper();
	
	public static Hopper grabInstance() {
		return thisInstance;
	}

	VictorSP agitatorMotor = new VictorSP(Constants.agitatorMotor);
	VictorSP serializerMotor = new VictorSP(Constants.serializerMotor);
	
	Toggle runMotor;
	
	boolean isEnabled = false;
	

	public void hopperControl(boolean button){
		runMotor = new Toggle(button);
	
		agitatorMotor.set(runMotor.getState()? 1.0 : 0.0);
		serializerMotor.set(runMotor.getState()? 1.0: 0.0);
		
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


	@Override
	public void controlLoops() {
		// TODO Auto-generated method stub
		
	}

}
