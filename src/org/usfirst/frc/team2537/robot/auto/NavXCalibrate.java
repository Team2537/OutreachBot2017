package org.usfirst.frc.team2537.robot.auto;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class NavXCalibrate extends Command {
	
	public NavXCalibrate() {
		this.setRunWhenDisabled(true);
	}
	
	public void initialize() {
		Robot.driveSys.getAhrs().reset();
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

}
