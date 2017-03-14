package ca.team4519.FRC2017.auton.modes;

import ca.team4519.FRC2017.auton.AutoMode;
import ca.team4519.FRC2017.auton.AutonException;
import ca.team4519.FRC2017.subsystems.GearBox.Gearage_State;

public class CenterGear extends AutoMode{

	protected double toPeg = crossBaseline + 20;
	
	@Override
	protected void sequence() throws AutonException {
		
		gear.closed();
		drive.setDistanceTarget((crossBaseline/2), halfSpeed/3);
	//	waitForDriveDistance((crossBaseline/2)+1.5, true, 5);
		wait(3.5);
		gear.open();
		wait(0.25);
		drive.setDistanceTarget((crossBaseline/2)-20, 30);
		
		//drive.setDistanceTarget(25);
	}

	@Override
	public void init() {
		gear.closed();
	}

}