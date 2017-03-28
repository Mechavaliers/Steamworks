package ca.team4519.FRC2017.auton.modes;

import ca.team4519.FRC2017.auton.AutoMode;
import ca.team4519.FRC2017.auton.AutonException;

public class CenterGear extends AutoMode{

	protected double toPeg = crossBaseline + 20;
	
	@Override
	protected void sequence() throws AutonException {
		
		gear.closed();
		drive.setDistanceTarget((crossBaseline/2), halfSpeed/3);
		waitForDriveDistance(crossBaseline/2, true, 2.0);
		wait(3.5);
		gear.open();
		wait(0.25);
		drive.setDistanceTarget((crossBaseline/2)-20, 30);
	}

	@Override
	public void init() {
		gear.closed();
	}

}