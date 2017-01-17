
package ca.team4519.FRC2017;

import ca.team4519.FRC2017.subsystems.Drivebase;
import ca.team4519.FRC2017.subsystems.Shooter;
import ca.team4519.FRC2017.subsystems.Climber;
import ca.team4519.FRC2017.subsystems.GearBox;
import ca.team4519.FRC2017.subsystems.Hopper;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Robot extends IterativeRobot {
  
	SendableChooser autoMode = new SendableChooser();
	Command autoToExecute;
	
	Drivebase driveBase = new Drivebase();
	Shooter flywheel = new Shooter();
	Climber muscles = new Climber();
	GearBox gearDevice = new GearBox();
	Hopper ballHolder = new Hopper();
	
	
    public void robotInit() {
       
    	
    	autoMode.addObject("Cross Baseline", null);
    	autoMode.addObject("Hang Gear Right", null);
    	autoMode.addObject("Hang Gear Center", null);
    	autoMode.addObject("Hang Gear Left", null);
    	autoMode.addObject("Red-Alliance: Gear+Shoot", null);
    	autoMode.addObject("Blue-Alliance: Gear+Shoot", null);
    	SmartDashboard.putData("Autonomous Mode Selector", autoMode);
    }
    
    public void updateAll() {
    	
    }
    
    public void autonomousInit() {
    	
    	autoToExecute = (Command) autoMode.getSelected();
    	autoToExecute.start();
    }

    public void autonomousPeriodic() {
    	Scheduler.getInstance().run();
    }

    public void teleopPeriodic() {
        
    }

    public void testPeriodic() {
    
    }
    
}
