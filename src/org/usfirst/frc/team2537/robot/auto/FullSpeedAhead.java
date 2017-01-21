package org.usfirst.frc.team2537.robot.auto;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class FullSpeedAhead extends Command {

	@Override
	protected void initialize() {
		Robot.driveSys.setDriveMotors(1);
	}

	@Override
	protected void execute() {
	
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Robot.driveSys.setDriveMotors(0);
	}

	@Override
	protected void interrupted() {
		Robot.driveSys.setDriveMotors(0);
	}

}
