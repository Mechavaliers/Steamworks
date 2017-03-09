package ca.team4519.FRC2017.auton.modes;

import ca.team4519.FRC2017.auton.AutoMode;
import ca.team4519.FRC2017.auton.AutonException;

public class B3Gear extends AutoMode{

	@Override
	protected void sequence() throws AutonException {
		drive.setDistanceTarget(crossBaseline);
		waitForDriveDistance(clearAirship, true, 3);
		drive.setTurnTarget(123);
		waitForTurn(123, true, 2);
		drive.setDistanceTarget(69);
		waitForDriveDistance(69, true, 2);
		//gear.open();
		//gear.eject();
		drive.setDistanceTarget(60);
		waitForDriveDistance(60, false, 2);
		
	}

	@Override
	public void init() {
			
	}

}
