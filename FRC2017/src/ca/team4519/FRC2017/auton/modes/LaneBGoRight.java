package ca.team4519.FRC2017.auton.modes;

import ca.team4519.FRC2017.auton.AutoMode;
import ca.team4519.FRC2017.auton.AutonException;

public class LaneBGoRight extends AutoMode {

	@Override
	protected void sequence() throws AutonException {
		drive.setDistanceTarget(10);
		waitForDriveDistance(10, true, 1);
		drive.setTurnTarget(45);
		waitForTurn(45, true, 2);
		drive.setDistanceTarget(60);
		waitForDriveDistance(60, true, 3);
		drive.setTurnTarget(0);
		//drive.setTurnTarget(45);
		waitForTurn(0, false, 2);
		drive.setDistanceTarget(90);
		waitForDriveDistance(90, false, 3);
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

}
