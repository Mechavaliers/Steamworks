package ca.team4519.FRC2017;

import ca.team4519.FRC2017.auton.modes.*;

import ca.team4519.FRC2017.subsystems.Drivebase;
import ca.team4519.FRC2017.subsystems.Shooter;
import ca.team4519.FRC2017.subsystems.Climber;
import ca.team4519.FRC2017.subsystems.Controller;
import ca.team4519.FRC2017.subsystems.GearBox;
import ca.team4519.FRC2017.subsystems.Hopper;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/*
 * @Author Connor Adams
 */
public class Robot extends IterativeRobot {
  
	Command autoToExecute;	
	SendableChooser<Command> autoMode = new SendableChooser<Command>();
	SendableChooser<String> allianceSelector = new SendableChooser<String>();
	SendableChooser<String> robotPosition = new SendableChooser<String>();
 	
	Controller PS4 = new Controller();
	
	

	public void robotInit() {
       
		allianceSelector.addObject("Red Alliance", "Red");
		allianceSelector.addObject("Blue Alliance", "Blue");
		SmartDashboard.putData("Alliance Selector", allianceSelector);
		
		robotPosition.addObject("Left Field", "Left");
		robotPosition.addObject("Center Field", "Center");
		robotPosition.addObject("Right Field", "Right");
		SmartDashboard.putData("Robot Position Oon Field", robotPosition);
		
    	autoMode.addDefault("Do Nothing", null);
    	autoMode.addObject("Hang Gear", new HangGear(robotPosition.getSelected()));
    	autoMode.addObject("Gear+Shoot", new GearNShoot(allianceSelector.getSelected()));
    	SmartDashboard.putData("Autonomous Mode Selector", autoMode);
    }
    
    
    public void autonomousInit() {
    	
    	autoToExecute = (Command) autoMode.getSelected();
    	autoToExecute.start();
    }

    public void autonomousPeriodic() {
    	Scheduler.getInstance().run();
    }

    public void teleopInit(){
    	Scheduler.getInstance().disable();
    	Drivebase.grabInstance().resetSensors();
    }
    
    public void teleopPeriodic() {
        Drivebase.grabInstance().arcadeDriveMath(PS4.getLeftStick_Y(), PS4.getRightStick_X());
    }

    
    public void disabledInit() {
    	Drivebase.grabInstance().disableSubsystem();
    	Climber.grabInstance().disableSubsystem();
    	GearBox.grabInstance().disableSubsystem();
    	Hopper.grabInstance().disableSubsystem();
    	Shooter.grabInstance().disableSubsystem();
    }
    
    public void allPeriodic() {
    	Drivebase.grabInstance().update();
    	Climber.grabInstance().update();
    	GearBox.grabInstance().update();
    	Hopper.grabInstance().update();
    	Shooter.grabInstance().update();
    }
}
