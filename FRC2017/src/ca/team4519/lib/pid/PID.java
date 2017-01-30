package ca.team4519.lib.pid;

import edu.wpi.first.wpilibj.Encoder;

public class PID {

	protected Encoder source;
	protected double kP, kI, kD, kF, deadband, ticksPerInch, output, target, error, pOut, iOut, dOut, iError, deltaError;
	protected double prevError = 0;
	protected boolean isEnabled = false;
	
	
	public PID (Encoder source, double kP, double kI, double kD, double kF, double deadband, double ticksPerInch){
		this.source = source;
		this.kP = kP;
		this.kI = kI;
		this.kD = kD;
		this.deadband = deadband;
		this.ticksPerInch = ticksPerInch;
		
	}
	
	public void calculate(){
		
		if(isEnabled){
			error = target - (source.getDistance() / ticksPerInch);		
			iError = error;
		
			deltaError = prevError - error;

			output = error * kP + iError * kI + deltaError * kD;
			prevError = error;
			
			if(output > 1.0){
				output = 1.0;
			}else if(output < -1.0){
				output = -1.0;
			}
			
			
		}else{
			output = 0;
		}
		
	}
	
	public boolean inRange(){
		return Math.abs(error) < deadband;
	}
	
	public void setDistance(final double target){
		this.target = target;
	}
	
	public void enable(){
		isEnabled = true;

	}
	
	public void disable(){
		isEnabled = false;
		setDistance(0.0);
	}
	
	public double controllerOutput(){
		return output;
	}
	
	public boolean isEnabled(){
		return isEnabled;
	}
}
