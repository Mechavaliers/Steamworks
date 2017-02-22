package ca.team4519.FRC2017.auton.modes;

import ca.team4519.FRC2017.auton.AutoMode;
import ca.team4519.FRC2017.auton.AutonException;

public class LaneBGearGoRight extends AutoMode{

	@Override
	protected void sequence() throws AutonException {
		drive.setDistanceTarget(40);
		waitForDriveDistance(40, true, 2);
		gear.open();
		wait(0.5);
		drive.setDistanceTarget(20);
		waitForDriveDistance(20, false, 1);
		gear.close();
		drive.setTurnTarget(-90);
		waitForTurn(-90, false, 4);
		drive.setDistanceTarget(80);
		waitForDriveDistance(80, true, 3);
		drive.setTurnTarget(0);
		//drive.setTurnTarget(45);
		waitForTurn(0, true, 4);
		drive.setDistanceTarget(120);
		waitForDriveDistance(120, false, 3);
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

}
