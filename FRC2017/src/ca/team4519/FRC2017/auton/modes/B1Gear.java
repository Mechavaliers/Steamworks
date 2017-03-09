package ca.team4519.FRC2017.auton.modes;

import ca.team4519.FRC2017.auton.AutoMode;
import ca.team4519.FRC2017.auton.AutonException;

public class B1Gear extends AutoMode{

	@Override
	protected void sequence() throws AutonException {
		drive.setDistanceTarget(10);
		waitForDriveDistance(10, true, 3);
		drive.setTurnTarget(45);
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

}
