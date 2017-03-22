package ca.team4519.FRC2017.auton.modes;

import java.util.ArrayList;
import java.util.List;

import ca.team4519.lib.motion.Coords;
import ca.team4519.lib.motion.Path;
import ca.team4519.lib.motion.Waypoint;
import ca.team4519.FRC2017.auton.AutoMode;
import ca.team4519.FRC2017.auton.AutonException;

public class PathTesting extends AutoMode{

	@Override
	protected void sequence() throws AutonException {
        
        List<Waypoint> mainPath = new ArrayList<>();
        mainPath.add(new Waypoint(new Coords(0,0), 100));
        mainPath.add(new Waypoint(new Coords(100, 0), 100, "AtBaseline"));
        mainPath.add(new Waypoint(new Coords(120, 50), 100));
        mainPath.add(new Waypoint(new Coords(100, 0), 100));
        
        drive.followPath(new Path(mainPath));
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}
	

}
