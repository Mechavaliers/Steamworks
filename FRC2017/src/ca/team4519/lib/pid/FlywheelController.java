package ca.team4519.lib.pid;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.SpeedController;

public class FlywheelController {

	public Counter source;
	public SpeedController motorController;
	public double kP, kI, kD, kF, ticksPerRev, deadband, output, target, error, pOut, iOut, dOut, iError, deltaError;
	public double prevError = 0;
	public boolean isEnabled = false;
	
	public FlywheelController(Counter source, SpeedController motorController, double kP, double kI, double kD, double kF, double ticksPerRev,double deadband){	
		this.source = source;
		this.motorController = motorController;
		this.kP = kP;
		this.kI = kI;
		this.kD = kD;
		this.kF = kF;
		this.ticksPerRev = ticksPerRev;
		this.deadband = deadband;
	}
	
	public void calculate(){
		
		
		error = target - source.getRate();
		
		iError = error;
		
		deltaError = prevError - error;

		output = error * kP + iError * kI + deltaError * kD;
		prevError = error;
		
		
	}
	
	public void setRPM(final double target){
		this.target = target;
	}
	
	public void enable(){
		isEnabled = true;
		motorController.set(output);
	}
	
	public void disable(){
		isEnabled = false;
		setRPM(0.0);
		motorController.set(0.0);
	}
	
	public boolean isEnabled(){
		return isEnabled;
	}
	
}
