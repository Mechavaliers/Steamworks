package ca.team4519.FRC2017.auton.tasks;

import ca.team4519.lib.RobotPose;

public class WaitForDistanceTask extends TimeoutTask {

	double distance;
	boolean forwards;
	
	public WaitForDistanceTask(double dist, boolean direction, double timeout) {
		super(timeout);
		distance = dist;
		forwards = direction;
	}

	@Override
	public boolean isDone() {
		RobotPose pose = drive.getRobotPose();
		double avg = (pose.getLeftDistance() + pose.getRightDistance())/2;
		return (forwards ? avg >= distance : avg <= distance || super.isDone());
	}
}
