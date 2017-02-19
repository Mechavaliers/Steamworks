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

	Toggle runMotor;
	
	boolean isEnabled = false;
	boolean run = false;
	boolean canSwitch = false;
	
	public VictorSP agitatorMotor;
	public VictorSP serializerMotor;
	
	public Hopper(){
		thisInstance = this;
		
		agitatorMotor = new VictorSP(Constants.agitatorMotor);
		serializerMotor = new VictorSP(Constants.serializerMotor);
		
	}
	
	
	
	
	

	public void hopperControl(boolean button){
		
		
		if(!button){
			canSwitch = true;
		}else if(canSwitch){
			run = run? false:true;
			canSwitch = false;
		}
		
		if(run){
			agitatorMotor.set(-0.25);
			serializerMotor.set(-0.25);
		}else{
			agitatorMotor.set(0.0);
			serializerMotor.set(0.0);
		}
		
		
		
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
