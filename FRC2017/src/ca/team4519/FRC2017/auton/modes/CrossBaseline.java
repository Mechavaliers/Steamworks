package ca.team4519.FRC2017.auton.modes;

import ca.team4519.FRC2017.auton.AutoMode;
import ca.team4519.FRC2017.auton.AutonException;

public class CrossBaseline extends AutoMode{

	protected double toPeg = crossBaseline + 20;
	
	@Override
	protected void sequence() throws AutonException {
		gear.closed();
		drive.setDistanceTarget((crossBaseline/2)+20, halfSpeed);
		//waitForDriveDistance((crossBaseline/2) +20, true, 5);
		//drive.setTurnTarget(-180, 83.3);
	
	}

	@Override
	public void init() {
		gear.closed();

	}

}