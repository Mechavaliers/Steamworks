package ca.team4519.lib.pid;

import edu.wpi.first.wpilibj.Counter;

public class FlywheelController {

	public Counter source;
	protected double kP, kI, kD, kF, ticksPerRev, deadband, output, target, error, pOut, iOut, dOut, iError, deltaError;
	protected double prevError = 0;
	protected boolean isEnabled = false;
	
	public FlywheelController(Counter source, double kP, double kI, double kD, double kF, double ticksPerRev,double deadband){	
		this.source = source;
		this.kP = kP;
		this.kI = kI;
		this.kD = kD;
		this.kF = kF;
		this.ticksPerRev = ticksPerRev;
		this.deadband = deadband;
	}
	
	public void calculate(){
		
		if(isEnabled){
			error = target - ((source.getRate()/ticksPerRev)/60) ;
		
			iError = error;
		
			deltaError = prevError - error;

			output = error * kP + iError * kI + deltaError * kD;
			prevError = error;
			
		}else{
			output = 0;
		}
		
	}
	
	public boolean inRange(){
		return Math.abs(error) < deadband;
	}
	
	public void setRPM(final double target){
		this.target = target;
	}
	
	public void enable(){
		isEnabled = true;

	}
	
	public void disable(){
		isEnabled = false;
		setRPM(0.0);
	}
	
	public double controllerOutput(){
		return output;
	}
	
	public boolean isEnabled(){
		return isEnabled;
	}
	
}
