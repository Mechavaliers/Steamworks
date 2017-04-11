package ca.team4519.FRC2017.auton.modes;

import ca.team4519.FRC2017.auton.AutoMode;
import ca.team4519.FRC2017.auton.AutonException;

public class CenterGearGoLeft extends AutoMode{

	protected double toPeg = crossBaseline + 20;
	
	@Override
	protected void sequence() throws AutonException {
		gear.closed();
		drive.setDistanceTarget((crossBaseline/2), halfSpeed/3);
		waitForDriveDistance(crossBaseline/2, true, 2.0);
		wait(0.25);
		gear.open();
		wait(1.0);
		drive.setDistanceTarget((crossBaseline/4), halfSpeed/3);
		waitForDriveDistance((crossBaseline/4),false , 2.0);
		gear.closed();
		drive.setTurnTarget(90, 50);
		waitForTurn(90, true, 2);
		double distanceCache = drive.averageDistance();
		drive.setDistanceTarget(distanceCache + 30, halfSpeed/2);
		waitForDriveDistance(distanceCache + 30, true, 2.0);
		//drive.setTurnTarget(0);
	}

	@Override
	public void init() {
		gear.closed();
	}



}
