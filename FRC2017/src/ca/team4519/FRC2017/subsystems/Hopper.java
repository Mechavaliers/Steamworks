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
	boolean runIntake = false;
	boolean canSwitch = false;
	boolean canSwitch1 = false;
	
	public VictorSP bottomMotor;
	public VictorSP topMotor;
	
	public Hopper(){
		thisInstance = this;
		
		topMotor = new VictorSP(Constants.agitatorMotor);
		bottomMotor = new VictorSP(Constants.serializerMotor);
		
	}
	
	
	
	
	

	public void hopperControl(boolean shooter, boolean intake){
		
		
		if(!shooter){
			canSwitch = true;
		}else if(canSwitch){
			run = run? false:true;
			canSwitch = false;
		}
		
		if(!intake){
			canSwitch1 = true;
		}else if(canSwitch1){
			runIntake = runIntake? false:true;
			canSwitch1 = false;
		}
		
		if(run){
			bottomMotor.set(-0.25);
		}else{
			bottomMotor.set(0.0);
		}
		if(run){
			topMotor.set(-0.3);
		}else if(runIntake){
		topMotor.set(0.5);
		}else{
			topMotor.set(0.0);
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
