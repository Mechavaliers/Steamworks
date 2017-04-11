package ca.team4519.FRC2017;

import ca.team4519.FRC2017.auton.AutoMode;
import ca.team4519.FRC2017.auton.AutonRunner;
import ca.team4519.FRC2017.auton.modes.*;
import ca.team4519.FRC2017.subsystems.Drivebase;
import ca.team4519.FRC2017.subsystems.Climber;
import ca.team4519.FRC2017.subsystems.GearBox;
import ca.team4519.lib.MechaRobotBase;
import ca.team4519.lib.MultiThreader;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends MechaRobotBase{
	
	MultiThreader autonLoop = new MultiThreader("100Hz - Auton", 1.0/100.0);
	MultiThreader teleopLoop = new MultiThreader("100Hz - Teleop" , 1.0/100.0);
	
	AutonRunner autonLoopRunner = new AutonRunner();
	
	SendableChooser<AutoMode> auton = new SendableChooser<AutoMode>();
	
	Joystick Ben = new Joystick(0);
	Joystick Paul = new Joystick(1);

	public void robotInit() {
       
		autonLoop.addThread(Drivebase.grabInstance());
	

		auton.addDefault("Center Gear", new CenterGear());
		auton.addObject("Center Gear Go Left", new CenterGearGoLeft());
		auton.addObject("Center Gear Go Right", new CenterGearGoRight());

		auton.addObject("Cross Baseline", new CrossBaseline());
		
		auton.addObject("Right Gear", new RightGear());
		auton.addObject("left Gear", new LeftGear());
		auton.addObject("Spin Test", new TurnTest());

    	SmartDashboard.putData("Selector 2.0", auton);
    	
    }

    public void autonomousInit() {
    	Drivebase.grabInstance().resetSensors();
    	
    	AutoMode mode = auton.getSelected();
    	//AutoMode mode = new RightGear();
    	autonLoopRunner.selectAuto(mode);
    	
    	autonLoop.start();
    	mode.init();
    	autonLoopRunner.start();
    }

    public void autonomousPeriodic() {
    }

    public void teleopInit(){
    	GearBox.grabInstance().closed();
    	Drivebase.grabInstance().resetSensors();
    	Drivebase.grabInstance().getRobotPose();
    	Drivebase.grabInstance().killController();
    	teleopLoop.start();
    	
    }
    
    public void teleopPeriodic() {

       Drivebase.grabInstance().setDrivePower(Drivebase.grabInstance().arcadeDriveMath(Ben.getRawAxis(1), Ben.getRawAxis(4), Ben.getRawButton(6)));
       Climber.grabInstance().climb(Paul.getRawButton(4), Paul.getRawAxis(1));
       GearBox.grabInstance().setState(Ben.getRawButton(4), Ben.getRawButton(1), Paul.getRawButton(3));   
       GearBox.grabInstance().holderState(Paul.getRawButton(2));

    }

    public void disabledInit() {
    	Drivebase.grabInstance().disableSubsystem();
    	Climber.grabInstance().disableSubsystem();
    	GearBox.grabInstance().disableSubsystem();
    	
    	autonLoop.stop();
    	teleopLoop.stop();
    }
    
    public void disabledPeriodic() {

    }
    
    public void allPeriodic() {
    	Drivebase.grabInstance().update();
    	Climber.grabInstance().update();
    	GearBox.grabInstance().update();
    }
}
