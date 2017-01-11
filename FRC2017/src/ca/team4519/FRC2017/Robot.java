
package ca.team4519.FRC2017;

import ca.team4519.FRC2017.subsystems.Drivebase;
import ca.team4519.FRC2017.subsystems.Shooter;
import ca.team4519.FRC2017.subsystems.Climber;
import ca.team4519.FRC2017.subsystems.GearBox;
import ca.team4519.FRC2017.subsystems.Hopper;

import edu.wpi.first.wpilibj.IterativeRobot;


public class Robot extends IterativeRobot {
  
	Drivebase driveBase = new Drivebase();
	Shooter flywheel = new Shooter();
	Climber muscles = new Climber();
	GearBox gearDevice = new GearBox();
	Hopper ballHolder = new Hopper();
	
	
    public void robotInit() {
       
    }
    
    public void updateAll() {
    	
    }
    
    public void autonomousInit() {

    }

    public void autonomousPeriodic() {
    
    }

    public void teleopPeriodic() {
        
    }

    public void testPeriodic() {
    
    }
    
}
