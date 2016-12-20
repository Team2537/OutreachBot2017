package org.usfirst.frc.team2537.robot.drive;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveLimitSwitchCommand extends Command {

	@Override
	protected void initialize() {
		Robot.driveSys.setLeftMotor(.2);
		Robot.driveSys.setRightMotor(.2);

	}

	@Override
	protected void execute() {

	}

	@Override
	protected boolean isFinished() {
		return Robot.driveSys.limitSwitch.get();

	}

	@Override
	protected void end() {
		Robot.driveSys.setLeftMotor(0);
		Robot.driveSys.setRightMotor(0);

	}

	@Override
	protected void interrupted() {
		Robot.driveSys.setLeftMotor(0);
		Robot.driveSys.setRightMotor(0);

	}

}
