package ca.team4519.FRC2017.auton.modes;

import ca.team4519.FRC2017.auton.AutoMode;
import ca.team4519.FRC2017.auton.AutonException;
import edu.wpi.first.wpilibj.Timer;

public class LaneBGear extends AutoMode {

	@Override
	protected void sequence() throws AutonException {
		drive.setDistanceTarget(50);
		waitForDriveDistance(50, true, 3);
		drive.setTurnTarget(45);
		waitForTurn(45, true, 2);
		drive.setDistanceTarget(100);
		waitForDriveDistance(100, false, 3);
		drive.setTurnTarget(90);
		waitForTurn(90, true, 2);
		drive.setDistanceTarget(150);
		waitForDriveDistance(150, true, 3);
		drive.setTurnTarget(135);
		waitForTurn(135, true, 2);
		drive.setDistanceTarget(200);
		waitForDriveDistance(200, false, 3);
		drive.setTurnTarget(180);
		waitForTurn(180, true, 2);
		
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

}
