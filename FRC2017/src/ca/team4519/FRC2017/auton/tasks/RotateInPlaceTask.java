package ca.team4519.FRC2017.auton.tasks;

public class RotateInPlaceTask implements Task {

	protected double robotHeading, goalAngle;
	
	public RotateInPlaceTask(double currentHeading, double headingGoal){
		
		this.robotHeading = currentHeading;
		this.goalAngle = headingGoal;
		
	}

	@Override
	public boolean isTaskComplete() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void exitTask() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void output() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void runTask() {
		// TODO Auto-generated method stub
		
	}
	
}
