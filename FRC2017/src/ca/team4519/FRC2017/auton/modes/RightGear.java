package ca.team4519.FRC2017.auton.modes;

import ca.team4519.FRC2017.auton.AutoMode;
import ca.team4519.FRC2017.auton.AutonException;


public class RightGear extends AutoMode{
	protected double toPeg = crossBaseline + 20;
	
	@Override
	protected void sequence() throws AutonException {
		
		drive.setDistanceTarget(crossBaseline);
		waitForDriveDistance(crossBaseline, true, 2);
		drive.setTurnTarget(pegAngle, 83.3);
		waitForTurn(pegAngle, true, 2);
		drive.setDistanceTarget(toPeg, halfSpeed);
		waitForDriveDistance(toPeg, true, 2);
		gear.open();
		wait(0.5);
		drive.setDistanceTarget(crossBaseline);
		waitForDriveDistance(crossBaseline, false, 1.5);
		gear.closed();
		drive.setTurnTarget(0);
		waitForTurn(0, false, 1);

	}

	@Override
	public void init() {
		gear.closed();
	}

}
