package ca.team4519.FRC2017.auton.modes;

import ca.team4519.FRC2017.auton.AutoMode;
import ca.team4519.FRC2017.auton.AutonException;

public class LeftGear extends AutoMode{
	protected double toPeg = crossBaseline + 40;
	
	@Override
	protected void sequence() throws AutonException {
		drive.setDistanceTarget(24);
		waitForDriveDistance(24, true, 2);
		drive.setTurnTarget(-59, 25);
		waitForTurn(-59, false, 4);
		//wait(1.5);
		drive.setDistanceTarget(48, halfSpeed/4);
		waitForDriveDistance(48, true, 2);
		gear.open();
		wait(0.5);
		drive.setDistanceTarget(30);
		waitForDriveDistance(30, false, 1.5);
		gear.closed();
		
	}

	@Override
	public void init() {
		gear.closed();
	}

}
