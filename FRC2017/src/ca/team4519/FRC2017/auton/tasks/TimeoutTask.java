package ca.team4519.FRC2017.auton.tasks;

import edu.wpi.first.wpilibj.Timer;

public class TimeoutTask extends Task {

	double timeout;
	double startTime;
	
	public TimeoutTask(double timeout) {
		this.timeout = timeout;
	}
	
	@Override
	public boolean isDone() {
		return Timer.getFPGATimestamp() >= (startTime + timeout);
	}

	@Override
	public void update() {	
	}

	@Override
	public void done() {		
	}

	@Override
	public void start() {
		startTime = Timer.getFPGATimestamp();
	}

}
