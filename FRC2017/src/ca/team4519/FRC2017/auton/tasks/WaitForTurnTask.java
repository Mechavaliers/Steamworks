package ca.team4519.FRC2017.auton.tasks;

public class WaitForTurnTask  extends TimeoutTask{

	double angle;
	boolean positive;
	
	public WaitForTurnTask(double angle, boolean direction, double timeout) {
		super(timeout);
		this.angle = angle;
		positive = direction;
	}

	
	public boolean isDone() {
    return super.isDone() || positive ?  drive.currHeading() >= angle : drive.currHeading() <= angle;
	}
}
