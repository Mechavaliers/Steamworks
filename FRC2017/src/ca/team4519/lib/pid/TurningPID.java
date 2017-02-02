package ca.team4519.lib.pid;

public class TurningPID {

	protected double kP, kI, kD, kF, output, target, error, pOut, iOut, dOut, iError, deltaError;
	protected double prevError = 0;
	protected double maxIn = 1.0;
	protected double minIn = 0.0;
	protected double maxOut = 1.0;
	protected double minOut = -1.0;
	
	public TurningPID(double kP, double kI, double kD){
		
	}
	
	public void setTarget(double target){
		this.target = target;
	}
	
	public double calculate(double input){
		error = target - input;		
		iError = error;
	
		deltaError = prevError - error;

		output = error * kP + iError * kI + deltaError * kD;
		prevError = error;
		
		if(output > 1.0){
			output = 1.0;
		}else if(output < -1.0){
			output = -1.0;
		}
		
		return output;
	}
	
}
