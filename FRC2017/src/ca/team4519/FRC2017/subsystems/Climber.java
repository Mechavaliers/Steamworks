package ca.team4519.FRC2017.subsystems;

import ca.team4519.FRC2017.Constants;
import ca.team4519.lib.Subsystem;
import ca.team4519.lib.Thread;
import edu.wpi.first.wpilibj.VictorSP;

public class Climber extends Subsystem implements Thread{

	private static Climber thisInstance = new Climber();
	
	public static Climber grabInstance() {
		return thisInstance;
	}
	
	VictorSP Climb1;
	VictorSP Climb2;
	
	public Climber(){
		thisInstance = this;
		
		Climb1 = new VictorSP(Constants.climb1);
		Climb2 = new VictorSP(Constants.climb2);
	}
	
	public void climb(boolean canClimb, double input){
		if(canClimb){
			input = Math.abs(input);
		Climb1.set(input > 0.1? -input:0.0);
		Climb2.set(input > 0.1? input:0.0);
		}else{
			Climb1.set(0.0);
			Climb2.set(0.0);
		}
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
