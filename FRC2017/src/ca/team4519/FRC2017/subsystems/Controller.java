package ca.team4519.FRC2017.subsystems;

import ca.team4519.lib.Subsystem;

import edu.wpi.first.wpilibj.Joystick;

public class Controller extends Subsystem {

	Joystick PS4 = new Joystick(0);
	
	public double getLeftStick_X(){
		return PS4.getRawAxis(0);
	}
	
	public double getLeftStick_Y(){
		return PS4.getRawAxis(1);
	}
	
	public boolean getLeftStick_Click(){
		return PS4.getRawButton(8);
	}
	
	public double getLeftTrigger(){
		return PS4.getRawAxis(2);
	}
	
	public boolean getLeftBumper(){
		return PS4.getRawButton(4);
	}
	
	public double getRightStick_X(){
		return PS4.getRawAxis(4);
	}
	
	public double getRightStick_Y(){
		return PS4.getRawAxis(5);
	}
	
	public boolean getRightStick_Click(){
		return PS4.getRawButton(9);
	}
	
	public double getRightTrigger(){
		return PS4.getRawAxis(3);
	}
	
	public boolean getRightBumper(){
		return PS4.getRawButton(5);
	}
	
	public boolean getTriangle(){
		return PS4.getRawButton(3);
		
	}
	
	public boolean getSquare(){
		return PS4.getRawButton(2);
	}
	
	public boolean getCircle(){
		return PS4.getRawButton(1);
	}
	
	public boolean getCross(){
		return PS4.getRawButton(0);
	}
	
	public boolean getShare(){
		return PS4.getRawButton(6);
	}
	
	public boolean getOptions(){
		return PS4.getRawButton(7);
	}
	
	public void rumbleLeft(double intensity){
		PS4.setRumble(Joystick.RumbleType.kLeftRumble, (float) intensity);
	}
	
	public void rumbleRight(double intensity){
		PS4.setRumble(Joystick.RumbleType.kRightRumble, (float) intensity);
	}
	
	public void rumbleOff(){
		PS4.setRumble(Joystick.RumbleType.kLeftRumble, 0.0f);
		PS4.setRumble(Joystick.RumbleType.kRightRumble, 0.0f);
	}
	
	@Override
  	public void resetSensors() {
	}

	@Override
	public void update() {
	}

}
