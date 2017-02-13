package ca.team4519.FRC2017;

import ca.team4519.FRC2017.auton.modes.*;

import ca.team4519.FRC2017.subsystems.Drivebase;
import ca.team4519.FRC2017.subsystems.Shooter;
import ca.team4519.FRC2017.subsystems.Climber;
import ca.team4519.FRC2017.subsystems.Controller;
import ca.team4519.FRC2017.subsystems.GearBox;
import ca.team4519.FRC2017.subsystems.Hopper;
import ca.team4519.lib.MechaRobotBase;
import ca.team4519.lib.MultiThreader;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/*
 * @Author Connor Adams
 */
public class Robot extends MechaRobotBase{
  
	MultiThreader controlLoops = new MultiThreader("100Hz", 1.0/100.0);
	
	Command autoToExecute;	
	SendableChooser<Command> autoMode = new SendableChooser<Command>();
	
	Controller PS4 = new Controller();
	
	

	public void robotInit() {
       
		controlLoops.addThread(controlLoops);
		
    	autoMode.addDefault("Do Nothing", null);
    	autoMode.addObject("Lane A Gear", new LaneAGear());
    	autoMode.addObject("Lane B Gear", null);
    	autoMode.addObject("Lane C Gear", null);
    	
    	SmartDashboard.putData("Autonomous Mode Selector", autoMode);
    }
    
    

    public void autonomousInit() {
    	
    	controlLoops.start();
    	
    	autoToExecute = (Command) autoMode.getSelected();
    	autoToExecute.start();
    }

    public void autonomousPeriodic() {
    	Scheduler.getInstance().run();
    }

    public void teleopInit(){
    	Scheduler.getInstance().disable();
    	Drivebase.grabInstance().resetSensors();
    	controlLoops.start();
    }
    
    public void teleopPeriodic() {
        Drivebase.grabInstance().arcadeDriveMath(PS4.getLeftStick_Y(), PS4.getRightStick_X());
    }

    
    public void disabledInit() {
    	controlLoops.stop();
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
