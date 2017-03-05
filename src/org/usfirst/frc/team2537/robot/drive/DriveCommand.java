package org.usfirst.frc.team2537.robot.drive;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveCommand extends Command {

	public DriveCommand() {
		requires(Robot.driveSys);
	}

	@Override
	protected void initialize() {
	}

	/**
	 * Sets motors to joystick input
	 */
	protected void execute() {
		Robot.driveSys.setDriveMotors(Robot.driveSys.getLeftJoystick(), Robot.driveSys.getRightJoystick());
	}

	/**
	 * Can't stop, won't stop
	 */
	@Override
	protected boolean isFinished() {
		return false;
	}

	/**
	 * Turns motors off
	 */
	@Override
	protected void end() {
		Robot.driveSys.setMotors(0);
	}

	/**
	 * Turns motors off
	 */
	@Override
	protected void interrupted() {
		Robot.driveSys.setMotors(0);
	}
}
