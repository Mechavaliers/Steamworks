package ca.team4519.FRC2017.auton.tasks;

import edu.wpi.first.wpilibj.Timer;

public class CountdownTask implements Task{

	double waitXSeconds;
	double startTime;
	
	public CountdownTask(double timeToWait){
		waitXSeconds = timeToWait;
	}

	@Override
	public boolean isTaskComplete() {
		return Timer.getFPGATimestamp() >= startTime+waitXSeconds;
	}

	@Override
	public void exitTask() {

	}

	@Override
	public void output() {

	}

	@Override
	public void runTask() {
		startTime = Timer.getFPGATimestamp();
	}
}
