package ca.team4519.FRC2017;

import ca.team4519.FRC2017.auton.commands.DriveDistanceCommand;
import ca.team4519.FRC2017.auton.modes.*;

import ca.team4519.FRC2017.subsystems.Drivebase;
import ca.team4519.FRC2017.subsystems.Shooter;
import ca.team4519.FRC2017.subsystems.Shooter.Flywheel_State;
import ca.team4519.FRC2017.subsystems.Climber;
import ca.team4519.FRC2017.subsystems.DualShock4;
import ca.team4519.FRC2017.subsystems.GearBox;
import ca.team4519.FRC2017.subsystems.Hopper;
import ca.team4519.lib.MechaRobotBase;
import ca.team4519.lib.MultiThreader;
import ca.team4519.lib.Threader;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/*
 * @Author Connor Adams
 */
public class Robot extends MechaRobotBase{
	
	MultiThreader controlLoops = new MultiThreader("100Hz", 1.0/100.0);
	Threader drivebaseLoop = new Threader("100Hz", Drivebase.grabInstance(), 1.0/100.0);
	
	Command autoToExecute;	
	SendableChooser<Command> autoMode = new SendableChooser<Command>();
	
	Joystick PS4 = new Joystick(0);
	Joystick OP = new Joystick(1);

	public void robotInit() {
       
		//controlLoops.addThread(Drivebase.grabInstance());
		/*controlLoops.addThread(Shooter.grabInstance());
		controlLoops.addThread(Hopper.grabInstance());
		controlLoops.addThread(GearBox.grabInstance());
		controlLoops.addThread(Climber.grabInstance());
		*/
    	autoMode.addDefault("Do Nothing", null);
    	autoMode.addObject("Lane A Gear", new DriveDistanceCommand(36));
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
    	//controlLoops.
    	Scheduler.getInstance().run();
    }

    public void teleopInit(){
    	Scheduler.getInstance().disable();
    	Drivebase.grabInstance().getRobotPose();
    	Drivebase.grabInstance().resetSensors();
    	controlLoops.start();
    	drivebaseLoop.start();
    
    }
    
    public void teleopPeriodic() {
       Drivebase.grabInstance().setDrivePower(Drivebase.grabInstance().arcadeDriveMath(PS4.getRawAxis(1), PS4.getRawAxis(4)));
       Hopper.grabInstance().hopperControl(OP.getRawButton(1), OP.getRawButton(3));
       Climber.grabInstance().climb(OP.getRawButton(4), OP.getRawAxis(1));
       Shooter.grabInstance().test(OP.getRawButton(1));
       GearBox.grabInstance().setDeg(PS4.getRawButton(4), PS4.getRawButton(1));
    /*   if(PS4.getRawButton(5)){
    	   Drivebase.grabInstance().testTheStuff();
    	   Drivebase.grabInstance().controlLoops();
       }*/
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
