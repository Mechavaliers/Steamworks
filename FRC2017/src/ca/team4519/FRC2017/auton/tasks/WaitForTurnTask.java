package ca.team4519.FRC2017.auton.tasks;

import ca.team4519.FRC2017.subsystems.Drivebase;

public class WaitForTurnTask  extends TimeoutTask{

	double angle;
	boolean positive;
	
	public WaitForTurnTask(double angle, boolean direction, double timeout) {
		super(timeout);
		this.angle = angle;
		positive = direction;
	}

	
	public boolean isDone() {
	double robotAngle = Drivebase.grabInstance().getRobotPose().getAngle();
    return super.isDone() || positive ?  robotAngle >= angle : robotAngle <= angle;
	}
}
