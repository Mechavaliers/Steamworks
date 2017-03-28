package ca.team4519.FRC2017.auton.modes;

import ca.team4519.FRC2017.auton.AutoMode;
import ca.team4519.FRC2017.auton.AutonException;

public class CenterGearGoRight extends AutoMode{

	protected double toPeg = crossBaseline + 20;
	
	@Override
	protected void sequence() throws AutonException {
		
		gear.closed();
		drive.setDistanceTarget((crossBaseline/2)-20);
		//waitForDriveDistance((crossBaseline/2)-20, true, 2.0);
		//drive.setDistanceTarget((crossBaseline/2), halfSpeed/3);
		
		wait(3.5);
		gear.open();
		wait(0.25);
		drive.setDistanceTarget((crossBaseline/2)-20, 30);
		waitForDriveDistance(((crossBaseline/2)-20), false, 2);
		gear.closed();
		drive.setTurnTarget(-90);
		waitForTurn(-90, false, 2);
		double distanceCache = drive.averageDistance();
		drive.setDistanceTarget(drive.averageDistance() + 60);
		waitForDriveDistance(distanceCache + 60, true, 0);
		drive.setTurnTarget(0);
	}

	@Override
	public void init() {
		gear.closed();
	}

}