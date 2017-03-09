package ca.team4519.FRC2017.auton.modes;


import ca.team4519.FRC2017.auton.AutoMode;
import ca.team4519.FRC2017.auton.AutonException;

public class B2GearGoLeft extends AutoMode{
	
	@Override
	protected void sequence() throws AutonException {
		drive.setDistanceTarget(crossBaseline, halfSpeed);
		waitForDriveDistance(crossBaseline, true, 3);
		//gear.open();
		//gear.eject();
		drive.setDistanceTarget(clearAirship);
		waitForDriveDistance(clearAirship, false, 2);
		//gear.close();
		drive.setTurnTarget(90);
		waitForTurn(90, true, 2);
		drive.setDistanceTarget(100);
		waitForDriveDistance(100, true, 3);
		drive.setTurnTarget(0);
		waitForTurn(0, false, 1);
		drive.setDistanceTarget(150);
		waitForDriveDistance(150, true, 2);
		
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

}
