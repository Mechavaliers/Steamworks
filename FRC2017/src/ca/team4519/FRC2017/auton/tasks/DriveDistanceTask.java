package ca.team4519.FRC2017.auton.tasks;

import ca.team4519.FRC2017.subsystems.Drivebase;

public class DriveDistanceTask implements Task {

	protected double distToGo;
	protected double maxVel;
	
	
	public DriveDistanceTask(double distance, double maxVelocity, String name){
		this.distToGo = distance;
		this.maxVel = maxVelocity;
	}


	public boolean isTaskComplete() {
		
		return false;
	}

	@Override
	public void exitTask() {

		
	}

	@Override
	public void output() {
		
		
	}

	@Override
	public void runTask() {
		Drivebase.grabInstance().setLeftRightPower(0, 0); //THIS IS TEMP PLZ CHANGE 
		
		
	}
	
	
	
}
