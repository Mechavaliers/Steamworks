package ca.team4519.FRC2017.auton.modes;

import edu.wpi.first.wpilibj.command.Command;

import ca.team4519.FRC2017.subsystems.Drivebase;
import ca.team4519.FRC2017.subsystems.Shooter;


public class ShootHigh extends Command {

    public ShootHigh() {
        
    }

    protected void initialize() {
    	Drivebase.grabInstance().resetSensors();
    	Shooter.grabInstance().resetSensors();
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
