package org.usfirst.frc.team2537.robot.drive;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveLimitSwitchCommand extends Command {
	public DriveLimitSwitchCommand() {
		requires(Robot.driveSys);
	}

	@Override
	protected void initialize() {
		// drives forward slowly
		Robot.driveSys.setLeftMotor(.1);
		Robot.driveSys.setRightMotor(.1);

	}

	@Override
	protected void execute() {

	}

	@Override
	protected boolean isFinished() {
		// stops command when limit switch is pressed
		return Robot.driveSys.limitSwitch.get();

	}

	@Override
	protected void end() {
		Robot.driveSys.setLeftMotor(0);
		Robot.driveSys.setRightMotor(0);

	}

	@Override
	protected void interrupted() {
		// stops motors
		Robot.driveSys.setLeftMotor(0);
		Robot.driveSys.setRightMotor(0);

	}

}
