package ca.team4519.FRC2017.auton.modes;

import ca.team4519.FRC2017.auton.AutoMode;
import ca.team4519.FRC2017.auton.AutonException;


public class RightGear extends AutoMode{
	protected double toPeg = crossBaseline + 20;
	
	@Override
	protected void sequence() throws AutonException {
		
		drive.setDistanceTarget(34.5);
		waitForDriveDistance(34.5, true, 2);
		drive.setTurnTarget(60, 25);
		waitForTurn(60, true, 4);
		drive.resetEncoders();
		drive.setDistanceTarget(34.5, halfSpeed/4);
		waitForDriveDistance(34.5, true, 2);
		gear.open();
		wait(0.5);
		drive.setDistanceTarget(0);
		waitForDriveDistance(0, false, 1.5);
		gear.closed();

	}

	@Override
	public void init() {
		gear.closed();
	}

}
