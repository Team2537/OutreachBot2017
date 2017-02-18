package org.usfirst.frc.team2537.robot.shooter;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ShooterKillCommand extends Command {

	public ShooterKillCommand() {
		requires(Robot.shooterSys);
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

	protected void end() {
		Robot.shooterSys.disable();
		Robot.shooterSys.setSpeed(0);
	}

}
