package ca.team4519.FRC2017.auton.modes;

import ca.team4519.FRC2017.auton.AutoMode;
import ca.team4519.FRC2017.auton.AutonException;

public class LaneBGearGoLeft extends AutoMode {
	
	@Override
	protected void sequence() throws AutonException {
		drive.setDistanceTarget(40, 50);
		waitForDriveDistance(40, true, 2);
		gear.open();
		wait(0.5);
		drive.setDistanceTarget(20);
		waitForDriveDistance(20, false, 1);
		gear.close();
		drive.setTurnTarget(45);
		waitForTurn(45, true, 5);
		drive.setDistanceTarget(60, 50);
		waitForDriveDistance(60, true, 3);
		drive.setTurnTarget(0);
		//drive.setTurnTarget(45);
		waitForTurn(0, false, 5);
		drive.setDistanceTarget(120, 50);
		waitForDriveDistance(120, false, 3);
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}
	
}
